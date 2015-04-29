package com.kosakorner.kosakore.api.command;

import com.kosakorner.kosakore.api.block.IBlock;

public interface IBlockCommandSender extends ICommandSender {

    public IBlock getBlock();

}
