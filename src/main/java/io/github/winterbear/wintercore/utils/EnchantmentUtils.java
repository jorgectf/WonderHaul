package io.github.winterbear.wintercore.utils;

import com.frequal.romannumerals.Converter;
import org.apache.commons.lang.WordUtils;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by WinterBear on 12/06/2019.
 */
public class EnchantmentUtils {

    private static Converter romanNumeralConverter = new Converter();

    public static String getName(Enchantment enchantment){
        return WordUtils.capitalize(enchantment.getKey().toString()
                .replace("minecraft:", "")
                .replace("_", " "));

    }

    public static List<Enchantment> enchantsValidForBoosting(Map<Enchantment, Integer> enchantments){
        return enchantments.entrySet().stream()
                .filter(e -> enchantmentValidForBoosting(e.getKey(), e.getValue())).map(e -> e.getKey())
                .collect(Collectors.toList());
    }

    private static boolean enchantmentValidForBoosting(Enchantment enchantment, Integer level){
        return !(level >= enchantment.getMaxLevel());
    }



    public static String boost(ItemStack item, Enchantment enchantment, int originalLevel){
        int newLevel = originalLevel + 1;
        item.addUnsafeEnchantment(enchantment, newLevel);
        return romanNumeralConverter.toRomanNumerals(newLevel);
    }

}
