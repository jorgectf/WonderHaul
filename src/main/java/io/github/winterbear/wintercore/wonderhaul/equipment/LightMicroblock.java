package io.github.winterbear.wintercore.wonderhaul.equipment;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by WinterBear on 18/04/2021.
 */
public abstract class LightMicroblock extends Microblock {

    public LightMicroblock(JavaPlugin plugin) {
        super(plugin);
    }

    @Override
    public boolean isLight(){
        return true;
    }
}
