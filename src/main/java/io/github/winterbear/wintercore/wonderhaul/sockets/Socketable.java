package io.github.winterbear.wintercore.wonderhaul.sockets;

import io.github.winterbear.wintercore.utils.TexturedHead;
import io.github.winterbear.wintercore.wonderhaul.sockets.application.SocketApplication;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;

/**
 * Created by WinterBear on 06/09/2020.
 */
public class Socketable implements ISocketable{

    private TexturedHead texture;

    private String itemName;

    private Ability ability;

    private ChatColor color;

    private SocketType socketType;

    private Collection<Material> applicableItems;

    private Sound sound;

    private String description;

    private String lore;

    public Socketable(JavaPlugin plugin, TexturedHead texture, String itemName,Ability ability, ChatColor color, SocketType socketType, Collection<Material> applicableItems, Sound sound, String description, String lore) {
        this.texture = texture;
        this.itemName = itemName;
        this.ability = ability;
        this.color = color;
        this.socketType = socketType;
        this.applicableItems = applicableItems;
        this.sound = sound;
        this.description = description;
        this.lore = lore;
    }

    @Override
    public TexturedHead getTexture() {
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
        return socketType.create(this);
    }

    @Override
    public Sound getSound() {
        return sound;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Ability getAbility() {
        return ability;
    }
}
