package io.github.winterbear.wintercore.wonderhaul.sockets.infusions.infusiontypes;

import io.github.winterbear.wintercore.utils.DelayUtils;
import io.github.winterbear.wintercore.utils.SoundUtils;
import io.github.winterbear.wintercore.utils.TexturedHeads;
import io.github.winterbear.wintercore.wonderhaul.dropper.Chance;
import io.github.winterbear.wintercore.wonderhaul.equipment.MaterialGroup;
import io.github.winterbear.wintercore.wonderhaul.sockets.EffectType;
import io.github.winterbear.wintercore.wonderhaul.sockets.infusions.OffensiveInfusion;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * Created by WinterBear on 30/08/2020.
 */
public class Carnivorous extends OffensiveInfusion {

    public Carnivorous(JavaPlugin plugin) {
        super(plugin);
    }

    @Override
    public void onAttack(Player attacker, EntityDamageByEntityEvent event, int socketLevel) {
        double chance = 3.0;
        if(socketLevel > 2){
            chance = 4.0;
        } else if (socketLevel > 1){
            chance = 5.0;
        }
        if(Chance.roll(chance)){
            attacker.setFoodLevel(attacker.getFoodLevel() + 1);
            playSound(attacker);
            DelayUtils.after(3, () -> playSound(attacker), plugin, 2);
        }
    }

    private void playSound(Player player){
        SoundUtils.playSound(player, Sound.ENTITY_GENERIC_EAT);
    }

    @Override
    public Supplier<ItemStack> getTexture() {
        return TexturedHeads.RAVENOUS_FLESHCRAWLER;
    }

    @Override
    public String getItemName() {
        return "Ravenous Fleshcrawler";
    }

    @Override
    public String getAbilityName() {
        return "Carnivorous";
    }

    @Override
    public ChatColor getColor() {
        return ChatColor.of("#ad4e66");
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
