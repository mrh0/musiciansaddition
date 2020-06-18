package com.mrh0.musiciansaddition.event;

import com.mrh0.musiciansaddition.event.opts.ItemOptions;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class ItemRegistry extends GenericRegistry<Item, ItemOptions>{
	
	public static ItemRegistry instance;
	
	public ItemRegistry() {
		super();
		instance = this;
	}

	@Override
	public void init(IForgeRegistry<Item> reg, Item obj) {
		reg.register(obj);
	}
}
