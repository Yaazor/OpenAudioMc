package com.craftmend.openaudiomc.generic.client.objects;

import com.craftmend.openaudiomc.OpenAudioMc;

import com.craftmend.openaudiomc.api.impl.event.events.ClientConnectEvent;
import com.craftmend.openaudiomc.api.impl.event.events.ClientDisconnectEvent;
import com.craftmend.openaudiomc.api.impl.event.events.ClientErrorEvent;
import com.craftmend.openaudiomc.api.interfaces.AudioApi;
import com.craftmend.openaudiomc.api.interfaces.Client;

import com.craftmend.openaudiomc.generic.client.ClientDataService;
import com.craftmend.openaudiomc.generic.client.store.ClientDataStore;
import com.craftmend.openaudiomc.generic.enviroment.GlobalConstantService;
import com.craftmend.openaudiomc.generic.enviroment.MagicValue;
import com.craftmend.openaudiomc.generic.networking.abstracts.AbstractPacket;
import com.craftmend.openaudiomc.generic.client.session.ClientAuth;
import com.craftmend.openaudiomc.generic.client.session.RtcSessionManager;
import com.craftmend.openaudiomc.generic.client.helpers.SerializableClient;
import com.craftmend.openaudiomc.generic.client.session.SessionData;
import com.craftmend.openaudiomc.generic.client.helpers.TokenFactory;
import com.craftmend.openaudiomc.generic.networking.enums.MediaError;
import com.craftmend.openaudiomc.generic.networking.interfaces.Authenticatable;
import com.craftmend.openaudiomc.generic.networking.interfaces.NetworkingService;
import com.craftmend.openaudiomc.generic.networking.packets.client.hue.PacketClientApplyHueColor;
import com.craftmend.openaudiomc.generic.networking.packets.client.media.PacketClientCreateMedia;
import com.craftmend.openaudiomc.generic.networking.packets.client.ui.PacketClientProtocolRevisionPacket;
import com.craftmend.openaudiomc.generic.networking.packets.client.ui.PacketClientSetVolume;
import com.craftmend.openaudiomc.generic.networking.rest.Task;
import com.craftmend.openaudiomc.generic.node.packets.ClientConnectedPacket;
import com.craftmend.openaudiomc.generic.node.packets.ClientDisconnectedPacket;
import com.craftmend.openaudiomc.generic.platform.interfaces.TaskService;
import com.craftmend.openaudiomc.generic.proxy.interfaces.UserHooks;
import com.craftmend.openaudiomc.generic.user.User;
import com.craftmend.openaudiomc.generic.storage.enums.StorageKey;
import com.craftmend.openaudiomc.generic.storage.interfaces.Configuration;
import com.craftmend.openaudiomc.generic.media.objects.Media;
import com.craftmend.openaudiomc.generic.networking.packets.*;
import com.craftmend.openaudiomc.generic.platform.Platform;
import com.craftmend.openaudiomc.generic.hue.HueState;
import com.craftmend.openaudiomc.generic.hue.SerializedHueColor;

import com.craftmend.openaudiomc.spigot.OpenAudioMcSpigot;
import com.craftmend.openaudiomc.spigot.modules.proxy.enums.OAClientMode;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;
import java.util.*;

public class ClientConnection implements Authenticatable, Client, Serializable {

    @Getter private transient final User user;

    @Getter private final SessionData session;
    @Setter @Getter private ClientAuth auth;
    @Getter private final RtcSessionManager rtcSessionManager;
    private transient final List<Runnable> connectHandlers = new ArrayList<>();
    private transient final List<Runnable> disconnectHandlers = new ArrayList<>();
    @Getter private ClientDataStore dataCache;

