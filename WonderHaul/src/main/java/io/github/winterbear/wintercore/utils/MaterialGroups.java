package io.github.winterbear.wintercore.utils;

import org.bukkit.Material;

import java.util.EnumSet;

/**
 * Created by WinterBear on 06/06/2019.
 */
public class MaterialGroups {

    public static final EnumSet<Material> PICKAXES = EnumSet.of(
            Material.DIAMOND_PICKAXE,
            Material.IRON_PICKAXE,
            Material.STONE_PICKAXE,
            Material.WOODEN_PICKAXE,
            Material.GOLDEN_PICKAXE
    );

    public static final EnumSet<Material> AXES = EnumSet.of(
            Material.DIAMOND_AXE,
            Material.IRON_AXE,
            Material.STONE_AXE,
            Material.WOODEN_AXE,
            Material.GOLDEN_AXE
    );

    public static final EnumSet<Material> SHOVELS = EnumSet.of(
            Material.DIAMOND_SHOVEL,
            Material.IRON_SHOVEL,
            Material.STONE_SHOVEL,
            Material.WOODEN_SHOVEL,
            Material.GOLDEN_SHOVEL
    );

    public static final EnumSet<Material> HOES = EnumSet.of(
            Material.DIAMOND_HOE,
            Material.IRON_HOE,
            Material.STONE_HOE,
            Material.WOODEN_HOE,
            Material.GOLDEN_HOE
    );

    public static final EnumSet<Material> SWORDS = EnumSet.of(
            Material.DIAMOND_SWORD,
            Material.GOLDEN_SWORD,
            Material.IRON_SWORD,
            Material.STONE_SWORD,
            Material.WOODEN_SWORD
    );

    public static final EnumSet<Material> HELMETS = EnumSet.of(
            Material.DIAMOND_HELMET,
            Material.GOLDEN_HELMET,
            Material.IRON_HELMET,
            Material.LEATHER_HELMET,
            Material.CHAINMAIL_HELMET,
            Material.TURTLE_HELMET
    );

    public static final EnumSet<Material> CHESTPLATES = EnumSet.of(
            Material.DIAMOND_CHESTPLATE,
            Material.GOLDEN_CHESTPLATE,
            Material.CHAINMAIL_CHESTPLATE,
            Material.LEATHER_CHESTPLATE,
            Material.IRON_CHESTPLATE
    );

    public static final EnumSet<Material> LEGGINGS = EnumSet.of(
            Material.DIAMOND_LEGGINGS,
            Material.GOLDEN_LEGGINGS,
            Material.CHAINMAIL_LEGGINGS,
            Material.LEATHER_LEGGINGS,
            Material.IRON_LEGGINGS
    );

    public static final EnumSet<Material> BOOTS = EnumSet.of(
            Material.DIAMOND_BOOTS,
            Material.GOLDEN_BOOTS,
            Material.CHAINMAIL_BOOTS,
            Material.LEATHER_BOOTS,
            Material.IRON_BOOTS
    );

    public static final EnumSet<Material> TOOLS = EnumUtils.combine(Material.class,
            PICKAXES,
            AXES,
            SHOVELS,
            HOES,
            EnumSet.of(Material.FISHING_ROD, Material.SHEARS)
    );

    public static final EnumSet<Material> WEAPONS = EnumUtils.combine(Material.class,
            SWORDS,
            AXES,
            EnumSet.of(Material.BOW, Material.TRIDENT, Material.SHIELD)
    );

    public static final EnumSet<Material> ARMOR = EnumUtils.combine(Material.class,
            HELMETS,
            CHESTPLATES,
            LEGGINGS,
            BOOTS);

    public static final EnumSet<Material> EQUIPMENT = EnumUtils.combine(Material.class,
            TOOLS,
            WEAPONS,
            ARMOR);

}
