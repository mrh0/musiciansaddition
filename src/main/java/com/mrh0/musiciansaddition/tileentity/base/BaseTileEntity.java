package com.mrh0.musiciansaddition.tileentity.base;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public abstract class BaseTileEntity extends TileEntity{
	
	public BaseTileEntity(TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
	}
}
