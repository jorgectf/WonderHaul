package io.github.winterbear.wintercore;

import io.github.winterbear.wintercore.Annotations.SpigotPlugin;
import io.github.winterbear.wintercore.wonderhaul.Tags.TagApplicator;
import io.github.winterbear.wintercore.wonderhaul.Tags.TagGenerator;
import io.github.winterbear.wintercore.servercore.DevTools;
import org.bukkit.plugin.java.JavaPlugin;


/**
 * Created by WinterBear on 07/05/2017.
 */
@SpigotPlugin(version = "2.0")
public class WonderHaul extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        CommandRegistry.register(this, DevTools.setHealth());
        CommandRegistry.register(this, DevTools.setHunger());
        CommandRegistry.register(this, DevTools.addLore());
        CommandRegistry.register(this, DevTools.clearLore());
        CommandRegistry.register(this, DevTools.removeLore());

        CommandRegistry.register(this, TagGenerator.getTag());
        loadListeners();
    }

    private void loadListeners(){
        new TagApplicator(this);
    }

    @Override
    public void saveDefaultConfig(){
        super.saveDefaultConfig();
    }
}
