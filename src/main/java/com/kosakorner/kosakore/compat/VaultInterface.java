package com.kosakorner.kosakore.compat;

import com.kosakorner.kosakore.Kore;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

public class VaultInterface {

    public static Permission perms = null;
    public static Economy    econ  = null;

    public static void addPerk(Player player, String perk) {
        perms.playerAdd(player, perk);
    }

    public static void removePerk(Player player, String perk) {
        perms.playerRemove(player, perk);
    }

    public static void addGroup(Player player, String perk) {
        perms.playerAddGroup(player, perk);
    }

    public static boolean checkPerk(Player player, String perk) {
        return perms.has(player, perk);
    }

    public static boolean checkPerk(CommandSender sender, String perk) {
        return perms.has(sender, perk);
    }

    public static boolean setupPermissions() {
        RegisteredServiceProvider rsp = Kore.instance().getServer().getServicesManager().getRegistration(Permission.class);
        if (rsp.getProvider() != null)
            perms = (Permission) rsp.getProvider();
        return perms != null;
    }

    public static boolean setupEconomy() {
        if (Kore.instance().getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider rsp = Kore.instance().getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = (Economy) rsp.getProvider();
        return econ != null;
    }

}