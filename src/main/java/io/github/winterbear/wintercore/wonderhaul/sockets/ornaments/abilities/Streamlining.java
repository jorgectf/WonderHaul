package io.github.winterbear.wintercore.wonderhaul.sockets.ornaments.abilities;

import io.github.winterbear.wintercore.wonderhaul.dropper.Chance;
import io.github.winterbear.wintercore.wonderhaul.sockets.TriggerType;
import io.github.winterbear.wintercore.wonderhaul.sockets.abilitytypes.DefensiveAbility;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Created by WinterBear on 13/09/2020.
 */
public class Streamlining extends DefensiveAbility {

    @Override
    public void onDefend(Player victim, EntityDamageByEntityEvent event, int socketLevel) {
        if(Chance.roll(10.0)){
            event.setCancelled(true);
            String message = "&aDodged!";
            victim.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
        }
    }

    @Override
    public String getName() {
        return "Streamlining";
    }

    @Override
    public TriggerType getTriggerType() {
        return TriggerType.ARMOR;
    }
}
