package com.mrh0.musiciansaddition.tileentity;

import com.mrh0.musiciansaddition.Index;
import com.mrh0.musiciansaddition.container.PianoContainer;
import com.mrh0.musiciansaddition.tileentity.base.BaseInstrument;
import com.mrh0.musiciansaddition.util.Notes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class PianoTileEntity extends BaseInstrument implements INamedContainerProvider {

	public PianoTileEntity() {
		super(Index.PIANO_TILE_ENTITY_TYPE);
	}
	
	@Override
	public Container createMenu(int windowId, PlayerInventory inv, PlayerEntity player) {
		return PianoContainer.create(windowId, inv, this.getPos());
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TranslationTextComponent("musiciansaddition.container.piano");
	}
	
	@Override
	public void playNote(int note) {
		if(note > 24)
			Notes.playNote(world, pos, SoundEvents.BLOCK_NOTE_BLOCK_CHIME, note - 24);
		else
			Notes.playNote(world, pos, SoundEvents.BLOCK_NOTE_BLOCK_PLING, note);//SoundEvents.BLOCK_NOTE_BLOCK_PLING
	}
}
