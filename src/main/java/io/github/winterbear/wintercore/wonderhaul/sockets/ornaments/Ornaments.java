package io.github.winterbear.wintercore.wonderhaul.sockets.ornaments;

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
import io.github.winterbear.wintercore.wonderhaul.sockets.ornaments.abilities.ArcaneRunes;
import io.github.winterbear.wintercore.wonderhaul.sockets.ornaments.abilities.Toxic;
import io.github.winterbear.wintercore.wonderhaul.sockets.ornaments.abilities.Streamlining;
import io.github.winterbear.wintercore.wonderhaul.sockets.ornaments.abilities.Studded;
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
        ItemStack item = socketable.getTexture().get();
        ItemBuilder.setDisplayName(item, socketable.getColor() + socketable.getItemName());
        item = Ornaments.setOrnamentLore(item, socketable.getColor(), socketable.getAbilityName());
        return item;
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
                .create(plugin));

        ORNAMENTS.add(SocketBuilder.createSocket(SocketType.ORNAMENT)
                .withTexture(TexturedHeads.THICK_STUDS)
                .withColor("#95a3c2")
                .withItemName("Thick Studs")
                .withAbility(new Studded())
                .forItems(MaterialGroup.ALL_ARMOR)
                .withSound(Sound.BLOCK_BELL_USE)
                .create(plugin));

        ORNAMENTS.add(SocketBuilder.createSocket(SocketType.ORNAMENT)
                .withTexture(TexturedHeads.ENCHANTED_LUBRICANT)
                .withColor("#45ff83")
                .withItemName("Enchanted Lubricant")
                .withAbility(new Streamlining())
                .forItems(MaterialGroup.ALL_ARMOR)
                .withSound(Sound.ENTITY_SLIME_SQUISH)
                .create(plugin));

        ORNAMENTS.add(SocketBuilder.createSocket(SocketType.ORNAMENT)
                .withTexture(TexturedHeads.RIPE_SHADEBERRY)
                .withColor("#6f6fc7")
                .withItemName("Ripe Shadeberry")
                .withAbility(new Toxic())
                .forItems(MaterialGroup.ALL_ARMOR)
                .withSound(Sound.BLOCK_CORAL_BLOCK_BREAK)
                .create(plugin));
    }
}
