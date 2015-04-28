package com.kosakorner.kosakore.api.command;

public interface IDispatcherFactory {

    public IDispatcher createCommandDispatcher(String pluginName, String command, String description);

}
