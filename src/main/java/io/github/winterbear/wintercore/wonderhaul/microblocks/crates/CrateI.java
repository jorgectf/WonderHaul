package io.github.winterbear.wintercore.wonderhaul.microblocks.crates;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by WinterBear on 19/09/2020.
 */
public class CrateI implements CrateType {

    @Override
    public String getLore() {
        return "Tier I";
    }

    public Map<Integer, Integer> getCrateDelays(){
        Map<Integer, Integer> delays = new HashMap<>();
        delays.put(25,1);
        delays.put(15,1);
        delays.put(5,1);
        delays.put(2,5);
        return delays;
    }

    public Map<Integer, SlotType> getSlots(){
        Map<Integer, SlotType> slots = new HashMap<>();
        slots.put(3, new GoldSlotType());
        slots.put(4, new LootSlotType());
        slots.put(5, new TagSlotType());
        return slots;
    }

    @Override
    public SlotSize getSlotSize() {
        return SlotSize.THREE;
    }
}
