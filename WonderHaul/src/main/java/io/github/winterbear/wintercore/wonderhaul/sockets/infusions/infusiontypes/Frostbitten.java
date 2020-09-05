package io.github.winterbear.wintercore.wonderhaul.sockets.infusions.infusiontypes;

import io.github.winterbear.wintercore.particles.ParticleEngine;
import io.github.winterbear.wintercore.utils.SoundUtils;
import io.github.winterbear.wintercore.utils.TexturedHeads;
import io.github.winterbear.wintercore.wonderhaul.dropper.Chance;
import io.github.winterbear.wintercore.wonderhaul.equipment.MaterialGroup;
import io.github.winterbear.wintercore.wonderhaul.sockets.EffectType;
import io.github.winterbear.wintercore.wonderhaul.sockets.infusions.OffensiveInfusion;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
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
public class Frostbitten extends OffensiveInfusion {

    public Frostbitten(JavaPlugin plugin) {
        super(plugin);
    }

    @Override
    public Supplier<ItemStack> getTexture() {
        return TexturedHeads.ICE_KINGS_CHALICE;
    }

    @Override
    public String getItemName() {
        return "Ice King's Chalice";
    }

    @Override
    public String getAbilityName() {
        return "Frostbitten";
    }

    @Override
    public ChatColor getColor() {
        return ChatColor.of("#adfff7");
    }

    @Override
    public EffectType getTriggerType() {
        return EffectType.MAINHAND;
    }

    @Override
    public Collection<Material> getApplicableItems() {
        return MaterialGroup.ALL_WEAPONS;
    }

    @Override
    public void onAttack(Player attacker, EntityDamageByEntityEvent event, int socketLevel) {
        double chance = 8.0;
        if(socketLevel > 2){
            chance = 10.0;
        } else if (socketLevel > 1){
            chance = 12.0;
        }
        if(event.getEntity() instanceof LivingEntity && Chance.roll(chance)){
            LivingEntity victim = (LivingEntity) event.getEntity();
            new PotionEffect(PotionEffectType.SLOW, 100, 1, true, false, false)
                    .apply(victim);
            SoundUtils.playSound(attacker, Sound.BLOCK_GLASS_BREAK);
            ParticleEngine.particleDotCloud(Particle.SNOW_SHOVEL, event.getEntity().getLocation(), 10, 1.0);
        }
    }

}

