package io.github.winterbear.wintercore.WonderHaul.Crafting.Expressions;

import io.github.winterbear.wintercore.WonderHaul.Crafting.InvalidItemStackException;
import org.bukkit.entity.Player;

/**
 * Created by WinterBear on 02/09/2017.
 */
public interface IngredientQuery {

    boolean searchPlayer(Player player);

    void removeFromPlayer(Player player);

}
