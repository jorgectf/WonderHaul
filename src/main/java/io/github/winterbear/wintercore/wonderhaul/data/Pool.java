package io.github.winterbear.wintercore.wonderhaul.data;

import io.github.winterbear.wintercore.wonderhaul.dropper.Chance;
import io.github.winterbear.wintercore.wonderhaul.equipment.generators.Generator;

/**
 * Created by WinterBear on 14/12/2020.
 */
public interface Pool<Trigger> {

    Chance getGlobalChance();

    BiomeSet getEnabledBiomes();

    boolean roll(Trigger trigger, long luckModifier);

    Generator getGenerator();

}
