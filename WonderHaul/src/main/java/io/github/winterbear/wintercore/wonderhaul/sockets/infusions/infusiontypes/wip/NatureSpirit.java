package io.github.winterbear.wintercore.wonderhaul.sockets.infusions.infusiontypes.wip;

import io.github.winterbear.wintercore.utils.TexturedHeads;
import io.github.winterbear.wintercore.wonderhaul.equipment.MaterialGroup;
import io.github.winterbear.wintercore.wonderhaul.sockets.EffectType;
import io.github.winterbear.wintercore.wonderhaul.sockets.infusions.BlockBreakInfusion;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;
import java.util.function.Supplier;

/**
 * Created by WinterBear on 30/08/2020.
 *
 * Note - probably buff this to occasionally drop rare fruits or something
 */
public class NatureSpirit extends BlockBreakInfusion {

    private static final Set<Material> HOE_DROPS = EnumSet.of(Material.CHORUS_FLOWER,
            Material.SWEET_BERRIES,
            Material.NETHER_WART,
            Material.COCOA);

    private static final Set<Material> HOE_MATERIALS = EnumSet.of(Material.WHEAT,
            Material.BEETROOT,
            Material.CARROT,
            Material.POTATO,
            Material.MELON,
            Material.COCOA_BEANS,
            Material.SWEET_BERRY_BUSH,
            Material.CHORUS_FLOWER);

    private static final Set<Material> SHEAR_MATERIALS = EnumSet.of(Material.DEAD_BUSH,
            Material.FERN,
            Material.GRASS,
            Material.OAK_LEAVES,
            Material.SPRUCE_LEAVES,
            Material.BIRCH_LEAVES,
            Material.JUNGLE_LEAVES,
            Material.ACACIA_LEAVES,
            Material.DARK_OAK_LEAVES);


    public NatureSpirit(JavaPlugin plugin) {
        super(plugin);
    }

    @Override
    public void onBlockBreak(Player player, BlockBreakEvent event) {

    }

    @Override
    public Supplier<ItemStack> getTexture() {
        return TexturedHeads.FRUIT_OF_FORTUNE;
    }

    @Override
    public String getItemName() {
        return "Fruit of Fortune";
    }

    @Override
    public String getAbilityName() {
        return "Nature Spirit";
    }

    @Override
    public ChatColor getColor() {
        return ChatColor.of("#ffca61");
    }

    @Override
    public EffectType getTriggerType() {
        return EffectType.MAINHAND;
    }

    @Override
    public Collection<Material> getApplicableItems() {
        Set<Material> materials = new HashSet<>();
        materials.addAll(MaterialGroup.HOE.getMaterials());
        materials.addAll(MaterialGroup.SHEARS.getMaterials());
        return materials;
    }
}
