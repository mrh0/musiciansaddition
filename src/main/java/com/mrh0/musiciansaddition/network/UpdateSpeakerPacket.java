package com.mrh0.musiciansaddition.network;

import java.util.function.Supplier;

import com.mrh0.musiciansaddition.tileentity.PianoTileEntity;
import com.mrh0.musiciansaddition.tileentity.SpeakerTileEntity;
import com.mrh0.musiciansaddition.tileentity.base.BaseInstrument;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;

public class UpdateSpeakerPacket {
	
	private BlockPos pos;
	private int data;
	
	public UpdateSpeakerPacket(BlockPos pos, int data) {
		this.pos = pos;
		this.data = data;
	}
	
	public static void encode(UpdateSpeakerPacket packet, PacketBuffer tag) {
        tag.writeBlockPos(packet.pos);
        tag.writeInt(packet.data);
    }
	
	public static UpdateSpeakerPacket decode(PacketBuffer buf) {
		UpdateSpeakerPacket scp = new UpdateSpeakerPacket(buf.readBlockPos(), buf.readInt());
        return scp;
    }
	
	public static void handle(UpdateSpeakerPacket pkt, Supplier<NetworkEvent.Context> ctx) {
		System.out.println("Sending Update");
		ctx.get().enqueueWork(() -> {
			try {
				ServerPlayerEntity player = ctx.get().getSender();
			
			if (player != null) {
				sendUpdate(pkt.pos, player, pkt.data);
			}
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		ctx.get().setPacketHandled(true);
		
		}
	private static void sendUpdate(BlockPos pos, ServerPlayerEntity player, int data) {
		SpeakerTileEntity te = (SpeakerTileEntity) player.world.getTileEntity(pos);
        if (te != null) {
        	te.updateData(data);
            SUpdateTileEntityPacket supdatetileentitypacket = te.getUpdatePacket();
            if (supdatetileentitypacket != null) {
                player.connection.sendPacket(supdatetileentitypacket);
            }
        }
    }
}
