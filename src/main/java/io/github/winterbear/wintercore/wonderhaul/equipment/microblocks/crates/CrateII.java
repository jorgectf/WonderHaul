package io.github.winterbear.wintercore.wonderhaul.equipment.microblocks.crates;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by WinterBear on 24/09/2020.
 */
public class CrateII implements CrateType {

    @Override
    public String getLore() {
        return "Tier II";
    }

    @Override
    public Map<Integer, Integer> getCrateDelays() {
        Map<Integer, Integer> delays = new HashMap<>();
        delays.put(25,1);
        delays.put(15,1);
        delays.put(5,1);
        delays.put(2,5);
        return delays;
    }

    @Override
    public Map<Integer, SlotType> getSlots() {
        Map<Integer, SlotType> slots = new HashMap<>();
        slots.put(2, new GoldSlotType());
        slots.put(3, new LootSlotType());
        slots.put(5, new BonusSlotType());
        slots.put(6, new TagSlotType());
        return slots;
    }

    @Override
    public SlotSize getSlotSize() {
        return SlotSize.FOUR;
    }
}
