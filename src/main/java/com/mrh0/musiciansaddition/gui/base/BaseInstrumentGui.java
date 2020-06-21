package com.mrh0.musiciansaddition.gui.base;

import com.mrh0.musiciansaddition.MusiciansAddition;
import com.mrh0.musiciansaddition.midi.IMidiEvent;
import com.mrh0.musiciansaddition.network.PlayNotePacket;
import com.mrh0.musiciansaddition.util.Notes;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public abstract class BaseInstrumentGui<T extends Container> extends ContainerScreen<T> implements IMidiEvent{

	protected int[] keys = new int[25];
	private boolean mode = false;
	
	public BaseInstrumentGui(T screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
		
		if(MusiciansAddition.midi != null)
			MusiciansAddition.midi.midiEvent = this;
	}
	
	public abstract int getOctaveOffset();

	@Override
	public void tick() {
		for(int i = 0; i < keys.length; i++) {
			if(keys[i] > 0)
				keys[i]--;
		}
		super.tick();
	}
	
	@Override
	public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
		if(keyCode == 256) {
			onClose();
		}
		if(keyCode == 258) {
			mode = !mode;
		}
		//System.out.println(keyCode);
		int note = Notes.getNoteFromKey(keyCode);
		if(note != -1) {
			if(!getPressed(note))
				sendNote((modifiers == 1) ^ mode ? note + 25 : note);
			setPressed(note, 15);//keys[note] = true;
		}
		return super.keyPressed(keyCode, scanCode, modifiers);
	}
	
	@Override
	public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
		int note = Notes.getNoteFromKey(keyCode);
		if(note != -1) {
			keys[note] = 0;
		}
		return super.keyReleased(keyCode, scanCode, modifiers);
	}
	
	private void sendNote(int note) {
		if(getTE().isRemoved())
			return;
		if(getTE() != null)
			MusiciansAddition.Network.sendToServer(new PlayNotePacket(this.getTE().getPos(), note));
	}

	@Override
	public void minecraftNote(int note, boolean on) {
		note -= getOctaveOffset() * 12;
		if(note < 0)
			return;
		if(on)
			sendNote(mode ? (note + 25) % 50 : note);
		setPressed(note);//keys[note] = on;
	}
	
	@Override
	public void onClose() {
		MusiciansAddition.midi.midiEvent = null;
		//System.out.println("CLOSE");
		super.onClose();
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		
	}
	
	public boolean within(int x, int y, int px, int py, int w, int h) {
		return x > px && x < px + w && y > py && y < py + h;
	}
	
	@Override
	protected void init() {
	  super.init();
	  this.minecraft.keyboardListener.enableRepeatEvents(false);
	}
	
	@Override
	public void removed() {
		super.removed();
		this.minecraft.keyboardListener.enableRepeatEvents(false);
		//this.container.removeListener(this);
	}
	
	@Override
	public void resize(Minecraft mc, int x, int y) {
		this.init(mc, x, y);
	}
	
	public boolean getPressed(int note) {
		if(note < 0)
			return false;
		return keys[note%25] > 0;
	}
	
	public void setPressed(int note) {
		if(note < 0)
			return;
		keys[note%25] = 5;
	}
	
	public void setPressed(int note, int len) {
		if(note < 0)
			return;
		keys[note%25] = len;
	}
	
	private boolean isUpperNote(int note) {
		return note == 0 || note == 2 || note == 4 || note == 7 || note == 9 || note == 12 || note == 14 || note == 16 || note == 19 || note == 21 || note == 24;
	}
	
	public abstract TileEntity getTE();
}
