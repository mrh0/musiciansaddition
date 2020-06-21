package com.mrh0.musiciansaddition.gui;

import java.util.ArrayList;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mrh0.musiciansaddition.MusiciansAddition;
import com.mrh0.musiciansaddition.container.PianoContainer;
import com.mrh0.musiciansaddition.container.SpeakerContainer;
import com.mrh0.musiciansaddition.gui.base.BaseInstrumentGui;
import com.mrh0.musiciansaddition.midi.IMidiEvent;
import com.mrh0.musiciansaddition.midi.MidiHandler;
import com.mrh0.musiciansaddition.network.PlayNotePacket;
import com.mrh0.musiciansaddition.network.UpdateSpeakerPacket;
import com.mrh0.musiciansaddition.tileentity.PianoTileEntity;
import com.mrh0.musiciansaddition.tileentity.SpeakerTileEntity;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.button.Button.IPressable;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class SpeakerGui extends ContainerScreen<SpeakerContainer> implements IMidiEvent {

	private final ResourceLocation gui = new ResourceLocation(MusiciansAddition.MODID, "textures/gui/container/keyboard.png");
	
	private final SpeakerContainer screenContainer;
	private final SpeakerTileEntity te;
	
	private ArrayList<String> captext = new ArrayList<String>();
	
	private MidiHandler handler;
	
	private static final int SIZE = 16;
	
	private Button connectBtn;
	
	private Button[] btns;
	private String[] names = {"bass", "snare", "hat", "basedrum", "bell", "flute", "chime", "guitar", 
			"xylophone", "iron_xylophone", "cow_bell", "didgeridoo", "bit", "banjo", "pling", "harp"};
	
	private String[] octaves = {"1-3", "1-3", "1-3", "1-3", "5-7", "4-6", "5-7", "2-4", 
			"5-7", "3-5", "4-6", "1-3", "3-5", "3-5", "3-5", "3-5"};
	
	public SpeakerGui(SpeakerContainer screenContainer, PlayerInventory inv, ITextComponent tc) {
		super(screenContainer, inv, tc);
		this.screenContainer = screenContainer;
		this.te = screenContainer.te;
		
		this.xSize = 384;
		this.ySize = 192;
		captext.add("");
		
		if(MusiciansAddition.midi != null)
			MusiciansAddition.midi.midiEvent = this;
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		this.renderBackground();
		
		GlStateManager.color4f(1.0f,  1.0f,  1.0f, 1.0f);
		
		this.minecraft.getTextureManager().bindTexture(gui);
		
		drawLowerKeys(guiLeft, guiTop, 0, false);
		drawLowerKeys(guiLeft, guiTop, 192, true);
		
		drawUpperKeys(guiLeft, guiTop, 0, false);
		drawUpperKeys(guiLeft, guiTop, 192, true);
		
		//String i = new TranslationTextComponent("musiciansaddition.container.piano").getFormattedText();
		//this.minecraft.fontRenderer.drawString(i, this.guiLeft + this.xSize / 2 - this.minecraft.fontRenderer.getStringWidth(i) / 2, this.guiTop + 6 + 84, 4210752);
	}
	
	@Override
	protected void init() {
		super.init();
		int x = this.width / 2;
		int y = this.height / 2;
		
		IPressable p = (b) -> {};
		
		connectBtn = new Button(x - 48, y + 24 * 4, 96, 20, MusiciansAddition.midi == null ? "Connect" : "Disconnect", (b) -> {
			if(MusiciansAddition.midi != null) {
				if(MusiciansAddition.midi.midiEvent == null)
					MusiciansAddition.midi.midiEvent = this;
				else
					MusiciansAddition.midi.midiEvent = null;
			connectBtn.setMessage(MusiciansAddition.midi.midiEvent == null ? "Connect" : "Disconnect");
			}
		});
		
		this.addButton(connectBtn);
		
		btns = new Button[SIZE];
		
		for(int i = 0; i < SIZE; i++) {
			btns[i] = new Button(x + (i > 7 ? -100: 4), y + (i%8 * 24) - 4*24, 96, 20, new TranslationTextComponent("note.musiciansaddition."+names[i]).getFormattedText(), p);
			this.addButton(btns[i]);
			btns[i].active = te.isInstrumentActive(i);
		}
		
	}
	
	@Override
	public boolean mouseClicked(double p_mouseClicked_1_, double p_mouseClicked_3_, int p_mouseClicked_5_) {
		for(int i = 0; i < SIZE; i++) {
			if(btns[i].isHovered())
				buttonClicked(btns[i], i);
		}
		
		return super.mouseClicked(p_mouseClicked_1_, p_mouseClicked_3_, p_mouseClicked_5_);
	}
	
	private void buttonClicked(Button b, int id) {
		b.active = !b.active;
		sendInstrumentUpdate(getEncoded());
	}
	
	private int getEncoded() {
		int r = 0;
		for(int i = 0; i < SIZE; i++) {
			r += btns[i].active ? Math.pow(2, i) : 0;
		}
		return r;
	}
	
	private void sendInstrumentUpdate(int data) {
		if(getTE() != null)
			MusiciansAddition.Network.sendToServer(new UpdateSpeakerPacket(this.getTE().getPos(), data));
	}
	
	private void drawLowerKeys(int left, int top, int y, boolean invert) {

	}
	
	private void drawUpperKeys(int left, int top, int y, boolean invert) {

	}
	
	@Override
	public void render(int x, int y, float ticks) {
		//this.renderBackground();
		super.render(x, y, ticks);
		GlStateManager.disableLighting();
		GlStateManager.disableBlend();
		
		captext.set(0, "");
		
		for(int i = 0; i < SIZE; i++) {
			if(btns[i].isHovered())
				captext.set(0, "F#"+octaves[i]);
		}
		
		if(captext.get(0).length() > 0)
			renderTooltip(captext, x, y);
		else
			this.renderHoveredToolTip(x, y);
	}
	
	public TileEntity getTE() {
		return te;
	}
	
	private void sendNote(int note) {
		if(getTE() != null)
			MusiciansAddition.Network.sendToServer(new PlayNotePacket(this.getTE().getPos(), note));
	}

	@Override
	public void minecraftNote(int note, boolean on) {
		note += 24;
		if(note < 0)
			return;
		if(on)
			sendNote(note);
	}
	
	@Override
	public void onClose() {
		MusiciansAddition.midi.midiEvent = null;
		System.out.println("CLOSE");
		super.onClose();
	}
}