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
 * Created by WinterBear on 30/08/2020.
 */
public class FirstStrike extends OffensiveInfusion {


    public FirstStrike(JavaPlugin plugin) {
        super(plugin);
    }

    @Override
    public void onAttack(Player attacker, EntityDamageByEntityEvent event, int socketLevel) {
        if(event.getEntity() instanceof LivingEntity){
            LivingEntity victim = (LivingEntity) event.getEntity();
            double health = victim.getHealth();
            AttributeInstance attribute = victim.getAttribute(Attribute.GENERIC_MAX_HEALTH);
            if(attribute != null) {
                double maxHealth = attribute.getValue();
                if (health == maxHealth) {
                    SoundUtils.playSound(attacker, Sound.ENTITY_IRON_GOLEM_REPAIR);
                    event.setDamage(event.getDamage() * 1.5);
                }
            }
        }
    }

    @Override
    public Supplier<ItemStack> getTexture() {
        return TexturedHeads.JEWEL_OF_AGILITY;
    }

    @Override
    public String getItemName() {
        return "Jewel of Agility";
    }

    @Override
    public String getAbilityName() {
        return "First Strike";
    }

    @Override
    public ChatColor getColor() {
        return ChatColor.of("#ff4942");
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
