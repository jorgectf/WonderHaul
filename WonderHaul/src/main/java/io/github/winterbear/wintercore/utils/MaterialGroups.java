package io.github.winterbear.wintercore.utils;

import io.github.winterbear.wintercore.wonderhaul.Equipment.MaterialGroup;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by WinterBear on 06/06/2019.
 */
public class MaterialGroups {

    public static Map<Material, MaterialGroup> MATERIALS = lookupTable();

    public static Map<Material, MaterialGroup> lookupTable(){

        Map<Material, MaterialGroup> table = new HashMap<>();
        Arrays.stream(MaterialGroup.values())
                .forEach(g -> g.getMaterials().stream()
                        .forEach(m -> table.put(m, g)));


        return table;
    }




}
