package com.kosakorner.kosakore.bukkit.command;

import com.kosakorner.kosakore.api.block.IBlock;
import com.kosakorner.kosakore.api.command.IBlockCommandSender;
import com.kosakorner.kosakore.api.command.SenderType;
import com.kosakorner.kosakore.bukkit.block.BukkitBlock;
import org.bukkit.command.BlockCommandSender;

public class BukkitBlockCommandSender implements IBlockCommandSender {

    private BlockCommandSender blockSender;

    public BukkitBlockCommandSender(BlockCommandSender blockSender) {
        this.blockSender = blockSender;
    }

    public IBlock getBlock() {
        return new BukkitBlock(blockSender.getBlock());
    }

    public SenderType getSenderType() {
        return SenderType.BLOCK;
    }

    public void sendMessage(String message) {
        blockSender.sendMessage(message);
    }

    public boolean hasPermission(String node) {
        return true;
    }

}
