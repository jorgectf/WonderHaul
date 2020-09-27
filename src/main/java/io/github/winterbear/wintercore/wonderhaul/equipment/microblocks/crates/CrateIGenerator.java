package io.github.winterbear.wintercore.wonderhaul.equipment.microblocks.crates;

import io.github.winterbear.WinterCoreUtils.ChatUtils;
import io.github.winterbear.wintercore.utils.ItemBuilder;
import io.github.winterbear.wintercore.utils.LoreUtils;
import io.github.winterbear.wintercore.utils.TexturedHeads;
import io.github.winterbear.wintercore.wonderhaul.equipment.Tier;
import io.github.winterbear.wintercore.wonderhaul.equipment.generators.Generator;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.inventory.ItemStack;

import java.awt.*;

/**
 * Created by WinterBear on 18/09/2020.
 */
public class CrateIGenerator implements Generator {

    private static final Tier tier = Tier.UNUSUAL;

    @Override
    public ItemStack create() {
        ItemStack crate = TexturedHeads.CRATE_I.get();

        String displayName = ChatUtils.format(tier.getColor() + ChatUtils.format("&lCrate I"));
        String lore = tier.getColouredLore("Crate", "Tier I");
        String description = "&7A mysterious crate containing some money, an item tag, and a random piece of equipment.";
        String usage = "Place and then right click to open, drops a selection of items";

        Color color = tier.getColor().getColor();
        ChatColor loreColor = ChatColor.of(color.darker());

        LoreUtils.addLoreLine(crate, lore);
        LoreUtils.addBlankLine(crate);
        LoreUtils.addMultiLineLore(crate, description);
        LoreUtils.addBlankLine(crate);
        LoreUtils.addMultiLineLore(crate, loreColor, usage);

        ItemBuilder.setDisplayName(crate, displayName);

        return crate;
    }

    @Override
    public String getName() {
        return "Crate I Generator";
    }
}
