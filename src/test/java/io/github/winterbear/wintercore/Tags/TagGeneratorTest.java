package io.github.winterbear.wintercore.Tags;

import io.github.winterbear.wintercore.SpigotTesting.SpigotTest;
import io.github.winterbear.wintercore.utils.ChatUtils;
import org.assertj.core.api.SoftAssertions;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.junit.Test;

import static org.junit.Assert.*;

public class TagGeneratorTest extends SpigotTest {

    @Test
    public void generate() {
        SoftAssertions softly = new SoftAssertions();
        ItemStack tag = TagGenerator.generate(new RepairTag());
        softly.assertThat(tag.getType()).isEqualTo(Material.NAME_TAG);
        softly.assertThat(tag.getItemMeta().getDisplayName()).isEqualTo("&aRepair Tag");
        softly.assertThat(tag.getItemMeta().getLore()).containsExactly(ChatUtils.convert("&7Tag&8: &7Repair Tag"));
        softly.assertAll();
    }
}