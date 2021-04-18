package io.github.winterbear.wintercore.utils;

import org.bukkit.inventory.ItemStack;

import java.util.function.Supplier;

import static io.github.winterbear.wintercore.utils.MicroblockUtils.getCustomSkull;

/**
 * Created by WinterBear on 26/09/2020.
 */
public class TexturedHead implements Supplier<ItemStack> {

    private String textureURL;

    public String getTextureURL(){
        return textureURL;
    }

    public TexturedHead(String textureURL){
        this.textureURL = textureURL;
    }

    @Override
    public ItemStack get() {
        return getCustomSkull(textureURL);
    }
}
