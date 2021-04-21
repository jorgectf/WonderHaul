package io.github.winterbear.wintercore.wonderhaul.microblocks.crates;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Created by WinterBear on 19/09/2020.
 */
public class CratePrize {

    private ItemStack prize;

    private SlotType type;

    public void rewardPlayer(Player player){
        type.reward(player, prize);
    }

    public CratePrize(ItemStack prize, SlotType type) {
        this.prize = prize;
        this.type = type;
    }
}
