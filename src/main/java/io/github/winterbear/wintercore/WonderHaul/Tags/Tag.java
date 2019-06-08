package io.github.winterbear.wintercore.WonderHaul.Tags;

import io.github.winterbear.wintercore.utils.ChatUtils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Created by WinterBear on 05/06/2019.
 */
public interface Tag {

    boolean apply(ItemStack item, Player player);

    String getDisplayName();

    String getInstructions();

    default void sendMessage(Player player, String text){
        ChatUtils.send(player, this.getDisplayName() + "&8: &7" + text);
    }

}
