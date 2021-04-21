package io.github.winterbear.wintercore.wonderhaul.microblocks.essencecollector;

import io.github.winterbear.wintercore.utils.ColorLoreMode;
import io.github.winterbear.wintercore.utils.ItemBuilder;
import io.github.winterbear.wintercore.utils.TexturedHeads;
import io.github.winterbear.wintercore.wonderhaul.ItemCategory;
import io.github.winterbear.wintercore.wonderhaul.equipment.Tier;
import io.github.winterbear.wintercore.wonderhaul.equipment.generators.Generator;
import org.bukkit.inventory.ItemStack;

/**
 * Created by WinterBear on 15/08/2020.
 */
public class EssenceCollectorGenerator implements Generator {

    private static final Tier tier = Tier.ASCENDED;

    @Override
    public ItemStack create() {

        String displayName = "Essence Collector";
        String description = "&7A mysterious altar, used to make offerings to some ancient force. It feels cold to the touch.";
        String usage = "Sacrifice items to upgrade your tools to a new tier";

        return ItemBuilder.newMicroblock(displayName,
                ItemCategory.ARTIFACT,
                tier.getColor(),
                ColorLoreMode.DARKER,
                TexturedHeads.ESSENCE_COLLECTOR)
                .withDescription(description)
                .withDisplayName(displayName)
                .withUsage(usage)
                .build();
    }

    @Override
    public String getName() {
        return "Essence Collector Generator";
    }
}
