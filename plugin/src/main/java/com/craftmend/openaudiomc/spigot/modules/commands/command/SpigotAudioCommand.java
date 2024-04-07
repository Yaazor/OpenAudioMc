package com.craftmend.openaudiomc.spigot.modules.commands.command;

import com.craftmend.openaudiomc.OpenAudioMc;
import com.craftmend.openaudiomc.api.EventApi;
import com.craftmend.openaudiomc.api.clients.Client;
import com.craftmend.openaudiomc.generic.client.objects.ClientConnection;
import com.craftmend.openaudiomc.generic.commands.CommandService;
import com.craftmend.openaudiomc.generic.commands.enums.CommandContext;
import com.craftmend.openaudiomc.generic.commands.helpers.CommandMiddewareExecutor;
import com.craftmend.openaudiomc.generic.commands.helpers.PromptProxyError;
import com.craftmend.openaudiomc.generic.commands.interfaces.CommandMiddleware;
import com.craftmend.openaudiomc.generic.commands.middleware.CatchCrashMiddleware;
import com.craftmend.openaudiomc.generic.commands.middleware.CatchLegalBindingMiddleware;
import com.craftmend.openaudiomc.generic.commands.middleware.CleanStateCheckMiddleware;
import com.craftmend.openaudiomc.generic.environment.MagicValue;
import com.craftmend.openaudiomc.generic.networking.interfaces.NetworkingService;
import com.craftmend.openaudiomc.generic.node.packets.ClientRunAudioPacket;
import com.craftmend.openaudiomc.generic.platform.Platform;
import com.craftmend.openaudiomc.generic.proxy.interfaces.UserHooks;
import com.craftmend.openaudiomc.generic.state.StateService;
import com.craftmend.openaudiomc.generic.state.interfaces.State;
import com.craftmend.openaudiomc.generic.state.states.WorkerState;
import com.craftmend.openaudiomc.generic.user.User;
import com.craftmend.openaudiomc.spigot.modules.users.adapters.SpigotUserAdapter;
import com.craftmend.openaudiomc.spigot.modules.events.SpigotAudioCommandEvent;
import com.craftmend.openaudiomc.spigot.modules.players.objects.SpigotPlayerSelector;

import lombok.NoArgsConstructor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;


public class SpigotAudioCommand implements CommandExecutor, TabCompleter {

    private final CommandMiddleware[] commandMiddleware = new CommandMiddleware[]{
            new CatchLegalBindingMiddleware(),
            new CatchCrashMiddleware(),
            new CleanStateCheckMiddleware()
    };

    private final CommandService commandService = OpenAudioMc.getService(CommandService.class);

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        SpigotAudioCommandEvent event = (SpigotAudioCommandEvent) EventApi.getInstance().callEvent(new SpigotAudioCommandEvent(commandSender, args));
        if (event.isCancelled()) {
            return true;
        }

        User<CommandSender> sua = new SpigotUserAdapter(commandSender);

        if (CommandMiddewareExecutor.shouldBeCanceled(sua, null, commandMiddleware))
            return true;

        State state = OpenAudioMc.getService(StateService.class).getCurrentState();

        if (state instanceof WorkerState) {
            // velocity work around
            if (commandSender instanceof Player && MagicValue.PARENT_PLATFORM.get(Platform.class) == Platform.VELOCITY) {
                UserHooks hooks = OpenAudioMc.resolveDependency(UserHooks.class);
                Player sender = (Player) commandSender;
                User user = hooks.byUuid(sender.getUniqueId());

                String enteredToken = null;
                if (args.length == 1) {
                    enteredToken = args[0];
                }

                OpenAudioMc.resolveDependency(UserHooks.class).sendPacket(user, new ClientRunAudioPacket(user.getUniqueId(), enteredToken));
            } else {
                // its on a sub-server without an activated proxy, so completely ignore it
                PromptProxyError.sendTo(sua);
            }
            return true;
        }

        if (commandSender instanceof Player) {
            // do we have an argument called "token",  "bedrock" or "key"?
            if (args.length == 1) {

                /*OpenAudioMc.getService(NetworkingService.class).getClient(sua.getUniqueId()).getAuth().activateToken(
                        sua,
                        args[0]
                );
                return true;*/
                if(args[0].equalsIgnoreCase("enable") || args[0].equalsIgnoreCase("disable")) {
                    boolean authorize = true;
                    Component st = Component.text("");
                    if(args[0].equalsIgnoreCase("enable")) {
                        st = Component.text("activé").color(TextColor.color(0, 255, 0));

                        if(((Player) commandSender).getScoreboardTags().contains("oa_no_notif")) {
                            ((Player) commandSender).removeScoreboardTag("oa_no_notif");
                        }else{
                            authorize = false;
                        }
                    }else{
                        st = Component.text("désactivé").color(TextColor.color(255, 0, 0));
                        // Si le joueur essaie de désactiver
                        if(!((Player) commandSender).getScoreboardTags().contains("oa_no_notif")) {
                            ((Player) commandSender).addScoreboardTag("oa_no_notif");
                        }else{
                            authorize = false;
                        }
                    }

                    Component comp = Component.text("").color(TextColor.fromHexString("#AAAAAA")).append(Component.text("» ").color(TextColor.fromHexString("#555555")));
                    if(authorize) {

                        comp = comp.append(Component.text("L'envoi du lien à la connexion est à présent ").append(st));
                    }else{
                        comp = comp.append(Component.text("L'envoi du lien à la connexion est déjà ").append(st));
                    }

                    commandSender.sendMessage(comp.append(Component.text(".")));
                    ((Player) commandSender).playSound(((Player) commandSender).getLocation(), Sound.BLOCK_NOTE_BLOCK_COW_BELL, 1f, 1f);
                }
            }else{
                Player sender = (Player) commandSender;
                OpenAudioMc.getService(NetworkingService.class).getClient(sender.getUniqueId()).getAuth().publishSessionUrl();
            }


        } else {
            if (args.length == 0) {
                commandSender.sendMessage(MagicValue.COMMAND_PREFIX.get(String.class) + ChatColor.RED + "You must provide a player name OR selector to send trigger the URL");
                return true;
            }

            SpigotPlayerSelector selector = new SpigotPlayerSelector();
            selector.setSender(sua);
            selector.setString(args[0]);

            for (User<CommandSender> result : selector.getResults()) {
                Optional<Client> client = result.findClient();
                client.ifPresent(value -> ((ClientConnection) value).getAuth().publishSessionUrl());
            }
        }
        return true;
    }


    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        User<?> sender = OpenAudioMc.resolveDependency(UserHooks.class).fromCommandSender(commandSender);
        return commandService.getTabCompletions(CommandContext.AUDIO, args, sender);
    }
}
