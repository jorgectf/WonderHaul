package io.github.winterbear.wintercore.wonderhaul.data;

import io.github.winterbear.wintercore.wonderhaul.dropper.Chance;
import io.github.winterbear.wintercore.wonderhaul.equipment.generators.Generator;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PoolBuilder {

    public static PoolBuilder create(String name){
        return new PoolBuilder(name);
    }

    private PoolBuilder(String name){
        this.name = name;
    }

    private String name;
    private Chance globalChance = new Chance(100);
    private List<String> enabledWorlds = new ArrayList<>();
    private BiomeSet enabledBiomes = BiomeSets.ALL_BIOMES;
    private Map<EntityType, Chance> mobChances = new HashMap<>();
    private Generator generator;

    public PoolBuilder setGlobalChance(Chance globalChance) {
        this.globalChance = globalChance;
        return this;
    }

    public PoolBuilder setEnabledWorlds(List<String> enabledWorlds) {
        this.enabledWorlds = enabledWorlds;
        return this;
    }

    public PoolBuilder setEnabledBiomes(BiomeSet enabledBiomes) {
        this.enabledBiomes = enabledBiomes;
        return this;
    }

    public PoolBuilder setMobChances(Map<EntityType, Chance> mobChances) {
        this.mobChances = mobChances;
        return this;
    }

    public PoolBuilder mobChance(EntityType entity, double chance){
        this.mobChances.put(entity, new Chance(chance));
        return this;
    }

    public PoolBuilder setGenerator(Generator generator) {
        this.generator = generator;
        return this;
    }

    public Pool createPool() {
        return new Pool(name, globalChance, enabledWorlds, enabledBiomes, mobChances, generator);
    }
}