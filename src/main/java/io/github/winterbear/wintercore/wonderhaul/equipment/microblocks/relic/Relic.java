package io.github.winterbear.wintercore.wonderhaul.equipment.microblocks.relic;

import io.github.winterbear.WinterCoreUtils.ChatUtils;
import io.github.winterbear.wintercore.WonderHaul;
import io.github.winterbear.wintercore.particles.ParticleEngine;
import io.github.winterbear.wintercore.utils.BlockUtils;
import io.github.winterbear.wintercore.utils.ItemUtils;
import io.github.winterbear.wintercore.utils.LoreUtils;
import io.github.winterbear.wintercore.utils.SoundUtils;
import io.github.winterbear.wintercore.wonderhaul.blockstorage.BlockMetadata;
import io.github.winterbear.wintercore.wonderhaul.equipment.Microblock;
import io.github.winterbear.wintercore.wonderhaul.equipment.Tier;
import io.github.winterbear.wintercore.wonderhaul.equipment.generators.Generators;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by WinterBear on 26/05/2020.
 */
public class Relic extends Microblock {

    private static final Map<Tier, RelicChance> CHANCES = chances();

    public Relic(JavaPlugin plugin) {
        super(plugin);
    }


    private static Map<Tier, RelicChance> chances(){

        Map<Tier, RelicChance> map = new HashMap<>();

        map.put(Tier.JUNK, new RelicChance(5.0f, 100.0f));
        map.put(Tier.ORDINARY, new RelicChance(8.0f, 20.0f, 0.1f));
        map.put(Tier.UNUSUAL, new RelicChance(60.0f, 5.0f));
        map.put(Tier.RARE, new RelicChance(80.0f, 0.0f));

        return map;

    }


    @Override
    public boolean interact(PlayerInteractEvent event, BlockMetadata metadata) {

        ItemStack tool = event.getPlayer().getInventory().getItemInMainHand();

        if(tool == null || Material.AIR.equals(tool.getType())){
            return true;
        }
        if(!metadata.getProperty("Owner").isPresent() && metadata.getProperty("Owner").get().equals(event.getPlayer().getUniqueId().toString())){
            ChatUtils.send(event.getPlayer(), "&5Relic &8>> &7This isn't your Relic.");
        }

        if(LoreUtils.getTier(tool).isPresent()) {
            event.getPlayer().getInventory().removeItem(ItemUtils.oneOf(tool));
            Tier tier = LoreUtils.getTier(tool).get();

            RelicChance chance = CHANCES.get(tier);
            if (chance == null) {
                ChatUtils.send(event.getPlayer(), "&5Relic &8>> &7That item is too powerful to be sacrificed.");
            } else if (chance.getIdentifyChance().roll()) {

                Tier upgradedTier = Tier.upgrade(tier);
                ItemStack drop;
                if(upgradedTier.isUpgradable() && chance.getDoubleUpgradeChance().roll()) {
                    drop = doubleUpgrade(tier);
                } else if(tier.isUpgradable() && chance.getUpgradeChance().roll()){
                    drop = upgrade(tier);
                } else {
                    drop = generate(tier);
                }

                event.getPlayer().getInventory().setItemInMainHand(drop);
                ChatUtils.send(event.getPlayer(), "&5Relic &8>> &7Sacrificed a " + tier.getColor() + tier.getName() + " tier &7item. The relic dropped an item.");
                SoundUtils.playSound(event.getPlayer(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER);
                ParticleEngine.particleDotCloud(Particle.SMOKE_NORMAL,
                        BlockUtils.getBlockCentre(metadata.getInternalLocation()),
                        80,
                        0.3);
                event.getClickedBlock().setType(Material.AIR);
                WonderHaul.getBlockStorage().clearBlockMetadata(metadata.getInternalLocation());
            } else {
                ChatUtils.send(event.getPlayer(), "&5Relic &8>> &7Sacrificed a " + tier.getColor() + tier.getName() + " tier &7item. It disintegrated into ash.");
                SoundUtils.playSound(event.getPlayer(), Sound.BLOCK_LAVA_EXTINGUISH);
                ParticleEngine.particleDotCloud(Particle.SMOKE_NORMAL,
                        BlockUtils.getBlockCentre(metadata.getInternalLocation()),
                        20,
                        0.3);
                metadata.getInternalLocation().getWorld().spawn(metadata.getInternalLocation(), ExperienceOrb.class).setExperience(200);
            }
        } else {
            ChatUtils.send(event.getPlayer(), "&5Relic &8>> &7You can only sacrifice a wonderhaul item.");
        }

        return true;
    }

    private static ItemStack generate(Tier tier){
        return Generators.fromName(tier.getName()).create();
    }

    private static ItemStack upgrade(Tier tier){
        return Generators.fromName(Tier.upgrade(tier).getName()).create();
    }

    private static ItemStack doubleUpgrade(Tier tier){
        return Generators.fromName(Tier.upgrade(Tier.upgrade(tier)).getName()).create();
    }

    @Override
    public String getReference() {
        return "Relic";
    }
}
