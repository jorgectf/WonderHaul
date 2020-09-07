package io.github.winterbear.wintercore.utils;

import io.github.winterbear.WinterCoreUtils.ChatUtils;
import net.md_5.bungee.api.ChatColor;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ChatUtilsTest {

    @Test
    public void uncolored() {
        assertThat(ChatUtils.uncolored("&7Test §bThat &kThis §3Works")).isEqualTo("Test That This Works");
    }

    @Test
    public void uncoloredHex() {
        assertThat(ChatUtils.uncolored(ChatColor.of("#eb236a") + "Test That This Works")).isEqualTo("Test That This Works");
    }
}