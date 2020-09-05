package io.github.winterbear.wintercore.wonderhaul.sockets.infusions.infusiontypes;

import io.github.winterbear.wintercore.utils.BlockUtils;
import io.github.winterbear.wintercore.utils.TexturedHeads;
import io.github.winterbear.wintercore.wonderhaul.equipment.MaterialGroup;
import io.github.winterbear.wintercore.wonderhaul.sockets.EffectType;
import io.github.winterbear.wintercore.wonderhaul.sockets.infusions.OffensiveInfusion;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * Created by WinterBear on 30/08/2020.
 */
public class AquaticAttunement extends OffensiveInfusion {

    private static final String ITEM_NAME = "Deep Sea Pumpkin";

    private static final String ABILITY_NAME = "Aquatic Attunement";

    private static final ChatColor COLOR = ChatColor.of("#45c8f7");

    public AquaticAttunement(JavaPlugin plugin) {
        super(plugin);
    }

    @Override
    public Supplier<ItemStack> getTexture() {
        return TexturedHeads.SEA_PUMPKIN;
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
        if(BlockUtils.isUnderwater(attacker.getLocation())){
            event.setDamage(event.getDamage() * 1.25);
        }
    }
}
