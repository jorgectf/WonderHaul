package io.github.winterbear.wintercore.wonderhaul.data;

import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.entity.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WinterBear on 17/06/2019.
 */
public class WHPool implements Serializable {

    private float globalChance;

    private List<String> enabledWorlds = new ArrayList<>();

    private List<String> enabledBiomes = new ArrayList<>();

    private Map<String, Float> mobChances = new HashMap<>();

    private String generator;

    private boolean worldEnabled(World world){
        return getEnabledWorlds().contains(world.getName());
    }

    private boolean biomeEnabled(Biome biome){
        return getEnabledBiomes().contains(biome.name());
    }

    private boolean victimMobEnabled(Entity victim){
        return getMobChances().containsKey(victim.getType().name());
    }


    public float getGlobalChance() {
        return globalChance;
    }

    public void setGlobalChance(float globalChance) {
        this.globalChance = globalChance;
    }

    public List<String> getEnabledWorlds() {
        return enabledWorlds;
    }

    public void setEnabledWorlds(List<String> enabledWorlds) {
        this.enabledWorlds = enabledWorlds;
    }

    public List<String> getEnabledBiomes() {
        return enabledBiomes;
    }

    public void setEnabledBiomes(List<String> enabledBiomes) {
        this.enabledBiomes = enabledBiomes;
    }

    public Map<String, Float> getMobChances() {
        return mobChances;
    }

    public void setMobChances(Map<String, Float> mobChances) {
        this.mobChances = mobChances;
    }

    public String getGenerator() {
        return generator;
    }

    public void setGenerator(String generator) {
        this.generator = generator;
    }
}
