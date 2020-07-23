package io.github.winterbear.wintercore.wonderhaul;

import io.github.winterbear.wintercore.utils.ItemUtils;
import io.github.winterbear.wintercore.utils.LoreUtils;
import io.github.winterbear.wintercore.utils.RepeatingTaskUtils;
import io.github.winterbear.wintercore.wonderhaul.blockstorage.BlockMetadata;
import io.github.winterbear.wintercore.wonderhaul.blockstorage.BlockStorage;
import io.github.winterbear.wintercore.wonderhaul.blockstorage.BlockStorageCommands;
import io.github.winterbear.wintercore.wonderhaul.data.PersistentDataHolder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by WinterBear on 24/05/2020.
 */
public class MicroblockDataListener implements Listener, PersistentDataHolder {

    private List<Material> microblockTypes = Arrays.asList(Material.PLAYER_HEAD, Material.PLAYER_WALL_HEAD);

    private BlockStorage blockStorage = new BlockStorage();

    private final JavaPlugin plugin;

    public MicroblockDataListener(JavaPlugin plugin){
        this.plugin = plugin;
        BlockStorageCommands.setStorage(this.blockStorage);
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
        blockStorage.loadFromDB();
        RepeatingTaskUtils.everyMinutes(10, this::saveBlockStorage, plugin);
    }

    public BlockStorage getBlockStorage() {
        return blockStorage;
    }

    private boolean saveBlockStorage(){
        blockStorage.saveToDB();
        return true;
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {

        if(event.getItemInHand().getType().equals(Material.PLAYER_HEAD)){

            Location loc = event.getBlockPlaced().getLocation();
            blockStorage.setBlockMetadata(new BlockMetadata(event.getBlockPlaced(), ItemUtils.oneOf(event.getItemInHand()), getType(event.getItemInHand())));


        }

    }

    private String getType(ItemStack item) {

        return LoreUtils.getLore(item).stream()
                .filter(lore -> lore.contains("✦"))
                .map(line -> line.substring(line.indexOf(':') + 1))
                .findFirst().orElse("None");

    }


    @EventHandler
    public void onBreak(BlockBreakEvent event) {

        Location blockLocation = event.getBlock().getLocation();

        if(event.getBlock().getType().equals(Material.PLAYER_HEAD)){

            Optional<BlockMetadata> blockMeta = blockStorage.get(blockLocation);

            if(blockMeta.isPresent()){
                event.setCancelled(true);
                event.getBlock().setType(Material.AIR);
                ItemUtils.dropNaturally(blockLocation, blockMeta.get().getInternalItem());
                blockStorage.clearBlockMetadata(blockLocation);
            }

        }


    }


    @Override
    public void save() {
        this.saveBlockStorage();
    }
}
