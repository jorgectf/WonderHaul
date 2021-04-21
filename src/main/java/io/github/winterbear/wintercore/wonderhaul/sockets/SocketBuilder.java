package io.github.winterbear.wintercore.wonderhaul.sockets;

import io.github.winterbear.wintercore.utils.TexturedHead;
import io.github.winterbear.wintercore.wonderhaul.equipment.MaterialGroup;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Stream;

/**
 * Created by WinterBear on 06/09/2020.
 */
public class SocketBuilder {

    private TexturedHead texture;

    private String itemName;

    private Ability ability;

    private ChatColor color;

    private SocketType socketType;

    private Collection<Material> applicableItems = new HashSet<>();

    private Sound sound;

    private String description;

    private String lore;

    protected JavaPlugin plugin;

    public static SocketBuilder createSocket(SocketType socketType){
        SocketBuilder socketBuilder = new SocketBuilder();
        socketBuilder.socketType = socketType;
        return socketBuilder;
    }

    public SocketBuilder withItemName(String itemName){
        this.itemName = itemName;
        return this;
    }

    public SocketBuilder withTexture(TexturedHead texture){
        this.texture = texture;
        return this;
    }

    public SocketBuilder forItems(MaterialGroup group){
        applicableItems.addAll(group.getMaterials());
        return this;
    }

    public SocketBuilder forItems(MaterialGroup... groups){
        Stream.of(groups).forEach(group -> applicableItems.addAll(group.getMaterials()));
        return this;
    }

    public SocketBuilder forItems(Collection<Material> group){
        applicableItems.addAll(group);
        return this;
    }

    public SocketBuilder forItem(Material... material){
        applicableItems.addAll(Arrays.asList(material));
        return this;
    }

    public SocketBuilder withColor(String hexRGB){
        this.color = ChatColor.of(hexRGB);
        return this;
    }

    public SocketBuilder withAbility(Ability ability){
        this.ability = ability;
        return this;
    }

    public SocketBuilder withSound(Sound sound){
        this.sound = sound;
        return this;
    }

    public SocketBuilder withDescription(String description){
        this.description = description;
        return this;
    }

    public SocketBuilder withLore(String lore){
        this.lore = lore;
        return this;
    }

    public ISocketable create(JavaPlugin plugin){
        Socketable socketable = new Socketable(plugin,
                texture,
                itemName,
                ability,
                color,
                socketType,
                applicableItems,
                sound,
                description,
                lore);
        ability.register(plugin, socketable);
        return socketable;
    }


}
