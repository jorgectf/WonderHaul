package io.github.winterbear.wintercore.wonderhaul.sockets.infusions.abilities;

import io.github.winterbear.wintercore.utils.SoundUtils;
import io.github.winterbear.wintercore.wonderhaul.sockets.TriggerType;
import io.github.winterbear.wintercore.wonderhaul.sockets.abilitytypes.OffensiveAbility;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Created by WinterBear on 30/08/2020.
 */
public class FirstStrike extends OffensiveAbility {

    @Override
    public void onAttack(Player attacker, EntityDamageByEntityEvent event, int socketLevel) {
        if(event.getEntity() instanceof LivingEntity){
            LivingEntity victim = (LivingEntity) event.getEntity();
            double health = victim.getHealth();
            AttributeInstance attribute = victim.getAttribute(Attribute.GENERIC_MAX_HEALTH);
            if(attribute != null) {
                double maxHealth = attribute.getValue();
                if (health == maxHealth) {
                    SoundUtils.playSound(attacker, Sound.ENTITY_IRON_GOLEM_REPAIR);
                    event.setDamage(event.getDamage() * 1.5);
                }
            }
        }
    }

    @Override
    public String getName() {
        return "First Strike";
    }

    @Override
    public TriggerType getTriggerType() {
        return TriggerType.MAINHAND;
    }

    @Override
    public String getDescription() {
        return "Increases damage against healthy enemies";
    }
}
