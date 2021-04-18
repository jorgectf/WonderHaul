package io.github.winterbear.wintercore.particles;

import io.github.winterbear.wintercore.utils.BlockUtils;
import io.github.winterbear.wintercore.wonderhaul.blockstorage.BlockMetadata;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by WinterBear on 16/04/2021.
 */
public class CandleParticleEffect {

    public static void render(BlockMetadata metadata, JavaPlugin plugin){
        if(metadata.getProperty("light").isPresent() && metadata.getProperty("light").get().equalsIgnoreCase("true")){
            Location loc = BlockUtils.getBlockCentre(metadata.getInternalLocation());
            loc.setY(loc.getY() + 0.2d);
            ParticleEngine.flame(loc);
        }
    }
}
