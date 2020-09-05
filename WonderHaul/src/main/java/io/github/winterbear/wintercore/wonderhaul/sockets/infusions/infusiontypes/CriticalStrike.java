package io.github.winterbear.wintercore.wonderhaul.sockets.infusions.infusiontypes;

import io.github.winterbear.wintercore.particles.ParticleEngine;
import io.github.winterbear.wintercore.utils.SoundUtils;
import io.github.winterbear.wintercore.utils.TexturedHeads;
import io.github.winterbear.wintercore.wonderhaul.dropper.Chance;
import io.github.winterbear.wintercore.wonderhaul.equipment.MaterialGroup;
import io.github.winterbear.wintercore.wonderhaul.sockets.EffectType;
import io.github.winterbear.wintercore.wonderhaul.sockets.infusions.OffensiveInfusion;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * Created by WinterBear on 28/08/2020.
 */
public class CriticalStrike extends OffensiveInfusion {

    private static final String ITEM_NAME = "Crucial Knowledge";

    private static final String ABILITY_NAME = "Critical Strike";

    private static final ChatColor COLOR = ChatColor.of("#b01962");

    public CriticalStrike(JavaPlugin plugin){
        super(plugin);
    }

    @Override
    public Supplier<ItemStack> getTexture() {
        return TexturedHeads.CRUCIAL_KNOWLEDGE;
    }

    @Override
    public String getItemName() {
        return ITEM_NAME;
    }

    @Override
    public String getAbilityName() {
        return ABILITY_NAME;
    }

    @Override
    public ChatColor getColor() {
        return COLOR;
    }

    @Override
    public EffectType getTriggerType() {
        return EffectType.MAINHAND;
    }

    @Override
    public Collection<Material> getApplicableItems() {
        return MaterialGroup.ALL_WEAPONS;
    }

    @Override
    public void onAttack(Player attacker, EntityDamageByEntityEvent event, int socketLevel) {
        double chance = 10.0;
        if(socketLevel > 2){
            chance = 18.0;
        } else if (socketLevel > 1){
            chance = 14.0;
        }
        if(Chance.roll(chance)){
            event.setDamage(event.getDamage() * 2);
            SoundUtils.playSound(attacker, Sound.ENTITY_IRON_GOLEM_REPAIR);
            ParticleEngine.particleDotCloud(Particle.CRIT, event.getEntity().getLocation(), 10, 1.0);
        }
    }

}
