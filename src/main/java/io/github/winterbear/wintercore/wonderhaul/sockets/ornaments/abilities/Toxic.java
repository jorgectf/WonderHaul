package io.github.winterbear.wintercore.wonderhaul.sockets.ornaments.abilities;

import io.github.winterbear.wintercore.wonderhaul.dropper.Chance;
import io.github.winterbear.wintercore.wonderhaul.sockets.TriggerType;
import io.github.winterbear.wintercore.wonderhaul.sockets.abilitytypes.DefensiveAbility;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Created by WinterBear on 13/09/2020.
 */
public class Toxic extends DefensiveAbility {

    @Override
    public void onDefend(Player victim, EntityDamageByEntityEvent event, int socketLevel) {
        if(event.getDamager() instanceof LivingEntity && Chance.roll(10.0)){
            LivingEntity attacker = (LivingEntity) event.getDamager();
            new PotionEffect(PotionEffectType.POISON, 100, 2).apply(attacker);
        }
    }

    @Override
    public String getName() {
        return "Toxic";
    }

    @Override
    public TriggerType getTriggerType() {
        return TriggerType.ARMOR;
    }
}
