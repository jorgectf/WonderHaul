package io.github.winterbear.wintercore.wonderhaul.equipment.enchanting;

import io.github.winterbear.WinterCoreUtils.ChatUtils;
import io.github.winterbear.wintercore.utils.RandomUtils;
import io.github.winterbear.wintercore.wonderhaul.equipment.MaterialGroup;
import io.github.winterbear.wintercore.wonderhaul.equipment.Tier;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by WinterBear on 17/06/2019.
 */
public class EnchantmentDictionary {

    public Map<MaterialGroup, Map<Tier, EnchantTierConfig>> dictionary = new HashMap<>();

    public EnchantmentDictionary(YamlConfiguration enchantConfig){
        for(MaterialGroup group : MaterialGroup.values()){
            String material = group.name().toLowerCase();
            Map<Tier, EnchantTierConfig> configs = new HashMap<>();
            for(Tier tier : Tier.values()){
                if(tier.isEnchantable()){
                    EnchantTierConfig tierConfig = new EnchantTierConfig();
                    String tierName = tier.name().toLowerCase();
                    int minEnchants = enchantConfig.getInt(material + "." + tierName + ".minenchants");
                    int maxEnchants = enchantConfig.getInt(material + "." + tierName + ".maxenchants");
                    List<EnchantConfig> enchants = new ArrayList<>();
                            Optional.ofNullable(enchantConfig.getList(material + "." + tierName + ".enchantments")).ifPresent(
                            e -> enchants.addAll(e.stream().map(m -> EnchantConfig.deserialize((Map<String, Object>) m)).collect(Collectors.toList())));
                    tierConfig.setMaxEnchants(maxEnchants);
                    tierConfig.setMinEnchants(minEnchants);
                    tierConfig.setEnchantConfigs(enchants);
                    configs.put(tier, tierConfig);
                }
            }

            dictionary.put(group, configs);
        }
    }

    public Optional<EnchantmentApplication> generateEnchantment(MaterialGroup material, Tier tier){
        Optional<EnchantTierConfig> config = lookup(material, tier);
        if(config.isPresent()){
            return Optional.of(config.get().generateEnchantment());
        }
        ChatUtils.warn("Could not generate an enchantment application for " + tier + " " + material);
        return Optional.empty();
    }

    public Optional<EnchantTierConfig> lookup(MaterialGroup material, Tier tier){
        if(dictionary.containsKey(material)){
            Map<Tier, EnchantTierConfig> tierConfigs = dictionary.get(material);
            if(tierConfigs != null && tierConfigs.containsKey(tier)){
                return Optional.ofNullable(tierConfigs.get(tier));
            }
        }
        return Optional.empty();
    }

    public Optional<Integer> getMaxEnchants(MaterialGroup mat, Tier tier){
        return Optional.of(dictionary.get(mat).get(tier).getMaxEnchants());
    }

    public Optional<Integer> getMinEnchants(MaterialGroup mat, Tier tier){
        return Optional.of(dictionary.get(mat).get(tier).getMinEnchants());
    }

    public Integer getRandomNumberOfEnchants(MaterialGroup mat, Tier tier){
        Optional<Integer> maxEnchants = getMaxEnchants(mat, tier);
        Optional<Integer> minEnchants = getMinEnchants(mat, tier);
        if(maxEnchants.isPresent() && minEnchants.isPresent()){
            return RandomUtils.getIntegerBetween(minEnchants.get(), maxEnchants.get());
        }
        return 0;
    }

}
