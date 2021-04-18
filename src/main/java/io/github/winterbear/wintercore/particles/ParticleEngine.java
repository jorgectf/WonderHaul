package io.github.winterbear.wintercore.particles;


import io.github.winterbear.wintercore.utils.RandomUtils;
import io.github.winterbear.wintercore.utils.RepeatingTaskUtils;
import io.github.winterbear.wintercore.wonderhaul.blockstorage.BlockMetadata;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by WinterBear on 28/07/2020.
 */
public class ParticleEngine {

    private static final Map<BlockMetadata, ParticleEffectType> effectQueue = new HashMap<>();

    public static void registerEffect(BlockMetadata metadata, ParticleEffectType type) {
        effectQueue.put(metadata, type);
    }

    public static void cancelEffect(BlockMetadata metadata) {
        if(effectQueue.containsKey(metadata)){
            effectQueue.remove(metadata);
        }
    }

    public static void start(JavaPlugin plugin){
        RepeatingTaskUtils.everyTick(1, () -> processRepeatingEffects(plugin), plugin);
    }

    public static boolean processRepeatingEffects(JavaPlugin plugin){

        for(BlockMetadata metadata : effectQueue.keySet()){
            effectQueue.get(metadata).render(metadata, plugin);
        }
        return true;

    }

    public static void flame(Location location){
        location.getWorld().spawnParticle(Particle.FLAME, location, 1, 0, 0, 0, 0);
    }


    public static void coloredDot(int r, int g, int b, Location location){
        location.getWorld().spawnParticle(Particle.REDSTONE, location.getX(), location.getY(), location.getZ(),
                0, new Particle.DustOptions(Color.fromRGB(r,g,b), 1.0f));
    }

    public static void particleDot(Particle particle, Location location, int count){
        location.getWorld().spawnParticle(particle, location, count, 0d, 0d,0d, 0d);
    }

    public static void coloredDotCloud(int r, int g, int b, Location location, int count, double radius){

        for(int i = 0; i < count; i++){
            double offsetX = RandomUtils.getDoubleBetween(-radius, radius);
            double offsetY = RandomUtils.getDoubleBetween(-radius, radius);
            double offsetZ = RandomUtils.getDoubleBetween(-radius, radius);
            Location offsetLocation = new Location(location.getWorld(),
                    location.getX() + offsetX,
                    location.getY() + offsetY,
                    location.getZ() + offsetZ);
            coloredDot(r, g, b, offsetLocation);
        }


    }

    public static void particleDotCloud(Particle particle, Location location, int count, double radius){

        for(int i = 0; i < count; i++){
            double offsetX = RandomUtils.getDoubleBetween(-radius, radius);
            double offsetY = RandomUtils.getDoubleBetween(-radius, radius);
            double offsetZ = RandomUtils.getDoubleBetween(-radius, radius);
            Location offsetLocation = new Location(location.getWorld(),
                    location.getX() + offsetX,
                    location.getY() + offsetY,
                    location.getZ() + offsetZ);
            particleDot(particle, offsetLocation, 1);
        }


    }



}
