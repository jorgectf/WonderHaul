package io.github.winterbear.wintercore.wonderhaul.equipment.packs;

import io.github.winterbear.WinterCoreUtils.ChatUtils;
import io.github.winterbear.wintercore.utils.InteractEventUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by WinterBear on 26/05/2020.
 */
public abstract class PackListener implements Listener {

    protected final JavaPlugin plugin;

    private static final Map<Player, Long> clickCooldown = new ConcurrentHashMap<>();

    public PackListener(JavaPlugin plugin){
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public abstract boolean handle(String packName, PlayerInteractEvent event);

    @EventHandler
    public void onInteractEvent(PlayerInteractEvent event) {
        if(InteractEventUtils.eventIsRightClickEvent(event, clickCooldown)){
            if(begin(event)){
                event.setCancelled(true);
            }
        }
    }

    protected boolean begin(PlayerInteractEvent event){

        ItemStack itemInMainHand = event.getPlayer().getInventory().getItemInMainHand();

        if(!itemInMainHand.getType().equals(Material.AIR)){
            Optional<String> packName = parse(itemInMainHand);

            if(packName.isPresent()){
                return handle(packName.get(), event);
            }
        }


        return false;


    }



    public static Optional<String> parse(ItemStack item){
        if(item != null && item.getItemMeta() != null && item.getItemMeta().getLore() != null) {
            Optional<String> loreLine = item.getItemMeta().getLore().stream()
                    .filter(line -> line.contains("⚒"))
                    .map(ChatUtils::uncolored).findFirst();
            if (loreLine.isPresent()) {
                String packName = loreLine.get().substring(8);
                return Optional.of(packName);
            }
        }
        return Optional.empty();
    }


}
