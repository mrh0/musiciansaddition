package com.mrh0.musiciansaddition.container;

import com.mrh0.musiciansaddition.Index;
import com.mrh0.musiciansaddition.container.base.BaseContainer;
import com.mrh0.musiciansaddition.tileentity.XylophoneTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;

public class XylophoneContainer extends BaseContainer {

	public final XylophoneTileEntity te;
	
	protected XylophoneContainer(int id, BlockPos pos) {
		super(Index.XYLOPHONE_CONTAINER, id);
		this.te = (XylophoneTileEntity) Minecraft.getInstance().world.getTileEntity(pos);
	}
	
	public static XylophoneContainer create(int windowId, PlayerInventory playerInventory, BlockPos pos) {
		return new XylophoneContainer(windowId, pos);
	}

	public static XylophoneContainer create(int windowId, PlayerInventory playerInventory, PacketBuffer buf) {
		return new XylophoneContainer(windowId, buf.readBlockPos());
	}

}