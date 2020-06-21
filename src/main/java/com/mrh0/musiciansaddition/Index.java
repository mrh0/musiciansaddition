package com.mrh0.musiciansaddition;

import com.mrh0.musiciansaddition.blocks.Bass;
import com.mrh0.musiciansaddition.blocks.Piano;
import com.mrh0.musiciansaddition.blocks.Speaker;
import com.mrh0.musiciansaddition.blocks.Xylophone;
import com.mrh0.musiciansaddition.container.BassContainer;
import com.mrh0.musiciansaddition.container.PianoContainer;
import com.mrh0.musiciansaddition.container.SpeakerContainer;
import com.mrh0.musiciansaddition.container.XylophoneContainer;
import com.mrh0.musiciansaddition.event.ContainerRegistry;
import com.mrh0.musiciansaddition.event.TileEntityRegistry;
import com.mrh0.musiciansaddition.event.opts.ContainerOptions;
import com.mrh0.musiciansaddition.event.opts.TileEntityOptions;
import com.mrh0.musiciansaddition.tileentity.BassTileEntity;
import com.mrh0.musiciansaddition.tileentity.PianoTileEntity;
import com.mrh0.musiciansaddition.tileentity.SpeakerTileEntity;
import com.mrh0.musiciansaddition.tileentity.XylophoneTileEntity;
import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.extensions.IForgeContainerType;

public class Index {
	
	public static final Block PIANO = new Piano();
	public static final Block XYLOPHONE = new Xylophone();
	public static final Block BASS = new Bass();
	
	public static final Block SPEAKER = new Speaker();
	
	public static TileEntityType<PianoTileEntity> PIANO_TILE_ENTITY_TYPE = 
			TileEntityRegistry.instance.<PianoTileEntity>register(PianoTileEntity::new, new TileEntityOptions("piano", PIANO));
	
	public static TileEntityType<XylophoneTileEntity> XYLOPHONE_TILE_ENTITY_TYPE = 
			TileEntityRegistry.instance.<XylophoneTileEntity>register(XylophoneTileEntity::new, new TileEntityOptions("xylophone", XYLOPHONE));
	
	public static TileEntityType<BassTileEntity> BASS_TILE_ENTITY_TYPE = 
			TileEntityRegistry.instance.<BassTileEntity>register(BassTileEntity::new, new TileEntityOptions("bass", BASS));
	
	public static TileEntityType<SpeakerTileEntity> SPEAKER_TILE_ENTITY_TYPE = 
			TileEntityRegistry.instance.<SpeakerTileEntity>register(SpeakerTileEntity::new, new TileEntityOptions("speaker", SPEAKER));
	
	public static ContainerType<PianoContainer> PIANO_CONTAINER = (ContainerType<PianoContainer>) 
			ContainerRegistry.instance.register(IForgeContainerType.create(PianoContainer::create).setRegistryName("piano_container"), new ContainerOptions());
	
	public static ContainerType<XylophoneContainer> XYLOPHONE_CONTAINER = (ContainerType<XylophoneContainer>) 
			ContainerRegistry.instance.register(IForgeContainerType.create(XylophoneContainer::create).setRegistryName("xylophone_container"), new ContainerOptions());
	
	public static ContainerType<BassContainer> BASS_CONTAINER = (ContainerType<BassContainer>) 
			ContainerRegistry.instance.register(IForgeContainerType.create(BassContainer::create).setRegistryName("bass_container"), new ContainerOptions());
	
	public static ContainerType<SpeakerContainer> SPEAKER_CONTAINER = (ContainerType<SpeakerContainer>) 
			ContainerRegistry.instance.register(IForgeContainerType.create(SpeakerContainer::create).setRegistryName("speaker_container"), new ContainerOptions());
	
	public Index() {
		
	}
}
