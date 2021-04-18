package io.github.winterbear.wintercore.wonderhaul.equipment;

import io.github.winterbear.wintercore.wonderhaul.equipment.microblocks.crates.CrateI;
import io.github.winterbear.wintercore.wonderhaul.equipment.microblocks.crates.CrateII;
import io.github.winterbear.wintercore.wonderhaul.equipment.microblocks.crates.VerticalCrate;
import io.github.winterbear.wintercore.wonderhaul.equipment.microblocks.decorations.Candle;
import io.github.winterbear.wintercore.wonderhaul.equipment.microblocks.decorations.CandleRollingTable;
import io.github.winterbear.wintercore.wonderhaul.equipment.microblocks.decorations.Stonecutter;
import io.github.winterbear.wintercore.wonderhaul.equipment.microblocks.decorations.StuffedToy;
import io.github.winterbear.wintercore.wonderhaul.equipment.microblocks.essencecollector.EssenceCollector;
import io.github.winterbear.wintercore.wonderhaul.equipment.microblocks.relic.Relic;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by WinterBear on 30/08/2020.
 */
public class Microblocks {

    public static final Map<String, Microblock> MICROBLOCKS = new HashMap<>();

    public static void registerAll(JavaPlugin plugin){
        load(new Relic(plugin));
        load(new EssenceCollector(plugin));
        load(new VerticalCrate(new CrateI(), plugin));
        load(new VerticalCrate(new CrateII(), plugin));
        load(new StuffedToy(plugin));
        load(new Candle(plugin));
        load(new CandleRollingTable(plugin));
        load(new Stonecutter(plugin));
    }

    private static void load(Microblock microblock){
        MICROBLOCKS.put(microblock.getReference(), microblock);
    }

    public static Microblock get(String reference){
        return MICROBLOCKS.get(reference);
    }


}
