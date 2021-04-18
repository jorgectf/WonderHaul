package io.github.winterbear.wintercore.wonderhaul.data;

import io.github.winterbear.wintercore.wonderhaul.dropper.Chance;
import io.github.winterbear.wintercore.wonderhaul.equipment.generators.Generator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WinterBear on 14/12/2020.
 */
public abstract class BasicPool<Trigger> implements Pool<Trigger> {

    protected String name;

    protected Chance globalChance;

    protected List<String> enabledWorlds = new ArrayList<>();

    protected BiomeSet enabledBiomes;

    protected Generator generator;

    
}
