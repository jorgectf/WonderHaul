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

import java.util.HashSet;
import java.util.Set;

/**
 * Created by WinterBear on 30/08/2020.
 */
public class Microblocks {

    public static final Set<Microblock> MICROBLOCKS = new HashSet<>();

    public static void registerAll(JavaPlugin plugin){
        MICROBLOCKS.add(new Relic(plugin));
        MICROBLOCKS.add(new EssenceCollector(plugin));
        MICROBLOCKS.add(new VerticalCrate(new CrateI(), plugin));
        MICROBLOCKS.add(new VerticalCrate(new CrateII(), plugin));
        MICROBLOCKS.add(new StuffedToy(plugin));
        MICROBLOCKS.add(new Candle(plugin));
        MICROBLOCKS.add(new CandleRollingTable(plugin));
        MICROBLOCKS.add(new Stonecutter(plugin));
    }


}
