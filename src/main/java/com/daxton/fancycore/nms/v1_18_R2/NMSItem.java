package com.daxton.fancycore.nms.v1_18_R2;


import net.minecraft.nbt.NBTBase;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.io.DataInput;
import java.lang.reflect.Method;

public class NMSItem {

	private static Method WRITE_NBT;
	private static Method READ_NBT;

	//把Json字串轉成NBT再轉成物品
	public static ItemStack jsonStringToItemStack(@NotNull String itemString){

		return new ItemStack(Material.STONE);
	}

	//把物品NBT轉成String
	public static String itemNBTtoString(@NotNull ItemStack itemStack){
		return "";
	}

	//從物品獲得NBT並轉成String
	public static String getNBTTagString(ItemStack itemStack, String key){
		return "";
	}

	//把物品轉成Base64字串
	public static String itemStackToBase64(ItemStack[] itemStacks) {
		return "";
	}
	//把Base64字串轉成物品
	public static ItemStack[] base64toItemStack(String itemString) {
		return new ItemStack[]{};
	}

	private static NBTBase readNbt(DataInput dataInput) {
		return null;
	}

	private static CraftItemStack getCraftVersion(ItemStack itemStack) {
		return null;
	}

}
