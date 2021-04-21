package io.github.winterbear.wintercore.wonderhaul.sockets.infusions;

import io.github.winterbear.WinterCoreUtils.ChatUtils;
import io.github.winterbear.wintercore.utils.ColorLoreMode;
import io.github.winterbear.wintercore.utils.ItemBuilder;
import io.github.winterbear.wintercore.utils.LoreUtils;
import io.github.winterbear.wintercore.utils.TexturedHeads;
import io.github.winterbear.wintercore.wonderhaul.ItemCategory;
import io.github.winterbear.wintercore.wonderhaul.equipment.MaterialGroup;
import io.github.winterbear.wintercore.wonderhaul.equipment.Tier;
import io.github.winterbear.wintercore.wonderhaul.sockets.ISocketable;
import io.github.winterbear.wintercore.wonderhaul.sockets.SocketBuilder;
import io.github.winterbear.wintercore.wonderhaul.sockets.SocketType;
import io.github.winterbear.wintercore.wonderhaul.sockets.application.SocketApplication;
import io.github.winterbear.wintercore.wonderhaul.sockets.infusions.abilities.*;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
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

    public static ItemStack create(ISocketable socketable){
        return ItemBuilder.newMicroblock(socketable.getAbilityName(),
                ItemCategory.INFUSION,
                socketable.getColor(),
                ColorLoreMode.BRIGHTER,
                socketable.getTexture())
                .withDescription(socketable.getDescription())
                .withDisplayName(socketable.getItemName())
                .withUsage(socketable.getAbility().getDescription())
                .build();
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
                .withDescription("A tome containing knowledge of advanced combat techniques")
                .withLore("This is the collected works of the Djinn Aru, he was a spirit of heroism and combat")
                .create(plugin));

        INFUSIONS.add(SocketBuilder.createSocket(SocketType.INFUSION)
                .withItemName("Deep Sea Pumpkin")
                .withTexture(TexturedHeads.SEA_PUMPKIN)
                .withColor("#45c8f7")
                .withAbility(new AquaticAttunement())
                .forItems(MaterialGroup.ALL_WEAPONS)
                .withSound(Sound.BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT)
                .withDescription("A strange vegetable that grows in distant seas")
                .withLore("The merfolk eat these sometimes, when there aren't any men around.")
                .create(plugin));

        INFUSIONS.add(SocketBuilder.createSocket(SocketType.INFUSION)
                .withItemName("Nullifying Varnish")
                .withTexture(TexturedHeads.NULLIFYING_VARNISH)
                .withColor("#87ffcd")
                .withAbility(new Warding())
                .forItems(MaterialGroup.SHIELD)
                .withSound(Sound.ENTITY_SLIME_SQUISH)
                .withDescription("A strange mixture that smells faintly of milk")
                .withLore("This is made from the milk and eggs of a nameless half bear, half bird creature.")
                .create(plugin));

        INFUSIONS.add(SocketBuilder.createSocket(SocketType.INFUSION)
                .withItemName("Jewel of Agility")
                .withTexture(TexturedHeads.JEWEL_OF_AGILITY)
                .withColor("#ff4942")
                .withAbility(new FirstStrike())
                .forItems(MaterialGroup.ALL_WEAPONS)
                .withSound(Sound.ITEM_ARMOR_EQUIP_DIAMOND)
                .withDescription("A dazzling jewel that seems to contain a glowing light")
                .withLore("This gemstone contains the essence of motion. It's an ancient relic from a civilisation long gone.")
                .create(plugin));

        INFUSIONS.add(SocketBuilder.createSocket(SocketType.INFUSION)
                .withItemName("Ice King's Chalice")
                .withTexture(TexturedHeads.ICE_KINGS_CHALICE)
                .withColor("#adfff7")
                .withAbility(new Frostbitten())
                .forItems(MaterialGroup.ALL_WEAPONS)
                .withSound(Sound.BLOCK_GLASS_BREAK)
                .withDescription("A cup that chills any liquid placed into it")
                .withLore("This once belonged to a powerful sorcerer, but was stolen by a trickster demon")
                .create(plugin));

        INFUSIONS.add(SocketBuilder.createSocket(SocketType.INFUSION)
                .withItemName("Ravenous Fleshcrawler")
                .withTexture(TexturedHeads.RAVENOUS_FLESHCRAWLER)
                .withColor("#ad4e66")
                .withAbility(new Carnivorous())
                .forItems(MaterialGroup.ALL_WEAPONS)
                .withSound(Sound.ENTITY_GENERIC_EAT)
                .withDescription("A writhing insect that devours anything placed in front of its mouth")
                .withLore("These nasty bugs inhabit the swamps of Lansoon, and quickly devour anything they find.")
                .create(plugin));

        INFUSIONS.add(SocketBuilder.createSocket(SocketType.INFUSION)
                .withItemName("Nervous Shroomling")
                .withTexture(TexturedHeads.NERVOUS_SHROOMLING)
                .withColor("#8ff745")
                .withAbility(new Adrenaline())
                .forItems(MaterialGroup.ALL_WEAPONS)
                .withSound(Sound.ENTITY_BAT_AMBIENT)
                .withDescription("This rare creature is extremely shy, but is a potent source of vigour")
                .withLore("You used to be able to find these on mushroom islands, but they have become quite rare.")
                .create(plugin));

        INFUSIONS.add(SocketBuilder.createSocket(SocketType.INFUSION)
                .withItemName("Crimson Skull")
                .withTexture(TexturedHeads.CRIMSON_SKULL)
                .withColor("#940000")
                .withAbility(new Vicious())
                .forItems(MaterialGroup.ALL_WEAPONS)
                .withSound(Sound.ENTITY_SKELETON_AMBIENT)
                .withDescription("A blood red polished skull that seems to be made of Carnelian")
                .withLore("This was once a trophy made by the tyrannical king Lethor from the blood of his enemies, or so the legend goes.")
                .create(plugin));

        INFUSIONS.add(SocketBuilder.createSocket(SocketType.INFUSION)
                .withItemName("Euclidian Slime")
                .withTexture(TexturedHeads.EUCLIDIAN_SLIME)
                .withColor("#952bff")
                .withAbility(new Distortion())
                .forItems(MaterialGroup.ALL_WEAPONS)
                .withSound(Sound.BLOCK_PORTAL_AMBIENT)
                .withDescription("A small creature that seems to be made of tiny stars")
                .withLore("This is an incredibly rare creature that inhabits the space between dimensions. This one seems to like you.")
                .create(plugin));
        
        INFUSIONS.add(SocketBuilder.createSocket(SocketType.INFUSION)
                .withItemName("Netherite Plating")
                .withTexture(TexturedHeads.NETHERITE_PLATING)
                .withColor("#6543b5")
                .withAbility(new Reinforced())
                .forItems(MaterialGroup.AXE, MaterialGroup.PICKAXE, MaterialGroup.HOE, MaterialGroup.SHOVEL)
                .withSound(Sound.BLOCK_ANVIL_USE)
                .withDescription("Hardened Netherite Alloy that can be used to increase the effectiveness of tools")
                .withLore("Piglin warsmiths once crafted this stuff, they said it is fueled by the power of hell")
                .create(plugin));

        INFUSIONS.add(SocketBuilder.createSocket(SocketType.INFUSION)
                .withItemName("Jar of Quicksilver")
                .withTexture(TexturedHeads.JAR_OF_QUICKSILVER)
                .withColor("#e8fbff")
                .withAbility(new Reflective())
                .forItem(Material.SHIELD)
                .withSound(Sound.ENTITY_VILLAGER_WORK_WEAPONSMITH)
                .withDescription("This liquid is so reflective it practically glows in its container")
                .withLore("Tiny traces of this stuff can be found underground, it takes a long time to find enough to fill a jar.")
                .create(plugin));

        INFUSIONS.add(SocketBuilder.createSocket(SocketType.INFUSION)
                .withItemName("Magmatic Runestone")
                .withTexture(TexturedHeads.MAGMATIC_RUNESTONE)
                .withColor("#ff6f00")
                .withAbility(new Fireproof())
                .forItem(Material.SHIELD)
                .withSound(Sound.BLOCK_LAVA_EXTINGUISH)
                .withDescription("A stone that burns continually")
                .withLore("This stone allows one to write in ancient netheric. Once the great black fortresses were sealed by its power.")
                .create(plugin));

    }

}
