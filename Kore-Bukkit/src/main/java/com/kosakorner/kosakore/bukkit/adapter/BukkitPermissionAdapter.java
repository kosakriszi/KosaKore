package com.kosakorner.kosakore.bukkit.adapter;

import com.kosakorner.kosakore.api.adapter.IPermissionAdapter;
import com.kosakorner.kosakore.api.entity.IPlayer;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

public class BukkitPermissionAdapter implements IPermissionAdapter {

    private static Permission perms;

    public BukkitPermissionAdapter() {
        RegisteredServiceProvider permService = Bukkit.getServicesManager().getRegistration(Permission.class);
        if (permService.getProvider() != null) {
            perms = (Permission) permService.getProvider();
        }
    }

    public void addPermission(IPlayer player, String node) {
        perms.playerAdd(Bukkit.getPlayer(player.getUniqueID()), node);
    }

}
