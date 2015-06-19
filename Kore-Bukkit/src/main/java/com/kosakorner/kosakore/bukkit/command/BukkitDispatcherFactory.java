package com.kosakorner.kosakore.bukkit.command;

import com.kosakorner.kosakore.api.command.IDispatcher;
import com.kosakorner.kosakore.api.command.IDispatcherFactory;
import com.kosakorner.kosakore.bukkit.Kore;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;

public class BukkitDispatcherFactory implements IDispatcherFactory {

    SimpleCommandMap commandMap;

    public BukkitDispatcherFactory() {
        Class<?> craftServerClass = null;
        try {
            craftServerClass = Bukkit.getServer().getClass();
            if (craftServerClass != null) {
                Method getCommandMap = craftServerClass.getMethod("getCommandMap");
                commandMap = (SimpleCommandMap) getCommandMap.invoke(Bukkit.getServer());
            }
        }
        catch (Exception e) {

        }

        if (craftServerClass == null) {
            Kore.log("FATAL - Unable to hook into the Bukkit command map. You will have no commands!");
        }
    }

    public IDispatcher createCommandDispatcher(String pluginName, String command, String[] aliases, String description) {
        try {
            BukkitDispatcher dispatcher = new BukkitDispatcher(command, description);
            Constructor<PluginCommand> constructor = PluginCommand.class.getDeclaredConstructor(String.class, Plugin.class);
            constructor.setAccessible(true);
            PluginCommand pluginCommand = constructor.newInstance(command, Kore.instance());
            pluginCommand.setAliases(Arrays.asList(aliases));
            pluginCommand.setExecutor(dispatcher);
            pluginCommand.setTabCompleter(dispatcher);
            boolean success = commandMap.register(pluginName, pluginCommand);
            Kore.log("Registered command \"" + command + "\": " + success);
            return dispatcher;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
