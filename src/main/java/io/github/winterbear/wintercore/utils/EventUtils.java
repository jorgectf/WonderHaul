package io.github.winterbear.wintercore.utils;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.PluginManager;

import java.util.Optional;

/**
 * Created by WinterBear on 07/07/2019.
 */
public class EventUtils {

    public static boolean damageCauseIsPlayer(EntityDamageEvent event){
        Optional<Entity> attacker = getAttacker(event);
        return attacker.isPresent() && attacker.get() instanceof Player;
    }

    public static Optional<Entity> getAttacker(EntityDamageEvent event){
        if(event instanceof EntityDamageByEntityEvent){
            EntityDamageByEntityEvent entityEvent = (EntityDamageByEntityEvent) event;
            return Optional.of(entityEvent.getDamager());
        }
        return Optional.empty();
    }

    public static boolean entityIsPlayer(EntityDeathEvent event) {
        return event.getEntity().getType().equals(EntityType.PLAYER);
    }

    public static boolean simulateBlockBreak(Block block, Player player, boolean shouldArmSwing) {
        PluginManager pluginManager = Bukkit.getServer().getPluginManager();

        // Support for NoCheat
        if (shouldArmSwing) {
            FakePlayerAnimationEvent event = new FakePlayerAnimationEvent(player);
            pluginManager.callEvent(event);
        }

        FakeBlockDamageEvent damageEvent = new FakeBlockDamageEvent(player, block, player.getInventory().getItemInMainHand(), true);
        pluginManager.callEvent(damageEvent);

        FakeBlockBreakEvent breakEvent = new FakeBlockBreakEvent(block, player);
        pluginManager.callEvent(breakEvent);

        return !damageEvent.isCancelled() && !breakEvent.isCancelled();
    }
}
