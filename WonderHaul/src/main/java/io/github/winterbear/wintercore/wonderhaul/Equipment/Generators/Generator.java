package io.github.winterbear.wintercore.wonderhaul.Equipment.Generators;

import org.bukkit.inventory.ItemStack;

/**
 * Created by WinterBear on 17/06/2019.
 */
public interface Generator {

    ItemStack create();

    String getName();
}
