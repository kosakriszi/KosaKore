package com.kosakorner.kosakore.bukkit.command;

import com.kosakorner.kosakore.api.command.Color;
import com.kosakorner.kosakore.api.command.CommandWrap;
import com.kosakorner.kosakore.api.command.ICommandSender;
import com.kosakorner.kosakore.api.command.IDispatcher;
import com.kosakorner.kosakore.bukkit.entity.BukkitPlayer;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.util.*;

public class BukkitDispatcher implements IDispatcher, CommandExecutor, TabCompleter {

    private String                       mRootCommandName;
    private String                       mRootCommandDescription;
    private HashMap<String, CommandWrap> mCommands;
    private CommandWrap                  rootCommand;

    public BukkitDispatcher(String commandName, String description) {
        mCommands = new HashMap<>();

        mRootCommandName = commandName;
        mRootCommandDescription = description;
    }

    public void registerCommand(Class<?> command) {
        try {
            if (command.isAnnotationPresent(com.kosakorner.kosakore.api.command.Command.class)) {
                com.kosakorner.kosakore.api.command.Command commandInfo = command.getAnnotation(com.kosakorner.kosakore.api.command.Command.class);
                if (commandInfo.aliases()[0].equals(mRootCommandName)) {
                    rootCommand = new CommandWrap(commandInfo, command);
                }
                else {
                    mCommands.put(commandInfo.aliases()[0], new CommandWrap(commandInfo, command));
                }
            }
            for (Class<?> found : command.getClasses()) {
                if (!found.isAnnotationPresent(com.kosakorner.kosakore.api.command.Command.class)) {
                    continue;
                }
                com.kosakorner.kosakore.api.command.Command commandInfo = found.getAnnotation(com.kosakorner.kosakore.api.command.Command.class);
                if (commandInfo.aliases()[0].equals(mRootCommandName)) {
                    rootCommand = new CommandWrap(commandInfo, command);
                }
                else {
                    mCommands.put(commandInfo.aliases()[0], new CommandWrap(commandInfo, found));
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean onCommand(CommandSender source, Command command, String label, String[] args) {
        ICommandSender sender;
        if (source instanceof ConsoleCommandSender) {
            sender = new BukkitConsole((ConsoleCommandSender) source);
        }
        else if (source instanceof BlockCommandSender) {
            sender = new BukkitCommandBlock((BlockCommandSender) source);
        }
        else {
            sender = new BukkitPlayer((Player) source);
        }

        if (args.length == 0) {
            displayUsage(sender, label, null);
            return true;
        }

        String subCommand = args[0].toLowerCase();
        String[] subArgs = (args.length > 1 ? Arrays.copyOfRange(args, 1, args.length) : new String[0]);

        CommandWrap com = null;
        if (mCommands.containsKey(subCommand)) {
            com = mCommands.get(subCommand);
        }
        else if (subCommand.equals("help") || subCommand.equals("?")) {
            if (subArgs.length != 0) {
                return false;
            }

            sender.sendMessage(Color.GOLD + mRootCommandDescription);
            sender.sendMessage(Color.GOLD + "Commands: \n");

            for (CommandWrap sub : mCommands.values()) {
                // Dont show commands that are irrelevant
                if (!sub.canBeCommandBlock() && sender instanceof BlockCommandSender) {
                    continue;
                }
                if (!sub.canBeConsole() && (sender instanceof ConsoleCommandSender || sender instanceof RemoteConsoleCommandSender)) {
                    continue;
                }

                if (sub.getPermission() != null && !sender.hasPermission(sub.getPermission())) {
                    continue;
                }

                String usageString = Color.GOLD + "/" + mRootCommandName + " " + sub.getUsageString();
                sender.sendMessage(usageString);
                String[] descriptionLines = sub.getDescription().split("\n");
                for (String line : descriptionLines) {
                    sender.sendMessage("  " + Color.WHITE + line);
                }
            }
            return true;
        }
        else {
            // Check aliases
            AliasCheck:
            for (Map.Entry<String, CommandWrap> ent : mCommands.entrySet()) {
                if (ent.getValue().getAliases() != null) {
                    String[] aliases = ent.getValue().getAliases();
                    for (String alias : aliases) {
                        if (subCommand.equalsIgnoreCase(alias)) {
                            com = ent.getValue();
                            break AliasCheck;
                        }
                    }
                }
            }
        }

        // Was not found
        if (com == null) {
            rootCommand.onCommand(sender, args);
//            displayUsage(sender, label, subCommand);
            return true;
        }

        // Check that the sender is correct
        if (!com.canBeConsole() && (source instanceof ConsoleCommandSender || source instanceof RemoteConsoleCommandSender)) {
            sender.sendMessage(Color.RED + "/" + label + " " + subCommand + " cannot be called from the console.");
            return true;
        }
        if (!com.canBeCommandBlock() && source instanceof BlockCommandSender) {
            sender.sendMessage(Color.RED + "/" + label + " " + subCommand + " cannot be called from a command block.");
            return true;
        }

        // Check that they have permission
        if (com.getPermission() != null && !sender.hasPermission(com.getPermission())) {
            sender.sendMessage(Color.RED + "You do not have permission to use /" + label + " " + subCommand);
            return true;
        }

        if (!com.onCommand(sender, subArgs)) {
            String usageString = Color.RED + "Usage: " + Color.GRAY + "/" + label + " " + com.getUsageString();
            sender.sendMessage(usageString);
        }

        return true;
    }

    private void displayUsage(ICommandSender sender, String label, String subcommand) {
        String usage = "";

        boolean first = true;
        boolean odd = true;
        // Build the list
        for (CommandWrap command : mCommands.values()) {
            // Check that the sender is correct
            if (!command.canBeConsole() && (sender instanceof ConsoleCommandSender || sender instanceof RemoteConsoleCommandSender)) {
                continue;
            }

            // Check that they have permission
            if (command.getPermission() != null && !sender.hasPermission(command.getPermission())) {
                continue;
            }

            if (odd) {
                usage += Color.WHITE;
            }
            else {
                usage += Color.GRAY;
            }
            odd = !odd;

            if (first) {
                usage += command.getName();
            }
            else {
                usage += ", " + command.getName();
            }

            first = false;
        }

        if (subcommand != null) {
            sender.sendMessage(Color.RED + "Unknown command: " + Color.RESET + "/" + label + " " + Color.GOLD + subcommand);
        }
        else {
            sender.sendMessage(Color.RED + "No command specified: " + Color.RESET + "/" + label + Color.GOLD + " <command>");
        }

        if (!first) {
            sender.sendMessage("Valid commands are:");
            sender.sendMessage(usage);
        }
        else {
            sender.sendMessage("There are no commands available to you");
        }
    }

    public List<String> onTabComplete(CommandSender source, Command command, String label, String[] args) {
        ICommandSender sender = new BukkitPlayer((Player) source);
        List<String> results = new ArrayList<>();
        results.addAll(rootCommand.onTabComplete(sender, args));
        if (args.length == 1) { // Tab completing the sub command
            for (CommandWrap registeredCommand : mCommands.values()) {
                if (registeredCommand.getName().toLowerCase().startsWith(args[0].toLowerCase())) {
                    // Check that the sender is correct
                    if (!registeredCommand.canBeConsole() && (sender instanceof ConsoleCommandSender || sender instanceof RemoteConsoleCommandSender)) {
                        continue;
                    }

                    // Check that they have permission
                    if (command.getPermission() != null && !sender.hasPermission(command.getPermission())) {
                        continue;
                    }

                    results.add(registeredCommand.getName());
                }
            }
        }
        else {
            // Find the command to use
            String subCommand = args[0].toLowerCase();
            String[] subArgs = (args.length > 1 ? Arrays.copyOfRange(args, 1, args.length) : new String[0]);

            CommandWrap com = null;
            if (mCommands.containsKey(subCommand)) {
                com = mCommands.get(subCommand);
            }
            else {
                // Check aliases
                AliasCheck:
                for (Map.Entry<String, CommandWrap> ent : mCommands.entrySet()) {
                    if (ent.getValue().getAliases() != null) {
                        String[] aliases = ent.getValue().getAliases();
                        for (String alias : aliases) {
                            if (subCommand.equalsIgnoreCase(alias)) {
                                com = ent.getValue();
                                break AliasCheck;
                            }
                        }
                    }
                }
            }

            // Was not found
            if (com == null) {
                return results;
            }

            // Check that the sender is correct
            if (!com.canBeConsole() && (sender instanceof ConsoleCommandSender || sender instanceof RemoteConsoleCommandSender)) {
                return results;
            }

            // Check that they have permission
            if (com.getPermission() != null && !sender.hasPermission(com.getPermission())) {
                return results;
            }

            results = com.onTabComplete(sender, subArgs);
            if (results == null) {
                return new ArrayList<>();
            }
        }
        return results;
    }

}
