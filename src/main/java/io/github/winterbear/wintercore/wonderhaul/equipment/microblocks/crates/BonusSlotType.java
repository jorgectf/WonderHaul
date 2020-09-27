package io.github.winterbear.wintercore.wonderhaul.equipment.microblocks.crates;

import io.github.winterbear.wintercore.utils.ItemUtils;
import io.github.winterbear.wintercore.wonderhaul.dropper.Chance;
import io.github.winterbear.wintercore.wonderhaul.equipment.microblocks.decorations.StuffedToyGenerator;
import io.github.winterbear.wintercore.wonderhaul.sockets.SocketGenerator;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Created by WinterBear on 24/09/2020.
 */
public class BonusSlotType implements SlotType{

    @Override
    public ItemStack getNewItem() {
        if(Chance.roll(80.0)){
            return new StuffedToyGenerator().create();
        } else {
            return new SocketGenerator().create();
        }
    }

    @Override
    public void reward(Player player, ItemStack item) {
        ItemUtils.safelyGiveItem(player, item);
    }
}
