package io.github.winterbear.wintercore.wonderhaul.equipment.microblocks.crates;

import java.util.Map;

/**
 * Created by WinterBear on 18/09/2020.
 */
public interface CrateType {

    String getLore();

    Map<Integer, Integer> getCrateDelays();

    Map<Integer, SlotType> getSlots();

    SlotSize getSlotSize();
}
