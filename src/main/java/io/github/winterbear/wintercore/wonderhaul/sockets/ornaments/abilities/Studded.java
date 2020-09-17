package io.github.winterbear.wintercore.wonderhaul.sockets.ornaments.abilities;

import io.github.winterbear.wintercore.wonderhaul.sockets.TriggerType;
import io.github.winterbear.wintercore.wonderhaul.sockets.abilitytypes.DefensiveAbility;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.meta.Damageable;

/**
 * Created by WinterBear on 13/09/2020.
 */
public class Studded extends DefensiveAbility {

    @Override
    public void onDefend(Player victim, EntityDamageByEntityEvent event, int socketLevel) {
        Entity attacker = event.getDamager();
        if(attacker instanceof LivingEntity){
            EntityEquipment equipment = ((LivingEntity) attacker).getEquipment();
            if(equipment != null && equipment.getItemInMainHand() instanceof Damageable){
                Damageable tool = (Damageable) equipment.getItemInMainHand();
                int damage = tool.getDamage();
                if(tool.getDamage() > 0) {
                    int newDamage = damage - 3;
                    if (newDamage < 1) {
                        newDamage = 1;
                    }
                    tool.setDamage(newDamage);
                }
            }
        }
    }

    @Override
    public String getName() {
        return "Studded";
    }

    @Override
    public TriggerType getTriggerType() {
        return TriggerType.ARMOR;
    }
}
