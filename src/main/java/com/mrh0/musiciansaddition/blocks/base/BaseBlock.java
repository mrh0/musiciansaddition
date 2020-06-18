package com.mrh0.musiciansaddition.blocks.base;

import com.mrh0.musiciansaddition.event.BlockRegistry;
import com.mrh0.musiciansaddition.event.opts.BlockOptions;

import net.minecraft.block.Block;

public class BaseBlock extends Block{

	public BaseBlock(String name, Properties block) {
		super(block);
		this.setRegistryName(name);
		BlockRegistry.instance.register(this, new BlockOptions());
	}
}