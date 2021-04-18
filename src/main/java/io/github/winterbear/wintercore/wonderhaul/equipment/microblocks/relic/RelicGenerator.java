package io.github.winterbear.wintercore.wonderhaul.equipment.microblocks.relic;

import io.github.winterbear.wintercore.utils.ColorLoreMode;
import io.github.winterbear.wintercore.utils.ItemBuilder;
import io.github.winterbear.wintercore.utils.TexturedHeads;
import io.github.winterbear.wintercore.wonderhaul.ItemCategory;
import io.github.winterbear.wintercore.wonderhaul.equipment.Tier;
import io.github.winterbear.wintercore.wonderhaul.equipment.generators.Generator;
import org.bukkit.inventory.ItemStack;

/**
 * Created by WinterBear on 24/05/2020.
 */
public class RelicGenerator implements Generator {

    private static final Tier tier = Tier.MYSTERIOUS;


    @Override
    public ItemStack create() {

        String displayName = tier.getColor() + "Ancient Relic";
        String description = "&7A relic of some ancient culture. It seems to contain an item, but requires a worthy sacrifice to open.";
        String usage = "Place then right click with items to sacrifice them";

        return ItemBuilder.newMicroblock("Relic",
                ItemCategory.ARTIFACT,
                tier.getColor(),
                ColorLoreMode.DARKER,
                TexturedHeads.RELIC_NEW)
                .withDescription(description)
                .withDisplayName(displayName)
                .withUsage(usage)
                .build();
    }

    @Override
    public String getName() {
        return "Relic Generator";
    }
}
