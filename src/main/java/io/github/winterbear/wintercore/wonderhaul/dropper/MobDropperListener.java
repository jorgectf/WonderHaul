package io.github.winterbear.wintercore.wonderhaul.dropper;

import io.github.winterbear.wintercore.Annotations.Dependency;
import io.github.winterbear.wintercore.Annotations.DependencyType;
import io.github.winterbear.wintercore.utils.EventUtils;
import io.github.winterbear.wintercore.utils.SoundUtils;
import me.lorinth.rpgmobs.LorinthsRpgMobs;
import org.bukkit.Bukkit;
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
@Dependency(name = "LorinthsRpgMobs", type = DependencyType.SOFT)
public class MobDropperListener implements Listener {

    public MobDropperListener(JavaPlugin plugin){
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

        Optional<Entity> attacker = EventUtils.getAttacker(event.getEntity().getLastDamageCause());

        if(attacker.isPresent() && attacker.get() instanceof Player) {


            List<ItemStack> drops = ItemDropper.generate(
                    event.getEntity().getLocation().getChunk(),
                    getBiome(event),
                    event.getEntity().getType(),
                    luckModifier + getLevelModifier(event.getEntity())
            );

            if(!drops.isEmpty()) {
                SoundUtils.playSound((Player) attacker.get(), Sound.BLOCK_ENCHANTMENT_TABLE_USE);
                event.getDrops().addAll(drops);
            }
        }
    }

    private static float getLevelModifier(Entity e){
        if (Bukkit.getPluginManager().getPlugin("LorinthsRpgMobs") != null) {
            Integer level = LorinthsRpgMobs.GetLevelOfEntity(e);
            Float lmod = getLevelModifier(level);
            if (lmod != null) return lmod;

        }

        return 0.0f;
    }

    public static Float getLevelModifier(Integer level) {
        if(level != null) {
            if (level != 1 && level < 100) {
                float lmod = 0.095f;
                return lmod * (level - 1);
            } else if (level >= 100) {
                float lmod = 0.101f;
                return 15.0f + (lmod * (level - 100));
            }
        }
        return null;
    }

    private World getWorld(EntityDeathEvent event){
        return event.getEntity().getWorld();
    }

    private Biome getBiome(EntityDeathEvent event){
        Entity e = event.getEntity();
        return e.getWorld().getBlockAt(e.getLocation().getBlockX(), e.getLocation().getBlockY()-1, e.getLocation().getBlockZ()).getBiome();
    }



}
