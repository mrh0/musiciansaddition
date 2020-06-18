package com.mrh0.musiciansaddition.tileentity.base;

import net.minecraft.tileentity.TileEntityType;

public abstract class BaseInstrument extends BaseTileEntity {
	public BaseInstrument(TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
	}

	public abstract void playNote(int note);
}
