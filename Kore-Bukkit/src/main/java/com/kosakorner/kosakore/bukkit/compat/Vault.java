package com.kosakorner.kosakore.bukkit.compat;

import com.kosakorner.kosakore.api.entity.IPlayer;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

public class Vault {

    private static Permission perms = null;
    private static Economy    econ  = null;

    public static void hook() {
        RegisteredServiceProvider permService = Bukkit.getServicesManager().getRegistration(Permission.class);
        if (permService.getProvider() != null) {
            perms = (Permission) permService.getProvider();
        }

        RegisteredServiceProvider ecoService = Bukkit.getServicesManager().getRegistration(Economy.class);
        if (ecoService != null) {
            econ = (Economy) ecoService.getProvider();
        }
    }

    public static Permission getPerms() {
        if (perms == null) {
            hook();
        }
        return perms;
    }

    public static void addPerm(IPlayer player, String node) {
        getPerms().playerAdd(Bukkit.getPlayer(player.getUniqueID()), node);
    }

    public static Economy getEcon() {
        if (econ == null) {
            hook();
        }
        return econ;
    }

    public static void deposit(IPlayer player, double amount) {
        getEcon().depositPlayer(Bukkit.getOfflinePlayer(player.getUniqueID()), amount);
    }

}