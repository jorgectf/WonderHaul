package io.github.winterbear.wintercore.wonderhaul.sockets;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * Created by WinterBear on 30/08/2020.
 */
public interface Socketable {

    Supplier<ItemStack> getTexture();

    String getItemName();

    String getAbilityName();

    ChatColor getColor();

    EffectType getTriggerType();

    SocketType getSocketType();

    Collection<Material> getApplicableItems();

    String getApplicationInstructions();

    boolean apply(ItemStack item, SocketApplication socketApplication);


}
