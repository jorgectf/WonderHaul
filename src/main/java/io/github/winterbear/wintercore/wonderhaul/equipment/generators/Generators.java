package io.github.winterbear.wintercore.wonderhaul.equipment.generators;

import io.github.winterbear.wintercore.wonderhaul.microblocks.crates.CrateIGenerator;
import io.github.winterbear.wintercore.wonderhaul.microblocks.crates.CrateIIGenerator;
import io.github.winterbear.wintercore.wonderhaul.microblocks.decorations.CandleRollingTableGenerator;
import io.github.winterbear.wintercore.wonderhaul.microblocks.decorations.StoneCutterGenerator;
import io.github.winterbear.wintercore.wonderhaul.microblocks.decorations.StuffedToyGenerator;
import io.github.winterbear.wintercore.wonderhaul.microblocks.essencecollector.EssenceCollectorGenerator;
import io.github.winterbear.wintercore.wonderhaul.microblocks.relic.RelicGenerator;
import io.github.winterbear.wintercore.wonderhaul.packs.PackGenerator;
import io.github.winterbear.wintercore.wonderhaul.tags.TagGenerator;

/**
 * Created by WinterBear on 17/06/2019.
 */
public class Generators {

    public static Generator JUNK = new JunkGenerator();

    public static Generator ORDINARY = new OrdinaryGenerator();

    public static Generator UNUSUAL = new UnusualGenerator();

    public static Generator RARE = new RareGenerator();

    public static Generator RELIC = new RelicGenerator();

    public static Generator TAG = new TagGenerator();

    public static Generator STUFFED_TOY = new StuffedToyGenerator();

    public static Generator PACK = new PackGenerator();

    public static Generator CRATE_I = new CrateIGenerator();

    public static Generator CRATE_II = new CrateIIGenerator();

    public static Generator ESSENCE_COLLECTOR = new EssenceCollectorGenerator();

    public static Generator CANDLE_ROLLING_TABLE_GENERATOR = new CandleRollingTableGenerator();

    public static Generator CARVING_STATION_GENERATOR = new StoneCutterGenerator();

    public static Generator fromName(String name){
        if(name.equalsIgnoreCase("junk")){
            return JUNK;
        } else if (name.equalsIgnoreCase("ordinary")){
            return ORDINARY;
        } else if (name.equalsIgnoreCase("unusual")){
            return UNUSUAL;
        } else if (name.equalsIgnoreCase("rare")){
            return RARE;
        } else if (name.equalsIgnoreCase("relic")){
            return RELIC;
        } else if (name.equalsIgnoreCase("tag")){
            return TAG;
        } else if (name.equalsIgnoreCase("pack")){
            return PACK;
        } else if (name.equalsIgnoreCase("toy")){
            return STUFFED_TOY;
        } else if (name.equalsIgnoreCase("essence collector")){
            return ESSENCE_COLLECTOR;
        } else if (name.equalsIgnoreCase("candle rolling table")){
            return CANDLE_ROLLING_TABLE_GENERATOR;
        } else if (name.equalsIgnoreCase("carving station")){
            return CARVING_STATION_GENERATOR;
        }
        return null;
    }




}
