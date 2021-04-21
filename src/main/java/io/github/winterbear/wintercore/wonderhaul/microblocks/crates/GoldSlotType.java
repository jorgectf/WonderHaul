package io.github.winterbear.wintercore.wonderhaul.microblocks.crates;

import io.github.winterbear.WinterCoreUtils.ChatUtils;
import io.github.winterbear.wintercore.utils.EconomyUtils;
import io.github.winterbear.wintercore.utils.ItemBuilder;
import io.github.winterbear.wintercore.utils.RandomUtils;
import io.github.winterbear.wintercore.utils.TexturedHeads;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Created by WinterBear on 19/09/2020.
 */
public class GoldSlotType implements SlotType {

    @Override
    public ItemStack getNewItem() {
        int gold = getGold();
        ItemStack moneyItem = new ItemStack(Material.GOLD_NUGGET);
        if(gold > 400){
            moneyItem = TexturedHeads.GOLD_HAUL.get();
        } else if (gold > 150){
            moneyItem = new ItemStack(Material.GOLD_INGOT);
        }
        ItemBuilder.setDisplayName(moneyItem, ChatUtils.format("&6❄" + getGold()));
        return moneyItem;
    }

    private int getGold(){
        return RandomUtils.getIntegerBetween(0, 500);
    }

    @Override
    public void reward(Player player, ItemStack item) {
        ChatUtils.send(player, ChatColor.GOLD + "Won " + item.getItemMeta().getDisplayName() + " money!");
        EconomyUtils.ECONOMY.depositPlayer(player, Double.parseDouble(ChatUtils.uncolored(item.getItemMeta().getDisplayName().replace("❄", ""))));
    }
}
