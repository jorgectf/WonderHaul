package io.github.winterbear.wintercore.wonderhaul.Equipment.Enchanting;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import io.github.winterbear.WinterCoreUtils.ChatUtils;
import org.bukkit.enchantments.Enchantment;

/**
 * Created by WinterBear on 17/06/2019.
 */
public class EnchantMap {

    public static BiMap<String, Enchantment> enchantments = getEnchants();

    public static BiMap getEnchants(){

        BiMap<String, Enchantment> enchantments = HashBiMap.create();
        enchantments.put("sharpness", Enchantment.DAMAGE_ALL);
        enchantments.put("bane", Enchantment.DAMAGE_ARTHROPODS);
        enchantments.put("knockback", Enchantment.KNOCKBACK);
        enchantments.put("efficiency", Enchantment.DIG_SPEED);
        enchantments.put("smite", Enchantment.DAMAGE_UNDEAD);
        enchantments.put("looting", Enchantment.LOOT_BONUS_MOBS);
        enchantments.put("unbreaking", Enchantment.DURABILITY);
        enchantments.put("fortune", Enchantment.LOOT_BONUS_BLOCKS);
        enchantments.put("fireaspect", Enchantment.FIRE_ASPECT);
        enchantments.put("silktouch", Enchantment.SILK_TOUCH);
        enchantments.put("mending", Enchantment.MENDING);
        enchantments.put("protection", Enchantment.PROTECTION_ENVIRONMENTAL);
        enchantments.put("fireprotection", Enchantment.PROTECTION_FIRE);
        enchantments.put("projectileprotection", Enchantment.PROTECTION_PROJECTILE);
        enchantments.put("blastprotection", Enchantment.PROTECTION_EXPLOSIONS);
        enchantments.put("featherfalling", Enchantment.PROTECTION_FALL);
        enchantments.put("thorns", Enchantment.THORNS);
        enchantments.put("punch", Enchantment.ARROW_KNOCKBACK);
        enchantments.put("power", Enchantment.ARROW_DAMAGE);
        enchantments.put("flame", Enchantment.ARROW_FIRE);
        enchantments.put("infinity", Enchantment.ARROW_INFINITE);
        enchantments.put("respiration", Enchantment.OXYGEN);
        enchantments.put("aquaaffinity", Enchantment.WATER_WORKER);
        enchantments.put("luckofthesea", Enchantment.LUCK);
        enchantments.put("lure", Enchantment.LURE);
        enchantments.put("sweepingedge", Enchantment.SWEEPING_EDGE);
        enchantments.put("impaling", Enchantment.IMPALING);
        enchantments.put("loyalty", Enchantment.LOYALTY);
        enchantments.put("riptide", Enchantment.RIPTIDE);
        enchantments.put("channelling", Enchantment.CHANNELING);
        enchantments.put("piercing", Enchantment.PIERCING);
        enchantments.put("multishot", Enchantment.MULTISHOT);
        enchantments.put("quickcharge", Enchantment.QUICK_CHARGE);
        enchantments.put("binding", Enchantment.BINDING_CURSE);
        enchantments.put("vanishing", Enchantment.VANISHING_CURSE);
        enchantments.put("depthstrider", Enchantment.DEPTH_STRIDER);
        enchantments.put("frostwalker", Enchantment.FROST_WALKER);
        return enchantments;

    }

    public static Enchantment get(String name){
        if(!enchantments.containsKey(name)){
            ChatUtils.warn("Unexpected Enchantment. Could not recognise " + name);
        }
        return enchantments.get(name);
    }

    public static String get(Enchantment enchant){
        return enchantments.inverse().get(enchant);
    }



}
