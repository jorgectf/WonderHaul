package io.github.winterbear.wintercore.wonderhaul.Equipment.Enchanting;

import io.github.winterbear.wintercore.ConfigLoader;
import io.github.winterbear.wintercore.utils.ChatUtils;
import io.github.winterbear.wintercore.wonderhaul.Equipment.MaterialGroup;
import io.github.winterbear.wintercore.wonderhaul.Equipment.Tier;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Optional;

/**
 * Created by WinterBear on 17/06/2019.
 */
public class Enchantments {


    private static EnchantmentDictionary dictionary;

    public static void enable(JavaPlugin plugin){
        Enchantments.reload(plugin);
    }

    public static void reload(JavaPlugin plugin){
        ConfigLoader.registerCustomConfig(plugin, "enchants", "enchants.yml");
        dictionary = new EnchantmentDictionary(ConfigLoader.getConfig("enchants"));
    }

    public static void enchant(ItemStack itemStack, MaterialGroup group, Tier tier){
        if(dictionary == null){
            ChatUtils.warn("Enchantment dictionary not initialised.");
            return;
        }
        int enchants = dictionary.getRandomNumberOfEnchants(group, tier);


        for(int i = 0; i < enchants; i++){
            dictionary.generateEnchantment(group, tier).ifPresent(e -> e.enchant(itemStack));
        }
    }

    public static Optional<EnchantmentApplication> generateEnchantment(MaterialGroup material, Tier tier) {
        if(dictionary == null){
            ChatUtils.warn("Enchantment dictionary not initialised.");
            return Optional.empty();
        }
        return dictionary.generateEnchantment(material, tier);
    }

    public static Optional<EnchantTierConfig> lookup(MaterialGroup material, Tier tier) {
        if(dictionary == null){
            ChatUtils.warn("Enchantment dictionary not initialised.");
            return Optional.empty();
        }
        return dictionary.lookup(material, tier);
    }
}
