package com.mrh0.musiciansaddition;

import com.mrh0.musiciansaddition.blocks.Piano;
import com.mrh0.musiciansaddition.blocks.Xylophone;
import com.mrh0.musiciansaddition.container.PianoContainer;
import com.mrh0.musiciansaddition.container.XylophoneContainer;
import com.mrh0.musiciansaddition.event.ContainerRegistry;
import com.mrh0.musiciansaddition.event.TileEntityRegistry;
import com.mrh0.musiciansaddition.event.opts.ContainerOptions;
import com.mrh0.musiciansaddition.event.opts.TileEntityOptions;
import com.mrh0.musiciansaddition.tileentity.PianoTileEntity;
import com.mrh0.musiciansaddition.tileentity.XylophoneTileEntity;

import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.extensions.IForgeContainerType;

public class Index {
	
	public static final Block PIANO = new Piano();
	public static final Block XYLOPHONE = new Xylophone();
	
	public static TileEntityType<PianoTileEntity> PIANO_TILE_ENTITY_TYPE = 
			TileEntityRegistry.instance.<PianoTileEntity>register(PianoTileEntity::new, new TileEntityOptions("piano", PIANO));
	
	public static TileEntityType<XylophoneTileEntity> XYLOPHONE_TILE_ENTITY_TYPE = 
			TileEntityRegistry.instance.<XylophoneTileEntity>register(XylophoneTileEntity::new, new TileEntityOptions("xylophone", XYLOPHONE));
	
	public static ContainerType<PianoContainer> PIANO_CONTAINER = (ContainerType<PianoContainer>) ContainerRegistry.instance.register(IForgeContainerType.create(PianoContainer::create).setRegistryName("piano_container"), new ContainerOptions());
	
	public static ContainerType<XylophoneContainer> XYLOPHONE_CONTAINER = (ContainerType<XylophoneContainer>) ContainerRegistry.instance.register(IForgeContainerType.create(XylophoneContainer::create).setRegistryName("xylophone_container"), new ContainerOptions());
	
	public Index() {
		
	}
}
