package com.daxton.fancycore.nms;

import org.bukkit.inventory.ItemStack;

public class NMSItem {

	//從物品獲得NBT並轉成String
	public static String getNBTTagString(ItemStack itemStack, String key){
		if(itemStack == null || key == null){
			return "";
		}

		String nmsVersion = NMSVersion.getNMSVersion();
		switch (nmsVersion){
			case "v1_13_R1":
				return com.daxton.fancycore.nms.v1_13_R1.NMSItem.getNBTTagString(itemStack, key);
			case "v1_13_R2":
				return com.daxton.fancycore.nms.v1_13_R2.NMSItem.getNBTTagString(itemStack, key);
			case "v1_14_R1":
				return com.daxton.fancycore.nms.v1_14_R1.NMSItem.getNBTTagString(itemStack, key);
			case "v1_15_R1":
				return com.daxton.fancycore.nms.v1_15_R1.NMSItem.getNBTTagString(itemStack, key);
			case "v1_16_R1":
				return com.daxton.fancycore.nms.v1_16_R1.NMSItem.getNBTTagString(itemStack, key);
			case "v1_16_R2":
				return com.daxton.fancycore.nms.v1_16_R2.NMSItem.getNBTTagString(itemStack, key);
			case "v1_16_R3":
				return com.daxton.fancycore.nms.v1_16_R3.NMSItem.getNBTTagString(itemStack, key);
			case "v1_17_R1":
				return com.daxton.fancycore.nms.v1_17_R1.NMSItem.getNBTTagString(itemStack, key);
		}

		return "";

	}

	//把物品轉成Base64字串
	public static String itemStackToBase64(ItemStack[] itemStacks) {
		if(itemStacks == null){
			return "";
		}

		String nmsVersion = NMSVersion.getNMSVersion();
		switch (nmsVersion){
			case "v1_13_R1":
				return com.daxton.fancycore.nms.v1_13_R1.NMSItem.itemStackToBase64(itemStacks);
			case "v1_13_R2":
				return com.daxton.fancycore.nms.v1_13_R2.NMSItem.itemStackToBase64(itemStacks);
			case "v1_14_R1":
				return com.daxton.fancycore.nms.v1_14_R1.NMSItem.itemStackToBase64(itemStacks);
			case "v1_15_R1":
				return com.daxton.fancycore.nms.v1_15_R1.NMSItem.itemStackToBase64(itemStacks);
			case "v1_16_R1":
				return com.daxton.fancycore.nms.v1_16_R1.NMSItem.itemStackToBase64(itemStacks);
			case "v1_16_R2":
				return com.daxton.fancycore.nms.v1_16_R2.NMSItem.itemStackToBase64(itemStacks);
			case "v1_16_R3":
				return com.daxton.fancycore.nms.v1_16_R3.NMSItem.itemStackToBase64(itemStacks);
			case "v1_17_R1":
				return com.daxton.fancycore.nms.v1_17_R1.NMSItem.itemStackToBase64(itemStacks);
		}

		return "";
	}

	//把Base64字串轉成物品
	public static ItemStack[] base64toItemStack(String itemString){

		if(itemString == null){
			return new ItemStack[]{};
		}

		String nmsVersion = NMSVersion.getNMSVersion();
		switch (nmsVersion){
			case "v1_13_R1":
				return com.daxton.fancycore.nms.v1_13_R1.NMSItem.base64toItemStack(itemString);
			case "v1_13_R2":
				return com.daxton.fancycore.nms.v1_13_R2.NMSItem.base64toItemStack(itemString);
			case "v1_14_R1":
				return com.daxton.fancycore.nms.v1_14_R1.NMSItem.base64toItemStack(itemString);
			case "v1_15_R1":
				return com.daxton.fancycore.nms.v1_15_R1.NMSItem.base64toItemStack(itemString);
			case "v1_16_R1":
				return com.daxton.fancycore.nms.v1_16_R1.NMSItem.base64toItemStack(itemString);
			case "v1_16_R2":
				return com.daxton.fancycore.nms.v1_16_R2.NMSItem.base64toItemStack(itemString);
			case "v1_16_R3":
				return com.daxton.fancycore.nms.v1_16_R3.NMSItem.base64toItemStack(itemString);
			case "v1_17_R1":
				return com.daxton.fancycore.nms.v1_17_R1.NMSItem.base64toItemStack(itemString);
		}

		return new ItemStack[]{};

	}

}
