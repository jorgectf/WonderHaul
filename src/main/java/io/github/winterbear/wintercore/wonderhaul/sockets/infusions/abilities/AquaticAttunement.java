package io.github.winterbear.wintercore.wonderhaul.sockets.infusions.abilities;

import io.github.winterbear.wintercore.utils.BlockUtils;
import io.github.winterbear.wintercore.wonderhaul.sockets.TriggerType;
import io.github.winterbear.wintercore.wonderhaul.sockets.abilitytypes.OffensiveAbility;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Created by WinterBear on 30/08/2020.
 */
public class AquaticAttunement extends OffensiveAbility {

    private static final String ABILITY_NAME = "Aquatic Attunement";

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
        return "Increases your damage when underwater";
    }

    @Override
    public void onAttack(Player attacker, EntityDamageByEntityEvent event, int socketLevel) {
        if(BlockUtils.isUnderwater(attacker.getLocation())){
            event.setDamage(event.getDamage() * 1.25);
        }
    }
}
