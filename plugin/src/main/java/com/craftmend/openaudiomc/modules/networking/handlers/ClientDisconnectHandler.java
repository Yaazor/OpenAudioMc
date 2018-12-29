package com.craftmend.openaudiomc.modules.networking.handlers;

import com.craftmend.openaudiomc.OpenAudioMc;
import com.craftmend.openaudiomc.modules.networking.abstracts.PayloadHandler;
import com.craftmend.openaudiomc.modules.networking.payloads.ClientDisconnectPayload;
import com.craftmend.openaudiomc.modules.players.objects.Client;
import org.bukkit.Bukkit;

public class ClientDisconnectHandler extends PayloadHandler<ClientDisconnectPayload> {

    @Override
    public void onReceive(ClientDisconnectPayload payload) {
        Client client = OpenAudioMc.getInstance().getPlayerModule().getClient(payload.getClient());
        if (client != null) client.onDisconnect();
    }
}
