package io.github.winterbear.wintercore.Tags;

import com.frequal.romannumerals.Converter;
import io.github.winterbear.wintercore.utils.CollectionUtils;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BoostTag implements Tag {

    private static Converter romanNumeralConverter = new Converter();

    @Override
    public boolean apply(ItemStack item, Player player) {
        final Map<Enchantment, Integer> enchantments = item.getEnchantments();
        if(enchantments.isEmpty()){
            sendMessage(player,"There are no enchantments on this item.");
            return false;
        } else {
            List<Enchantment> boostableEnchantments  = enchantments.entrySet().stream().filter(e -> enchantmentValidForBoosting(e.getKey(), e.getValue())).map(e -> e.getKey()).collect(Collectors.toList());
            if(boostableEnchantments.isEmpty()){
                sendMessage(player, "All the enchantments on this item are already at their maximum level.");
                return false;
            } else {
                Enchantment toBoost = CollectionUtils.getRandomElementOf(boostableEnchantments);
                int newLevel = enchantments.get(toBoost) + 1;
                item.addEnchantment(toBoost, newLevel);
                sendMessage(player, "The " + toBoost.toString() +  " enchantment was boosted to level " + romanNumeralConverter.toRomanNumerals(newLevel) +  "!");
                return true;
            }
        }
    }


    private boolean enchantmentValidForBoosting(Enchantment enchantment, Integer level){
        return !(level >= enchantment.getMaxLevel());
    }

    @Override
    public String getDisplayName() {
        return "&cBoost Tag";
    }

    @Override
    public String getInstructions() {
        return "&7Use on an enchanted item to boost the level of one of its enchantments!";
    }
}
