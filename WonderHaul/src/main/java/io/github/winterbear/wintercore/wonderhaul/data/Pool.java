package io.github.winterbear.wintercore.wonderhaul.data;

import io.github.winterbear.wintercore.wonderhaul.dropper.Chance;
import io.github.winterbear.wintercore.wonderhaul.equipment.generators.Generator;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WinterBear on 17/06/2019.
 */
public class Pool {

    private String name;

    private Chance globalChance;

    private List<String> enabledWorlds = new ArrayList<>();

    private BiomeSet enabledBiomes;

    private Map<EntityType, Chance> mobChances = new HashMap<>();

    private Generator generator;



    public Pool(String name, Chance globalChance, List<String> enabledWorlds, BiomeSet enabledBiomes, Map<EntityType, Chance> mobChances, Generator generator) {
        this.globalChance = globalChance;
        this.enabledWorlds = enabledWorlds;
        this.enabledBiomes = enabledBiomes;
        this.mobChances = mobChances;
        this.generator = generator;
        this.name = name;
    }

    public WHPool getSerializable(){
        WHPool sPool = new WHPool();
        sPool.setGenerator(this.getGenerator().getName());
        sPool.setEnabledBiomes(enabledBiomes.toSerializable());
        return sPool;
    }


    public Chance getGlobalChance() {
        return globalChance;
    }

    public void setGlobalChance(Chance globalChance) {
        this.globalChance = globalChance;
    }

    public List<String> getEnabledWorlds() {
        return enabledWorlds;
    }

    public void setEnabledWorlds(List<String> enabledWorlds) {
        this.enabledWorlds = enabledWorlds;
    }

    public BiomeSet getEnabledBiomes() {
        return enabledBiomes;
    }

    public void setEnabledBiomes(BiomeSet enabledBiomes) {
        this.enabledBiomes = enabledBiomes;
    }

    public Map<EntityType, Chance> getMobChances() {
        return mobChances;
    }

    public void setMobChances(Map<EntityType, Chance> mobChances) {
        this.mobChances = mobChances;
    }

    public Generator getGenerator() {
        return generator;
    }

    public void setGenerator(Generator generator) {
        this.generator = generator;
    }
}
