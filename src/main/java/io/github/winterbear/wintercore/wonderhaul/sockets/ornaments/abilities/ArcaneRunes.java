package io.github.winterbear.wintercore.wonderhaul.sockets.ornaments.abilities;

import io.github.winterbear.wintercore.wonderhaul.sockets.TriggerType;
import io.github.winterbear.wintercore.wonderhaul.sockets.abilitytypes.XPAbility;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerExpChangeEvent;

/**
 * Created by WinterBear on 13/09/2020.
 */
public class ArcaneRunes extends XPAbility {


    @Override
    public void onXPChange(Player player, PlayerExpChangeEvent event, int socketLevel) {
        int xpAmount = event.getAmount();
        if(xpAmount > 0){
            event.setAmount(xpAmount * 2);
        }
    }

    @Override
    public String getName() {
        return "Arcane Runes";
    }

    @Override
    public TriggerType getTriggerType() {
        return TriggerType.ARMOR;
    }

    @Override
    public String getDescription() {
        return "Increases your XP gain from all sources";
    }
}
