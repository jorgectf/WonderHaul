package io.github.winterbear.wintercore.wonderhaul.sockets.infusions.abilities;

import io.github.winterbear.wintercore.wonderhaul.sockets.TriggerType;
import io.github.winterbear.wintercore.wonderhaul.sockets.abilitytypes.OffensiveAbility;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Created by WinterBear on 30/08/2020.
 */
public class Adrenaline extends OffensiveAbility {

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
    public String getName() {
        return "Adrenaline";
    }

    @Override
    public TriggerType getTriggerType() {
        return TriggerType.MAINHAND;
    }
}
