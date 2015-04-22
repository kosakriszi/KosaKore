package com.kosakorner.kosakore.api.adapter;

import com.kosakorner.kosakore.api.entity.IPlayer;

public interface IPermissionAdapter extends IAdapter {

    public void addPermission(IPlayer player, String node);

}
