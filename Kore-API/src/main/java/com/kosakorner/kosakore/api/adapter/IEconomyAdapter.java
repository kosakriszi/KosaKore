package com.kosakorner.kosakore.api.adapter;

import com.kosakorner.kosakore.api.entity.IPlayer;

public interface IEconomyAdapter extends IAdapter {

    public void depositCurrency(IPlayer player, double amount);

}
