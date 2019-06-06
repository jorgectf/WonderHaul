package io.github.winterbear.wintercore;

import io.github.winterbear.wintercore.servercore.DevTools;
import org.bukkit.plugin.java.JavaPlugin;


/**
 * Created by WinterBear on 07/05/2017.
 */
public class WinterCore extends JavaPlugin {

    @Override
    public void onEnable() {
        CommandRegistry.register(this, DevTools.setHealth());
    }
}
