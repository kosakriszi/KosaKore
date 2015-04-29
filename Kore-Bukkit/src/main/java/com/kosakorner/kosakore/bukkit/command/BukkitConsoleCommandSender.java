package com.kosakorner.kosakore.bukkit.command;

import com.kosakorner.kosakore.api.command.ICommandSender;
import com.kosakorner.kosakore.api.command.SenderType;
import org.bukkit.command.ConsoleCommandSender;

public class BukkitConsoleCommandSender implements ICommandSender {

    private ConsoleCommandSender console;

    public BukkitConsoleCommandSender(ConsoleCommandSender console) {
        this.console = console;
    }

    public SenderType getSenderType() {
        return SenderType.CONSOLE;
    }

    public void sendMessage(String message) {
        console.sendMessage(message);
    }

    public boolean hasPermission(String node) {
        return true;
    }

}
