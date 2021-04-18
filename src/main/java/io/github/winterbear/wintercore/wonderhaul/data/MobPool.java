package io.github.winterbear.wintercore.wonderhaul.data;

import io.github.winterbear.WinterCoreUtils.ChatUtils;
import io.github.winterbear.wintercore.wonderhaul.dropper.Chance;
import io.github.winterbear.wintercore.wonderhaul.equipment.generators.Generator;
import org.bukkit.entity.EntityType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WinterBear on 17/06/2019.
 */
public class MobPool extends BasicPool<EntityType> {

    private Map<EntityType, Chance> mobChances = new HashMap<>();

    public MobPool(String name, Chance globalChance, List<String> enabledWorlds, BiomeSet enabledBiomes, Generator generator) {
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

    public MobPool mobChance(EntityType entity, double chance){
        this.mobChances.put(entity, new Chance(chance));
        return this;
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

    @Override
    public boolean roll(EntityType entityType, long luckModifier){
        if(mobChances.containsKey(entityType)){
            ChatUtils.info(this.name + " rolling chance " + mobChances.get(entityType));
            return mobChances.get(entityType).rollModified(luckModifier);
        }
        return false;
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
