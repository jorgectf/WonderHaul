package io.github.winterbear.wintercore.wonderhaul.equipment;

import io.github.winterbear.wintercore.WonderHaul;
import io.github.winterbear.wintercore.utils.InteractEventUtils;
import io.github.winterbear.wintercore.wonderhaul.blockstorage.BlockMetadata;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by WinterBear on 27/07/2020.
 */
public abstract class Microblock implements Listener  {

    private static final List<Material> MICROBLOCK_TYPES = Arrays.asList(Material.PLAYER_HEAD, Material.PLAYER_WALL_HEAD);

    private final Map<Player, Long> clickCooldown = new ConcurrentHashMap<>();

    protected final JavaPlugin plugin;

    public Microblock(JavaPlugin plugin){
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onInteractEvent(PlayerInteractEvent event){
        if(InteractEventUtils.eventIsRightClickEvent(event, clickCooldown)
            && event.getClickedBlock() != null
            && MICROBLOCK_TYPES.contains(event.getClickedBlock().getType())){
            Optional<BlockMetadata> metadata = WonderHaul.getBlockStorage().get(event.getClickedBlock().getLocation());
            if(metadata.isPresent() && metadata.get().getType().equalsIgnoreCase(getReference())){
                if(interact(event, metadata.get())){
                    event.setCancelled(true);
                }
            }
        }
    }

    public abstract boolean interact(PlayerInteractEvent event, BlockMetadata metadata);


    public abstract String getReference();

}
