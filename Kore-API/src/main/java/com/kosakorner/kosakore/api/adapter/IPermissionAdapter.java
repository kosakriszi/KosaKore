package com.kosakorner.kosakore.api.adapter;

import com.kosakorner.kosakore.api.entity.IPlayer;

public interface IPermissionAdapter extends IAdapter {

    void addPermission(IPlayer player, String node);

}
