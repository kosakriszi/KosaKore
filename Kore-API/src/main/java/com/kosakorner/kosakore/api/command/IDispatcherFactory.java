package com.kosakorner.kosakore.api.command;

public interface IDispatcherFactory {

    IDispatcher createCommandDispatcher(String pluginName, String command, String[] aliases, String description);

}
