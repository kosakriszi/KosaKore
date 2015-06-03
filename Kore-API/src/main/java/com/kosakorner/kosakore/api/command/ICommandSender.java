package com.kosakorner.kosakore.api.command;

/**
 * A command sender.
 */
public interface ICommandSender {

    /**
     * Send this command sender a message.
     *
     * @param message The message to send
     */
    void sendMessage(String message);

    /**
     * Check whether a command sender has a permission node.
     *
     * @param node The node to check
     * @return Whether the command sender has permission
     */
    boolean hasPermission(String node);

}
