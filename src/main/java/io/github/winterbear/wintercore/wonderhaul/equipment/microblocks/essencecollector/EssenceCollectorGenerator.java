package io.github.winterbear.wintercore.wonderhaul.equipment.microblocks.essencecollector;

import io.github.winterbear.WinterCoreUtils.ChatUtils;
import io.github.winterbear.wintercore.utils.ItemBuilder;
import io.github.winterbear.wintercore.utils.LoreUtils;
import io.github.winterbear.wintercore.utils.TexturedHeads;
import io.github.winterbear.wintercore.wonderhaul.equipment.Tier;
import io.github.winterbear.wintercore.wonderhaul.equipment.generators.Generator;
import org.bukkit.inventory.ItemStack;

/**
 * Created by WinterBear on 15/08/2020.
 */
public class EssenceCollectorGenerator implements Generator {

    private static final Tier tier = Tier.ASCENDED;

    @Override
    public ItemStack create() {
        ItemStack collector = TexturedHeads.ESSENCE_COLLECTOR.get();

        String displayName = ChatUtils.format(tier.getColor() + "Essence Collector");
        String lore = tier.getColouredLore("Artifact", "Essence Collector");
        String loreText1 = "  &7&oSacrifice items to";
        String loreText2 = "  &7&oupgrade your tools";
        String loreText3 = "  &7&oto a new tier";

        LoreUtils.addLoreLine(collector, lore);
        LoreUtils.addLoreLine(collector, "");
        LoreUtils.addLoreLine(collector, loreText1);
        LoreUtils.addLoreLine(collector, loreText2);
        LoreUtils.addLoreLine(collector, loreText3);
        LoreUtils.addLoreLine(collector, "");

        ItemBuilder.setDisplayName(collector, displayName);

        return collector;
    }

    @Override
    public String getName() {
        return "Essence Collector Generator";
    }
}
