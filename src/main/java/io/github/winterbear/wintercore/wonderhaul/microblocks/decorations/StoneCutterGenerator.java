package io.github.winterbear.wintercore.wonderhaul.microblocks.decorations;

import io.github.winterbear.wintercore.utils.ColorLoreMode;
import io.github.winterbear.wintercore.utils.ItemBuilder;
import io.github.winterbear.wintercore.utils.TexturedHeads;
import io.github.winterbear.wintercore.wonderhaul.ItemCategory;
import io.github.winterbear.wintercore.wonderhaul.equipment.generators.Generator;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.inventory.ItemStack;

/**
 * Created by WinterBear on 17/04/2021.
 */
public class StoneCutterGenerator implements Generator {

    @Override
    public ItemStack create() {
        String displayName = "Carving Station";
        String description = "&7A stone plinth with the tools needed to make high quality stone carvings";
        String usage = "Lets you craft stone carvings using smooth stone";

        return ItemBuilder.newMicroblock(displayName,
                ItemCategory.EQUIPMENT,
                ChatColor.of("#3a3a52"),
                ColorLoreMode.BRIGHTER,
                TexturedHeads.STONECUTTER)
                .withDescription(description)
                .withDisplayName(displayName)
                .withUsage(usage)
                .build();
    }

    @Override
    public String getName() {
        return "Carving Station Generator";
    }
}
