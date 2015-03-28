package com.kosakorner.kosakore.bukkit.world;

import com.kosakorner.kosakore.api.entity.IEntity;
import com.kosakorner.kosakore.api.world.IChunk;
import com.kosakorner.kosakore.bukkit.entity.BukkitEntity;
import org.bukkit.Chunk;
import org.bukkit.entity.Entity;

public class BukkitChunk implements IChunk {

    private Chunk backingChunk;

    public BukkitChunk(Chunk backingChunk) {
        this.backingChunk = backingChunk;
    }

    public IEntity[] getEntities() {
        Entity[] entities = backingChunk.getEntities();
        IEntity[] toReturn = new IEntity[entities.length];
        for (int i = 0; i < entities.length; i++) {
            toReturn[i] = new BukkitEntity(entities[i]);
        }
        return toReturn;
    }

}
