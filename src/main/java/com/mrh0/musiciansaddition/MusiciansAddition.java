package com.mrh0.musiciansaddition;

import com.mrh0.musiciansaddition.proxy.CommonProxy;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

import com.mrh0.musiciansaddition.config.Config;
import com.mrh0.musiciansaddition.event.BlockRegistry;
import com.mrh0.musiciansaddition.event.ContainerRegistry;
import com.mrh0.musiciansaddition.event.EventHandler;
import com.mrh0.musiciansaddition.event.ItemRegistry;
import com.mrh0.musiciansaddition.event.TileEntityRegistry;
import com.mrh0.musiciansaddition.itemgroup.ModGroup;
import com.mrh0.musiciansaddition.midi.MidiHandler;
import com.mrh0.musiciansaddition.network.PlayNotePacket;
import com.mrh0.musiciansaddition.proxy.*;

/*
 * Author: MRH0 (aka MRH, MRHminer & hminer)
 * Musicians Instruments & Additions
 * MIT License
 */
@Mod(MusiciansAddition.MODID)
public class MusiciansAddition {
	public static final String MODID = "musiciansaddition";
	
	public static CommonProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);
	
	private static final String PROTOCOL = "1";
	
	public static MidiHandler midi;
	
	public static final SimpleChannel Network = NetworkRegistry.ChannelBuilder.named(new ResourceLocation(MODID, "main"))
            .clientAcceptedVersions(PROTOCOL::equals)
            .serverAcceptedVersions(PROTOCOL::equals)
            .networkProtocolVersion(() -> PROTOCOL)
            .simpleChannel();
	
	public MusiciansAddition() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    	FMLJavaModLoadingContext.get().getModEventBus().addListener(this::postInit);
    	MinecraftForge.EVENT_BUS.addListener(this::serverevt);
    	MinecraftForge.EVENT_BUS.register(this);
    	
    	new ModGroup("musicians_addition_group");
    	
    	new BlockRegistry();
    	new ItemRegistry();
    	new TileEntityRegistry();
    	new ContainerRegistry();
    	
    	
    	
    	new Index();
    	
    	Config.loadConfig(Config.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve("musiciansaddition-common.toml"));
    	
    	if(Config.MIDI_ENABLED.get())
        	midi = new MidiHandler(null);
	}

    private void setup(final FMLCommonSetupEvent evt) {
    	proxy.init(evt);
    	DistExecutor.runWhenOn(Dist.CLIENT, () -> EventHandler::registerScreenFactories);
    }

    public void postInit(FMLLoadCompleteEvent evt) {
    	int i = 0;
        Network.registerMessage(i++, PlayNotePacket.class, PlayNotePacket::encode, PlayNotePacket::decode, PlayNotePacket::handle);
        
    	System.out.println("Musicians Addition Initialized!");
    }
    
    public void serverevt(FMLServerStartingEvent evt) {
    	
    }
}
