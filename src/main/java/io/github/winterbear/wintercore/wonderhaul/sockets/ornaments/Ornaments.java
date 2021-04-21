package io.github.winterbear.wintercore.wonderhaul.sockets.ornaments;

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
import io.github.winterbear.wintercore.wonderhaul.sockets.ornaments.abilities.ArcaneRunes;
import io.github.winterbear.wintercore.wonderhaul.sockets.ornaments.abilities.Streamlining;
import io.github.winterbear.wintercore.wonderhaul.sockets.ornaments.abilities.Studded;
import io.github.winterbear.wintercore.wonderhaul.sockets.ornaments.abilities.Toxic;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by WinterBear on 07/09/2020.
 */
public class Ornaments {

    private static final String SYMBOL = "❖";

    public static String PREFIX = Tier.ASCENDED.getColor() + SYMBOL + ChatUtils.format(" &7Ornament&8: &7");

    public static Set<ISocketable> ORNAMENTS = new HashSet<>();

    public static ItemStack create(ISocketable socketable){
        return ItemBuilder.newMicroblock(socketable.getAbilityName(),
                ItemCategory.ORNAMENT,
                socketable.getColor(),
                ColorLoreMode.BRIGHTER,
                socketable.getTexture())
                .withDescription(socketable.getDescription())
                .withDisplayName(socketable.getItemName())
                .withUsage(socketable.getAbility().getDescription())
                .build();
    }

    public static String getOrnamentLore(SocketApplication application){
        return PREFIX + application.getSocketable().getColor() + application.getSocketable().getAbilityName();
    }

    public static ItemStack setOrnamentLore(ItemStack item, ChatColor color, String name){
        LoreUtils.addLoreLine(item, PREFIX + color + name);
        return item;
    }

    public static void registerAll(JavaPlugin plugin){
        ORNAMENTS.add(SocketBuilder.createSocket(SocketType.ORNAMENT)
                .withTexture(TexturedHeads.THAUMIC_RUNESTONE)
                .withColor("#4d4aff")
                .withItemName("Thaumic Runestone")
                .withAbility(new ArcaneRunes())
                .forItems(MaterialGroup.ALL_ARMOR)
                .withSound(Sound.BLOCK_PORTAL_AMBIENT)
                .withDescription("A powerful stone that can be used to inscribe ancient runes")
                .withLore("The inscription of ancient runes is a lost art, but there is still great power in this stone.")
                .create(plugin));

        ORNAMENTS.add(SocketBuilder.createSocket(SocketType.ORNAMENT)
                .withTexture(TexturedHeads.THICK_STUDS)
                .withColor("#95a3c2")
                .withItemName("Thick Studs")
                .withAbility(new Studded())
                .forItems(MaterialGroup.ALL_ARMOR)
                .withSound(Sound.BLOCK_BELL_USE)
                .withDescription("A set of spiked studs made of Lanthanite")
                .withLore("These appear to have once been part of a larger set")
                .create(plugin));

        ORNAMENTS.add(SocketBuilder.createSocket(SocketType.ORNAMENT)
                .withTexture(TexturedHeads.ENCHANTED_LUBRICANT)
                .withColor("#45ff83")
                .withItemName("Enchanted Lubricant")
                .withAbility(new Streamlining())
                .forItems(MaterialGroup.ALL_ARMOR)
                .withSound(Sound.ENTITY_SLIME_SQUISH)
                .withDescription("A highly fragrant salve that glows somewhat")
                .withLore("As slippery as it's scent is pleasant, this stuff is made from the roots of ancient trees")
                .create(plugin));

        ORNAMENTS.add(SocketBuilder.createSocket(SocketType.ORNAMENT)
                .withTexture(TexturedHeads.RIPE_SHADEBERRY)
                .withColor("#6f6fc7")
                .withItemName("Ripe Shadeberry")
                .withAbility(new Toxic())
                .forItems(MaterialGroup.ALL_ARMOR)
                .withSound(Sound.BLOCK_CORAL_BLOCK_BREAK)
                .withDescription("As delicious as it is deadly")
                .withLore("Interestingly, alcohol seems to negate it's effects. My aunt makes an excellent Shadeberry Whisky")
                .create(plugin));
    }
}
