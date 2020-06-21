package com.mrh0.musiciansaddition.tileentity;

import com.mrh0.musiciansaddition.Index;
import com.mrh0.musiciansaddition.MusiciansAddition;
import com.mrh0.musiciansaddition.container.PianoContainer;
import com.mrh0.musiciansaddition.container.SpeakerContainer;
import com.mrh0.musiciansaddition.network.PlayNotePacket;
import com.mrh0.musiciansaddition.tileentity.base.BaseInstrument;
import com.mrh0.musiciansaddition.util.Notes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class SpeakerTileEntity extends BaseInstrument implements INamedContainerProvider {

	private static final int SIZE = 16;
	private int instruments;
	
	public SpeakerTileEntity() {
		super(Index.SPEAKER_TILE_ENTITY_TYPE);
	}
	
	@Override
	public Container createMenu(int windowId, PlayerInventory inv, PlayerEntity player) {
		return SpeakerContainer.create(windowId, inv, this.getPos());
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TranslationTextComponent("musiciansaddition.container.speaker");
	}
	
	@Override
	public void playNote(int note) {
		if(note > 0 && note <= 24) {
			if(isInstrumentActive(0))
				Notes.playNote(world, pos, SoundEvents.BLOCK_NOTE_BLOCK_BASS, note);
			if(isInstrumentActive(1))
				Notes.playNote(world, pos, SoundEvents.BLOCK_NOTE_BLOCK_SNARE, note);
			if(isInstrumentActive(2))
				Notes.playNote(world, pos, SoundEvents.BLOCK_NOTE_BLOCK_HAT, note);
			if(isInstrumentActive(3))
				Notes.playNote(world, pos, SoundEvents.BLOCK_NOTE_BLOCK_BASEDRUM, note);
			if(isInstrumentActive(11))
				Notes.playNote(world, pos, SoundEvents.BLOCK_NOTE_BLOCK_DIDGERIDOO, note);
		}
		if(note > 12 && note <= 36) {
			if(isInstrumentActive(7))
				Notes.playNote(world, pos, SoundEvents.BLOCK_NOTE_BLOCK_GUITAR, note - 12);
		}
		if(note > 24 && note <= 48) {
			if(isInstrumentActive(6))
				Notes.playNote(world, pos, SoundEvents.BLOCK_NOTE_BLOCK_CHIME, note - 24);
			if(isInstrumentActive(9))
				Notes.playNote(world, pos, SoundEvents.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE, note - 24);
			if(isInstrumentActive(12))
				Notes.playNote(world, pos, SoundEvents.BLOCK_NOTE_BLOCK_BIT, note - 24);
			if(isInstrumentActive(13))
				Notes.playNote(world, pos, SoundEvents.BLOCK_NOTE_BLOCK_BANJO, note - 24);
			if(isInstrumentActive(14))
				Notes.playNote(world, pos, SoundEvents.BLOCK_NOTE_BLOCK_PLING, note - 24);
			if(isInstrumentActive(15))
				Notes.playNote(world, pos, SoundEvents.BLOCK_NOTE_BLOCK_HARP, note - 24);
		}
		if(note > 36 && note <= 60) {
			if(isInstrumentActive(5))
				Notes.playNote(world, pos, SoundEvents.BLOCK_NOTE_BLOCK_FLUTE, note - 36);
			if(isInstrumentActive(10))
				Notes.playNote(world, pos, SoundEvents.BLOCK_NOTE_BLOCK_COW_BELL, note - 36);
		}
		if(note > 48 ) {//&& note <= 72
			if(isInstrumentActive(4))
				Notes.playNote(world, pos, SoundEvents.BLOCK_NOTE_BLOCK_BELL, note - 48);
			if(isInstrumentActive(8))
				Notes.playNote(world, pos, SoundEvents.BLOCK_NOTE_BLOCK_XYLOPHONE, note - 48);
		}
	}
	
	public void updateData(int encoded) {
		instruments = encoded;
	}
	
	@Override
	public void read(CompoundNBT nbt) {
		instruments = nbt.getInt("instruments");
		super.read(nbt);
	}
	
	@Override
	public CompoundNBT write(CompoundNBT nbt) {
		nbt.putInt("instruments", instruments);
		return super.write(nbt);
	}
	
	private int getBit(int n, int k) {
	    return (n >> k) & 1;
	}
	
	public boolean isInstrumentActive(int index) {
		return getBit(instruments, index) > 0;
	}

	@Override
	public SUpdateTileEntityPacket getUpdatePacket() {
		CompoundNBT update = getUpdateTag();
        int data = 0;
        return new SUpdateTileEntityPacket(this.pos, data, update);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
		CompoundNBT update = pkt.getNbtCompound();
        handleUpdateTag(update);
	}
	
	@Override
	public CompoundNBT getUpdateTag() {
		CompoundNBT nbt = new CompoundNBT();
        write(nbt);
        return nbt;
	}
	
	@Override
	public void handleUpdateTag(CompoundNBT nbt) {
		this.read(nbt);
	}
}
