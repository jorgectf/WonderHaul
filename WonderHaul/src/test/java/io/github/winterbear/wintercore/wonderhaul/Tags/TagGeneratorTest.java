package io.github.winterbear.wintercore.wonderhaul.Tags;

import io.github.winterbear.WinterCoreUtils.ChatUtils;
import io.github.winterbear.wintercore.SpigotTesting.SpigotTest;
import org.assertj.core.api.SoftAssertions;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class TagGeneratorTest extends SpigotTest {

    @Test
    public void generate() {
        SoftAssertions softly = new SoftAssertions();
        ItemStack tag = TagGenerator.generate(new RepairTag());
        softly.assertThat(tag.getType()).isEqualTo(Material.NAME_TAG);
        softly.assertThat(tag.getItemMeta().getDisplayName()).isEqualTo(ChatUtils.format("&aRepair Tag"));
        softly.assertThat(tag.getItemMeta().getLore()).containsExactly(ChatUtils.format("&7Tag&8: &7Repair Tag"));
        softly.assertAll();
    }
}