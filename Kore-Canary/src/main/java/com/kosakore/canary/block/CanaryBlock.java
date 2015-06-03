package com.kosakore.canary.block;

import com.kosakorner.kosakore.api.inventory.IInventory;
import com.kosakorner.kosakore.api.type.Type;
import com.kosakorner.kosakore.api.world.IBlock;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.BlockType;

public class CanaryBlock implements IBlock {

    private Block backingBlock;

    public CanaryBlock(Block backingBlock) {
        this.backingBlock = backingBlock;
    }

    public Type getType() {
        return Type.getType(backingBlock.getType().getMachineName());
    }

    public void setType(Type type) {
        backingBlock.setType(BlockType.fromString(type.name()));
    }

    public IInventory getInventory() {
        return null;
    }

}
