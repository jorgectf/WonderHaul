package io.github.winterbear.wintercore.utils;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerAnimationEvent;

/**
 * Created by WinterBear on 13/09/2020.
 */
public class FakePlayerAnimationEvent extends PlayerAnimationEvent {

    public FakePlayerAnimationEvent(Player player) {
        super(player);
    }
}
