package com.kosakore.canary.command;

import com.kosakorner.kosakore.api.command.IDispatcher;
import com.kosakorner.kosakore.api.command.IDispatcherFactory;

public class CanaryDispatcherFactory implements IDispatcherFactory {

    public IDispatcher createCommandDispatcher(String pluginName, String command, String[] aliases, String description) {
        return null;
    }

}
