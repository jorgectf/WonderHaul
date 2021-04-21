package io.github.winterbear.wintercore.wonderhaul.sockets.infusions.abilities.wip;

import io.github.winterbear.wintercore.wonderhaul.sockets.TriggerType;
import io.github.winterbear.wintercore.wonderhaul.sockets.abilitytypes.BlockBreakAbility;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.EnumSet;
import java.util.Set;

/**
 * Created by WinterBear on 30/08/2020.
 *
 * Note - probably buff this to occasionally drop rare fruits or something
 */
public class NatureSpirit extends BlockBreakAbility {

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

    @Override
    public void onBlockBreak(Player player, BlockBreakEvent event, int socketLevel) {

    }

    @Override
    public String getName() {
        return "Nature Spirit";
    }

    @Override
    public TriggerType getTriggerType() {
        return TriggerType.MAINHAND;
    }

    @Override
    public String getDescription() {
        return null;
    }

    //@Override
    //public ChatColor getColor() {
    //    return ChatColor.of("#ffca61");
    //}

    //@Override
    //public Collection<Material> getApplicableItems() {
    //    Set<Material> materials = new HashSet<>();
    //    materials.addAll(MaterialGroup.HOE.getMaterials());
    //    materials.addAll(MaterialGroup.SHEARS.getMaterials());
    //    return materials;
    //}
}
