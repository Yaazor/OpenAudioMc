package com.craftmend.openaudiomc.spigot.modules.speakers.listeners;

import com.craftmend.openaudiomc.OpenAudioMc;
import com.craftmend.openaudiomc.generic.database.DatabaseService;
import com.craftmend.openaudiomc.generic.environment.MagicValue;
import com.craftmend.openaudiomc.spigot.modules.speakers.SpeakerService;
import com.craftmend.openaudiomc.spigot.modules.speakers.objects.MappedLocation;
import com.craftmend.openaudiomc.spigot.modules.speakers.objects.Speaker;
import com.craftmend.openaudiomc.spigot.modules.speakers.utils.SpeakerUtils;
import lombok.AllArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

@AllArgsConstructor
public class SpeakerDestroyListener implements Listener {

    private OpenAudioMc openAudioMc;
    private SpeakerService speakerService;

    @EventHandler
    public void onExplode(EntityExplodeEvent event) {
        for (Block broken : event.blockList()) {
            if (SpeakerUtils.isSpeakerSkull(broken)) {
                MappedLocation location = new MappedLocation(broken.getLocation());
                Speaker speaker = speakerService.getSpeaker(location);
                if (speaker != null) {
                    /*broken.getWorld().dropItem(
                            broken.getLocation(),
                            SpeakerUtils.getSkull(speaker.getSource(), speaker.getRadius())
                    );*/
                }
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block broken = event.getBlock();
        if (SpeakerUtils.isSpeakerSkull(broken)) {
            if (!isAllowed(event.getPlayer())) {
                event.setCancelled(true);
                return;
            }

            MappedLocation location = new MappedLocation(broken.getLocation());
            Speaker speaker = speakerService.getSpeaker(location);
            if (speaker == null) return;

            speakerService.unlistSpeaker(location);

            //save to config
            OpenAudioMc.getService(DatabaseService.class).getRepository(Speaker.class).delete(speaker);

            event.getPlayer().sendMessage(MagicValue.COMMAND_PREFIX.get(String.class) + ChatColor.RED + "Speaker destroyed");

            event.getPlayer().getInventory().addItem(SpeakerUtils.getSkull(speaker.getSource(), speaker.getRadius()));

            try {
                event.setDropItems(false);
            } catch (Exception ignored) {}
        }
    }

    private boolean isAllowed(Player player) {
        return player.isOp()
                || player.hasPermission("openaudiomc.speakers.*")
                || player.hasPermission("openaudiomc.*")
                || player.hasPermission("openaudiomc.speakers.destroy");
    }

}
