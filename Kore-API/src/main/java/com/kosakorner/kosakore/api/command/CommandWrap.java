package com.kosakorner.kosakore.api.command;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Command wrapper class.
 */
public class CommandWrap {

    private Command commandInfo;
    private Object  command;
    private Method  onCommand;
    private Method  onTabComplete;

    public CommandWrap(Command commandInfo, Class<?> commandClass) {
        this.commandInfo = commandInfo;
        try {
            command = commandClass.newInstance();
            onCommand = commandClass.getMethod("onCommand", ICommandSender.class, String[].class);
            try {
                onTabComplete = commandClass.getMethod("onTabComplete", ICommandSender.class, String[].class);
            }
            catch (NoSuchMethodException e) {
                onTabComplete = null;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return commandInfo.aliases()[0];
    }

    public String[] getAliases() {
        return commandInfo.aliases();
    }

    public String getPermission() {
        return commandInfo.perm();
    }

    public String getUsageString() {
        return commandInfo.usage();
    }

    public String getDescription() {
        return commandInfo.desc();
    }

    public boolean canBeConsole() {
        return commandInfo.allowConsole();
    }

    public boolean canBeCommandBlock() {
        return commandInfo.allowCommandBlock();
    }

    public boolean onCommand(ICommandSender sender, String[] args) {
        try {
            return (boolean) onCommand.invoke(command, sender, args);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    public List<String> onTabComplete(ICommandSender sender, String[] args) {
        try {
            if (onTabComplete != null) {
                return (List<String>) onTabComplete.invoke(command, sender, args);
            }
            else {
                return new ArrayList<>();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
