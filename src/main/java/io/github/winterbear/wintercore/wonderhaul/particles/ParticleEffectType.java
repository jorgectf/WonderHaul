package io.github.winterbear.wintercore.wonderhaul.particles;

import io.github.winterbear.wintercore.wonderhaul.blockstorage.BlockMetadata;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by WinterBear on 15/08/2020.
 */
public enum ParticleEffectType {

    ESSENCE_COLLECTOR(new EssenceCollectorParticleEffect()),
    CANDLE(new CandleParticleEffect());

    private ParticleEffect effect;

    ParticleEffectType(ParticleEffect effect){
        this.effect = effect;
    }

    public void render(BlockMetadata metadata, JavaPlugin plugin){
        effect.render(metadata, plugin);
    }
}
