package io.github.winterbear.wintercore.wonderhaul.sockets.infusions.infusiontypes;

import io.github.winterbear.wintercore.utils.TexturedHeads;
import io.github.winterbear.wintercore.wonderhaul.equipment.MaterialGroup;
import io.github.winterbear.wintercore.wonderhaul.sockets.EffectType;
import io.github.winterbear.wintercore.wonderhaul.sockets.infusions.OffensiveInfusion;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * Created by WinterBear on 30/08/2020.
 */
public class Adrenaline extends OffensiveInfusion {

    public Adrenaline(JavaPlugin plugin) {
        super(plugin);
    }

    @Override
    public void onAttack(Player attacker, EntityDamageByEntityEvent event, int socketLevel) {
        if(attacker.getActivePotionEffects()
            .stream()
            .noneMatch(e -> e.getType().equals(PotionEffectType.SPEED)
                         && e.getAmplifier() > socketLevel)){
            PotionEffect effect = new PotionEffect(PotionEffectType.SPEED, 200, socketLevel);
            effect.apply(attacker);
        }
    }

    @Override
    public Supplier<ItemStack> getTexture() {
        return TexturedHeads.NERVOUS_SHROOMLING;
    }

    @Override
    public String getItemName() {
        return "Nervous Shroomling";
    }

    @Override
    public String getAbilityName() {
        return "Adrenaline";
    }

    @Override
    public ChatColor getColor() {
        return ChatColor.of("#8ff745");
    }

    @Override
    public EffectType getTriggerType() {
        return EffectType.MAINHAND;
    }

    @Override
    public Collection<Material> getApplicableItems() {
        return MaterialGroup.ALL_WEAPONS;
    }
}
