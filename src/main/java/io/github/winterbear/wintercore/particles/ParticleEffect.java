package io.github.winterbear.wintercore.particles;

import io.github.winterbear.wintercore.wonderhaul.blockstorage.BlockMetadata;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by WinterBear on 18/04/2021.
 */
public interface ParticleEffect {

    void render(BlockMetadata metadata, JavaPlugin plugin);


}