    public ClientConnection(User playerContainer, SerializableClient fromSerialized) {
        this.user = playerContainer;
        this.auth = new TokenFactory().build(this);
        rtcSessionManager = new RtcSessionManager(this);
        session = new SessionData(this);

        if (fromSerialized != null) {
            this.session.applySerializedSession(fromSerialized);
        }

        if (OpenAudioMc.getInstance().getConfiguration().getBoolean(StorageKey.SETTINGS_SEND_URL_ON_JOIN))
            this.getAuth().publishSessionUrl();

        if (!OpenAudioMc.getInstance().getInvoker().isNodeServer()) {
            OpenAudioMc.getService(GlobalConstantService.class).sendNotifications(user);
        }

        getDataStore().setWhenFinished(dataStore -> {
            this.dataCache = dataStore;
            this.dataCache.setLastSeen(Instant.now());
        });
    }

    // client connected!
    @Override
    public void onConnect() {
        session.setSessionUpdated(true);
        if (isConnected()) return;
        Configuration Configuration = OpenAudioMc.getInstance().getConfiguration();

        session.setConnected(true);
        session.setWaitingToken(false);

        OpenAudioMc.resolveDependency(TaskService.class).schduleSyncDelayedTask(() -> {
                    OpenAudioMc.getService(NetworkingService.class).send(this, new PacketClientProtocolRevisionPacket());

                    session.getOngoingMedia().forEach(this::sendMedia);

                    connectHandlers.forEach(a -> a.run());
                },
                3
        );

        // am I a proxy thingy? then send it to my other thingy
        OpenAudioMc.resolveDependency(UserHooks.class).sendPacket(user, new ClientConnectedPacket(user.getUniqueId()));

        AudioApi.getInstance().getEventDriver().fire(new ClientConnectEvent(this));

        if (OpenAudioMc.getInstance().getPlatform() == Platform.SPIGOT && OpenAudioMcSpigot.getInstance().getProxyModule().getMode() == OAClientMode.NODE)
            return;
        if (OpenAudioMc.getInstance().getPlatform() == Platform.STANDALONE)
            return;
        String connectedMessage = Configuration.getString(StorageKey.MESSAGE_CLIENT_OPENED);
        user.sendMessage(Platform.translateColors(connectedMessage));
    }

    @Override
    public void onDisconnect() {
        if (!isConnected()) return;
        session.setSessionUpdated(true);
        session.setApiSpeakers(0);
        session.setConnectedToRtc(false);
        session.setHasHueLinked(false);
        session.setConnected(false);
        disconnectHandlers.forEach(event -> event.run());

        // am I a proxy thingy? then send it to my other thingy
        OpenAudioMc.resolveDependency(UserHooks.class).sendPacket(user, new ClientDisconnectedPacket(user.getUniqueId()));

        AudioApi.getInstance().getEventDriver().fire(new ClientDisconnectEvent(this));

        // Don't send if i'm spigot and a node
        if (OpenAudioMc.getInstance().getPlatform() == Platform.SPIGOT && OpenAudioMcSpigot.getInstance().getProxyModule().getMode() == OAClientMode.NODE)
            return;
        if (OpenAudioMc.getInstance().getPlatform() == Platform.STANDALONE)
            return;
        String message = OpenAudioMc.getInstance().getConfiguration().getString(StorageKey.MESSAGE_CLIENT_CLOSED);
        user.sendMessage(Platform.translateColors(message));
    }

    public Task<ClientDataStore> getDataStore() {
        return OpenAudioMc.getService(ClientDataService.class).getClientData(user.getUniqueId(), true, true);
    }

    public ClientConnection addOnConnectHandler(Runnable runnable) {
        this.connectHandlers.add(runnable);
        return this;
    }

    public ClientConnection addOnDisconnectHandler(Runnable runnable) {
        this.disconnectHandlers.add(runnable);
        return this;
    }

    /**
     * change the volume for the client
     *
     * @param volume the new volume
     */
    public void setVolume(int volume) {
        if (volume < 0 || volume > 100) {
            throw new IllegalArgumentException("Volume must be between 0 and 100");
        }
        session.setVolume(volume);
        user.sendMessage(Platform.translateColors(StorageKey.MESSAGE_CLIENT_VOLUME_CHANGED.getString()).replaceAll("__amount__", volume + ""));
        OpenAudioMc.getService(NetworkingService.class).send(this, new PacketClientSetVolume(volume));
    }

