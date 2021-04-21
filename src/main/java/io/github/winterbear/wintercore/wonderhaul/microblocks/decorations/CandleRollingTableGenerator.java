package io.github.winterbear.wintercore.wonderhaul.microblocks.decorations;

import io.github.winterbear.wintercore.utils.ColorLoreMode;
import io.github.winterbear.wintercore.utils.ItemBuilder;
import io.github.winterbear.wintercore.utils.TexturedHeads;
import io.github.winterbear.wintercore.wonderhaul.ItemCategory;
import io.github.winterbear.wintercore.wonderhaul.equipment.Tier;
import io.github.winterbear.wintercore.wonderhaul.equipment.generators.Generator;
import org.bukkit.inventory.ItemStack;

/**
 * Created by WinterBear on 17/04/2021.
 */
public class CandleRollingTableGenerator implements Generator {

    private static final Tier tier = Tier.RARE;

    @Override
    public ItemStack create() {

        String displayName = "Candle Rolling Table";
        String description = "&7An ornate table with the tools needed to make high quality candles";
        String usage = "Lets you craft fancy candles with honeycomb and dyes";

        return ItemBuilder.newMicroblock(displayName,
                ItemCategory.EQUIPMENT,
                tier.getColor(),
                ColorLoreMode.DARKER,
                TexturedHeads.CANDLE_ROLLING_TABLE)
                .withDescription(description)
                .withDisplayName(displayName)
                .withUsage(usage)
                .build();
    }

    @Override
    public String getName() {
        return "Candle Rolling Table Generator";
    }
}
