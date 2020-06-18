package com.mrh0.musiciansaddition.gui;

import java.util.ArrayList;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mrh0.musiciansaddition.MusiciansAddition;
import com.mrh0.musiciansaddition.container.PianoContainer;
import com.mrh0.musiciansaddition.midi.IMidiEvent;
import com.mrh0.musiciansaddition.midi.MidiHandler;
import com.mrh0.musiciansaddition.network.PlayNotePacket;
import com.mrh0.musiciansaddition.tileentity.PianoTileEntity;
import com.mrh0.musiciansaddition.util.Notes;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class PianoGui extends BaseInstrumentGui<PianoContainer> implements IMidiEvent {

	private final ResourceLocation gui = new ResourceLocation(MusiciansAddition.MODID, "textures/gui/container/keyboard.png");
	
	private final PianoContainer screenContainer;
	private final PianoTileEntity te;
	
	private ArrayList<String> captext = new ArrayList<String>();
	
	private MidiHandler handler;
	
	public PianoGui(PianoContainer screenContainer, PlayerInventory inv, ITextComponent tc) {
		super(screenContainer, inv, tc);
		this.screenContainer = screenContainer;
		this.te = screenContainer.te;
		
		this.xSize = 384;
		this.ySize = 192;
		captext.add("");
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		//this.renderBackground();
		
		GlStateManager.color4f(1.0f,  1.0f,  1.0f, 1.0f);
		
		this.minecraft.getTextureManager().bindTexture(gui);
		
		drawLowerKeys(guiLeft, guiTop, 0, false);
		drawLowerKeys(guiLeft, guiTop, 192, true);
		
		drawUpperKeys(guiLeft, guiTop, 0, false);
		drawUpperKeys(guiLeft, guiTop, 192, true);
		
		//String i = new TranslationTextComponent("musiciansaddition.container.piano").getFormattedText();
		//this.minecraft.fontRenderer.drawString(i, this.guiLeft + this.xSize / 2 - this.minecraft.fontRenderer.getStringWidth(i) / 2, this.guiTop + 6 + 84, 4210752);
	}
	
	private void drawLowerKeys(int left, int top, int y, boolean invert) {
		int w = 24;
		int h = 96;
		
		if(invert) {
			blit(left, top, 0, h+y, w, h, this.xSize, this.ySize*2);
			blit(left+w*15, top, w*15, h+y, w, h, this.xSize, this.ySize*2);
		}
		
		if(getPressed(1) ^ invert)
			blit(left+w, top, w, h+y, w, h, this.xSize, this.ySize*2);
		if(getPressed(3) ^ invert)
			blit(left+w*2, top, w*2, h+y, w, h, this.xSize, this.ySize*2);
		if(getPressed(5) ^ invert)
			blit(left+w*3, top, w*3, h+y, w, h, this.xSize, this.ySize*2);
		if(getPressed(6) ^ invert)
			blit(left+w*4, top, w*4, h+y, w, h, this.xSize, this.ySize*2);
		if(getPressed(8) ^ invert)
			blit(left+w*5, top, w*5, h+y, w, h, this.xSize, this.ySize*2);
		if(getPressed(10) ^ invert)
			blit(left+w*6, top, w*6, h+y, w, h, this.xSize, this.ySize*2);
		if(getPressed(11) ^ invert)
			blit(left+w*7, top, w*7, h+y, w, h, this.xSize, this.ySize*2);
		if(getPressed(13) ^ invert)
			blit(left+w*8, top, w*8, h+y, w, h, this.xSize, this.ySize*2);
		if(getPressed(15) ^ invert)
			blit(left+w*9, top, w*9, h+y, w, h, this.xSize, this.ySize*2);
		if(getPressed(17) ^ invert)
			blit(left+w*10, top, w*10, h+y, w, h, this.xSize, this.ySize*2);
		if(getPressed(18) ^ invert)
			blit(left+w*11, top, w*11, h+y, w, h, this.xSize, this.ySize*2);
		if(getPressed(20) ^ invert)
			blit(left+w*12, top, w*12, h+y, w, h, this.xSize, this.ySize*2);
		if(getPressed(22) ^ invert)
			blit(left+w*13, top, w*13, h+y, w, h, this.xSize, this.ySize*2);
		if(getPressed(23) ^ invert)
			blit(left+w*14, top, w*14, h+y, w, h, this.xSize, this.ySize*2);
	}
	
	private void drawUpperKeys(int left, int top, int y, boolean invert) {
		int w = 14;
		int h = 54;

		if(getPressed(0) ^ invert)
			blit(left+17, top, 17, y, w, h, this.xSize, this.ySize*2);
		if(getPressed(2) ^ invert)
			blit(left+41, top, 41, y, w, h, this.xSize, this.ySize*2);
		if(getPressed(4) ^ invert)
			blit(left+65, top, 65, y, w, h, this.xSize, this.ySize*2);
		if(getPressed(7) ^ invert)
			blit(left+113, top, 113, y, w, h, this.xSize, this.ySize*2);
		if(getPressed(9) ^ invert)
			blit(left+137, top, 137, y, w, h, this.xSize, this.ySize*2);
		if(getPressed(12) ^ invert)
			blit(left+185, top, 185, y, w, h, this.xSize, this.ySize*2);
		if(getPressed(14) ^ invert)
			blit(left+209, top, 209, y, w, h, this.xSize, this.ySize*2);
		if(getPressed(16) ^ invert)
			blit(left+233, top, 233, y, w, h, this.xSize, this.ySize*2);
		if(getPressed(19) ^ invert)
			blit(left+281, top, 281, y, w, h, this.xSize, this.ySize*2);
		if(getPressed(21) ^ invert)
			blit(left+305, top, 305, y, w, h, this.xSize, this.ySize*2);
		if(getPressed(24) ^ invert)
			blit(left+353, top, 353, y, w, h, this.xSize, this.ySize*2);
	}
	
	
	
	@Override
	public void render(int x, int y, float ticks) {
		//this.renderBackground();
		super.render(x, y, ticks);
		GlStateManager.disableLighting();
		GlStateManager.disableBlend();
		
		captext.set(0, "");
		
		int w = 24;
		int h = 96;
		
		int left = guiLeft;
		int top = guiTop;
		
		if(within(x, y, left+w, top, w, h))
			captext.set(0, "G [A]");
		if(within(x, y, left+w*2, top, w, h))
			captext.set(0, "A [Z]");
		if(within(x, y, left+w*3, top, w, h))
			captext.set(0, "B [S]");
		if(within(x, y, left+w*4, top, w, h))
			captext.set(0, "C [X]");
		if(within(x, y, left+w*5, top, w, h))
			captext.set(0, "D [D]");
		if(within(x, y, left+w*6, top, w, h))
			captext.set(0, "E [C]");
		if(within(x, y, left+w*7, top, w, h))
			captext.set(0, "F [F]");
		if(within(x, y, left+w*8, top, w, h))
			captext.set(0, "G [V]");
		if(within(x, y, left+w*9, top, w, h))
			captext.set(0, "A [G]");
		if(within(x, y, left+w*10, top, w, h))
			captext.set(0, "B [B]");
		if(within(x, y, left+w*11, top, w, h))
			captext.set(0, "C [H]");
		if(within(x, y, left+w*12, top, w, h))
			captext.set(0, "D [N]");
		if(within(x, y, left+w*13, top, w, h))
			captext.set(0, "E [J]");
		if(within(x, y, left+w*14, top, w, h))
			captext.set(0, "F [M]");
		
		w = 14;
		h = 54;
		
		if(within(x, y, left+17, top, w, h))
			captext.set(0, "F# [Q]");
		if(within(x, y, left+41, top, w, h))
			captext.set(0, "G# [W]");
		if(within(x, y, left+65, top, w, h))
			captext.set(0, "A# [E]");
		if(within(x, y, left+113, top, w, h))
			captext.set(0, "C# [R]");
		if(within(x, y, left+137, top, w, h))
			captext.set(0, "D# [T]");
		if(within(x, y, left+185, top, w, h))
			captext.set(0, "F# [Y]");
		if(within(x, y, left+209, top, w, h))
			captext.set(0, "G# [U]");
		if(within(x, y, left+233, top, w, h))
			captext.set(0, "A# [I]");
		if(within(x, y, left+281, top, w, h))
			captext.set(0, "C# [O]");
		if(within(x, y, left+305, top, w, h))
			captext.set(0, "D# [P]");
		if(within(x, y, left+353, top, w, h))
			captext.set(0, "F# [K]");
		
		if(captext.get(0).length() > 0)
			renderTooltip(captext, x, y);
		else
			this.renderHoveredToolTip(x, y);
	}
	
	@Override
	public TileEntity getTE() {
		return te;
	}
}
