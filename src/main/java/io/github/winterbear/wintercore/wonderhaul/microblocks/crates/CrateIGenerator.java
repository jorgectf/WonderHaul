package io.github.winterbear.wintercore.wonderhaul.microblocks.crates;

import io.github.winterbear.wintercore.utils.ColorLoreMode;
import io.github.winterbear.wintercore.utils.ItemBuilder;
import io.github.winterbear.wintercore.utils.TexturedHeads;
import io.github.winterbear.wintercore.wonderhaul.ItemCategory;
import io.github.winterbear.wintercore.wonderhaul.equipment.Tier;
import io.github.winterbear.wintercore.wonderhaul.equipment.generators.Generator;
import org.bukkit.inventory.ItemStack;

/**
 * Created by WinterBear on 18/09/2020.
 */
public class CrateIGenerator implements Generator {

    private static final Tier tier = Tier.UNUSUAL;

    @Override
    public ItemStack create() {

        String displayName = "&lCrate I";
        String description = "&7A mysterious crate containing some money, an item tag and a random piece of equipment.";
        String usage = "Place and then right click to open, drops a selection of items";

        return ItemBuilder.newMicroblock("Tier I",
                ItemCategory.CRATE,
                tier.getColor(),
                ColorLoreMode.DARKER,
                TexturedHeads.CRATE_I)
                .withDisplayName(displayName)
                .withDescription(description)
                .withUsage(usage)
                .build();
    }

    @Override
    public String getName() {
        return "Crate I Generator";
    }
}
