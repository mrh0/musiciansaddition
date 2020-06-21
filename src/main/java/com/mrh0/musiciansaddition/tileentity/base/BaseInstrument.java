package com.mrh0.musiciansaddition.tileentity.base;

import com.mrh0.musiciansaddition.MusiciansAddition;
import net.minecraft.tileentity.TileEntityType;

public abstract class BaseInstrument extends BaseTileEntity {
	public BaseInstrument(TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
	}

	public abstract void playNote(int note);
	
	@Override
	public void remove() {
		if(MusiciansAddition.midi != null)
			if(MusiciansAddition.midi.midiEvent == this)
				MusiciansAddition.midi.midiEvent = null;
		super.remove();
	}
	
	@Override
	public void onChunkUnloaded() {
		super.onChunkUnloaded();
		if(MusiciansAddition.midi != null)
			if(MusiciansAddition.midi.midiEvent == this)
				MusiciansAddition.midi.midiEvent = null;
	}
}
