package io.github.winterbear.wintercore.wonderhaul.sockets.infusions.abilities;

import io.github.winterbear.wintercore.wonderhaul.sockets.TriggerType;
import io.github.winterbear.wintercore.wonderhaul.sockets.abilitytypes.DefensiveAbility;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.EnumSet;
import java.util.Set;

/**
 * Created by WinterBear on 14/09/2020.
 */
public class Fireproof extends DefensiveAbility {

    private static final Set<EntityDamageEvent.DamageCause> CAUSES = EnumSet.of(EntityDamageEvent.DamageCause.FIRE,
            EntityDamageEvent.DamageCause.FIRE_TICK,
            EntityDamageEvent.DamageCause.HOT_FLOOR,
            EntityDamageEvent.DamageCause.LAVA,
            EntityDamageEvent.DamageCause.DRAGON_BREATH);

    @Override
    public void onDefend(Player victim, EntityDamageByEntityEvent event, int socketLevel) {
        if(victim.isBlocking() && CAUSES.contains(event.getCause())){
            event.setCancelled(true);
        }
    }

    @Override
    public String getName() {
        return "Fireproof";
    }

    @Override
    public TriggerType getTriggerType() {
        return TriggerType.OFFHAND;
    }
}
