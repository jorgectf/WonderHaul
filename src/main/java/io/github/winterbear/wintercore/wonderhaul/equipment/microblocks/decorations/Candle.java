package io.github.winterbear.wintercore.wonderhaul.equipment.microblocks.decorations;

import io.github.winterbear.wintercore.utils.LightUtils;
import io.github.winterbear.wintercore.utils.SoundUtils;
import io.github.winterbear.wintercore.wonderhaul.blockstorage.BlockMetadata;
import io.github.winterbear.wintercore.wonderhaul.equipment.Microblock;
import org.bukkit.Sound;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by WinterBear on 16/04/2021.
 */
public class Candle extends Microblock {

    public Candle(JavaPlugin plugin) {
        super(plugin);
    }

    @Override
    public boolean interact(PlayerInteractEvent event, BlockMetadata metadata) {
        boolean lit = LightUtils.toggleLight(metadata);

        if(lit){
            SoundUtils.playSound(event.getPlayer(), Sound.ITEM_FLINTANDSTEEL_USE);
        } else {
            SoundUtils.playSound(event.getPlayer(), Sound.BLOCK_FIRE_EXTINGUISH);
        }

        return true;
    }

    @Override
    public String getReference() {
        return "Candle";
    }
}
