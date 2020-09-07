package io.github.winterbear.wintercore.utils;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LoreUtilsTest {

    @Test
    public void testMultilineLore(){

        String test = "&7This is a very long line of text that uses &6Different &bColors &7and also &kDifferent Formats &7hi.";
        List<String> result = LoreUtils.getMultiline(test, 20, 0);
        result.forEach(r -> System.out.println(r));
        assertThat(result.get(0).startsWith("§7"));
        assertThat(result.get(1).startsWith("§7"));
        assertThat(result.get(2).startsWith("§7"));
        assertThat(result.get(3).startsWith("§b"));
        assertThat(result.get(4).startsWith("§7&k"));

    }

    @Test
    public void testMultilineLore_2(){

        String test = "&7A symbol of an eldritch god is carved into it";
        List<String> result = LoreUtils.getMultiline(test, 20, 0);
        result.forEach(r -> System.out.println(r));

    }

}