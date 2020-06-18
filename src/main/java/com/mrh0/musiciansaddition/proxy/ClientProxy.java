package com.mrh0.musiciansaddition.proxy;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class ClientProxy extends CommonProxy {
	@Override
	public void init(final FMLCommonSetupEvent evt){
		//RenderingRegistry.registerEntityRenderingHandler(Index.SEAT_ENTITY_TYPE, new SeatRenderFactory());
	}
}
