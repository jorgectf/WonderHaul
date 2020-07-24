package io.github.winterbear.wintercore.wonderhaul.tags;

import io.github.winterbear.WinterCoreUtils.ChatUtils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Created by WinterBear on 05/06/2019.
 */
public interface Tag {

    boolean apply(ItemStack item, TagApplication application);

    String getDisplayName();

    String getInstructions();

    default void sendMessage(Player player, String text){
        ChatUtils.send(player, this.getDisplayName() + "&8: &7" + text);
    }

    default ItemStack modify(ItemStack item){
        return item;
    }

}
