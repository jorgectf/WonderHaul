package io.github.winterbear.wintercore.wonderhaul.equipment.microblocks.decorations;

import io.github.winterbear.WinterCoreUtils.ChatUtils;
import io.github.winterbear.wintercore.utils.LoreUtils;
import io.github.winterbear.wintercore.utils.SoundUtils;
import io.github.winterbear.wintercore.wonderhaul.blockstorage.BlockMetadata;
import io.github.winterbear.wintercore.wonderhaul.equipment.Microblock;
import org.bukkit.Sound;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Optional;

/**
 * Created by WinterBear on 26/09/2020.
 */
public class StuffedToy extends Microblock {

    public StuffedToy(JavaPlugin plugin) {
        super(plugin);
    }

    @Override
    public boolean interact(PlayerInteractEvent event, BlockMetadata metadata) {
        LoreUtils.getLoreText(metadata.getInternalItem()).forEach(line -> ChatUtils.send(event.getPlayer(), line));
        Optional<Sound> sound = StuffedToyGenerator.Toys.getSound(metadata);
        sound.ifPresent(s -> SoundUtils.playSound(event.getPlayer(), s));
        return true;
    }

    @Override
    public String getReference() {
        return "Stuffed Toy";
    }
}
