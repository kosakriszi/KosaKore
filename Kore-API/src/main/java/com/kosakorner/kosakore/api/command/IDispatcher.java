package com.kosakorner.kosakore.api.command;

/**
 * A command executor and tab completer.
 */
public interface IDispatcher {

    /**
     * Register a command class with this dispatcher.
     *
     * @param command The command to register
     */
    void registerCommand(Class<?> command);

}
