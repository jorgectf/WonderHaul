package io.github.winterbear.wintercore.wonderhaul.tags;

import io.github.winterbear.wintercore.utils.EnchantmentUtils;
import io.github.winterbear.wintercore.utils.RandomUtils;
import io.github.winterbear.wintercore.utils.SoundUtils;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;

public class BoostTag implements Tag {



    @Override
    public boolean apply(ItemStack item, TagApplication application) {
        Player player = application.getPlayer();
        final Map<Enchantment, Integer> enchantments = item.getEnchantments();
        if(enchantments.isEmpty()){
            sendMessage(player,"There are no enchantments on this item.");
            return false;
        } else {
            List<Enchantment> boostableEnchantments  = EnchantmentUtils.enchantsValidForBoosting(enchantments);
            if(boostableEnchantments.isEmpty()){
                sendMessage(player, "All the enchantments on this item are already at their maximum level.");
                return false;
            } else {
                Enchantment toBoost = RandomUtils.getRandomElementOf(boostableEnchantments);
                String newLevel = EnchantmentUtils.boost(item, toBoost, enchantments.get(toBoost));
                SoundUtils.playSound(player, Sound.ENTITY_PLAYER_LEVELUP);
                sendMessage(player, "The " + EnchantmentUtils.getName(toBoost) +  " enchantment was boosted to level " + newLevel +  "!");
                return true;
            }
        }
    }

    @Override
    public String getDisplayName() {
        return "Boost Tag";
    }

    @Override
    public String getInstructions() {
        return "Use on an enchanted item to boost the level of one of its enchantments!";
    }

    @Override
    public String getDescription() {
        return "A worn fragment of parchment, torn off from a greater whole. The runes written on it still hold some power.";
    }

    @Override
    public ChatColor getColor() {
        return ChatColor.of("#fc5e03");
    }
}
