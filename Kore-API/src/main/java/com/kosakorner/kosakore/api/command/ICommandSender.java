package com.kosakorner.kosakore.api.command;

public interface ICommandSender {

    public SenderType getSenderType();

    public void sendMessage(String message);

    public boolean hasPermission(String node);

}
