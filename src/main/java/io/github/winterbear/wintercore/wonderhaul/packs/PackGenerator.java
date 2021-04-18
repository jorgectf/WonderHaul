package io.github.winterbear.wintercore.wonderhaul.packs;

import io.github.winterbear.WinterCoreUtils.ChatUtils;
import io.github.winterbear.wintercore.utils.*;
import io.github.winterbear.wintercore.wonderhaul.ItemCategory;
import io.github.winterbear.wintercore.wonderhaul.equipment.Tier;
import io.github.winterbear.wintercore.wonderhaul.equipment.generators.Generator;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

/**
 * Created by WinterBear on 26/05/2020.
 */
public class PackGenerator implements Generator {

    public enum Packs {

        SMALL_EXP_HAUL("&6Small &6E&exp&ae&frie&an&ece &6Haul", Tier.RARE, TexturedHeads.EXPERIENCE_HAUL,
                "Contains a modest amount of experience"),
        MEDIUM_EXP_HAUL("&6Medium &6E&exp&ae&frie&an&ece &6Haul", Tier.RARE, TexturedHeads.EXPERIENCE_HAUL,
                "Contains a reasonable amount of experience"),
        LARGE_EXP_HAUL("&6Large &6E&exp&ae&frie&an&ece &6Haul", Tier.RARE, TexturedHeads.EXPERIENCE_HAUL,
                "Contains a decent amount of experience"),
        COLOSSAL_EXP_HAUL("&6Colossal &6E&exp&ae&frie&an&ece &6Haul", Tier.RARE, TexturedHeads.EXPERIENCE_HAUL,
                "Contains a massive amount of experience"),
        SMALL_GOLD_HAUL("&6Small &6Go&el&fd H&ea&6ul", Tier.RARE, TexturedHeads.GOLD_HAUL,
                "Contains a modest amount of money"),
        MEDIUM_GOLD_HAUL("&6Medium &6Go&el&fd H&ea&6ul", Tier.RARE, TexturedHeads.GOLD_HAUL,
                "Contains a reasonable amount of money"),
        LARGE_GOLD_HAUL("&6Large &6Go&el&fd H&ea&6ul", Tier.RARE, TexturedHeads.GOLD_HAUL,
                "Contains a decent amount of money"),
        COLOSSAL_GOLD_HAUL("&6Colossal &6Go&el&fd H&ea&6ul", Tier.RARE, TexturedHeads.GOLD_HAUL,
                "Contains a massive amount of money");

        private final String name;

        private final Tier tier;

        private final TexturedHead texture;

        private final String description;

        Packs(String name, Tier tier, TexturedHead texture, String description){
            this.name = name;
            this.tier = tier;
            this.texture = texture;
            this.description = description;
        }


        public static Packs getRandom(){
            return RandomUtils.getRandomElementOf((Arrays.asList(Packs.values())));
        }

        public ItemStack generate(){

            return ItemBuilder.newMicroblock(ChatUtils.uncolored(name),
                    ItemCategory.PACK,
                    ChatColor.GOLD,
                    ColorLoreMode.SAME,
                    texture)
                    .withDisplayName(name)
                    .withDescription(description)
                    .withUsage("Right click to use.")
                    .build();
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
