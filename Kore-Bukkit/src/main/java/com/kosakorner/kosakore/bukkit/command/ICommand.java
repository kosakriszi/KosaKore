package com.kosakorner.kosakore.bukkit.command;

import org.bukkit.command.CommandSender;

import java.util.List;

public interface ICommand {

    public String getName();

    public String[] getAliases();

    public String getPermission();

    public String[] getUsageString(String label, CommandSender sender);

    public String getDescription();

    public boolean canBeConsole();

    public boolean canBeCommandBlock();

    public boolean onCommand(CommandSender sender, String label, String[] args);

    public List<String> onTabComplete(CommandSender sender, String label, String[] args);

}
