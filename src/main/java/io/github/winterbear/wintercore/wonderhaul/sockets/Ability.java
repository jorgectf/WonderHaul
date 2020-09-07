package io.github.winterbear.wintercore.wonderhaul.sockets;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by WinterBear on 06/09/2020.
 */
public abstract class Ability implements Listener {

    public abstract String getName();

    public abstract TriggerType getTriggerType();

    protected ISocketable socketable;

    protected JavaPlugin plugin;

    public void register(JavaPlugin plugin, ISocketable socketable){
        this.socketable = socketable;
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

}
