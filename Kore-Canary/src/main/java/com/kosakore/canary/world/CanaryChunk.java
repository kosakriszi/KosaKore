package com.kosakore.canary.world;

import com.kosakore.canary.entity.CanaryEntity;
import com.kosakorner.kosakore.api.entity.IEntity;
import com.kosakorner.kosakore.api.world.IChunk;
import net.canarymod.api.entity.Entity;
import net.canarymod.api.world.Chunk;

import java.util.ArrayList;
import java.util.List;

public class CanaryChunk implements IChunk {

    private Chunk backingChunk;

    public CanaryChunk(Chunk backingChunk) {
        this.backingChunk = backingChunk;
    }

    public IEntity[] getEntities() {
        List<IEntity> found = new ArrayList<IEntity>();
        for (List<Entity> entities : backingChunk.getEntityLists()) {
            for (Entity entity : entities) {
                found.add(new CanaryEntity(entity));
            }
        }
        IEntity[] toReturn = new IEntity[found.size()];
        for (int i = 0; i < found.size(); i++) {
            toReturn[i] = found.get(i);
        }
        return toReturn;
    }

}
