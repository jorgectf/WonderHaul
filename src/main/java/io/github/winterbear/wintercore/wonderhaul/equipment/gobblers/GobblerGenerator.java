package io.github.winterbear.wintercore.wonderhaul.equipment.gobblers;

import io.github.winterbear.wintercore.utils.ColorLoreMode;
import io.github.winterbear.wintercore.utils.ItemBuilder;
import io.github.winterbear.wintercore.utils.LoreUtils;
import io.github.winterbear.wintercore.utils.RandomUtils;
import io.github.winterbear.wintercore.wonderhaul.ItemCategory;
import io.github.winterbear.wintercore.wonderhaul.equipment.generators.Generator;
import org.bukkit.inventory.ItemStack;

/**
 * Created by WinterBear on 26/09/2020.
 */
public class GobblerGenerator implements Generator {

    public static ItemStack getActiveGobbler(Gobbler gobbler){


        return ItemBuilder.newMicroblock("Gobbler",
                ItemCategory.EQUIPMENT,
                gobbler.getColor(),
                ColorLoreMode.BRIGHTER,
                gobbler.getOpenTexture())
                .withDescription("Loves to eat " + LoreUtils.getItemsAsVerbal(gobbler.getItems()) + ". " + gobbler.getWasteAction())
                .withUsage("Right click to toggle. Will destroy materials you pick up while active.")
                .withDisplayName(gobbler.getDisplayName())
                .build();

    }

    public static ItemStack getInactiveGobbler(Gobbler gobbler){

        return ItemBuilder.newMicroblock("Inactive Gobbler",
                ItemCategory.EQUIPMENT,
                gobbler.getColor(),
                ColorLoreMode.BRIGHTER,
                gobbler.getClosedTexture())
                .withDescription("Loves to eat " + LoreUtils.getItemsAsVerbal(gobbler.getItems()) + ". " + gobbler.getWasteAction())
                .withUsage("Right click to toggle. Will destroy materials you pick up while active.")
                .withDisplayName(gobbler.getDisplayName())
                .build();


    }

    @Override
    public ItemStack create() {
        return getInactiveGobbler(RandomUtils.getRandomElementOf(Gobblers.GOBBLERS));
    }

    @Override
    public String getName() {
        return "Gobbler";
    }
}
