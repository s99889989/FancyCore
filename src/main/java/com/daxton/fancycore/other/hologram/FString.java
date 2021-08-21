package com.daxton.fancycore.other.hologram;

import org.bukkit.inventory.ItemStack;

public class FString {

	public boolean isItem;

	public ItemStack itemStack;

	public String message;

	public double height;

	public FString (ItemStack itemStack){
		isItem = true;
		this.itemStack = itemStack;
	}

	public FString (String message){
		isItem = false;
		this.message = message;
	}

}
