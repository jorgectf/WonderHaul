package io.github.winterbear.wintercore.wonderhaul.sockets.infusions.abilities;

import io.github.winterbear.wintercore.utils.PotionUtils;
import io.github.winterbear.wintercore.wonderhaul.dropper.Chance;
import io.github.winterbear.wintercore.wonderhaul.sockets.TriggerType;
import io.github.winterbear.wintercore.wonderhaul.sockets.abilitytypes.PotionAbility;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityPotionEffectEvent;

/**
 * Created by WinterBear on 30/08/2020.
 */
public class Warding extends PotionAbility {

    @Override
    public void onPotionEffect(Player victim, EntityPotionEffectEvent event) {
        if(PotionUtils.isNegative(event.getOldEffect(), event.getNewEffect()) && Chance.roll(50.0)){
            event.setCancelled(true);
        }
    }

    @Override
    public String getName() {
        return "Warding";
    }

    @Override
    public TriggerType getTriggerType() {
        return TriggerType.OFFHAND;
    }

    @Override
    public String getDescription() {
        return "Your shield has a chance to absorb potion effects";
    }

}
