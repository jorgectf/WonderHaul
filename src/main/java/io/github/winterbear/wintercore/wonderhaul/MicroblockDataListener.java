package io.github.winterbear.wintercore.wonderhaul;

import io.github.winterbear.WinterCoreUtils.ChatUtils;
import io.github.winterbear.wintercore.utils.ItemUtils;
import io.github.winterbear.wintercore.utils.LightUtils;
import io.github.winterbear.wintercore.utils.LoreUtils;
import io.github.winterbear.wintercore.utils.RepeatingTaskUtils;
import io.github.winterbear.wintercore.wonderhaul.blockstorage.BlockMetadata;
import io.github.winterbear.wintercore.wonderhaul.blockstorage.BlockStorage;
import io.github.winterbear.wintercore.wonderhaul.blockstorage.BlockStorageCommands;
import io.github.winterbear.wintercore.wonderhaul.data.PersistentDataHolder;
import io.github.winterbear.wintercore.wonderhaul.equipment.Microblock;
import io.github.winterbear.wintercore.wonderhaul.equipment.Microblocks;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by WinterBear on 24/05/2020.
 */
public class MicroblockDataListener implements Listener, PersistentDataHolder {

    private BlockStorage blockStorage = new BlockStorage();

    public MicroblockDataListener(JavaPlugin plugin){
        BlockStorageCommands.setStorage(this.blockStorage);
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        blockStorage.loadFromDB();
        RepeatingTaskUtils.everyMinutes(5, this::saveBlockStorage, plugin);
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
        if(Microblock.MICROBLOCK_TYPES.contains(event.getItemInHand().getType())){
            String type = LoreUtils.getType(event.getItemInHand());
            BlockMetadata metadata = new BlockMetadata(event.getBlockPlaced(), ItemUtils.oneOf(event.getItemInHand()), type);
            metadata.setProperty("Owner", event.getPlayer().getUniqueId().toString());
            if(Microblocks.get(type).isLight()){
                LightUtils.toggleLight(metadata);
            }
            if(Microblocks.get(type).getEffectType() != null){
                metadata.setProperty("ParticleEffect", Microblocks.get(type).getEffectType().toString());
            }
            blockStorage.setBlockMetadata(metadata);
        }
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event){
        List<Block> blocks = new ArrayList<>(event.blockList());
        for (Block block : blocks) {
            if(blockStorage.get(block.getLocation()).isPresent()){
                event.blockList().remove(block);
            }
        }
    }

    @EventHandler
    public void onBlockExplode(BlockExplodeEvent event){
        List<Block> blocks = new ArrayList<>(event.blockList());
        for (Block block : blocks) {
            if(blockStorage.get(block.getLocation()).isPresent()){
                event.blockList().remove(block);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockFromTo(BlockFromToEvent event) {
        Block block = event.getToBlock();
        if (blockStorage.get(block.getLocation()).isPresent()) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPistonEvent(BlockPistonExtendEvent e){
        if (e.getBlocks().stream().anyMatch(b -> blockStorage.get(b.getLocation()).isPresent())){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPistonEvent(BlockPistonRetractEvent e){
        if (e.getBlocks().stream().anyMatch(b -> blockStorage.get(b.getLocation()).isPresent())){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {

        Location blockLocation = event.getBlock().getLocation();

        if(Microblock.MICROBLOCK_TYPES.contains(event.getBlock().getType())){

            Optional<BlockMetadata> blockMeta = blockStorage.get(blockLocation);

            if(blockMeta.isPresent()){
                event.setCancelled(true);
                if(!isOwner(blockMeta.get(), event.getPlayer()) && !canOverrideLock(event.getPlayer())){
                    ChatUtils.send(event.getPlayer(), "&7This doesn't belong to you.");
                    return;
                }
                event.getBlock().setType(Material.AIR);
                ItemUtils.dropNaturally(blockLocation, blockMeta.get().getInternalItem());
                if(!blockMeta.get().getCustomInventories().keySet().isEmpty()){
                    blockMeta.get().getCustomInventories().values().forEach(i -> {
                            i.getItems().forEach(d -> ItemUtils.dropNaturally(blockLocation, d.getInternalItem()));
                    });
                }
                LightUtils.removeLight(blockMeta.get());
                blockStorage.clearBlockMetadata(blockLocation);
            }
        }
    }

    private boolean canOverrideLock(Player player){
        return player.hasPermission("wonderhaul.mod.overridelock");
    }

    private boolean isOwner(BlockMetadata metadata, Player player){
        if(metadata.getProperty("Owner").isPresent()){
            return metadata.getProperty("Owner").get().equals(player.getUniqueId().toString());
        } else {
            return false;
        }
    }


    @Override
    public void save() {
        this.saveBlockStorage();
    }
}
