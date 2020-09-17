package io.github.winterbear.wintercore.wonderhaul.sockets.infusions.abilities;

import io.github.winterbear.wintercore.wonderhaul.sockets.TriggerType;
import io.github.winterbear.wintercore.wonderhaul.sockets.abilitytypes.DefensiveAbility;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;

/**
 * Created by WinterBear on 14/09/2020.
 */
public class Reflective extends DefensiveAbility {


    @Override
    public void onDefend(Player victim, EntityDamageByEntityEvent event, int socketLevel) {

        if(victim.isBlocking() && event.getDamager() instanceof LivingEntity){
            LivingEntity attacker = (LivingEntity) event.getDamager();
            Location victimLocation = victim.getEyeLocation();
            Location attackerLocation = attacker.getEyeLocation();

            double x = attackerLocation.getX() - victimLocation.getX();
            double y = attackerLocation.getY() - victimLocation.getY();
            double z = attackerLocation.getZ() - victimLocation.getZ();

            Vector throwVector = new Vector(x, y, z);

            throwVector.normalize();
            throwVector.multiply(1.5D);
            throwVector.setY(1.0D);

            event.getDamager().setVelocity(throwVector);
        }


    }

    @Override
    public String getName() {
        return "Reflective";
    }

    @Override
    public TriggerType getTriggerType() {
        return TriggerType.ARMOR;
    }
}
