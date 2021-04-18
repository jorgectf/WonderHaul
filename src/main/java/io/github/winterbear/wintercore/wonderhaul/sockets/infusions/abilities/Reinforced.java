package io.github.winterbear.wintercore.wonderhaul.sockets.infusions.abilities;

import com.google.common.collect.Sets;
import io.github.winterbear.wintercore.utils.BlockUtils;
import io.github.winterbear.wintercore.utils.EventUtils;
import io.github.winterbear.wintercore.utils.FakeBlockBreakEvent;
import io.github.winterbear.wintercore.utils.ItemUtils;
import io.github.winterbear.wintercore.wonderhaul.equipment.MaterialGroup;
import io.github.winterbear.wintercore.wonderhaul.sockets.TriggerType;
import io.github.winterbear.wintercore.wonderhaul.sockets.abilitytypes.ToolAbility;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Set;

/**
 * Created by WinterBear on 12/09/2020.
 */
public class Reinforced extends ToolAbility {

    private static Set<Material> PICKAXE_MATERIALS = Sets.newHashSet(
            Material.STONE,
            Material.DIORITE,
            Material.GRANITE,
            Material.ANDESITE,
            Material.END_STONE,
            Material.COBBLESTONE,
            Material.SANDSTONE,
            Material.BLACKSTONE,
            Material.BASALT,
            Material.RED_SANDSTONE,
            Material.NETHERRACK
    );

    private static Set<Material> SHOVEL_MATERIALS = Sets.newHashSet(
            Material.DIRT,
            Material.SAND,
            Material.RED_SAND,
            Material.CLAY,
            Material.GRASS_PATH,
            Material.GRASS_BLOCK,
            Material.GRAVEL,
            Material.MYCELIUM,
            Material.PODZOL,
            Material.COARSE_DIRT,
            Material.SOUL_SAND,
            Material.SOUL_SOIL,
            Material.SNOW_BLOCK,
            Material.SNOW
    );

    private static Set<Material> AXE_MATERIALS = Sets.newHashSet(
            Material.OAK_LOG,
            Material.SPRUCE_LOG,
            Material.BIRCH_LOG,
            Material.JUNGLE_LOG,
            Material.DARK_OAK_LOG,
            Material.ACACIA_LOG,
            Material.WARPED_HYPHAE,
            Material.CRIMSON_HYPHAE
    );

    private static Set<Material> HOE_MATERIALS = Sets.newHashSet(
            Material.DIRT,
            Material.GRASS_BLOCK
    );

    @Override
    public void onBlockBreak(Player player, BlockBreakEvent event, int socketLevel) {
        if(!(event instanceof FakeBlockBreakEvent)) {
            if (MaterialGroup.PICKAXE.getMaterials().contains(player.getInventory().getItemInMainHand().getType())
                    && PICKAXE_MATERIALS.contains(event.getBlock().getType())) {
                handleDirectionalBlockbreak(player, event, PICKAXE_MATERIALS);
            } else if (MaterialGroup.SHOVEL.getMaterials().contains(player.getInventory().getItemInMainHand().getType())
                    && SHOVEL_MATERIALS.contains(event.getBlock().getType())) {
                handleDirectionalBlockbreak(player, event, SHOVEL_MATERIALS);
            } else if (MaterialGroup.AXE.getMaterials().contains(player.getInventory().getItemInMainHand().getType())
                    && AXE_MATERIALS.contains(event.getBlock().getType())) {
                handleAxeBlockbreak(player, event, socketLevel);
            }
        }
    }

    @Override
    public void onPlayerInteract(Player player, PlayerInteractEvent event, int socketLevel) {
        if (event.getClickedBlock() != null &&
                MaterialGroup.HOE.getMaterials().contains(player.getInventory().getItemInMainHand().getType())
                && HOE_MATERIALS.contains(event.getClickedBlock().getType())) {
            handleHoeBlockupdate(player, event, socketLevel);
        }
    }

    private void handleHoeBlockupdate(Player player, PlayerInteractEvent event, int socketLevel){
        if (event.getClickedBlock() != null){
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    Block block = event.getClickedBlock().getRelative(i, 0, j);
                    if (HOE_MATERIALS.contains(block.getType()) &&
                            !block.equals(event.getClickedBlock())) {

                        BlockUtils.setBlock(block, Material.FARMLAND);
                    }
                }
            }
        }
    }

    private void handleAxeBlockbreak(Player player, BlockBreakEvent event, int socketLevel){
        for (int i = -2; i < 3; i++){
            Block block = event.getBlock().getRelative(0, i, 0);
            if(block.getType().equals(event.getBlock().getType()) &&
                    !block.equals(event.getBlock()) &&
                    EventUtils.simulateBlockBreak(block, player, true)) {

                ItemUtils.dropNaturally(BlockUtils.getBlockCentre(block.getLocation()), block.getDrops());

                BlockUtils.setBlock(block, Material.AIR);
            }
        }
    }

    private void handleDirectionalBlockbreak(Player player, BlockBreakEvent event, Set<Material> materials){
        String direction = getDirection(player);

        for (int i = -1; i < 2; i++){
            for (int j = -1; j < 2; j++){
                Block block = null;
                if (direction.equals("North") || direction.equals("South")){
                    block = event.getBlock().getRelative(0, i, j);
                } if (direction.equals("East") || direction.equals("West")){
                    block = event.getBlock().getRelative(i, j, 0);
                } if (direction.equals("Up") || direction.equals("Down")){
                    block = event.getBlock().getRelative(i, 0, j);
                }
                if(block != null && materials.contains(block.getType()) &&
                        !block.equals(event.getBlock()) &&
                        EventUtils.simulateBlockBreak(block, player, true)) {

                    ItemUtils.dropNaturally(BlockUtils.getBlockCentre(block.getLocation()), block.getDrops());

                    BlockUtils.setBlock(block, Material.AIR);
                }
            }
        }
    }



    private String getDirection(Player player) {
        double rot = (player.getLocation().getYaw() - 90) % 360;
        if (rot < 0) {
            rot += 360.0;
        }

        double updown = player.getLocation().getPitch();
        String direction;
        if(updown > 45.0){
            direction = "Down";
        } else if(updown < -45.0){
            direction = "Up";
        } else if (0.0 <= rot && rot < 45.0) {
            direction =  "North";
        } else if (45.0 <= rot && rot < 135.0) {
            direction =  "East";
        } else if (135.0 <= rot && rot < 225.0) {
            direction = "South";
        } else if (225.0 <= rot && rot < 315.0) {
            direction = "West";
        } else {
            direction = "North";
        }
        return direction;
    }

    @Override
    public String getName() {
        return "Reinforced";
    }

    @Override
    public TriggerType getTriggerType() {
        return TriggerType.MAINHAND;
    }

    @Override
    public String getDescription() {
        return "Increases the range of your tools effect";
    }
}
