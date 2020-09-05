package io.github.winterbear.wintercore.wonderhaul.sockets.infusions.infusiontypes;

import io.github.winterbear.wintercore.utils.TexturedHeads;
import io.github.winterbear.wintercore.wonderhaul.equipment.MaterialGroup;
import io.github.winterbear.wintercore.wonderhaul.sockets.EffectType;
import io.github.winterbear.wintercore.wonderhaul.sockets.infusions.OffensiveDefensiveInfusion;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * Created by WinterBear on 31/08/2020.
 */
public class Distortion extends OffensiveDefensiveInfusion {


    public Distortion(JavaPlugin plugin) {
        super(plugin);
    }

    @Override
    public void onDefend(Player victim, EntityDamageByEntityEvent event, int socketLevel) {
        double multiplier = 1.2;
        if(socketLevel > 2){
            multiplier = 1.5;
        } else if (socketLevel > 1){
            multiplier = 1.8;
        }
        event.setDamage(event.getDamage() * multiplier);
    }

    @Override
    public void onAttack(Player attacker, EntityDamageByEntityEvent event, int socketLevel) {
        double multiplier = 1.2;
        if(socketLevel > 2){
            multiplier = 1.5;
        } else if (socketLevel > 1){
            multiplier = 1.8;
        }
        event.setDamage(event.getDamage() * multiplier);
    }

    @Override
    public Supplier<ItemStack> getTexture() {
        return TexturedHeads.EUCLIDIAN_SLIME;
    }

    @Override
    public String getItemName() {
        return "Euclidian Slime";
    }

    @Override
    public String getAbilityName() {
        return "Distortion";
    }

    @Override
    public ChatColor getColor() {
        return ChatColor.of("#952bff");
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
