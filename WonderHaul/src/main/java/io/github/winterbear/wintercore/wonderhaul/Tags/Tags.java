package io.github.winterbear.wintercore.wonderhaul.Tags;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by WinterBear on 26/05/2020.
 */
public class Tags {

    public static final Tag LORE = new LoreTag();

    public static final Tag REPAIR = new RepairTag();

    public static final Tag BOOST = new BoostTag();

    public static final Tag MIMIC = new MimicTag();

    private static Map<String, Tag> tagMap = createTagMap();

    private static Map<String, Tag> createTagMap(){
        Map<String, Tag> map = new HashMap<>();
        map.put("Repair Tag", REPAIR);
        map.put("Boost Tag", BOOST);
        map.put("Mimic Tag", MIMIC);
        map.put("Lore Tag", LORE);
        return map;
    }

    public static Tag get(String reference){
        return tagMap.get(reference);
    }


}
