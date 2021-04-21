package io.github.winterbear.wintercore.wonderhaul.equipment.generators;

import io.github.winterbear.WinterCoreUtils.ChatUtils;
import io.github.winterbear.wintercore.utils.ItemBuilder;
import io.github.winterbear.wintercore.wonderhaul.equipment.ItemNames;
import io.github.winterbear.wintercore.wonderhaul.equipment.MaterialGroup;
import io.github.winterbear.wintercore.wonderhaul.equipment.Prefixes;
import io.github.winterbear.wintercore.wonderhaul.equipment.Tier;
import io.github.winterbear.wintercore.wonderhaul.equipment.enchanting.Enchantments;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

/**
 * Created by WinterBear on 17/06/2019.
 */
public class GeneratorCommons {


    public static Optional<String> generateName(MaterialGroup group, Tier tier){
        Optional<String> prefix = Prefixes.generatePrefix(tier);
        Optional<String> itemName = ItemNames.getItemName(group);
        if(prefix.isPresent() && itemName.isPresent()){
            return Optional.of(ChatUtils.format(tier.getColor() + prefix.get() + " " + itemName.get()));
        }
        return Optional.empty();
    }

    public static ItemStack generateDefault(Material mat, Tier tier){
        MaterialGroup group = MaterialGroup.fromMaterial(mat);
        Optional<String> name = GeneratorCommons.generateName(group, tier);
        if(name.isPresent()){
            ItemStack item = ItemBuilder.createItem(name.get(), tier.getTierLore(mat), mat);
            Enchantments.enchant(item, group, tier);
            return item;
        }
        ChatUtils.warn("WonderHaul has either failed to load or an invalid configuration has been set: Could not load item names or prefixes for " + tier + " " + group);
        return new ItemStack(mat);
    }

    public static ItemStack generateWithLore(Material mat, Tier tier){
        ItemStack item = generateDefault(mat, tier);

        //LoreUtils.addMultiLineLore();
        //TODO
        return item;
    }


}
