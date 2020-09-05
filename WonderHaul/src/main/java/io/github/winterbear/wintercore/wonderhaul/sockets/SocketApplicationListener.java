package io.github.winterbear.wintercore.wonderhaul.sockets;

import io.github.winterbear.WinterCoreUtils.ChatUtils;
import io.github.winterbear.wintercore.utils.InteractEventUtils;
import io.github.winterbear.wintercore.utils.ItemUtils;
import io.github.winterbear.wintercore.utils.RepeatingTaskUtils;
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
 * Created by WinterBear on 05/06/2019.
 */
public class SocketApplicationListener implements Listener {

    private static final int BASE_COUNTER = 15;

    private final JavaPlugin plugin;

    private static final Map<Player, Long> clickCooldown = new ConcurrentHashMap<>();

    public SocketApplicationListener(JavaPlugin plugin){
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }




    @EventHandler
    public void onInteractEvent(PlayerInteractEvent event) {
        if(InteractEventUtils.eventIsRightClickEvent(event, clickCooldown)){
            if(begin(event.getPlayer())){
                event.setCancelled(true);
            }
        }
    }

    private boolean begin(Player player){
        ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
        if(itemInMainHand != null && itemInMainHand.getType().equals(Material.PLAYER_HEAD)){
            Optional<Socketable> socketable = SocketParser.parse(itemInMainHand);
            if(socketable.isPresent()){
                //ChatUtils.info(player.getDisplayName() + "Started tag application " + tag.get());
                if(SocketApplicationRegister.get(player) != null){
                    ChatUtils.send(player,"&7You are already applying a socket, please use the first socket, right click with an empty hand, or wait 15 seconds for it to time out!");
                    return true;
                }
                initiateApplication(socketable.get(), ItemUtils.oneOf(itemInMainHand), player);
                return true;
            }
        } else {
            //ChatUtils.info(player.getDisplayName() + " - Looking for existing application...");
            SocketApplication application = SocketApplicationRegister.get(player);
            if(application != null){
                apply(player, application);
                return true;
            }
            //ChatUtils.info(player.getDisplayName() + "Found none.");
        }

        return false;
    }

    private void initiateApplication(Socketable socketable, ItemStack item, final Player player){
        SocketApplication application = SocketApplicationBuilder.create()
                .forPlayer(player)
                .withCounter(BASE_COUNTER)
                .withSocket(socketable)
                .withSocketItem(item)
                .build();
        SocketApplicationRegister.register(player, application);
        ChatUtils.send(player, socketable.getItemName() + "&8: &7You selected a " + socketable.getItemName() + "&7. " + socketable.getApplicationInstructions());
        player.getInventory().removeItem(item);
        RepeatingTaskUtils.everySeconds(1, () -> SocketApplicationRegister.countdown(player), plugin);
    }

    private void apply(Player player, SocketApplication application){
        ItemStack heldItem =  player.getInventory().getItemInMainHand();
        if(heldItem == null || heldItem.getType().equals(Material.AIR)){
            application.returnSocket(player);
            SocketApplicationRegister.remove(player);
            return;
        }
        if(application.getSocketable().apply(player.getInventory().getItemInMainHand(), application)){
            SocketApplicationRegister.remove(player);
        }
    }

}
