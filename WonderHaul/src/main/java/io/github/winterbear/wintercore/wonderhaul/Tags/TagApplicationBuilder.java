package io.github.winterbear.wintercore.wonderhaul.Tags;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class TagApplicationBuilder {
    private int counter;
    private ItemStack tagItem;
    private Tag tag;
    private Player player;

    public static TagApplicationBuilder create(){
        return new TagApplicationBuilder();
    }

    public TagApplicationBuilder forPlayer(Player player){
        this.player = player;
        return this;
    }

    public TagApplicationBuilder withCounter(int counter) {
        this.counter = counter;
        return this;
    }

    public TagApplicationBuilder withTagItem(ItemStack tagItem) {
        this.tagItem = tagItem;
        return this;
    }

    public TagApplicationBuilder withTag(Tag tag) {
        this.tag = tag;
        return this;
    }

    public TagApplication build() {
        return new TagApplication(counter, tagItem, tag, player);
    }
}