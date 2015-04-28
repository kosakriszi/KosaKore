package com.kosakorner.kosakore.bukkit.command;

import com.kosakorner.kosakore.api.command.IDispatcher;
import com.kosakorner.kosakore.api.command.IDispatcherFactory;
import com.kosakorner.kosakore.bukkit.Kore;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.craftbukkit.v1_8_R2.CraftServer;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Constructor;

public class BukkitDispatcherFactory implements IDispatcherFactory {

    SimpleCommandMap commandMap;

    public BukkitDispatcherFactory() {
        try {
            commandMap = ((CraftServer) Bukkit.getServer()).getCommandMap();
        }
        catch (Exception e) {
            Kore.log("FATAL - Unable to hook into Bukkit command map!");
            e.printStackTrace();
        }
    }

    public IDispatcher createCommandDispatcher(String pluginName, String command, String description) {
        try {
            BukkitDispatcher dispatcher = new BukkitDispatcher(command, description);
            Constructor<PluginCommand> constructor = PluginCommand.class.getDeclaredConstructor(String.class, Plugin.class);
            constructor.setAccessible(true);
            PluginCommand pluginCommand = constructor.newInstance(command, Kore.instance());
            pluginCommand.setExecutor(dispatcher);
            pluginCommand.setTabCompleter(dispatcher);
            boolean success = commandMap.register(pluginName, pluginCommand);
            Kore.log("Registered \"" + command + "\": " + success);
            return dispatcher;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
