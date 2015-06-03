package com.kosakorner.kosakore.bukkit.command;

import com.kosakorner.kosakore.api.command.ICommandSender;
import org.bukkit.command.BlockCommandSender;

/**
 * A command block command sender.
 */
public class BukkitCommandBlock implements ICommandSender {

    private BlockCommandSender block;

    public BukkitCommandBlock(BlockCommandSender block) {
        this.block = block;
    }

    /**
     * {@inheritDoc}
     */
    public void sendMessage(String message) {
        block.sendMessage(message);
    }

    /**
     * {@inheritDoc}
     */
    public boolean hasPermission(String node) {
        return true;
    }

}
