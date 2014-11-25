package com.kosakorner.kosakore.command;

import com.kosakorner.kosakore.compat.VaultInterface;
import org.bukkit.ChatColor;
import org.bukkit.command.*;

import java.util.*;

public class CommandDispatcher implements CommandExecutor, TabCompleter {

    private String                    mRootCommandName;
    private String                    mRootCommandDescription;
    private HashMap<String, ICommand> mCommands;

    public CommandDispatcher(String commandName, String description) {
        mCommands = new HashMap<String, ICommand>();

        mRootCommandName = commandName;
        mRootCommandDescription = description;

        registerCommand(new InternalHelp());
    }

    public void registerCommand(ICommand command) {
        mCommands.put(command.getName().toLowerCase(), command);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            displayUsage(sender, label, null);
            return true;
        }

        String subCommand = args[0].toLowerCase();
        String[] subArgs = (args.length > 1 ? Arrays.copyOfRange(args, 1, args.length) : new String[0]);

        ICommand com = null;
        if (mCommands.containsKey(subCommand)) {
            com = mCommands.get(subCommand);
        }
        else {
            // Check aliases
            AliasCheck:
            for (Map.Entry<String, ICommand> ent : mCommands.entrySet()) {
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
            displayUsage(sender, label, subCommand);
            return true;
        }

        // Check that the sender is correct
        if (!com.canBeConsole() && (sender instanceof ConsoleCommandSender || sender instanceof RemoteConsoleCommandSender)) {
            sender.sendMessage(ChatColor.RED + "/" + label + " " + subCommand + " cannot be called from the console.");
            return true;
        }
        if (!com.canBeCommandBlock() && sender instanceof BlockCommandSender) {
            sender.sendMessage(ChatColor.RED + "/" + label + " " + subCommand + " cannot be called from a command block.");
            return true;
        }

        // Check that they have permission
        if (com.getPermission() != null && !VaultInterface.checkPerk(sender, com.getPermission())) {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use /" + label + " " + subCommand);
            return true;
        }

        if (!com.onCommand(sender, subCommand, subArgs)) {
            String[] lines = com.getUsageString(subCommand, sender);
            String usageString = "";

            if (lines.length > 1) {
                usageString = ChatColor.RED + "Usage:\n    ";
            }
            else {
                usageString = ChatColor.RED + "Usage: ";
            }

            boolean first = true;
            for (String line : lines) {
                if (!first) {
                    usageString += "\n    ";
                }
                first = false;

                usageString += ChatColor.GRAY + "/" + label + " " + line;
            }

            sender.sendMessage(usageString);
        }

        return true;
    }

    private void displayUsage(CommandSender sender, String label, String subcommand) {
        String usage = "";

        boolean first = true;
        boolean odd = true;
        // Build the list
        for (ICommand command : mCommands.values()) {
            // Check that the sender is correct
            if (!command.canBeConsole() && (sender instanceof ConsoleCommandSender || sender instanceof RemoteConsoleCommandSender)) {
                continue;
            }

            // Check that they have permission
            if (command.getPermission() != null && !VaultInterface.checkPerk(sender, command.getPermission())) {
                continue;
            }

            if (odd) {
                usage += ChatColor.WHITE;
            }
            else {
                usage += ChatColor.GRAY;
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
            sender.sendMessage(ChatColor.RED + "Unknown command: " + ChatColor.RESET + "/" + label + " " + ChatColor.GOLD + subcommand);
        }
        else {
            sender.sendMessage(ChatColor.RED + "No command specified: " + ChatColor.RESET + "/" + label + ChatColor.GOLD + " <command>");
        }

        if (!first) {
            sender.sendMessage("Valid commands are:");
            sender.sendMessage(usage);
        }
        else {
            sender.sendMessage("There are no commands available to you");
        }


    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> results = new ArrayList<String>();
        if (args.length == 1) // Tab completing the sub command
        {
            for (ICommand registeredCommand : mCommands.values()) {
                if (registeredCommand.getName().toLowerCase().startsWith(args[0].toLowerCase())) {
                    // Check that the sender is correct
                    if (!registeredCommand.canBeConsole() && (sender instanceof ConsoleCommandSender || sender instanceof RemoteConsoleCommandSender)) {
                        continue;
                    }

                    // Check that they have permission
                    if (command.getPermission() != null && !VaultInterface.checkPerk(sender, command.getPermission())) {
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

            ICommand com = null;
            if (mCommands.containsKey(subCommand)) {
                com = mCommands.get(subCommand);
            }
            else {
                // Check aliases
                AliasCheck:
                for (Map.Entry<String, ICommand> ent : mCommands.entrySet()) {
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
            if (com.getPermission() != null && !VaultInterface.checkPerk(sender, com.getPermission())) {
                return results;
            }

            results = com.onTabComplete(sender, subCommand, subArgs);
            if (results == null) {
                return new ArrayList<String>();
            }
        }
        return results;
    }

    private class InternalHelp implements ICommand {

        @Override
        public String getName() {
            return "help";
        }

        @Override
        public String[] getAliases() {
            return null;
        }

        @Override
        public String getPermission() {
            return null;
        }

        @Override
        public String[] getUsageString(String label, CommandSender sender) {
            return new String[]{label};
        }

        @Override
        public String getDescription() {
            return "Displays this screen.";
        }

        @Override
        public boolean canBeConsole() {
            return true;
        }

        @Override
        public boolean canBeCommandBlock() {
            return true;
        }

        @Override
        public boolean onCommand(CommandSender sender, String label, String[] args) {
            if (args.length != 0) {
                return false;
            }

            sender.sendMessage(ChatColor.GOLD + mRootCommandDescription);
            sender.sendMessage(ChatColor.GOLD + "Commands: \n");

            for (ICommand command : mCommands.values()) {
                // Dont show commands that are irrelevant
                if (!command.canBeCommandBlock() && sender instanceof BlockCommandSender) {
                    continue;
                }
                if (!command.canBeConsole() && (sender instanceof ConsoleCommandSender || sender instanceof RemoteConsoleCommandSender)) {
                    continue;
                }

                if (command.getPermission() != null && !VaultInterface.checkPerk(sender, command.getPermission())) {
                    continue;
                }


                String usageString = "";
                boolean first = true;
                for (String line : command.getUsageString(command.getName(), sender)) {
                    if (!first) {
                        usageString += "\n";
                    }

                    first = false;

                    usageString += ChatColor.GOLD + "/" + mRootCommandName + " " + line;
                }

                sender.sendMessage(usageString);
                String[] descriptionLines = command.getDescription().split("\n");
                for (String line : descriptionLines) {
                    sender.sendMessage("  " + ChatColor.WHITE + line);
                }
            }
            return true;
        }

        @Override
        public List<String> onTabComplete(CommandSender sender, String label, String[] args) {
            return null;
        }

    }
}

