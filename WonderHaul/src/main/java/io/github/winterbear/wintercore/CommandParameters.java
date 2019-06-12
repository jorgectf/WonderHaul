package io.github.winterbear.wintercore;

import org.bukkit.command.CommandSender;

/**
 * Created by WinterBear on 20/05/2019.
 */
public class CommandParameters {

    private CommandSender sender;

    private CommandParameters command;

    private String label;

    private String[] args;

    public CommandParameters(CommandSender sender, CommandParameters command, String label, String[] args) {
        this.sender = sender;
        this.command = command;
        this.label = label;
        this.args = args;
    }

    public CommandSender getSender() {
        return sender;
    }

    public void setSender(CommandSender sender) {
        this.sender = sender;
    }

    public CommandParameters getCommand() {
        return command;
    }

    public void setCommand(CommandParameters command) {
        this.command = command;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }
}
