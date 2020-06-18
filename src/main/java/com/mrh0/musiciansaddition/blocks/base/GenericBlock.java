package com.mrh0.musiciansaddition.blocks.base;

import com.mrh0.musiciansaddition.event.BlockRegistry;
import com.mrh0.musiciansaddition.event.opts.BlockOptions;

public class GenericBlock extends BaseBlock{

	public GenericBlock(String name, Properties properties, BlockOptions opts) {
		super(name, properties);
		BlockRegistry.instance.register(this, opts);
	}
}
