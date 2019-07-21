package io.github.winterbear.wintercore.wonderhaul.Tags;

import io.github.winterbear.wintercore.utils.ChatUtils;
import io.github.winterbear.wintercore.utils.ItemUtils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Created by WinterBear on 05/06/2019.
 */
public class TagApplication {

    private int counter;

    private ItemStack tagItem;

    private Tag tag;

    private Player player;

    public TagApplication(int counter, ItemStack tagItem, Tag tag, Player player) {
        this.counter = counter;
        this.tagItem = tagItem;
        this.tag = tag;
        this.player = player;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public ItemStack getTagItem() {
        return tagItem;
    }

    public Player getPlayer() { return player; }

    public void setTagItem(ItemStack tagItem) {
        this.tagItem = tagItem;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public void returnTag(Player player){
        ChatUtils.send(player, tag.getDisplayName() + ": &7Tag application cancelled. Giving you back your tag...");
        ItemUtils.safelyGiveItem(player, tagItem);
    }
}
