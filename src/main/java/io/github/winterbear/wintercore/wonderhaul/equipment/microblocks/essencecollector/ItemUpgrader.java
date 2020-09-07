package io.github.winterbear.wintercore.wonderhaul.equipment.microblocks.essencecollector;

import io.github.winterbear.WinterCoreUtils.ChatUtils;
import io.github.winterbear.wintercore.utils.*;
import io.github.winterbear.wintercore.wonderhaul.equipment.MaterialGroup;
import io.github.winterbear.wintercore.wonderhaul.equipment.Tier;
import io.github.winterbear.wintercore.wonderhaul.equipment.enchanting.EnchantConfig;
import io.github.winterbear.wintercore.wonderhaul.equipment.enchanting.EnchantTierConfig;
import io.github.winterbear.wintercore.wonderhaul.equipment.enchanting.Enchantments;
import io.github.winterbear.wintercore.wonderhaul.sockets.SocketType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by WinterBear on 28/08/2020.
 */
public class ItemUpgrader {

    public static ItemStack upgrade(ItemStack item, Tier tier){
        ItemStack newItem = ItemUtils.oneOf(item);
        Tier newTier = Tier.upgrade(tier);
        Optional<EnchantTierConfig> config = Enchantments.lookup(MaterialGroup.fromMaterial(newItem.getType()), newTier);
        if(config.isPresent()){
            int max = config.get().getMaxEnchants();
            int min = config.get().getMinEnchants();
            int numEnchants = RandomUtils.getIntegerBetween(min, max);
            int current = newItem.getEnchantments().keySet().size();
            int newEnchants = numEnchants - current;
            Map<Enchantment, Integer> possibleUpgrades = calculateUpgradeChances(newItem, tier, newTier);
            int maxUpgrades = possibleUpgrades.values().stream()
                    .reduce(0, Integer::sum);
            int upgradechances = Math.min(maxUpgrades, 5);
            if(newEnchants > 0) {
                List<Enchantment> possibleEnchantments = config.get()
                        .getEnchantConfigs().stream()
                        .map(EnchantConfig::getEnchantment)
                        .filter(e -> !newItem.getEnchantments().keySet().contains(e))
                        .collect(Collectors.toList());
                for(int i = 0; i < 3; i++){
                    if(!possibleEnchantments.isEmpty() && i < newEnchants){
                        Enchantment newEnchantment = RandomUtils.getRandomElementOf(possibleEnchantments);
                        Optional<EnchantConfig> econf = config.get().getEnchantConfigs()
                                .stream()
                                .filter(e -> e.getEnchantment().equals(newEnchantment))
                                .findFirst();
                        if(econf.isPresent()){
                            econf.get().generateEnchantment().enchant(newItem);
                            possibleEnchantments.remove(newEnchantment);
                            upgradechances=- 1;
                        }
                    }
                }

            }
            while(upgradechances-- > 0 && !possibleUpgrades.isEmpty()){
                Enchantment enchantment = RandomUtils.getRandomElementOf(possibleUpgrades.keySet());
                EnchantmentUtils.boost(newItem, enchantment, newItem.getItemMeta().getEnchantLevel(enchantment));
                int remainingBoosts = possibleUpgrades.get(enchantment) - 1;
                if(remainingBoosts < 1){
                    possibleUpgrades.remove(enchantment);
                } else {
                    possibleUpgrades.put(enchantment, remainingBoosts);
                }
            }
        }

        String oldName = ChatUtils.uncolored(newItem.getItemMeta().getDisplayName());
        ItemBuilder.setDisplayName(newItem, newTier.getColor() + oldName);
        LoreUtils.changeTier(newItem, newTier);
        if(newTier == Tier.ASCENDED){
            if(MaterialGroup.ARMOR.contains(MaterialGroup.fromMaterial(newItem.getType()))){
                LoreUtils.addEmptySocket(newItem, SocketType.ORNAMENT);
            } else {
                LoreUtils.addEmptySocket(newItem, SocketType.INFUSION);
            }
        }
        return newItem;
    }

    private static Map<Enchantment, Integer> calculateUpgradeChances(ItemStack item, Tier tier, Tier newTier){
        Optional<EnchantTierConfig> config = Enchantments.lookup(MaterialGroup.fromMaterial(item.getType()), newTier);
        Map<Enchantment, Integer> currentEnchants = item.getEnchantments();
        Map<Enchantment, Integer> availableUpgrades = new HashMap<>();
        if(config.isPresent()){
            for (Enchantment enchantment : item.getEnchantments().keySet()){
                int currentLevel = currentEnchants.get(enchantment);
                int max = config.get().getEnchantConfigs().stream()
                        .filter(e -> e.getEnchantment().equals(enchantment))
                        .map(e -> e.getMaxLevel())
                        .findFirst().orElse(0);
                if (max > currentLevel){
                    availableUpgrades.put(enchantment, max - currentLevel);
                }
            }
        }

        return availableUpgrades;



    }
}
