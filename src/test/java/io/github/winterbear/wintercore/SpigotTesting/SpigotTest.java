package io.github.winterbear.wintercore.SpigotTesting;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemFactory;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.mock;

import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

/**
 * Created by WinterBear on 30/09/2018.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Bukkit.class)
public class SpigotTest<T> {

    public SpigotTest(){
        this.mockItemFactory();
    }

    protected void mockItemFactory(){
        mockStatic(Bukkit.class);
        ItemFactory itemFactory = mock(ItemFactory.class);
        when(Bukkit.getItemFactory()).thenReturn(itemFactory);
        when(itemFactory.getItemMeta(any())).thenReturn(new MockItemMeta());
    }

}
