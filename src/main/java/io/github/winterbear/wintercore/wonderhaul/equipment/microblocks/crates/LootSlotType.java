package io.github.winterbear.wintercore.wonderhaul.equipment.microblocks.crates;

import io.github.winterbear.wintercore.utils.ItemUtils;
import io.github.winterbear.wintercore.wonderhaul.dropper.Chance;
import io.github.winterbear.wintercore.wonderhaul.equipment.generators.Generators;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Created by WinterBear on 19/09/2020.
 */
public class LootSlotType implements SlotType {

    @Override
    public ItemStack getNewItem() {
        return generateWonderhaulLoot();
    }

    @Override
    public void reward(Player player, ItemStack item) {
        ItemUtils.safelyGiveItem(player, item);
    }

    private static ItemStack generateWonderhaulLoot() {
        ItemStack wonderItem;
        if(Chance.roll(75)){
            wonderItem = Generators.ORDINARY.create();
        } else if(Chance.roll(80)){
            wonderItem = Generators.UNUSUAL.create();
        } else {
            wonderItem = Generators.RARE.create();
        }
        return wonderItem;
    }
}
