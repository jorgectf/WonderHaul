package io.github.winterbear.wintercore.wonderhaul.sockets.infusions.abilities;

import io.github.winterbear.wintercore.wonderhaul.sockets.TriggerType;
import io.github.winterbear.wintercore.wonderhaul.sockets.abilitytypes.OffensiveDefensiveAbility;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Created by WinterBear on 31/08/2020.
 */
public class Distortion extends OffensiveDefensiveAbility {
    @Override
    public void onDefend(Player victim, EntityDamageByEntityEvent event, int socketLevel) {
        double multiplier = 1.2;
        if(socketLevel > 2){
            multiplier = 1.8;
        } else if (socketLevel > 1){
            multiplier = 1.5;
        }
        event.setDamage(event.getDamage() * multiplier);
    }

    @Override
    public void onAttack(Player attacker, EntityDamageByEntityEvent event, int socketLevel) {
        double multiplier = 1.2;
        if(socketLevel > 2){
            multiplier = 1.8;
        } else if (socketLevel > 1){
            multiplier = 1.5;
        }
        event.setDamage(event.getDamage() * multiplier);
    }

    @Override
    public String getName() {
        return "Distortion";
    }

    @Override
    public TriggerType getTriggerType() {
        return TriggerType.MAINHAND;
    }

    @Override
    public String getDescription() {
        return "Increases damage dealt, but also damage taken";
    }
}
