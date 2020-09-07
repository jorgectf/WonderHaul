package io.github.winterbear.wintercore.wonderhaul.sockets;

import io.github.winterbear.wintercore.utils.ItemBuilder;
import io.github.winterbear.wintercore.wonderhaul.sockets.application.SocketApplication;
import io.github.winterbear.wintercore.wonderhaul.sockets.infusions.Infusions;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * Created by WinterBear on 06/09/2020.
 */
public class Socketable implements ISocketable{

    private Supplier<ItemStack> texture;

    private String itemName;

    private Ability ability;

    private ChatColor color;

    private SocketType socketType;

    private Collection<Material> applicableItems;

    private Sound sound;

    public Socketable(JavaPlugin plugin, Supplier<ItemStack> texture, String itemName,Ability ability, ChatColor color, SocketType socketType, Collection<Material> applicableItems, Sound sound) {
        this.texture = texture;
        this.itemName = itemName;
        this.ability = ability;
        this.color = color;
        this.socketType = socketType;
        this.applicableItems = applicableItems;
        this.sound = sound;
    }

    @Override
    public Supplier<ItemStack> getTexture() {
        return texture;
    }

    @Override
    public String getItemName() {
        return itemName;
    }

    @Override
    public String getAbilityName() {
        return ability.getName();
    }

    @Override
    public ChatColor getColor() {
        return color;
    }

    @Override
    public TriggerType getTriggerType() {
        return ability.getTriggerType();
    }

    @Override
    public SocketType getSocketType() {
        return socketType;
    }

    @Override
    public Collection<Material> getApplicableItems() {
        return applicableItems;
    }

    @Override
    public String getApplicationInstructions() {
        return socketType.getApplicationInstructions();
    }

    @Override
    public boolean apply(ItemStack item, SocketApplication socketApplication) {
        return socketType.apply(socketApplication, item);
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = getTexture().get();
        ItemBuilder.setDisplayName(item, getColor() + getItemName());
        item = Infusions.setInfusionLore(item, getColor(), getAbilityName());
        return item;
    }

    @Override
    public Sound getSound() {
        return sound;
    }
}
