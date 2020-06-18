package com.mrh0.musiciansaddition.container;

import com.mrh0.musiciansaddition.Index;
import com.mrh0.musiciansaddition.container.base.BaseContainer;
import com.mrh0.musiciansaddition.tileentity.PianoTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;

public class PianoContainer extends BaseContainer {

	public final PianoTileEntity te;
	
	protected PianoContainer(int id, BlockPos pos) {
		super(Index.PIANO_CONTAINER, id);
		this.te = (PianoTileEntity) Minecraft.getInstance().world.getTileEntity(pos);
	}
	
	public static PianoContainer create(int windowId, PlayerInventory playerInventory, BlockPos pos) {
		return new PianoContainer(windowId, pos);
	}

	public static PianoContainer create(int windowId, PlayerInventory playerInventory, PacketBuffer buf) {
		return new PianoContainer(windowId, buf.readBlockPos());
	}

}
