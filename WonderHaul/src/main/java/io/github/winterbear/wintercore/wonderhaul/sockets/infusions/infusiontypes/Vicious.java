package io.github.winterbear.wintercore.wonderhaul.sockets.infusions.infusiontypes;

import io.github.winterbear.wintercore.utils.SoundUtils;
import io.github.winterbear.wintercore.utils.TexturedHeads;
import io.github.winterbear.wintercore.wonderhaul.equipment.MaterialGroup;
import io.github.winterbear.wintercore.wonderhaul.sockets.EffectType;
import io.github.winterbear.wintercore.wonderhaul.sockets.infusions.OffensiveInfusion;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * Created by WinterBear on 31/08/2020.
 */
public class Vicious extends OffensiveInfusion {

    public Vicious(JavaPlugin plugin) {
        super(plugin);
    }

    @Override
    public void onAttack(Player attacker, EntityDamageByEntityEvent event, int socketLevel) {
        if(event.getEntity() instanceof LivingEntity){
            LivingEntity victim = (LivingEntity) event.getEntity();
            double health = victim.getHealth();
            AttributeInstance attribute = victim.getAttribute(Attribute.GENERIC_MAX_HEALTH);
            if(attribute != null) {
                double limit = attribute.getValue() / 4.0;
                if (health <= limit) {
                    SoundUtils.playSound(attacker, Sound.ENTITY_IRON_GOLEM_REPAIR);
                    event.setDamage(event.getDamage() * 1.5);
                }
            }
        }
    }

    @Override
    public Supplier<ItemStack> getTexture() {
        return TexturedHeads.CRIMSON_SKULL;
    }

    @Override
    public String getItemName() {
        return "Crimson Skull";
    }

    @Override
    public String getAbilityName() {
        return "Vicious";
    }

    @Override
    public ChatColor getColor() {
        return ChatColor.of("#940000");
    }

    @Override
    public EffectType getTriggerType() {
        return EffectType.MAINHAND;
    }

    @Override
    public Collection<Material> getApplicableItems() {
        return MaterialGroup.ALL_WEAPONS;
    }
}
