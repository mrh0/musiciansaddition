package com.mrh0.musiciansaddition.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Notes {
	public static int getNoteFromKey(int key) {
		switch(key) {
			case 65:
				return 1;
			case 90:
				return 3;
			case 83:
				return 5;
			case 88:
				return 6;
			case 68:
				return 8;
			case 67:
				return 10;
			case 70:
				return 11;
			case 86:
				return 13;
			case 71:
				return 15;
			case 66:
				return 17;
			case 72:
				return 18;
			case 78:
				return 20;
			case 74:
				return 22;
			case 77:
				return 23;//
			case 81:
				return 0;
			case 87:
				return 2;
			case 69:
				return 4;
			case 82:
				return 7;
			case 84:
				return 9;
			case 89:
				return 12;
			case 85:
				return 14;
			case 73:
				return 16;
			case 79:
				return 19;
			case 80:
				return 21;
			case 75:
				return 24;
			default:
				return -1;
		}
	}
	
	public static String buttomFromNote(int n) {
		switch(n) {
			case 0:
				return "Q";
		}
		return "?";
	}
	
	public static void playNote(World world, BlockPos pos, SoundEvent evt, int note) {
		if(note < 0)// || note > 24)
			return;
		float f = (float)Math.pow(2.0D, (double)(note - 12) / 12.0D);
		world.playSound((PlayerEntity)null, pos, evt, SoundCategory.RECORDS, 3.0F, f);
		//world.addParticle(ParticleTypes.NOTE, (double)pos.getX() + 0.5D, (double)pos.getY() + 1.2D, (double)pos.getZ() + 0.5D, (double)note / 24.0D, 0.0D, 0.0D);
	}
}
