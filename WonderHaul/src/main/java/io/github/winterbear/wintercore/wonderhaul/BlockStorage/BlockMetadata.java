package io.github.winterbear.wintercore.wonderhaul.BlockStorage;

import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;

/**
 * Created by WinterBear on 24/05/2020.
 */
public class BlockMetadata {

    private ItemStack internalItem;

    private boolean drops = true;

    private String type;

    private Map<String, List<ItemStack>> customInventories;

    private Integer counter;

    public BlockMetadata(ItemStack item, String type){
        this.internalItem = item;
        this.type = type;
    }

    public ItemStack getInternalItem() {
        return internalItem;
    }

    public void setInternalItem(ItemStack internalItem) {
        this.internalItem = internalItem;
    }

    public boolean drops() {
        return drops;
    }

    public void setDrops(boolean drops) {
        this.drops = drops;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, List<ItemStack>> getCustomInventories() {
        return customInventories;
    }

    public void setCustomInventories(Map<String, List<ItemStack>> customInventories) {
        this.customInventories = customInventories;
    }

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }
}
