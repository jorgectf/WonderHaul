package io.github.winterbear.wintercore.wonderhaul.sockets.infusions;

import io.github.winterbear.wintercore.utils.ItemBuilder;
import io.github.winterbear.wintercore.utils.LoreUtils;
import io.github.winterbear.wintercore.wonderhaul.sockets.EffectType;
import io.github.winterbear.wintercore.wonderhaul.sockets.SocketApplication;
import io.github.winterbear.wintercore.wonderhaul.sockets.SocketType;
import io.github.winterbear.wintercore.wonderhaul.sockets.Socketable;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.function.Supplier;

/**
 * Created by WinterBear on 30/08/2020.
 */
public abstract class Infusion implements Listener, Socketable {

    public static SocketType TYPE = SocketType.INFUSION;

    protected final JavaPlugin plugin;

    public abstract Supplier<ItemStack> getTexture();

    public abstract String getItemName();

    public abstract String getAbilityName();

    public abstract ChatColor getColor();

    public abstract EffectType getTriggerType();

    public SocketType getSocketType(){
        return SocketType.INFUSION;
    }

    public ItemStack getItem(){
        ItemStack item = getTexture().get();
        ItemBuilder.setDisplayName(item, getColor() + getItemName());
        item = Infusions.setInfusionLore(item, getColor(), getAbilityName());
        return item;
    }

    public Infusion(JavaPlugin plugin){
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public String getApplicationInstructions(){
        return "Right click with a weapon or tool that has an Infusion socket! Any existing sockets will be overwritten.";
    }

    public boolean apply(ItemStack itemStack, SocketApplication application){
        if(LoreUtils.getTag(itemStack, "Infusion").isEmpty()){

        }
        return true;
    }




}
