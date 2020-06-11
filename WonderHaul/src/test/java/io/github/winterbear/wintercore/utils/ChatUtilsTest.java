package io.github.winterbear.wintercore.utils;

import io.github.winterbear.WinterCoreUtils.ChatUtils;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ChatUtilsTest {

    @Test
    public void uncolored() {
        assertThat(ChatUtils.uncolored("&7Test §bThat &kThis §3Works")).isEqualTo("Test That This Works");
    }
}