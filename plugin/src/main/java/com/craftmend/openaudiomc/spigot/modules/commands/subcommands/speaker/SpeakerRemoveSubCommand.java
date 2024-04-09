package com.craftmend.openaudiomc.spigot.modules.commands.subcommands.speaker;

import com.craftmend.openaudiomc.OpenAudioMc;
import com.craftmend.openaudiomc.generic.commands.interfaces.SubCommand;
import com.craftmend.openaudiomc.generic.database.DatabaseService;
import com.craftmend.openaudiomc.generic.storage.interfaces.Configuration;
import com.craftmend.openaudiomc.generic.user.User;
import com.craftmend.openaudiomc.spigot.modules.commands.subcommands.SpeakersSubCommand;
import com.craftmend.openaudiomc.spigot.modules.speakers.SpeakerService;
import com.craftmend.openaudiomc.spigot.modules.speakers.objects.MappedLocation;
import com.craftmend.openaudiomc.spigot.modules.speakers.objects.Speaker;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class SpeakerRemoveSubCommand extends SubCommand {

    private final SpeakersSubCommand speakersSubCommand;

    public SpeakerRemoveSubCommand(SpeakersSubCommand parent) {
        super("remove");
        this.speakersSubCommand = parent;
    }

    @Override
    public void onExecute(User sender, String[] args) {
        MappedLocation mappedLocation = speakersSubCommand.locationFromArguments(args);
        if (mappedLocation == null) {
            // failed to parse location
            // On verif si le joueur a essayé de mettre un rayon
            if(args.length == 2) {
                try {
                    int radius = Integer.parseInt(args[1]);
                    Player player = Bukkit.getPlayer(sender.getUniqueId());
                    Block playerBlock = player.getLocation().getBlock();

                    if(radius <= 20) {
                        for(int x = -radius; x <= radius; x++) {
                            for(int y = -radius; y <= radius; y++) {
                                for(int z = -radius; z <= radius; z++) {
                                    Block target = playerBlock.getLocation().clone().add(x, y, z).getBlock();
                                    // On verif pas si le truc est une tête de joueur, pour gérer les cas où le block aurait depop
                                    MappedLocation l = new MappedLocation(target.getLocation());
                                    this.deleteSpeakerAtCoords(l, sender);

                                }
                            }
                        }
                        return;
                    }else{
                        message(sender, "Le rayon spécifié est trop élevé. (Max: 20)");
                        return;
                    }
                }catch (Exception e) {
                    sender.sendMessage("Rayon invalide.");
                    message(sender, "Rayon invalide.");
                    return;
                }
            }

            message(sender, "Invalid location (xyz) or world");
            return;
        }

        this.deleteSpeakerAtCoords(mappedLocation, sender);

    }

    private void deleteSpeakerAtCoords(MappedLocation mappedLocation, User sender){
        // remove from cache
        Configuration config = OpenAudioMc.getInstance().getConfiguration();
        SpeakerService speakerService = OpenAudioMc.getService(SpeakerService.class);
        if(speakerService.hasSpeaker(mappedLocation)) {

            Speaker speaker = speakerService.getSpeaker(mappedLocation);
            speakerService.unlistSpeaker(mappedLocation);

            // save
            OpenAudioMc.getService(DatabaseService.class)
                    .getRepository(Speaker.class)
                    .delete(speaker);

            message(sender, "Removed speaker");
            Block targetBlock = mappedLocation.toBukkit().getBlock();
            if(targetBlock.getType() == Material.PLAYER_HEAD) {
                targetBlock.setType(Material.AIR);
            }
        }

    }
}


