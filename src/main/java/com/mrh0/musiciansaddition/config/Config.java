package com.mrh0.musiciansaddition.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class Config {
	public static final String CATAGORY_GENERAL = "general";
	public static final String CATAGORY_INPUT = "input";
	
	private static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
	
	public static ForgeConfigSpec COMMON_CONFIG;
	
	public static ForgeConfigSpec.BooleanValue MOD_ENABLE;
	
	public static ForgeConfigSpec.BooleanValue MIDI_ENABLED;
	
	static {
		
		/*COMMON_BUILDER.comment("General Settings").push(CATAGORY_GENERAL);
		MOD_ENABLE = COMMON_BUILDER.comment("Enable or disable Musicians Instruments & Additions")
				.define("mod_enable", true);
		COMMON_BUILDER.pop();*/
		
		COMMON_BUILDER.comment("General Settings").push(CATAGORY_GENERAL);
		
		MIDI_ENABLED = COMMON_BUILDER.comment("Enable or disable midi input")
				.define("midi_enable", true);

		COMMON_BUILDER.pop();
		
		COMMON_CONFIG = COMMON_BUILDER.build();
	}
	
	public static void loadConfig(ForgeConfigSpec spec, java.nio.file.Path path) {
		final CommentedFileConfig configData = CommentedFileConfig.builder(path)
				.sync()
				.autosave()
				.writingMode(WritingMode.REPLACE)
				.build();
		configData.load();
		spec.setConfig(configData);
	}
}
