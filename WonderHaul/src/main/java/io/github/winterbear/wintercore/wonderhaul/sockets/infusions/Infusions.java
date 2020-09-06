package io.github.winterbear.wintercore.wonderhaul.sockets.infusions;

import io.github.winterbear.WinterCoreUtils.ChatUtils;
import io.github.winterbear.wintercore.utils.ItemBuilder;
import io.github.winterbear.wintercore.utils.LoreUtils;
import io.github.winterbear.wintercore.utils.TexturedHeads;
import io.github.winterbear.wintercore.wonderhaul.equipment.MaterialGroup;
import io.github.winterbear.wintercore.wonderhaul.equipment.Tier;
import io.github.winterbear.wintercore.wonderhaul.sockets.ISocketable;
import io.github.winterbear.wintercore.wonderhaul.sockets.SocketBuilder;
import io.github.winterbear.wintercore.wonderhaul.sockets.SocketType;
import io.github.winterbear.wintercore.wonderhaul.sockets.application.SocketApplication;
import io.github.winterbear.wintercore.wonderhaul.sockets.infusions.abilities.*;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by WinterBear on 28/08/2020.
 */
public class Infusions {

    private static final String SYMBOL = "✵";

    public static String PREFIX = Tier.ASCENDED.getColor() + SYMBOL + ChatUtils.format(" &7Infusion&8: &7");

    public static Set<ISocketable> INFUSIONS = new HashSet<>();

    public ItemStack create(ISocketable socketable){
        ItemStack item = socketable.getTexture().get();
        ItemBuilder.setDisplayName(item, socketable.getColor() + socketable.getItemName());
        item = Infusions.setInfusionLore(item, socketable.getColor(), socketable.getAbilityName());
        return item;
    }

    public static String getInfusionLore(SocketApplication application){
        return PREFIX + application.getSocketable().getColor() + application.getSocketable().getAbilityName();
    }

    public static ItemStack setInfusionLore(ItemStack item, ChatColor color, String name){
        LoreUtils.addLoreLine(item, PREFIX + color + name);
        return item;
    }

    public static void registerAll(JavaPlugin plugin){
        INFUSIONS.add(SocketBuilder.createSocket(SocketType.INFUSION)
                .withTexture(TexturedHeads.CRUCIAL_KNOWLEDGE)
                .withColor("#b01962")
                .withItemName("Crucial Knowledge")
                .withAbility(new CriticalStrike())
                .forItems(MaterialGroup.ALL_WEAPONS)
                .withSound(Sound.ITEM_BOOK_PAGE_TURN)
                .create(plugin));

        INFUSIONS.add(SocketBuilder.createSocket(SocketType.INFUSION)
                .withItemName("Deep Sea Pumpkin")
                .withTexture(TexturedHeads.SEA_PUMPKIN)
                .withColor("#45c8f7")
                .withAbility(new AquaticAttunement())
                .forItems(MaterialGroup.ALL_WEAPONS)
                .withSound(Sound.BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT)
                .create(plugin));

        INFUSIONS.add(SocketBuilder.createSocket(SocketType.INFUSION)
                .withItemName("Nullifying Varnish")
                .withTexture(TexturedHeads.NULLIFYING_VARNISH)
                .withColor("#87ffcd")
                .withAbility(new Warding())
                .forItems(MaterialGroup.SHIELD)
                .withSound(Sound.ENTITY_SLIME_SQUISH)
                .create(plugin));

        INFUSIONS.add(SocketBuilder.createSocket(SocketType.INFUSION)
                .withItemName("Jewel of Agility")
                .withTexture(TexturedHeads.JEWEL_OF_AGILITY)
                .withColor("#ff4942")
                .withAbility(new FirstStrike())
                .forItems(MaterialGroup.ALL_WEAPONS)
                .withSound(Sound.ITEM_ARMOR_EQUIP_DIAMOND)
                .create(plugin));

        INFUSIONS.add(SocketBuilder.createSocket(SocketType.INFUSION)
                .withItemName("Ice King's Chalice")
                .withTexture(TexturedHeads.ICE_KINGS_CHALICE)
                .withColor("#adfff7")
                .withAbility(new Frostbitten())
                .forItems(MaterialGroup.ALL_WEAPONS)
                .withSound(Sound.BLOCK_GLASS_BREAK)
                .create(plugin));

        INFUSIONS.add(SocketBuilder.createSocket(SocketType.INFUSION)
                .withItemName("Ravenous Fleshcrawler")
                .withTexture(TexturedHeads.RAVENOUS_FLESHCRAWLER)
                .withColor("#ad4e66")
                .withAbility(new Carnivorous())
                .forItems(MaterialGroup.ALL_WEAPONS)
                .withSound(Sound.ENTITY_GENERIC_EAT)
                .create(plugin));

        INFUSIONS.add(SocketBuilder.createSocket(SocketType.INFUSION)
                .withItemName("Nervous Shroomling")
                .withTexture(TexturedHeads.NERVOUS_SHROOMLING)
                .withColor("#8ff745")
                .withAbility(new Adrenaline())
                .forItems(MaterialGroup.ALL_WEAPONS)
                .withSound(Sound.ENTITY_BAT_AMBIENT)
                .create(plugin));

        INFUSIONS.add(SocketBuilder.createSocket(SocketType.INFUSION)
                .withItemName("Crimson Skull")
                .withTexture(TexturedHeads.CRIMSON_SKULL)
                .withColor("#940000")
                .withAbility(new Vicious())
                .forItems(MaterialGroup.ALL_WEAPONS)
                .withSound(Sound.ENTITY_SKELETON_AMBIENT)
                .create(plugin));

        INFUSIONS.add(SocketBuilder.createSocket(SocketType.INFUSION)
                .withItemName("Euclidian Slime")
                .withTexture(TexturedHeads.EUCLIDIAN_SLIME)
                .withColor("#952bff")
                .withAbility(new Distortion())
                .forItems(MaterialGroup.ALL_WEAPONS)
                .withSound(Sound.BLOCK_PORTAL_AMBIENT)
                .create(plugin));
    }

}
