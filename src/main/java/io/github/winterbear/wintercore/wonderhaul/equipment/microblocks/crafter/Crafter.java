package io.github.winterbear.wintercore.wonderhaul.equipment.microblocks.crafter;

import io.github.winterbear.wintercore.wonderhaul.crafting.CustomCrafter;
import io.github.winterbear.wintercore.wonderhaul.equipment.Microblock;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by WinterBear on 02/08/2020.
 */
public abstract class Crafter extends Microblock {

    private CustomCrafter crafter;


    public Crafter(JavaPlugin plugin, CustomCrafter crafter) {

        super(plugin);
        this.crafter = crafter;

    }


}
