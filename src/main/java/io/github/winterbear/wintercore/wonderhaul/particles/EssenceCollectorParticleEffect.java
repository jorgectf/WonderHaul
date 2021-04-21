package io.github.winterbear.wintercore.wonderhaul.particles;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import io.github.winterbear.WinterCoreUtils.ChatUtils;
import io.github.winterbear.wintercore.utils.BlockUtils;
import io.github.winterbear.wintercore.wonderhaul.blockstorage.BlockMetadata;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by WinterBear on 15/08/2020.
 */
public class EssenceCollectorParticleEffect implements ParticleEffect {


    public void render(BlockMetadata metadata, JavaPlugin plugin){
        double essenceCount = 0;
        double cost = 0;


        if(metadata.getProperty("EssenceCount").isPresent() && metadata.getProperty("UpgradeCost").isPresent()){
            essenceCount = Integer.parseInt(metadata.getProperty("EssenceCount").get());
            cost = Integer.parseInt(metadata.getProperty("UpgradeCost").get());
            int percentage = (int) ((essenceCount * 100.0f) / cost);
            Location loc = BlockUtils.getBlockCentre(metadata.getInternalLocation());
            if(percentage > 80){
                ParticleEngine.coloredDotCloud(235, 35, 106, loc, 4, 0.2d);
            }
            if (percentage > 60){
                ParticleEngine.coloredDotCloud(255, 215, 84, loc, 4, 0.2d);
            }
            if (percentage > 40){
                ParticleEngine.coloredDotCloud(66, 245, 173, loc, 4, 0.2d);
            }
            if (percentage > 20){
                ParticleEngine.coloredDotCloud(66, 203, 245, loc, 4, 0.2d);
            } else {
                ParticleEngine.coloredDotCloud(249, 255, 184, loc, 4, 0.2d);
            }
        }

        if(Bukkit.getPluginManager().isPluginEnabled("HolographicDisplays")){
            if(metadata.getCustomInventories().containsKey("UpgradeItem")){
                ItemStack upgradeItem = metadata.getCustomInventories().get("UpgradeItem").getItems().get(0).getInternalItem();
                if(HologramEngine.hologramDoesNotExist(metadata)){
                    Hologram hologram = HologramsAPI.createHologram(plugin, BlockUtils.getBlockCentre(metadata.getInternalLocation(), 0.0, 1.2, 0.0));
                    hologram.appendTextLine(ChatUtils.format(((int)essenceCount) + "&8/&f" + ((int)cost)));
                    hologram.appendItemLine(upgradeItem);
                    HologramEngine.setHologram(metadata, hologram);
                } else {
                    HologramEngine.getHologram(metadata).getLine(0).removeLine();
                    HologramEngine.getHologram(metadata).insertTextLine(0, ChatUtils.format(((int)essenceCount) + "&8/&f" + ((int)cost)));
                }
            } else {
                HologramEngine.removeHologram(metadata);
            }
        }
    }

}
