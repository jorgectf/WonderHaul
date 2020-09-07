package io.github.winterbear.wintercore.utils;

import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Map;

/**
 * Created by WinterBear on 27/05/2020.
 */
public class InteractEventUtils {

    public static boolean eventIsRightClickEvent(PlayerInteractEvent event, Map<Player, Long> clickCooldown){
        //ChatUtils.info("Checking for right click event");
        if(clickCooldown.containsKey(event.getPlayer()) && System.currentTimeMillis() - clickCooldown.get(event.getPlayer()) < 100L){
            //duplicate event
            return false;
        }
        clickCooldown.put(event.getPlayer(), System.currentTimeMillis());
        //ChatUtils.info("Event is click event " + event.getAction());
        return event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK);
    }

}
