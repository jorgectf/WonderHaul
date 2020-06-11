package io.github.winterbear.wintercore.wonderhaul.Equipment.Enchanting;

import io.github.winterbear.WinterCoreUtils.ChatUtils;
import io.github.winterbear.wintercore.utils.RandomUtils;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.enchantments.Enchantment;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by WinterBear on 17/06/2019.
 */
@SerializableAs("EnchantConfig")
public class EnchantConfig implements ConfigurationSerializable {

    private Enchantment enchantment;
    private Integer minLevel;
    private Integer maxLevel;

    public EnchantConfig(Map<String, Object> initialiser){
        Optional<String> enchantment = initialiser.keySet().stream().findFirst();
        if(enchantment.isPresent()){
            this.enchantment = EnchantMap.get(enchantment.get());
            Map<String, Object> properties = (Map<String, Object>) initialiser.get(enchantment.get());
            this.minLevel = (Integer) properties.get("minlevel");
            this.maxLevel = (Integer) properties.get("maxlevel");
        } else {
            ChatUtils.error("Invalid configuration. EnchantConfig not initialised.");
        }

    }

    public Enchantment getEnchantment() {
        return enchantment;
    }

    public void setEnchantment(Enchantment enchantment) {
        this.enchantment = enchantment;
    }

    public int getMinLevel() {
        return minLevel;
    }

    public void setMinLevel(int minLevel) {
        this.minLevel = minLevel;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    public EnchantmentApplication generateEnchantment(){

        return new EnchantmentApplication(RandomUtils.getIntegerBetween(minLevel, maxLevel), enchantment);
    }

    public static EnchantConfig deserialize(Map<String, Object> map){
        return new EnchantConfig(map);
    }

    public static EnchantConfig valueOf(Map<String, Object> map){
        return new EnchantConfig(map);
    }

    @Override
    public Map<String, Object> serialize() {
        LinkedHashMap<String, Object> serialized = new LinkedHashMap<>();
        serialized.put("enchantment", EnchantMap.get(enchantment));
        serialized.put("minlevel", minLevel);
        serialized.put("maxlevel", maxLevel);
        return serialized;
    }
}
