package io.github.winterbear.wintercore;

import io.github.winterbear.WinterCoreUtils.ChatUtils;
import io.github.winterbear.WinterCoreUtils.CommandRegistry;
import io.github.winterbear.WinterCoreUtils.CommandWrapper;
import io.github.winterbear.wintercore.Annotations.Command;
import io.github.winterbear.wintercore.Annotations.SpigotPlugin;
import io.github.winterbear.wintercore.wonderhaul.Dropper.MobDropperListener;
import io.github.winterbear.wintercore.wonderhaul.Equipment.Enchanting.EnchantConfig;
import io.github.winterbear.wintercore.wonderhaul.Equipment.Enchanting.Enchantments;
import io.github.winterbear.wintercore.wonderhaul.Equipment.ItemNames;
import io.github.winterbear.wintercore.wonderhaul.Equipment.Lore;
import io.github.winterbear.wintercore.wonderhaul.Equipment.Packs.ExperiencePackListener;
import io.github.winterbear.wintercore.wonderhaul.Equipment.Prefixes;
import io.github.winterbear.wintercore.wonderhaul.Tags.TagApplicationListener;
import io.github.winterbear.wintercore.wonderhaul.data.PersistentDataHolder;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/**
 *
 *    ██╗    ██╗ ██████╗ ███╗   ██╗██████╗ ███████╗██████╗ ██╗  ██╗ █████╗ ██╗   ██╗██╗       ██╗ ██╗
 *    ██║    ██║██╔═══██╗████╗  ██║██╔══██╗██╔════╝██╔══██╗██║  ██║██╔══██╗██║   ██║██║       ██║ ██║
 *    ██║ █╗ ██║██║   ██║██╔██╗ ██║██║  ██║█████╗  ██████╔╝███████║███████║██║   ██║██║       ██║ ██║
 *    ██║███╗██║██║   ██║██║╚██╗██║██║  ██║██╔══╝  ██╔══██╗██╔══██║██╔══██║██║   ██║██║       ██║ ██║
 *    ╚███╔███╔╝╚██████╔╝██║ ╚████║██████╔╝███████╗██║  ██║██║  ██║██║  ██║╚██████╔╝███████╗  ██║ ██║
 *     ╚══╝╚══╝  ╚═════╝ ╚═╝  ╚═══╝╚═════╝ ╚══════╝╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═╝ ╚═════╝ ╚══════╝  ╚═╝ ╚═╝
 *
 *     Created by WinterBear on 07/05/2017.
 */
@SpigotPlugin(version = "2.0.1")
public class WonderHaul extends JavaPlugin {

    private List<PersistentDataHolder> dataHolders = new ArrayList<>();

    @Override
    public void onEnable() {
        ChatUtils.info("Loading WonderHaul");
        saveDefaultConfig();
        ChatUtils.info("Registering WonderHaul commands");
        loadCommands();
        ChatUtils.info("Registering WonderHaul event listeners");
        loadListeners();
        ChatUtils.info("Registering WonderHaul configuration entities");
        loadConfigEntities();
        ChatUtils.info("Loading WonderHaul config files");
        loadConfigs();
    }

    @Override
    public void onDisable() {
        dataHolders.forEach(PersistentDataHolder::save);
    }

    private void loadConfigs(){
        Enchantments.enable(this);
        Prefixes.enable(this);
        ItemNames.enable(this);
        Lore.enable(this);

    }

    private void loadCommands() {
        Set<Method> methods = getCommands();
        for (Method method : methods){
            try {
                CommandRegistry.register(this, (CommandWrapper) method.invoke(null));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void loadListeners(){
        new TagApplicationListener(this);
        new MobDropperListener(this);
        new ExperiencePackListener(this);
        //dataHolders.add(new MicroblockDataListener(this)); //Disabled until database support added


    }

    private void loadConfigEntities(){
        ConfigurationSerialization.registerClass(EnchantConfig.class, "EnchantConfig");
    }

    @Override
    public void saveDefaultConfig(){
        super.saveDefaultConfig();
    }

    public static Set<Method> getCommands() {
        return new Reflections("io.github.winterbear.wintercore", new MethodAnnotationsScanner()).getMethodsAnnotatedWith(Command.class);
    }
}
