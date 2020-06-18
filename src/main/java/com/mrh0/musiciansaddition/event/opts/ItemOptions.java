package com.mrh0.musiciansaddition.event.opts;

public class ItemOptions extends RegOptions<ItemOptions> {
	public boolean addTooltip = false;
	public ItemOptions showTooltip() {
		addTooltip = true;
		return this;
	}
}
