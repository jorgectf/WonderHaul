package io.github.winterbear.wintercore.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.bukkit.enchantments.Enchantment;

/**
 * Created by WinterBear on 12/06/2019.
 */
public class EnchantmentUtils {

    public static String getName(Enchantment enchantment){
        return WordUtils.capitalize(enchantment.getKey().toString()
                .replace("minecraft:", "")
                .replace("_", " "));

    }

}