    /**
     * change the players hue lights
     *
     * @param hueState the new light state
     */
    public void setHue(HueState hueState) {
        hueState.getColorMap().forEach((light, color) -> {
            SerializedHueColor serializedHueColor = new SerializedHueColor(color.getRed(), color.getGreen(), color.getGreen(), color.getBrightness());
            OpenAudioMc.getService(NetworkingService.class).send(this, new PacketClientApplyHueColor(serializedHueColor, "[" + light + "]"));
        });
    }

    /**
     * Close the clients web client
     */
    public void kick() {
        OpenAudioMc.resolveDependency(TaskService.class).runAsync(() -> {
            OpenAudioMc.getService(NetworkingService.class).send(this, new PacketSocketKickClient());
        });
    }

    /**
     * send media to the client to play
     *
     * @param media media to be send
     */
    public void sendMedia(Media media) {
        if (media.getKeepTimeout() != -1 && !session.getOngoingMedia().contains(media)) {
            session.getOngoingMedia().add(media);

            // stop after x seconds
            OpenAudioMc.resolveDependency(TaskService.class).schduleSyncDelayedTask(() -> session.getOngoingMedia().remove(media), (20 * media.getKeepTimeout()));
        }
        if (isConnected()) {
            sendPacket(new PacketClientCreateMedia(media));
        } else {
            session.tickClient();
        }
    }

    public void sendPacket(AbstractPacket packet) {
        OpenAudioMc.getService(NetworkingService.class).send(this, packet);
    }

    public void onDestroy() {
        this.getRtcSessionManager().makePeersDrop();
        OpenAudioMc.getService(ClientDataService.class).save(dataCache, user.getUniqueId());
        OpenAudioMc.getService(ClientDataService.class).dropFromCache(user.getUniqueId());
    }

    @Override
    public User getOwner() {
        return this.getUser();
    }

    @Override
    public ClientAuth getAuth() {
        return auth;
    }

    @Override
    public void handleError(MediaError error, String source) {
        AudioApi.getInstance().getEventDriver().fire(new ClientErrorEvent(this, error, source));
        if (this.getUser().isAdministrator() && OpenAudioMc.getInstance().getConfiguration().getBoolean(StorageKey.SETTINGS_STAFF_TIPS)) {
            String prefix = MagicValue.COMMAND_PREFIX.get(String.class);
            this.getUser().sendMessage(prefix + "Something went wrong while playing a sound for you, here's what we know:");
            this.getUser().sendMessage(prefix + "what happened: " + error.getExplanation());
            this.getUser().sendMessage(prefix + "where: " + source);
            this.getUser().sendMessage(prefix + Platform.makeColor("YELLOW") + "Players do NOT receive this warning, only staff does. You can disable it in the config.");
        }
    }

    @Override
    public boolean isConnected() {
        return this.session.isConnected();
    }

    @Override
    public void onConnect(Runnable runnable) {
        addOnConnectHandler(runnable);
    }

    @Override
    public void onDisconnect(Runnable runnable) {
        addOnDisconnectHandler(runnable);
    }

    @Override
    public int getVolume() {
        return session.getVolume();
    }

    @Override
    public void setHueState(HueState state) {
        if (this.session.isConnected() && this.session.isHasHueLinked()) this.setHue(state);
    }

    @Override
    public boolean hasPhilipsHue() {
        return this.isConnected() && this.session.isHasHueLinked();
    }

    @Override
    public boolean isMicrophoneActive() {
        return this.isConnected() && this.getRtcSessionManager().isReady() && this.getRtcSessionManager().isMicrophoneEnabled();
    }

    @Override
    public void forcefullyDisableMicrophone(boolean disabled) {
        this.getRtcSessionManager().allowSpeaking(!disabled);
    }
}
