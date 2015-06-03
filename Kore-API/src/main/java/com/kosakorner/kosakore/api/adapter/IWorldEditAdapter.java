package com.kosakorner.kosakore.api.adapter;

import com.kosakorner.kosakore.api.world.Location;

import java.io.File;

public interface IWorldEditAdapter extends IAdapter {

    void pasteSchematic(Location location, File schematic);

}
