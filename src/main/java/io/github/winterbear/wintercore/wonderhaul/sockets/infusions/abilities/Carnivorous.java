package io.github.winterbear.wintercore.wonderhaul.sockets.infusions.abilities;

import io.github.winterbear.wintercore.utils.DelayUtils;
import io.github.winterbear.wintercore.utils.SoundUtils;
import io.github.winterbear.wintercore.wonderhaul.dropper.Chance;
import io.github.winterbear.wintercore.wonderhaul.sockets.TriggerType;
import io.github.winterbear.wintercore.wonderhaul.sockets.abilitytypes.OffensiveAbility;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Created by WinterBear on 30/08/2020.
 */
public class Carnivorous extends OffensiveAbility {

    @Override
    public void onAttack(Player attacker, EntityDamageByEntityEvent event, int socketLevel) {
        double chance = 10.0;
        if(socketLevel > 2){
            chance = 15.0;
        } else if (socketLevel > 1){
            chance = 12.0;
        }
        if(Chance.roll(chance)){
            attacker.setFoodLevel(attacker.getFoodLevel() + 1);
            playSound(attacker);
            DelayUtils.after(3, () -> playSound(attacker), plugin, 2);
        }
    }

    private void playSound(Player player){
        SoundUtils.playSound(player, Sound.ENTITY_GENERIC_EAT);
    }


    @Override
    public String getName() {
        return "Carnivorous";
    }

    @Override
    public TriggerType getTriggerType() {
        return TriggerType.MAINHAND;
    }

}
