package com.kosakorner.kosakore.api.command;

import java.util.List;

public interface ICommand {

    public String getName();

    public String[] getAliases();

    public String getPermission();

    public String[] getUsageString(String label, ICommandSender sender);

    public String getDescription();

    public boolean canBeConsole();

    public boolean canBeCommandBlock();

    public boolean onCommand(ICommandSender sender, String label, String[] args);

    public List<String> onTabComplete(ICommandSender sender, String label, String[] args);

}
