package io.github.winterbear.wintercore.wonderhaul.Dropper;

import io.github.winterbear.wintercore.utils.EventUtils;
import io.github.winterbear.wintercore.utils.SoundUtils;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Optional;

/**
 * Created by WinterBear on 13/06/2019.
 */
public class MobDropper implements Listener {

    public MobDropper(JavaPlugin plugin){
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }


    @EventHandler
    public void onEntityDeathEvent(EntityDeathEvent event){
        drop(event);
    }


    private void drop(EntityDeathEvent event){
        if(EventUtils.entityIsPlayer(event)){
            return;
        }

        float luckModifier = 1.0f;

        if(EventUtils.damageCauseIsPlayer(event.getEntity().getLastDamageCause())){

        }

        List<ItemStack> drops = ItemDropper.generate(
                getWorld(event),
                getBiome(event),
                event.getEntity().getType(),
                luckModifier
        );



        if(!drops.isEmpty()){
            Optional<Entity> attacker = EventUtils.getAttacker(event.getEntity().getLastDamageCause());
            if(attacker.isPresent() && attacker.get() instanceof Player){
                SoundUtils.playSound((Player) attacker.get(), Sound.BLOCK_ENCHANTMENT_TABLE_USE);
            }

            event.getDrops().addAll(drops);

            //log something probably

        }


    }

    private World getWorld(EntityDeathEvent event){
        return event.getEntity().getWorld();
    }

    private Biome getBiome(EntityDeathEvent event){
        Entity e = event.getEntity();
        return e.getWorld().getBlockAt(e.getLocation().getBlockX(), e.getLocation().getBlockY()-1, e.getLocation().getBlockZ()).getBiome();
    }



}
