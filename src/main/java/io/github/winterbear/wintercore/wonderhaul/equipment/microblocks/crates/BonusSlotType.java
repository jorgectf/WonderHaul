package io.github.winterbear.wintercore.wonderhaul.equipment.microblocks.crates;

import io.github.winterbear.wintercore.utils.ItemUtils;
import io.github.winterbear.wintercore.wonderhaul.dropper.Chance;
import io.github.winterbear.wintercore.wonderhaul.equipment.generators.Generators;
import io.github.winterbear.wintercore.wonderhaul.equipment.microblocks.decorations.StuffedToyGenerator;
import io.github.winterbear.wintercore.wonderhaul.equipment.microblocks.essencecollector.EssenceCollectorGenerator;
import io.github.winterbear.wintercore.wonderhaul.sockets.SocketGenerator;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Created by WinterBear on 24/09/2020.
 */
public class BonusSlotType implements SlotType{

    @Override
    public ItemStack getNewItem() {
        if(Chance.roll(50.0)){ //50%
            return new StuffedToyGenerator().create();
        } else if (Chance.roll(20.0)) { //10%
            return Generators.CANDLE_ROLLING_TABLE_GENERATOR.create();
        } else if (Chance.roll(10.0)) {
            return Generators.CARVING_STATION_GENERATOR.create();
        } else if (Chance.roll(90.0)){
            return new SocketGenerator().create();
        } else { //10%
            return new EssenceCollectorGenerator().create();
        }
    }

    @Override
    public void reward(Player player, ItemStack item) {
        ItemUtils.safelyGiveItem(player, item);
    }
}
