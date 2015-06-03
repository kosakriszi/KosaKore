package com.kosakorner.kosakore.api.command;

import com.kosakorner.kosakore.api.world.IBlock;

public interface IBlockCommandSender extends ICommandSender {

    IBlock getBlock();

}
