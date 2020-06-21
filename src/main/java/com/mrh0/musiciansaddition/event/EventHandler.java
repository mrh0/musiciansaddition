package com.mrh0.musiciansaddition.event;

import com.mrh0.musiciansaddition.Index;
import com.mrh0.musiciansaddition.MusiciansAddition;
import com.mrh0.musiciansaddition.gui.BassGui;
import com.mrh0.musiciansaddition.gui.PianoGui;
import com.mrh0.musiciansaddition.gui.SpeakerGui;
import com.mrh0.musiciansaddition.gui.XylophoneGui;

import net.minecraft.block.Block;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = MusiciansAddition.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventHandler {
	@SubscribeEvent
	public static void registerBlock(Register<Block> evt){
		IForgeRegistry<Block> reg = evt.getRegistry();
		BlockRegistry.instance.initAll(reg);
	}
	
	@SubscribeEvent
	public static void registerItem(Register<Item> evt){
		IForgeRegistry<Item> reg = evt.getRegistry();
		ItemRegistry.instance.initAll(reg);
	}
	
	@SubscribeEvent
	public static void registerTileEntity(Register<TileEntityType<?>> evt){
		IForgeRegistry<TileEntityType<?>> reg = evt.getRegistry();
		TileEntityRegistry.instance.initAll(reg);
	}
	
	@SubscribeEvent
	public static void registerContainer(Register<ContainerType<?>> evt){
		IForgeRegistry<ContainerType<?>> reg = evt.getRegistry();
		ContainerRegistry.instance.initAll(reg);
	}
	
    @OnlyIn(Dist.CLIENT)
    public static void registerScreenFactories(){
    	ScreenManager.registerFactory(Index.PIANO_CONTAINER, PianoGui::new);
    	ScreenManager.registerFactory(Index.XYLOPHONE_CONTAINER, XylophoneGui::new);
    	ScreenManager.registerFactory(Index.BASS_CONTAINER, BassGui::new);
    	ScreenManager.registerFactory(Index.SPEAKER_CONTAINER, SpeakerGui::new);
    	//ScreenManager.registerFactory(Index.ELECTRIC_FURNACE_CONTAINER, ElectricFurnaceContainerGui::new);
    }
	
	@SubscribeEvent
    public static void registerEntityType(Register<EntityType<?>> evt) {
    	
    }
}
