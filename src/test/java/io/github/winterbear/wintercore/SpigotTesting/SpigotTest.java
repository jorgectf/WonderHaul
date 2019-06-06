package io.github.winterbear.wintercore.SpigotTesting;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

/**
 * Created by WinterBear on 30/09/2018.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({Bukkit.class, JavaPlugin.class, PlayerInteractEvent.class})
public class SpigotTest<T> {

    public SpigotTest(){
        this.mockItemFactory();
    }

    protected void mockItemFactory(){
        mockStatic(Bukkit.class);
        ItemFactory itemFactory = mock(ItemFactory.class);
        when(Bukkit.getItemFactory()).thenReturn(itemFactory);
        BukkitScheduler scheduler = mock(BukkitScheduler.class);
        when(Bukkit.getScheduler()).thenReturn(scheduler);
        when(itemFactory.getItemMeta(any())).thenReturn(new MockItemMeta());
    }

    protected PlayerInteractEvent mockEvent(Action action, Player player){
        PlayerInteractEvent mockedEvent = PowerMockito.mock(PlayerInteractEvent.class);
        when(mockedEvent.getAction()).thenReturn(action);
        when(mockedEvent.getPlayer()).thenReturn(player);
        return mockedEvent;
    }

    protected JavaPlugin mockJavaPlugin(){
        JavaPlugin plugin = PowerMockito.mock(JavaPlugin.class);
        Server server = mock(Server.class);

        when(plugin.getServer()).thenReturn(server);

        PluginManager pluginManager = mock(PluginManager.class);
        when(server.getPluginManager()).thenReturn(pluginManager);
        return plugin;
    }

}
