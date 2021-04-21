package io.github.winterbear.wintercore.wonderhaul.sockets.infusions.abilities;

import io.github.winterbear.wintercore.wonderhaul.particles.ParticleEngine;
import io.github.winterbear.wintercore.utils.SoundUtils;
import io.github.winterbear.wintercore.wonderhaul.dropper.Chance;
import io.github.winterbear.wintercore.wonderhaul.sockets.TriggerType;
import io.github.winterbear.wintercore.wonderhaul.sockets.abilitytypes.OffensiveAbility;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Created by WinterBear on 30/08/2020.
 */
public class Frostbitten extends OffensiveAbility {

    @Override
    public String getName() {
        return "Frostbitten";
    }

    @Override
    public TriggerType getTriggerType() {
        return TriggerType.MAINHAND;
    }

    @Override
    public String getDescription() {
        return "Has a chance to freeze enemies, slowing them down";
    }


    @Override
    public void onAttack(Player attacker, EntityDamageByEntityEvent event, int socketLevel) {
        double chance = 8.0;
        if(socketLevel > 2){
            chance = 12.0;
        } else if (socketLevel > 1){
            chance = 10.0;
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

