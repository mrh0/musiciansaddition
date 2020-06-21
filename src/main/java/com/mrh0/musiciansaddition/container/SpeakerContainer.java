package com.mrh0.musiciansaddition.container;

import com.mrh0.musiciansaddition.Index;
import com.mrh0.musiciansaddition.container.base.BaseContainer;
import com.mrh0.musiciansaddition.tileentity.SpeakerTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;

public class SpeakerContainer extends BaseContainer {

	public final SpeakerTileEntity te;
	
	protected SpeakerContainer(int id, BlockPos pos) {
		super(Index.SPEAKER_CONTAINER, id);
		this.te = (SpeakerTileEntity) Minecraft.getInstance().world.getTileEntity(pos);
	}
	
	public static SpeakerContainer create(int windowId, PlayerInventory playerInventory, BlockPos pos) {
		return new SpeakerContainer(windowId, pos);
	}

	public static SpeakerContainer create(int windowId, PlayerInventory playerInventory, PacketBuffer buf) {
		return new SpeakerContainer(windowId, buf.readBlockPos());
	}
}
