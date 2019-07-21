package io.github.winterbear.wintercore.wonderhaul.Equipment.Enchanting;

import io.github.winterbear.wintercore.utils.RandomUtils;

import java.util.List;

/**
 * Created by WinterBear on 17/06/2019.
 */
public class EnchantTierConfig {

    private Integer maxEnchants;
    private Integer minEnchants;
    private List<EnchantConfig> enchantConfigs;


    public int getMaxEnchants() {
        return maxEnchants;
    }

    public void setMaxEnchants(int maxEnchants) {
        this.maxEnchants = maxEnchants;
    }

    public int getMinEnchants() {
        return minEnchants;
    }

    public void setMinEnchants(int minEnchants) {
        this.minEnchants = minEnchants;
    }

    public List<EnchantConfig> getEnchantConfigs() {
        return enchantConfigs;
    }

    public void setEnchantConfigs(List<EnchantConfig> enchantConfigs) {
        this.enchantConfigs = enchantConfigs;
    }

    public EnchantmentApplication generateEnchantment(){

        return RandomUtils.getRandomElementOf(enchantConfigs).generateEnchantment();
    }

}
