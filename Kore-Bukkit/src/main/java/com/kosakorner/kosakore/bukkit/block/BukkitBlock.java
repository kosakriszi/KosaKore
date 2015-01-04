package com.kosakorner.kosakore.bukkit.block;

import com.kosakorner.kosakore.api.block.IBlock;
import com.kosakorner.kosakore.api.inventory.IInventory;
import com.kosakorner.kosakore.api.type.Type;
import com.kosakorner.kosakore.api.type.TypeParser;
import com.kosakorner.kosakore.bukkit.inventory.BukkitInventory;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.InventoryHolder;

public class BukkitBlock implements IBlock {

    private Block backingBlock;

    public BukkitBlock(Block block) {
        backingBlock = block;
    }

    public Type getType() {
        return TypeParser.parse(backingBlock.getType().name());
    }

    public void setType(Type type) {
        backingBlock.setType(Material.getMaterial(type.name()));
    }

    public IInventory getInventory() {
        if (backingBlock.getState() instanceof InventoryHolder) {
            return new BukkitInventory(((InventoryHolder) backingBlock.getState()).getInventory());
        }
        else {
            return null;
        }
    }

}
