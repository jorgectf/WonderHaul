package io.github.winterbear.wintercore;

import io.github.winterbear.wintercore.WonderHaul.Tags.TagApplicator;
import io.github.winterbear.wintercore.WonderHaul.Tags.TagGenerator;
import io.github.winterbear.wintercore.servercore.DevTools;
import org.bukkit.plugin.java.JavaPlugin;


/**
 * Created by WinterBear on 07/05/2017.
 */
public class WinterCore extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        CommandRegistry.register(this, DevTools.setHealth());
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
