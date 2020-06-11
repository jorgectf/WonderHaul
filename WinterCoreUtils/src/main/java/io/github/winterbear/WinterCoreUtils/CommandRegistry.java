package io.github.winterbear.WinterCoreUtils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Optional;


/**
 * Created by WinterBear on 20/05/2019.
 */
public class CommandRegistry {

    public static CommandWrapper createCommand(String commandAlias, FunctionalCommand<CommandSender, Command, String, String[]> function){

        return new CommandWrapper(new CommandExecutor() {
            @Override
            public boolean onCommand(CommandSender commandSender, Command command, String alias, String[] arguments) {
                try {
                    if(alias.equalsIgnoreCase(commandAlias)){
                        function.execute(commandSender, command, alias, arguments);
                        return true;
                    }
                    return false;
                } catch (Exception e){
                    ChatUtils.error(commandSender, "An unexpected " + e.getClass().getSimpleName() + " occurred. See console for stacktrace.");
                    e.printStackTrace();
                    return false;
                }

            }
        }, commandAlias);


    }


    public static CommandWrapper createPlayerCommand(String commandAlias, FunctionalCommand<Player, Command, String, String[]> function){
        return createCommand(commandAlias, (sender, command, alias, args) -> {
            Optional<Player> player = CommandSenderUtils.getPlayer(sender);
            if(CommandSenderUtils.getPlayer(sender).isPresent()){
                function.execute(player.get(), command, alias, args);
            } else {
                ChatUtils.send(sender, "Only players can execute that command.");
            }

        });
    }

    public static void register(JavaPlugin plugin, CommandWrapper wrapper){
        ChatUtils.info("Loading Command " + wrapper.getName());
        plugin.getCommand(wrapper.getName()).setExecutor(wrapper.getExecutor());
    }


}
