package com.mrh0.musiciansaddition.items.base;

import java.util.List;
import com.mrh0.musiciansaddition.event.ItemRegistry;
import com.mrh0.musiciansaddition.event.opts.ItemOptions;
import com.mrh0.musiciansaddition.itemgroup.ModGroup;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class BaseItem extends Item{

	private String itemName;
	private ItemOptions opts;
	public BaseItem(String name, Properties properties, ItemOptions opts) {
		super(properties.group(ModGroup.MAIN));
		this.itemName = name;
		this.opts = opts;
		setRegistryName(name);
		ItemRegistry.instance.register(this, opts);
	}
	
	@Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if(opts.addTooltip)
			tooltip.add(new TranslationTextComponent("tooltip.electricaddition." + getItemName()));
    	super.addInformation(stack, worldIn, tooltip, flagIn);
    }
	
	public String getItemName() {
		return itemName;
	}
}
