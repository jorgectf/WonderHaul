package io.github.winterbear.wintercore.wonderhaul.sockets.application;

import org.bukkit.inventory.ItemStack;

/**
 * Created by WinterBear on 06/09/2020.
 */
public interface SocketApplicator {

    boolean apply(SocketApplication application, ItemStack item);

}
