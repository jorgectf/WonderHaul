package io.github.winterbear.wintercore.wonderhaul.data;

import io.github.winterbear.wintercore.wonderhaul.dropper.Chance;
import io.github.winterbear.wintercore.wonderhaul.equipment.generators.Generator;

import java.util.ArrayList;
import java.util.List;

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



    public PoolBuilder setGenerator(Generator generator) {
        this.generator = generator;
        return this;
    }

    public MobPool createMobPool() {
        return new MobPool(name, globalChance, enabledWorlds, enabledBiomes, generator);
    }

    public BlockPool createBlockPool(){
        return new BlockPool(name, globalChance, enabledWorlds, enabledBiomes, generator);
    }
}