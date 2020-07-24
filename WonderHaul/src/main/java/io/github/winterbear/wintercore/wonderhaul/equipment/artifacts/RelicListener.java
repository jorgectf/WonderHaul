package io.github.winterbear.wintercore.wonderhaul.equipment.artifacts;

import io.github.winterbear.wintercore.WonderHaul;
import io.github.winterbear.wintercore.utils.InteractEventUtils;
import io.github.winterbear.wintercore.wonderhaul.blockstorage.BlockMetadata;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by WinterBear on 26/05/2020.
 */
public class RelicListener {

    private static final Map<Player, Long> clickCooldown = new ConcurrentHashMap<>();

    @EventHandler
    public void onInteractEvent(PlayerInteractEvent event) {
        if(InteractEventUtils.eventIsRightClickEvent(event, clickCooldown)){
            if(begin(event)){
                event.setCancelled(true);
            }
        }
    }

    private boolean begin(PlayerInteractEvent event){
        Optional<BlockMetadata> metadata = WonderHaul.getBlockStorage().get(event.getClickedBlock().getLocation());

        if(metadata.isPresent() && metadata.get().getType().equals("Relic")){

            //TODO implement relics


        }

        return false;
    }



}
