package io.github.winterbear.wintercore.wonderhaul.Equipment;

import io.github.winterbear.wintercore.utils.EnumUtils;
import io.github.winterbear.wintercore.utils.MaterialGroups;
import org.bukkit.Material;

import java.util.EnumSet;

/**
 * Created by WinterBear on 17/06/2019.
 */
public enum MaterialGroup {



    PICKAXE("Pickaxe", EnumSet.of(
            Material.DIAMOND_PICKAXE,
            Material.IRON_PICKAXE,
            Material.STONE_PICKAXE,
            Material.WOODEN_PICKAXE,
            Material.GOLDEN_PICKAXE
    )),
    AXE("Axe", EnumSet.of(
            Material.DIAMOND_AXE,
            Material.IRON_AXE,
            Material.STONE_AXE,
            Material.WOODEN_AXE,
            Material.GOLDEN_AXE
    )),
    SHOVEL("Shovel", EnumSet.of(
            Material.DIAMOND_SHOVEL,
            Material.IRON_SHOVEL,
            Material.STONE_SHOVEL,
            Material.WOODEN_SHOVEL,
            Material.GOLDEN_SHOVEL
    )),
    HOE("Hoe", EnumSet.of(
            Material.DIAMOND_HOE,
            Material.IRON_HOE,
            Material.STONE_HOE,
            Material.WOODEN_HOE,
            Material.GOLDEN_HOE
    )),
    SWORD("Sword", EnumSet.of(
            Material.DIAMOND_SWORD,
            Material.GOLDEN_SWORD,
            Material.IRON_SWORD,
            Material.STONE_SWORD,
            Material.WOODEN_SWORD
    )),
    HELMET("Helmet", EnumSet.of(
            Material.DIAMOND_HELMET,
            Material.GOLDEN_HELMET,
            Material.IRON_HELMET,
            Material.LEATHER_HELMET,
            Material.CHAINMAIL_HELMET,
            Material.TURTLE_HELMET
    )),
    CHESTPLATE("Chestplate", EnumSet.of(
            Material.DIAMOND_CHESTPLATE,
            Material.GOLDEN_CHESTPLATE,
            Material.CHAINMAIL_CHESTPLATE,
            Material.LEATHER_CHESTPLATE,
            Material.IRON_CHESTPLATE
    )),
    LEGGINGS("Leggings", EnumSet.of(
            Material.DIAMOND_LEGGINGS,
            Material.GOLDEN_LEGGINGS,
            Material.CHAINMAIL_LEGGINGS,
            Material.LEATHER_LEGGINGS,
            Material.IRON_LEGGINGS
    )),
    BOOTS("Boots", EnumSet.of(
            Material.DIAMOND_BOOTS,
            Material.GOLDEN_BOOTS,
            Material.CHAINMAIL_BOOTS,
            Material.LEATHER_BOOTS,
            Material.IRON_BOOTS
    )),
    BOW("Bow", EnumSet.of(Material.BOW)),
    TRIDENT("Trident",EnumSet.of(Material.TRIDENT)),
    CROSSBOW("Crossbow",EnumSet.of(Material.CROSSBOW)),
    SHIELD("Shield",EnumSet.of(Material.SHIELD)),
    ROD("Rod",EnumSet.of(Material.FISHING_ROD)),
    SHEARS("Shears",EnumSet.of(Material.SHEARS)),
    CARROTSTICK("Carrot on a Stick",EnumSet.of(Material.CARROT_ON_A_STICK)),
    FLINT("Flint and Steel",EnumSet.of(Material.FLINT_AND_STEEL)),
    ELYTRA("Elytra",EnumSet.of(Material.ELYTRA));



    private EnumSet<Material> materials;

    private String displayName;

    public EnumSet<Material> getMaterials(){
        return materials;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static MaterialGroup fromMaterial(Material material){
        return MaterialGroups.MATERIALS.get(material);
    }

    MaterialGroup(String name, EnumSet<Material> materials){
        this.materials = materials;
        this.displayName = name;
    }

    public static final EnumSet<Material> ALL_TOOLS = EnumUtils.combine(Material.class,
            PICKAXE.getMaterials(),
            AXE.getMaterials(),
            SHOVEL.getMaterials(),
            HOE.getMaterials(),
            EnumSet.of(Material.FISHING_ROD, Material.SHEARS)
    );

    public static final EnumSet<Material> ALL_WEAPONS = EnumUtils.combine(Material.class,
            SWORD.getMaterials(),
            AXE.getMaterials(),
            EnumSet.of(Material.BOW, Material.TRIDENT, Material.SHIELD, Material.CROSSBOW)
    );

    public static final EnumSet<Material> ALL_ARMOR = EnumUtils.combine(Material.class,
            HELMET.getMaterials(),
            CHESTPLATE.getMaterials(),
            LEGGINGS.getMaterials(),
            BOOTS.getMaterials());

    public static final EnumSet<Material> ALL_EQUIPMENT = EnumUtils.combine(Material.class,
            ALL_TOOLS,
            ALL_WEAPONS,
            ALL_ARMOR);

}
