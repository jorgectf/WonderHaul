package io.github.winterbear.wintercore.wonderhaul.Equipment.Packs;

import io.github.winterbear.WinterCoreUtils.ChatUtils;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Optional;

/**
 * Created by WinterBear on 26/05/2020.
 */
public abstract class PackListener implements Listener {

    protected final JavaPlugin plugin;

    public PackListener(JavaPlugin plugin){
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public abstract boolean handle(String packName, PlayerInteractEvent event);

    protected boolean begin(PlayerInteractEvent event){

        ItemStack itemInMainHand = event.getPlayer().getInventory().getItemInMainHand();

        if(itemInMainHand != null && !itemInMainHand.getType().equals(Material.AIR)){
            Optional<String> packName = parse(itemInMainHand);

            if(packName.isPresent()){
                return handle(packName.get(), event);
            }
        }


        return false;


    }



    public static Optional<String> parse(ItemStack item){
        Optional<String> loreLine = item.getItemMeta().getLore().stream()
                .filter(line -> line.contains("⚒"))
                .map(line -> ChatUtils.uncolored(line)).findFirst();
        if(loreLine.isPresent()){
            String packName = loreLine.get().substring(8);
            return Optional.of(packName);
        }
        return Optional.empty();
    }


}
