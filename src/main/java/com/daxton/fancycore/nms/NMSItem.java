package com.daxton.fancycore.nms;

import com.daxton.fancycore.FancyCore;
import com.daxton.fancycore.manager.PlayerManagerCore;
import com.daxton.fancycore.other.playerdata.PlayerDataFancy;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class NMSItem {

	//把Json字串轉成NBT再轉成物品
	public static ItemStack jsonStringToItemStack(@NotNull String itemJson, Player player){
		UUID uuid = player.getUniqueId();
		PlayerDataFancy playerDataFancy = PlayerManagerCore.player_Data_Map.get(uuid);
		String nmsVersion = NMSVersion.versionToNMSVersion(playerDataFancy.client_version);
		switch (nmsVersion){
			case "v1_13_R1":
				return com.daxton.fancycore.nms.v1_13_R1.NMSItem.jsonStringToItemStack(itemJson);
			case "v1_13_R2":
				return com.daxton.fancycore.nms.v1_13_R2.NMSItem.jsonStringToItemStack(itemJson);
			case "v1_14_R1":
				return com.daxton.fancycore.nms.v1_14_R1.NMSItem.jsonStringToItemStack(itemJson);
			case "v1_15_R1":
				return com.daxton.fancycore.nms.v1_15_R1.NMSItem.jsonStringToItemStack(itemJson);
			case "v1_16_R1":
				return com.daxton.fancycore.nms.v1_16_R1.NMSItem.jsonStringToItemStack(itemJson);
			case "v1_16_R2":
				return com.daxton.fancycore.nms.v1_16_R2.NMSItem.jsonStringToItemStack(itemJson);
			case "v1_16_R3":
				return com.daxton.fancycore.nms.v1_16_R3.NMSItem.jsonStringToItemStack(itemJson);
			case "v1_17_R1":
				return com.daxton.fancycore.nms.v1_17_R1.NMSItem.jsonStringToItemStack(itemJson);
			case "v1_18_R1":
				return com.daxton.fancycore.nms.v1_18_R1.NMSItem.jsonStringToItemStack(itemJson);
			case "v1_18_R2":
				return com.daxton.fancycore.nms.v1_18_R2.NMSItem.jsonStringToItemStack(itemJson);
		}
		return new ItemStack(Material.STONE);
	}

	//把物品NBT轉成String(依據給定NMS版本)
	public static String itemNBTtoStringClient(@NotNull ItemStack itemStack, String nmsVersion){
		switch (nmsVersion){
			case "v1_13_R1":
				return com.daxton.fancycore.nms.v1_13_R1.NMSItem.itemNBTtoString(itemStack);
			case "v1_13_R2":
				return com.daxton.fancycore.nms.v1_13_R2.NMSItem.itemNBTtoString(itemStack);
			case "v1_14_R1":
				return com.daxton.fancycore.nms.v1_14_R1.NMSItem.itemNBTtoString(itemStack);
			case "v1_15_R1":
				return com.daxton.fancycore.nms.v1_15_R1.NMSItem.itemNBTtoString(itemStack);
			case "v1_16_R1":
				return com.daxton.fancycore.nms.v1_16_R1.NMSItem.itemNBTtoString(itemStack);
			case "v1_16_R2":
				return com.daxton.fancycore.nms.v1_16_R2.NMSItem.itemNBTtoString(itemStack);
			case "v1_16_R3":
				return com.daxton.fancycore.nms.v1_16_R3.NMSItem.itemNBTtoString(itemStack);
			case "v1_17_R1":
				return com.daxton.fancycore.nms.v1_17_R1.NMSItem.itemNBTtoString(itemStack);
			case "v1_18_R1":
				return com.daxton.fancycore.nms.v1_18_R1.NMSItem.itemNBTtoString(itemStack);
			case "v1_18_R2":
				return com.daxton.fancycore.nms.v1_18_R2.NMSItem.itemNBTtoString(itemStack);
		}
		return "";
	}

	//把物品NBT轉成String(依據客戶端版本)
	public static String itemNBTtoStringClient(@NotNull ItemStack itemStack, Player player){
		UUID uuid = player.getUniqueId();
		PlayerDataFancy playerDataFancy = PlayerManagerCore.player_Data_Map.get(uuid);
		String nmsVersion = NMSVersion.versionToNMSVersion(playerDataFancy.client_version);
		switch (nmsVersion){
			case "v1_13_R1":
				return com.daxton.fancycore.nms.v1_13_R1.NMSItem.itemNBTtoString(itemStack);
			case "v1_13_R2":
				return com.daxton.fancycore.nms.v1_13_R2.NMSItem.itemNBTtoString(itemStack);
			case "v1_14_R1":
				return com.daxton.fancycore.nms.v1_14_R1.NMSItem.itemNBTtoString(itemStack);
			case "v1_15_R1":
				return com.daxton.fancycore.nms.v1_15_R1.NMSItem.itemNBTtoString(itemStack);
			case "v1_16_R1":
				return com.daxton.fancycore.nms.v1_16_R1.NMSItem.itemNBTtoString(itemStack);
			case "v1_16_R2":
				return com.daxton.fancycore.nms.v1_16_R2.NMSItem.itemNBTtoString(itemStack);
			case "v1_16_R3":
				return com.daxton.fancycore.nms.v1_16_R3.NMSItem.itemNBTtoString(itemStack);
			case "v1_17_R1":
				return com.daxton.fancycore.nms.v1_17_R1.NMSItem.itemNBTtoString(itemStack);
			case "v1_18_R1":
				return com.daxton.fancycore.nms.v1_18_R1.NMSItem.itemNBTtoString(itemStack);
			case "v1_18_R2":
				return com.daxton.fancycore.nms.v1_18_R2.NMSItem.itemNBTtoString(itemStack);
		}
		return "";
	}

	//把物品NBT轉成String(依據伺服器版本)
	public static String itemNBTtoString(@NotNull ItemStack itemStack){
		String nmsVersion = NMSVersion.getNMSVersion();
		switch (nmsVersion){
			case "v1_13_R1":
				return com.daxton.fancycore.nms.v1_13_R1.NMSItem.itemNBTtoString(itemStack);
			case "v1_13_R2":
				return com.daxton.fancycore.nms.v1_13_R2.NMSItem.itemNBTtoString(itemStack);
			case "v1_14_R1":
				return com.daxton.fancycore.nms.v1_14_R1.NMSItem.itemNBTtoString(itemStack);
			case "v1_15_R1":
				return com.daxton.fancycore.nms.v1_15_R1.NMSItem.itemNBTtoString(itemStack);
			case "v1_16_R1":
				return com.daxton.fancycore.nms.v1_16_R1.NMSItem.itemNBTtoString(itemStack);
			case "v1_16_R2":
				return com.daxton.fancycore.nms.v1_16_R2.NMSItem.itemNBTtoString(itemStack);
			case "v1_16_R3":
				return com.daxton.fancycore.nms.v1_16_R3.NMSItem.itemNBTtoString(itemStack);
			case "v1_17_R1":
				return com.daxton.fancycore.nms.v1_17_R1.NMSItem.itemNBTtoString(itemStack);
			case "v1_18_R1":
				return com.daxton.fancycore.nms.v1_18_R1.NMSItem.itemNBTtoString(itemStack);
			case "v1_18_R2":
				return com.daxton.fancycore.nms.v1_18_R2.NMSItem.itemNBTtoString(itemStack);
		}
		return "";
	}

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
			case "v1_18_R1":
				return com.daxton.fancycore.nms.v1_18_R1.NMSItem.getNBTTagString(itemStack, key);
			case "v1_18_R2":
				return com.daxton.fancycore.nms.v1_18_R2.NMSItem.getNBTTagString(itemStack, key);
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
			case "v1_18_R1":
				return com.daxton.fancycore.nms.v1_18_R1.NMSItem.itemStackToBase64(itemStacks);
			case "v1_18_R2":
				return com.daxton.fancycore.nms.v1_18_R2.NMSItem.itemStackToBase64(itemStacks);
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
			case "v1_18_R1":
				return com.daxton.fancycore.nms.v1_18_R1.NMSItem.base64toItemStack(itemString);
			case "v1_18_R2":
				return com.daxton.fancycore.nms.v1_18_R2.NMSItem.base64toItemStack(itemString);
		}

		return new ItemStack[]{};

	}

}
