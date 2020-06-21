package com.mrh0.musiciansaddition.tileentity;

import com.mrh0.musiciansaddition.Index;
import com.mrh0.musiciansaddition.container.BassContainer;
import com.mrh0.musiciansaddition.tileentity.base.BaseInstrument;
import com.mrh0.musiciansaddition.util.Notes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class BassTileEntity extends BaseInstrument implements INamedContainerProvider {

	public BassTileEntity() {
		super(Index.BASS_TILE_ENTITY_TYPE);
	}
	
	@Override
	public Container createMenu(int windowId, PlayerInventory inv, PlayerEntity player) {
		return BassContainer.create(windowId, inv, this.getPos());
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TranslationTextComponent("musiciansaddition.container.bass");
	}
	
	@Override
	public void playNote(int note) {
		if(note >= 12 && note <= 36 )
			Notes.playNote(world, pos, SoundEvents.BLOCK_NOTE_BLOCK_GUITAR, note - 12);
		if(note <= 24 && note >= 0)
			Notes.playNote(world, pos, SoundEvents.BLOCK_NOTE_BLOCK_BASS, note);//SoundEvents.BLOCK_NOTE_BLOCK_PLING
	}
}