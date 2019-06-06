package io.github.winterbear.wintercore.Tags;

import org.bukkit.inventory.ItemStack;

public class TagApplicationBuilder {
    private int counter;
    private ItemStack tagItem;
    private Tag tag;

    public static TagApplicationBuilder create(){
        return new TagApplicationBuilder();
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
        return new TagApplication(counter, tagItem, tag);
    }
}