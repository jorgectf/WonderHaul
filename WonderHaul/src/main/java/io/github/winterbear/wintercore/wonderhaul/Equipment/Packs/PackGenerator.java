package io.github.winterbear.wintercore.wonderhaul.Equipment.Packs;

import io.github.winterbear.WinterCoreUtils.ChatUtils;
import io.github.winterbear.wintercore.utils.ItemBuilder;
import io.github.winterbear.wintercore.utils.LoreUtils;
import io.github.winterbear.wintercore.utils.RandomUtils;
import io.github.winterbear.wintercore.utils.TexturedHeads;
import io.github.winterbear.wintercore.wonderhaul.Equipment.Generators.Generator;
import io.github.winterbear.wintercore.wonderhaul.Equipment.Tier;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.function.Supplier;

/**
 * Created by WinterBear on 26/05/2020.
 */
public class PackGenerator implements Generator {

    private enum Packs{

        EXP_HAUL("&6E&exp&ae&frie&an&ece &6Haul", Tier.RARE, TexturedHeads.EXPERIENCE_HAUL),
        GOLD_HAUL("&6Go&el&fd H&ea&6ul", Tier.RARE, TexturedHeads.GOLD_HAUL);

        private String name;

        private Tier tier;

        private Supplier<ItemStack> texture;

        Packs(String name, Tier tier, Supplier<ItemStack> texture){
            this.name = name;
            this.tier = tier;
            this.texture = texture;
        }


        public static Packs getRandom(){
            return RandomUtils.getRandomElementOf((Arrays.asList(Packs.values())));
        }

        public ItemStack generate(){

            ItemStack item = texture.get();
            ItemBuilder.setDisplayName(item, name);
            LoreUtils.addLoreLine(item, tier.getColouredLore("&d⚒","Pack", tier.getColorCode() + ChatUtils.uncolored(name)));
            return item;
        }

    }

    @Override
    public ItemStack create() {
        return Packs.getRandom().generate();
    }

    @Override
    public String getName() {
        return "Pack Generator";
    }
}
