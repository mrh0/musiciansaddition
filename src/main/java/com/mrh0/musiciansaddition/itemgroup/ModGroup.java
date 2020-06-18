package com.mrh0.musiciansaddition.itemgroup;

import com.mrh0.musiciansaddition.MusiciansAddition;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModGroup extends ItemGroup{
	public static ModGroup MAIN;;
	
	public ModGroup(String name) {
		super(MusiciansAddition.MODID+":"+name);
		MAIN = this;
	}

	@Override
	public ItemStack createIcon() {
		return new ItemStack(Blocks.NOTE_BLOCK);
	}
}
