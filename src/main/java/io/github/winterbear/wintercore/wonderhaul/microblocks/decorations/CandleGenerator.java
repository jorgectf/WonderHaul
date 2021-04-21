package io.github.winterbear.wintercore.wonderhaul.microblocks.decorations;

import io.github.winterbear.wintercore.utils.ColorLoreMode;
import io.github.winterbear.wintercore.utils.ItemBuilder;
import io.github.winterbear.wintercore.utils.TexturedHead;
import io.github.winterbear.wintercore.utils.TexturedHeads;
import io.github.winterbear.wintercore.wonderhaul.ItemCategory;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Created by WinterBear on 16/04/2021.
 */
public class CandleGenerator {

    public enum Candles {

        ARCTIC_CANDLE("Arctic Candle", TexturedHeads.WHITE_CANDLE, Material.WHITE_DYE, "#ffffff",
                "The scent from this candle reminds you of icy mountain air"),
        DUNE_CANDLE("Dune Candle", TexturedHeads.ORANGE_CANDLE, Material.ORANGE_DYE, "#ff8533",
                "The scent from this candle reminds you of the burning heat of the desert"),
        SOUL_CANDLE("Soul Candle", TexturedHeads.MAGENTA_CANDLE, Material.MAGENTA_DYE, "#fc33ff",
                "The scent from this candle reminds you of the mysteries of life"),
        ZEPHYR_CANDLE("Zephyr Candle", TexturedHeads.LIGHT_BLUE_CANDLE, Material.LIGHT_BLUE_DYE, "#b3fffc",
                "The scent from this candle reminds you of a light breeze on a summers day"),
        COFFEE_CANDLE("Coffee Candle", TexturedHeads.BROWN_CANDLE, Material.BROWN_DYE, "#7a4206",
                "The scent from this candle reminds you of the feeling of waking up in the morning"),
        HONEY_CANDLE("Honey Candle", TexturedHeads.YELLOW_CANDLE, Material.YELLOW_DYE, "#f5c425",
                "The scent from this candle reminds you of a sweet treat from your childhood"),
        MEADOW_CANDLE("Meadow Candle", TexturedHeads.LIGHT_GREEN_CANDLE, Material.LIME_DYE, "#baf525",
                "The scent from this candle reminds you of flowers in spring"),
        CANDYFLOSS_CANDLE("Candyfloss Candle", TexturedHeads.PINK_CANDLE, Material.PINK_DYE, "#ffcff5",
                "The scent from this candle reminds you of soft fluffy clouds"),
        SLATE_CANDLE("Slate Candle", TexturedHeads.GREY_CANDLE, Material.GRAY_DYE, "#666666",
                "The scent from this candle reminds you of harsh truths"),
        MARBLE_CANDLE("Marble Candle", TexturedHeads.LIGHT_GREY_CANDLE, Material.LIGHT_GRAY_DYE, "#bababa",
                "The scent from this candle reminds you of the sound of rain on an overcast day"),
        SEAFOAM_CANDLE("Seafoam Candle", TexturedHeads.AQUA_CANDLE, Material.CYAN_DYE, "#00ffea",
                "The scent from this candle reminds you of the sound of waves on the shore"),
        REGAL_CANDLE("Regal Candle", TexturedHeads.PURPLE_CANDLE, Material.PURPLE_DYE, "#aa00ff",
                "The scent from this candle reminds you of a feeling of superiority"),
        TIDAL_CANDLE("Tidal Candle", TexturedHeads.BLUE_CANDLE, Material.BLUE_DYE, "#006eff",
                "The scent from this candle reminds you of the open ocean"),
        FOREST_CANDLE("Forest Candle", TexturedHeads.GREEN_CANDLE, Material.GREEN_DYE, "#008c07",
                "The scent from this candle reminds you of sunbeams passing through a leafy canopy"),
        RUBY_CANDLE("Ruby Candle", TexturedHeads.RED_CANDLE, Material.RED_DYE, "#bd002f",
                "The scent from this candle reminds you of treasure lying deep under the earth"),
        MIDNIGHT_CANDLE("Midnight Candle", TexturedHeads.BLACK_CANDLE, Material.BLACK_DYE, "#3a3a52",
                "The scent from this candle reminds you of the vast emptiness of space");

        private final Material dye;

        private final TexturedHead texture;

        private final String displayName;

        private final String hexColor;

        private final String description;

        Candles(String displayName, TexturedHead texture, Material dye, String hexColor, String description){
            this.displayName = displayName;
            this.texture = texture;
            this.dye = dye;
            this.hexColor = hexColor;
            this.description = description;
        }

        public Material getDye() {
            return dye;
        }

        public TexturedHead getTexture() {
            return texture;
        }

        public String getDisplayName() {
            return displayName;
        }

        public String getHexColor() {
            return hexColor;
        }

        public String getDescription() {
            return description;
        }
    }

    public static ItemStack generate(Candles candle){
        return ItemBuilder.newMicroblock("Candle",
                ItemCategory.DECORATION,
                ChatColor.of(candle.getHexColor()),
                ColorLoreMode.BRIGHTER,
                candle.getTexture())
                .withDescription(candle.getDescription())
                .withDisplayName(candle.getDisplayName())
                .withUsage("Lights up an area. Right click while placed to turn on or off.")
                .build();
    }

}
