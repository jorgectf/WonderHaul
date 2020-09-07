package io.github.winterbear.wintercore.wonderhaul.equipment.microblocks.relic;

import io.github.winterbear.WinterCoreUtils.ChatUtils;
import io.github.winterbear.wintercore.utils.ItemBuilder;
import io.github.winterbear.wintercore.utils.LoreUtils;
import io.github.winterbear.wintercore.utils.TexturedHeads;
import io.github.winterbear.wintercore.wonderhaul.equipment.generators.Generator;
import io.github.winterbear.wintercore.wonderhaul.equipment.Tier;
import org.bukkit.inventory.ItemStack;

/**
 * Created by WinterBear on 24/05/2020.
 */
public class RelicGenerator implements Generator {

    private static final Tier tier = Tier.MYSTERIOUS;


    @Override
    public ItemStack create() {

        ItemStack relic = TexturedHeads.RELIC.get();

        String displayName = ChatUtils.format(tier.getColor() + "Relic");
        String lore = tier.getColouredLore("Artifact", "Relic");
        String loreText1 = "  &7&oSacrifice items for";
        String loreText2 = "  &7&oa chance to get a better";
        String loreText3 = "  &7&oitem";

        LoreUtils.addLoreLine(relic, lore);
        LoreUtils.addLoreLine(relic, "");
        LoreUtils.addLoreLine(relic, loreText1);
        LoreUtils.addLoreLine(relic, loreText2);
        LoreUtils.addLoreLine(relic, loreText3);
        LoreUtils.addLoreLine(relic, "");

        ItemBuilder.setDisplayName(relic, displayName);


        return relic;
    }

    @Override
    public String getName() {
        return "Relic Generator";
    }
}
