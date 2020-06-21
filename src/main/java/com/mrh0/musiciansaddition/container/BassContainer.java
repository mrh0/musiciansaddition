package com.mrh0.musiciansaddition.container;

import com.mrh0.musiciansaddition.Index;
import com.mrh0.musiciansaddition.container.base.BaseContainer;
import com.mrh0.musiciansaddition.tileentity.BassTileEntity;
import com.mrh0.musiciansaddition.tileentity.PianoTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;

public class BassContainer extends BaseContainer {

	public final BassTileEntity te;
	
	protected BassContainer(int id, BlockPos pos) {
		super(Index.BASS_CONTAINER, id);
		this.te = (BassTileEntity) Minecraft.getInstance().world.getTileEntity(pos);
	}
	
	public static BassContainer create(int windowId, PlayerInventory playerInventory, BlockPos pos) {
		return new BassContainer(windowId, pos);
	}

	public static BassContainer create(int windowId, PlayerInventory playerInventory, PacketBuffer buf) {
		return new BassContainer(windowId, buf.readBlockPos());
	}

}
