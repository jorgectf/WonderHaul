package io.github.winterbear.wintercore.wonderhaul.Equipment.Enchanting;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

/**
 * Created by WinterBear on 17/06/2019.
 */
public class EnchantmentApplication {

    private int level;
    private Enchantment enchantment;

    public EnchantmentApplication(int level, Enchantment enchantment) {
        if(enchantment == null){
            throw new IllegalArgumentException("Enchantment cannot be null.");
        }
        this.level = level;
        this.enchantment = enchantment;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Enchantment getEnchantment() {
        return enchantment;
    }

    public void setEnchantment(Enchantment enchantment) {
        this.enchantment = enchantment;
    }

    public void enchant(ItemStack item){
        if(item.containsEnchantment(enchantment)){
            if(item.getEnchantmentLevel(enchantment) >= level){
                return;
            }
        } else {
            item.addUnsafeEnchantment(enchantment, level);
        }
    }
}
