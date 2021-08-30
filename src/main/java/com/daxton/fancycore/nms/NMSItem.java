package com.daxton.fancycore.nms;

import org.bukkit.inventory.ItemStack;

public class NMSItem {

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

}
