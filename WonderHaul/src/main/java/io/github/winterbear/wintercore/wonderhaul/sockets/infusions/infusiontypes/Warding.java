package io.github.winterbear.wintercore.wonderhaul.sockets.infusions.infusiontypes;

import io.github.winterbear.wintercore.utils.PotionUtils;
import io.github.winterbear.wintercore.utils.TexturedHeads;
import io.github.winterbear.wintercore.wonderhaul.dropper.Chance;
import io.github.winterbear.wintercore.wonderhaul.equipment.MaterialGroup;
import io.github.winterbear.wintercore.wonderhaul.sockets.EffectType;
import io.github.winterbear.wintercore.wonderhaul.sockets.infusions.PotionInfusion;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * Created by WinterBear on 30/08/2020.
 */
public class Warding extends PotionInfusion {


    public Warding(JavaPlugin plugin) {
        super(plugin);
    }

    @Override
    public void onPotionEffect(Player victim, EntityPotionEffectEvent event) {
        if(PotionUtils.isNegative(event.getOldEffect(), event.getNewEffect()) && Chance.roll(25.0)){
            event.setCancelled(true);
        }
    }

    @Override
    public Supplier<ItemStack> getTexture() {
        return TexturedHeads.NULLIFYING_VARNISH;
    }

    @Override
    public String getItemName() {
        return "Nullifying Varnish";
    }

    @Override
    public String getAbilityName() {
        return "Warding";
    }

    @Override
    public ChatColor getColor() {
        return ChatColor.of("#87ffcd");
    }

    @Override
    public EffectType getTriggerType() {
        return EffectType.OFFHAND;
    }

    @Override
    public Collection<Material> getApplicableItems() {
        return MaterialGroup.SHIELD.getMaterials();
    }
}
