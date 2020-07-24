package io.github.winterbear.wintercore.wonderhaul.data;

import io.github.winterbear.wintercore.wonderhaul.dropper.Chance;
import io.github.winterbear.wintercore.wonderhaul.equipment.generators.Generators;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WinterBear on 17/06/2019.
 */
public class Pools {

    public static List<Pool> getPools(){
        return getDefaultPools();
    }



    private static List<Pool> getDefaultPools(){

        List<Pool> defaultPools = new ArrayList<>();

        defaultPools.add(PoolBuilder.create("Junk")
                .setGenerator(Generators.JUNK)
                .setEnabledBiomes(BiomeSets.ALL_BIOMES)
                .setGlobalChance(new Chance(100.0))
                .mobChance(EntityType.ZOMBIE, 1.0)
                .mobChance(EntityType.SPIDER, 1.0)
                .mobChance(EntityType.SKELETON, 1.0)
                .mobChance(EntityType.SLIME, 1.0)
                .mobChance(EntityType.BLAZE, 1.0)
                .mobChance(EntityType.SILVERFISH, 1.0)
                .mobChance(EntityType.CREEPER, 1.4)
                .mobChance(EntityType.MAGMA_CUBE, 1.4)
                .mobChance(EntityType.GUARDIAN, 1.4)
                .mobChance(EntityType.WITCH, 1.4)
                .mobChance(EntityType.VEX, 1.4)
                .mobChance(EntityType.ENDERMAN, 1.4)
                .mobChance(EntityType.ZOMBIFIED_PIGLIN, 1.4)
                .createPool());


        defaultPools.add(PoolBuilder.create("Ordinary")
                .setGenerator(Generators.ORDINARY)
                .setEnabledBiomes(BiomeSets.ALL_BIOMES)
                .setGlobalChance(new Chance(100.0))
                .mobChance(EntityType.ZOMBIE, 0.08)
                .mobChance(EntityType.SPIDER, 0.16)
                .mobChance(EntityType.SKELETON, 0.16)
                .mobChance(EntityType.SLIME, 0.37)
                .mobChance(EntityType.BLAZE, 0.16)
                .mobChance(EntityType.SILVERFISH, 0.37)
                .mobChance(EntityType.CREEPER, 0.5)
                .mobChance(EntityType.MAGMA_CUBE, 0.5)
                .mobChance(EntityType.GUARDIAN, 0.5)
                .mobChance(EntityType.WITCH, 0.5)
                .mobChance(EntityType.VEX, 0.5)
                .mobChance(EntityType.ENDERMAN, 0.5)
                .mobChance(EntityType.ZOMBIFIED_PIGLIN, 0.5)
                .mobChance(EntityType.ENDERMITE, 0.63)
                .mobChance(EntityType.GHAST, 0.63)
                .mobChance(EntityType.WITHER_SKELETON, 0.63)
                .mobChance(EntityType.ELDER_GUARDIAN, 0.63)
                .mobChance(EntityType.EVOKER, 0.63)
                .mobChance(EntityType.VINDICATOR, 0.63)
                .mobChance(EntityType.SHULKER, 0.63)
                .createPool());

        defaultPools.add(PoolBuilder.create("Unusual")
                .setGenerator(Generators.UNUSUAL)
                .setEnabledBiomes(BiomeSets.ALL_BIOMES)
                .setGlobalChance(new Chance(100.0))
                .mobChance(EntityType.ZOMBIE, 0.005)
                .mobChance(EntityType.SPIDER, 0.005)
                .mobChance(EntityType.SKELETON, 0.005)
                .mobChance(EntityType.SLIME, 0.2)
                .mobChance(EntityType.BLAZE, 0.2)
                .mobChance(EntityType.SILVERFISH, 0.2)
                .mobChance(EntityType.CREEPER, 0.12)
                .mobChance(EntityType.MAGMA_CUBE, 0.12)
                .mobChance(EntityType.GUARDIAN, 0.12)
                .mobChance(EntityType.WITCH, 0.12)
                .mobChance(EntityType.VEX, 0.12)
                .mobChance(EntityType.ENDERMAN, 0.12)
                .mobChance(EntityType.PHANTOM, 0.14)
                .mobChance(EntityType.PILLAGER, 0.14)
                .mobChance(EntityType.ZOMBIFIED_PIGLIN, 0.12)
                .mobChance(EntityType.ENDERMITE, 0.28)
                .mobChance(EntityType.GHAST, 0.28)
                .mobChance(EntityType.WITHER_SKELETON, 0.28)
                .mobChance(EntityType.ELDER_GUARDIAN, 0.28)
                .mobChance(EntityType.EVOKER, 0.28)
                .mobChance(EntityType.VINDICATOR, 0.28)
                .mobChance(EntityType.SHULKER, 0.28)
                .mobChance(EntityType.RAVAGER, 0.35)
                .createPool());

        defaultPools.add(PoolBuilder.create("Tags")
                .setGenerator(Generators.TAG)
                .setEnabledBiomes(BiomeSets.ALL_BIOMES)
                .setGlobalChance(new Chance(100.0))
                .mobChance(EntityType.ZOMBIE, 0.005)
                .mobChance(EntityType.SPIDER, 0.005)
                .mobChance(EntityType.SKELETON, 0.005)
                .mobChance(EntityType.SLIME, 0.2)
                .mobChance(EntityType.BLAZE, 0.2)
                .mobChance(EntityType.SILVERFISH, 0.2)
                .mobChance(EntityType.CREEPER, 0.12)
                .mobChance(EntityType.MAGMA_CUBE, 0.12)
                .mobChance(EntityType.GUARDIAN, 0.12)
                .mobChance(EntityType.WITCH, 0.12)
                .mobChance(EntityType.VEX, 0.12)
                .mobChance(EntityType.ENDERMAN, 0.12)
                .mobChance(EntityType.PHANTOM, 0.14)
                .mobChance(EntityType.PILLAGER, 0.14)
                .mobChance(EntityType.ZOMBIFIED_PIGLIN, 0.12)
                .mobChance(EntityType.ENDERMITE, 0.28)
                .mobChance(EntityType.GHAST, 0.28)
                .mobChance(EntityType.WITHER_SKELETON, 0.28)
                .mobChance(EntityType.ELDER_GUARDIAN, 0.28)
                .mobChance(EntityType.EVOKER, 0.28)
                .mobChance(EntityType.VINDICATOR, 0.28)
                .mobChance(EntityType.SHULKER, 0.28)
                .mobChance(EntityType.RAVAGER, 0.35)
                .createPool());

        defaultPools.add(PoolBuilder.create("Rare_CommonBiomes")
                .setGenerator(Generators.RARE)
                .setEnabledBiomes(BiomeSets.COMMON_BIOMES)
                .setGlobalChance(new Chance(100.0))
                .mobChance(EntityType.SLIME, 0.025)
                .mobChance(EntityType.BLAZE, 0.025)
                .mobChance(EntityType.SILVERFISH, 0.025)
                .mobChance(EntityType.CREEPER, 0.05)
                .mobChance(EntityType.MAGMA_CUBE, 0.05)
                .mobChance(EntityType.GUARDIAN, 0.05)
                .mobChance(EntityType.WITCH, 0.05)
                .mobChance(EntityType.VEX, 0.05)
                .mobChance(EntityType.ENDERMAN, 0.05)
                .mobChance(EntityType.PHANTOM, 0.05)
                .mobChance(EntityType.PILLAGER, 0.05)
                .mobChance(EntityType.ZOMBIFIED_PIGLIN, 0.05)
                .mobChance(EntityType.ENDERMITE, 0.36)
                .mobChance(EntityType.GHAST, 0.36)
                .mobChance(EntityType.WITHER_SKELETON, 0.36)
                .mobChance(EntityType.ELDER_GUARDIAN, 0.36)
                .mobChance(EntityType.EVOKER, 0.36)
                .mobChance(EntityType.VINDICATOR, 0.36)
                .mobChance(EntityType.SHULKER, 0.36)
                .mobChance(EntityType.RAVAGER, 0.36)
                .mobChance(EntityType.WITHER, 1)
                .mobChance(EntityType.ENDER_DRAGON, 100)
                .createPool());

        defaultPools.add(PoolBuilder.create("Rare_RareBiomes")
                .setGenerator(Generators.RARE)
                .setEnabledBiomes(BiomeSets.RARE_BIOMES)
                .setGlobalChance(new Chance(100.0))
                .mobChance(EntityType.SLIME, 0.04)
                .mobChance(EntityType.BLAZE, 0.04)
                .mobChance(EntityType.SILVERFISH, 0.04)
                .mobChance(EntityType.CREEPER, 0.08)
                .mobChance(EntityType.MAGMA_CUBE, 0.08)
                .mobChance(EntityType.GUARDIAN, 0.08)
                .mobChance(EntityType.WITCH, 0.08)
                .mobChance(EntityType.VEX, 0.08)
                .mobChance(EntityType.ENDERMAN, 0.08)
                .mobChance(EntityType.PHANTOM, 0.08)
                .mobChance(EntityType.PILLAGER, 0.08)
                .mobChance(EntityType.ZOMBIFIED_PIGLIN, 0.08)
                .mobChance(EntityType.ENDERMITE, 0.65)
                .mobChance(EntityType.GHAST, 0.65)
                .mobChance(EntityType.WITHER_SKELETON, 0.65)
                .mobChance(EntityType.ELDER_GUARDIAN, 0.65)
                .mobChance(EntityType.EVOKER, 0.65)
                .mobChance(EntityType.VINDICATOR, 0.65)
                .mobChance(EntityType.SHULKER, 0.65)
                .mobChance(EntityType.RAVAGER, 0.65)
                .mobChance(EntityType.WITHER, 1)
                .mobChance(EntityType.ENDER_DRAGON, 100)
                .createPool());


//        defaultPools.add(PoolBuilder.create("Relics") //Disabled until block-metadata persistence is ready
//                .setGenerator(Generators.RELIC)
//                .setEnabledBiomes(BiomeSets.ALL_BIOMES)
//                .setGlobalChance(new Chance(100.0))
//                .mobChance(EntityType.ZOMBIE, 0.18)
//                .mobChance(EntityType.SPIDER, 0.18)
//                .mobChance(EntityType.SKELETON, 0.18)
//                .mobChance(EntityType.SLIME, 0.18)
//                .mobChance(EntityType.BLAZE, 0.18)
//                .mobChance(EntityType.SILVERFISH, 0.18)
//                .mobChance(EntityType.CREEPER, 1.2)
//                .mobChance(EntityType.MAGMA_CUBE, 1.4)
//                .mobChance(EntityType.GUARDIAN, 1.4)
//                .mobChance(EntityType.WITCH, 1.4)
//                .mobChance(EntityType.VEX, 1.4)
//                .mobChance(EntityType.ENDERMAN, 1.4)
//                .mobChance(EntityType.PHANTOM, 1.4)
//                .mobChance(EntityType.PILLAGER, 1.4)
//                .mobChance(EntityType.PIG_ZOMBIE, 1.4)
//                .mobChance(EntityType.ENDERMITE, 3.0)
//                .mobChance(EntityType.GHAST, 3.0)
//                .mobChance(EntityType.WITHER_SKELETON, 3.0)
//                .mobChance(EntityType.ELDER_GUARDIAN, 3.0)
//                .mobChance(EntityType.EVOKER, 3.0)
//                .mobChance(EntityType.VINDICATOR, 3.0)
//                .mobChance(EntityType.SHULKER, 3.0)
//                .mobChance(EntityType.RAVAGER, 3.0)
//                .mobChance(EntityType.WITHER, 33)
//                .mobChance(EntityType.ENDER_DRAGON, 100)
//                .createPool());
//
//        defaultPools.add(PoolBuilder.create("Bonus_Relic_1")
//                .setGenerator(Generators.RELIC)
//                .setEnabledBiomes(BiomeSets.ALL_BIOMES)
//                .mobChance(EntityType.WITHER, 10)
//                .mobChance(EntityType.ENDER_DRAGON, 50)
//                .createPool());
//
//        defaultPools.add(PoolBuilder.create("Bonus_Relic_2")
//                .setGenerator(Generators.RELIC)
//                .setEnabledBiomes(BiomeSets.ALL_BIOMES)
//                .mobChance(EntityType.ENDER_DRAGON, 25)
//                .createPool());

        return defaultPools;


    }





}
