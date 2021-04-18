package io.github.winterbear.wintercore.wonderhaul.equipment.microblocks.crates;

import io.github.winterbear.wintercore.WonderHaul;
import io.github.winterbear.wintercore.utils.*;
import io.github.winterbear.wintercore.wonderhaul.blockstorage.BlockMetadata;
import io.github.winterbear.wintercore.wonderhaul.equipment.microblocks.InventoryAwareMicroblock;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

/**
 * Created by WinterBear on 18/09/2020.
 */
public class VerticalCrate extends InventoryAwareMicroblock {

    private CrateType crateType;

    private Map<Player, Integer> CRATE_TICKS = new HashMap<>();


    public VerticalCrate(CrateType type, JavaPlugin plugin) {
        super(plugin);
        this.crateType = type;
    }

    public void setBlankColumn(int topSlot, InventoryView inventoryView){
        for (int i = topSlot; i < (topSlot + 19); i+=9){
            inventoryView.setItem(i, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        }
    }

    private void setBorderColumn(int topSlot, InventoryView inventoryView){
        inventoryView.setItem(topSlot, new ItemStack(Material.YELLOW_STAINED_GLASS_PANE));
        inventoryView.setItem(topSlot + 9, new ItemStack(Material.WHITE_STAINED_GLASS_PANE));
        inventoryView.setItem(topSlot + 18, new ItemStack(Material.YELLOW_STAINED_GLASS_PANE));
    }


    @Override
    public boolean interact(PlayerInteractEvent event, BlockMetadata metadata) {
        InventoryUtils.openInventory(event.getPlayer(), 3, getReference(), getReference());
        InventoryView inventoryView = event.getPlayer().getOpenInventory();

        crateType.getSlotSize().getBlankColumns().forEach(column -> setBlankColumn(column, inventoryView));
        crateType.getSlotSize().getBorderColumns().forEach(column -> setBorderColumn(column, inventoryView));

        List<CratePrize> prizes = new ArrayList<>();

        for(Integer slot : crateType.getSlots().keySet()){
            for(int i = slot; i < (slot + 19); i +=9){
                event.getPlayer().getOpenInventory().setItem(i, crateType.getSlots().get(slot).getNewItem());
            }
            prizes.add(new CratePrize(inventoryView.getItem(slot + 9), crateType.getSlots().get(slot)));
        }

        DelayUtils.after(2, () -> doCrateTick(event.getPlayer(), metadata, 1, prizes), plugin);

        return true;
    }



    private void doCrateTick(Player player, BlockMetadata metadata, int delay, List<CratePrize> prizes){
        if(InventoryUtils.getCurrentInventory(player).isPresent() && InventoryUtils.getCurrentInventory(player).get().equals(WHInventoryType.CRATE)){
            if(!WonderHaul.getBlockStorage().get(metadata.getInternalLocation()).isPresent()){
                return;
            }
            CRATE_TICKS.putIfAbsent(player, 35);
            int currentTick = CRATE_TICKS.get(player);

            if(currentTick < 1){
                endCrate(player, metadata, prizes);
                return;
            }

            List<CratePrize> newPrizes = new ArrayList<>();
            for (Integer i : crateType.getSlots().keySet()){
                ItemStack newPrize = tickSlot(player.getOpenInventory(), i, crateType.getSlots().get(i));
                newPrizes.add(new CratePrize(newPrize, crateType.getSlots().get(i)));
            }

            SoundUtils.playSound(player, Sound.UI_BUTTON_CLICK);
            final int finalDelay;
            if(crateType.getCrateDelays().containsKey(currentTick)){
                finalDelay = delay + crateType.getCrateDelays().get(currentTick);
            } else {
                finalDelay = delay;
            }

            CRATE_TICKS.put(player, CRATE_TICKS.get(player) - 1);
            DelayUtils.after(finalDelay, () -> doCrateTick(player, metadata, finalDelay, newPrizes), plugin);
        } else {
            endCrate(player, metadata, prizes);
        }
    }

    private ItemStack tickSlot(InventoryView inventoryView, int slot, SlotType slotType){
        ItemStack top = inventoryView.getItem(slot);
        ItemStack middle = inventoryView.getItem(slot + 9);
        ItemStack newItem = slotType.getNewItem();

        inventoryView.setItem(slot, newItem);
        inventoryView.setItem(slot + 9, top);
        inventoryView.setItem(slot + 18, middle);
        return top;
    }

    private void endCrate(Player player, BlockMetadata metadata, List<CratePrize> prizes) {
        if(WonderHaul.getBlockStorage().get(metadata.getInternalLocation()).isPresent()){
            BlockUtils.setBlock(metadata.getInternalLocation().getBlock(), Material.AIR);
            WonderHaul.getBlockStorage().clearBlockMetadata(metadata.getInternalLocation());

            CRATE_TICKS.remove(player);
            SoundUtils.playSound(player, Sound.BLOCK_NOTE_BLOCK_CHIME);

            player.getOpenInventory().close();
            InventoryUtils.closeInventory(player);

            prizes.forEach(p -> p.rewardPlayer(player));
        }
    }



    @Override
    public String getReference() {
        return crateType.getLore();
    }

    @Override
    public void handleClick(InventoryClickEvent event) {
        event.setCancelled(true);
    }
}
