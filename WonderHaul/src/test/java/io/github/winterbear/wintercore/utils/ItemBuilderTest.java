package io.github.winterbear.wintercore.utils;

import io.github.winterbear.wintercore.SpigotTesting.SpigotTest;
import org.assertj.core.api.SoftAssertions;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.junit.Test;
import io.github.winterbear.wintercore.utils.ItemBuilder;


import static org.assertj.core.api.Assertions.*;

/**
 * Created by WinterBear on 30/09/2018.
 */
public class ItemBuilderTest extends SpigotTest {

    @Test
    public void testBuildItem_nameAndMat(){
        String expectedName = "Thermal Indicator";
        Material expectedMaterial = Material.TRIPWIRE_HOOK;
        ItemStack result = ItemBuilder.createItem(expectedName, expectedMaterial);

        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(result.getItemMeta().getDisplayName()).isEqualTo(expectedName);
        softly.assertThat(result.getType()).isEqualTo(expectedMaterial);

        softly.assertAll();

    }

    @Test
    public void testBuildItem_nameLoreAndMat(){
        String expectedName = "Thermal Indicator";
        Material expectedMaterial = Material.TRIPWIRE_HOOK;
        String lore1 = "Test1";
        String lore2 = "Test2";
        ItemStack result = ItemBuilder.createItem(expectedName,  lore1 + "||" + lore2, expectedMaterial);

        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(result.getItemMeta().getDisplayName()).isEqualTo(expectedName);
        softly.assertThat(result.getItemMeta().getLore()).contains(lore1, lore2);
        softly.assertThat(result.getType()).isEqualTo(expectedMaterial);

        softly.assertAll();

    }

    @Test
    public void testBuildItem_loreColors(){
        String expectedName = "Thermal Indicator";
        Material expectedMaterial = Material.TRIPWIRE_HOOK;
        String lore1 = "§bTest1";
        String lore2 = "§cTest2";
        ItemStack result = ItemBuilder.createItem(expectedName, "&bTest1||&cTest2", expectedMaterial);
        assertThat(result.getItemMeta().getLore()).contains(lore1, lore2);
    }

}