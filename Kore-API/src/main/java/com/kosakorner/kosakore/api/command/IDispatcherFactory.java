package com.kosakorner.kosakore.api.command;

import com.kosakorner.kosakore.api.lang.ILocalization;

public interface IDispatcherFactory {

    IDispatcher createCommandDispatcher(String pluginName, String command, String[] aliases, String description, ILocalization localization);

}
