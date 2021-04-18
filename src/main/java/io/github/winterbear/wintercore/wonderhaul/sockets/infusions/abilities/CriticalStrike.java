package io.github.winterbear.wintercore.wonderhaul.sockets.infusions.abilities;

import io.github.winterbear.wintercore.particles.ParticleEngine;
import io.github.winterbear.wintercore.utils.SoundUtils;
import io.github.winterbear.wintercore.wonderhaul.dropper.Chance;
import io.github.winterbear.wintercore.wonderhaul.sockets.TriggerType;
import io.github.winterbear.wintercore.wonderhaul.sockets.abilitytypes.OffensiveAbility;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Created by WinterBear on 28/08/2020.
 */
public class CriticalStrike extends OffensiveAbility {

    private static final String ABILITY_NAME = "Critical Strike";

    @Override
    public String getName() {
        return ABILITY_NAME;
    }

    @Override
    public TriggerType getTriggerType() {
        return TriggerType.MAINHAND;
    }

    @Override
    public String getDescription() {
        return "Adds a chance to do double damage";
    }

    @Override
    public void onAttack(Player attacker, EntityDamageByEntityEvent event, int socketLevel) {
        double chance = 10.0;
        if(socketLevel > 2){
            chance = 18.0;
        } else if (socketLevel > 1){
            chance = 14.0;
        }
        if(Chance.roll(chance)){
            event.setDamage(event.getDamage() * 2);
            SoundUtils.playSound(attacker, Sound.ENTITY_IRON_GOLEM_REPAIR);
            ParticleEngine.particleDotCloud(Particle.CRIT, event.getEntity().getLocation(), 10, 1.0);
        }
    }

}
