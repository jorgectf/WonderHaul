package io.github.winterbear.wintercore.wonderhaul.tags;

import com.frequal.romannumerals.Converter;
import io.github.winterbear.wintercore.utils.EnchantmentUtils;
import io.github.winterbear.wintercore.utils.RandomUtils;
import io.github.winterbear.wintercore.utils.SoundUtils;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BoostTag implements Tag {

    private static Converter romanNumeralConverter = new Converter();

    @Override
    public boolean apply(ItemStack item, TagApplication application) {
        Player player = application.getPlayer();
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
                Enchantment toBoost = RandomUtils.getRandomElementOf(boostableEnchantments);
                int newLevel = enchantments.get(toBoost) + 1;
                item.addEnchantment(toBoost, newLevel);
                SoundUtils.playSound(player, Sound.ENTITY_PLAYER_LEVELUP);
                sendMessage(player, "The " + EnchantmentUtils.getName(toBoost) +  " enchantment was boosted to level " + romanNumeralConverter.toRomanNumerals(newLevel) +  "!");
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
