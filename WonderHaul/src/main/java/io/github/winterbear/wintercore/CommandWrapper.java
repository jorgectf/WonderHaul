package io.github.winterbear.wintercore;

import org.bukkit.command.CommandExecutor;

/**
 * Created by WinterBear on 16/07/2017.
 */
public class CommandWrapper {

    private CommandExecutor executor;

    private String name;

    public CommandWrapper(CommandExecutor executor, String name) {
        this.executor = executor;
        this.name = name;
    }

    public CommandExecutor getExecutor() {
        return executor;
    }

    public String getName() {
        return name;
    }
}
