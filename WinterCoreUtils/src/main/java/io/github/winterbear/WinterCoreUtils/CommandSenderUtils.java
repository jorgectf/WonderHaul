package io.github.winterbear.WinterCoreUtils;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Optional;

/**
 * Created by WinterBear on 27/05/2019.
 */
public class CommandSenderUtils {

    public static Optional<Player> getPlayer(CommandSender sender){
        return Optional.ofNullable(sender.getServer().getPlayer(sender.getName()));
    }

}
