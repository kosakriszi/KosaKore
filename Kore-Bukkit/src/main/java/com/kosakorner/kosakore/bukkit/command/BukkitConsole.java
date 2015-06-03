package com.kosakorner.kosakore.bukkit.command;

import com.kosakorner.kosakore.api.command.ICommandSender;
import org.bukkit.command.ConsoleCommandSender;

/**
 * The console command sender.
 */
public class BukkitConsole implements ICommandSender {

    private ConsoleCommandSender console;

    public BukkitConsole(ConsoleCommandSender console) {
        this.console = console;
    }

    /**
     * {@inheritDoc}
     */
    public void sendMessage(String message) {
        console.sendMessage(message);
    }

    /**
     * {@inheritDoc}
     */
    public boolean hasPermission(String node) {
        return true;
    }

}
