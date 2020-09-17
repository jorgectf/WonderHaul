package io.github.winterbear.wintercore;

import io.github.winterbear.WinterCoreUtils.ChatUtils;
import io.github.winterbear.WinterCoreUtils.CommandRegistry;
import io.github.winterbear.WinterCoreUtils.CommandWrapper;
import io.github.winterbear.wintercore.Annotations.Command;
import io.github.winterbear.wintercore.Annotations.SpigotPlugin;
import io.github.winterbear.wintercore.database.HibernateUtil;
import io.github.winterbear.wintercore.particles.ParticleEngine;
import io.github.winterbear.wintercore.utils.DelayUtils;
import io.github.winterbear.wintercore.wonderhaul.MicroblockDataListener;
import io.github.winterbear.wintercore.wonderhaul.blockstorage.BlockStorage;
import io.github.winterbear.wintercore.wonderhaul.data.PersistentDataHolder;
import io.github.winterbear.wintercore.wonderhaul.dropper.MobDropperListener;
import io.github.winterbear.wintercore.wonderhaul.equipment.ItemNames;
import io.github.winterbear.wintercore.wonderhaul.equipment.Lore;
import io.github.winterbear.wintercore.wonderhaul.equipment.Microblocks;
import io.github.winterbear.wintercore.wonderhaul.equipment.Prefixes;
import io.github.winterbear.wintercore.wonderhaul.equipment.enchanting.EnchantConfig;
import io.github.winterbear.wintercore.wonderhaul.equipment.enchanting.Enchantments;
import io.github.winterbear.wintercore.wonderhaul.equipment.packs.ExperiencePackListener;
import io.github.winterbear.wintercore.wonderhaul.sockets.Sockets;
import io.github.winterbear.wintercore.wonderhaul.sockets.infusions.Infusions;
import io.github.winterbear.wintercore.wonderhaul.sockets.ornaments.Ornaments;
import io.github.winterbear.wintercore.wonderhaul.tags.Tags;
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

    private static BlockStorage blockStorage;

    private List<PersistentDataHolder> dataHolders = new ArrayList<>();

    private static boolean databaseConfigured = false;

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
        PluginConfig config = new PluginConfig(this);
        HibernateUtil.setConfig(config);
        try {
            HibernateUtil.setupDatabase();
            databaseConfigured = true;
        } catch (Exception e){
            databaseConfigured = false;
            ChatUtils.error("An error occurred while enabling the database. Please check your config. Block persistence will not work until this is resolved.");
            e.printStackTrace();
        }

        Tags.registerListeners(this);
        Sockets.registerListeners(this);
        new MobDropperListener(this);
        new ExperiencePackListener(this);
        if(databaseConfigured) {
            DelayUtils.after(1, this::loadData, this);
        }
        Microblocks.registerAll(this);
        Infusions.registerAll(this);
        Ornaments.registerAll(this);
        ParticleEngine.start(this);
    }

    private void loadData(){
        MicroblockDataListener microblockDataListener = new MicroblockDataListener(this);
        blockStorage = microblockDataListener.getBlockStorage();
        dataHolders.add(microblockDataListener);
    }

    public static BlockStorage getBlockStorage() {
        return blockStorage;
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
