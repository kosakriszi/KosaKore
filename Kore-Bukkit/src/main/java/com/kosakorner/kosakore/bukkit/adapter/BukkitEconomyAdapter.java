package com.kosakorner.kosakore.bukkit.adapter;

import com.kosakorner.kosakore.api.adapter.IEconomyAdapter;
import com.kosakorner.kosakore.api.entity.IPlayer;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

public class BukkitEconomyAdapter implements IEconomyAdapter {

    private static Economy econ;

    public BukkitEconomyAdapter() {
        RegisteredServiceProvider ecoService = Bukkit.getServicesManager().getRegistration(Economy.class);
        if (ecoService != null) {
            econ = (Economy) ecoService.getProvider();
        }
    }

    public void depositCurrency(IPlayer player, double amount) {
        econ.depositPlayer(Bukkit.getOfflinePlayer(player.getUniqueID()), amount);
    }

}
