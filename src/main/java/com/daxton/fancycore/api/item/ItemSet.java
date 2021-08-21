package com.daxton.fancycore.api.item;

import com.daxton.fancycore.FancyCore;
import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ItemSet {
	//設定物品顯示名稱
	public static void setDisplayName(ItemStack itemStack, String displayName){
		ItemMeta itemMeta = itemStack.getItemMeta();
		assert itemMeta != null;
		itemMeta.setDisplayName(displayName);
		itemStack.setItemMeta(itemMeta);
	}
	//設定物品CustomModelData
	public static void setCustomModelData(ItemStack itemStack, int customModelData){
		ItemMeta itemMeta = itemStack.getItemMeta();
		assert itemMeta != null;
		itemMeta.setCustomModelData(customModelData);
		itemStack.setItemMeta(itemMeta);
	}
	//設定物品Lore
	public static void setLore(ItemStack itemStack, List<String> loreList){
		ItemMeta itemMeta = itemStack.getItemMeta();
		List<String> newLoreList = new ArrayList<>();

		if(itemMeta != null && itemMeta.getLore() != null){
			newLoreList = itemMeta.getLore();
		}
		if(loreList != null){
			newLoreList.addAll(loreList);
		}
		if(itemMeta != null){
			itemMeta.setLore(newLoreList);
			itemStack.setItemMeta(itemMeta);
		}
	}
	//設定物品的頭值
	public static void setHeadValue(ItemStack itemStack, String headValue){
		FancyCore fancyCore = FancyCore.fancyCore;
		Material material = itemStack.getType();
		SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
		if(material == Material.PLAYER_HEAD){
			if(headValue.length() < 50){
				OfflinePlayer targetPlayer = fancyCore.getServer().getOfflinePlayer(headValue);
				assert skullMeta != null;
				skullMeta.setOwningPlayer(targetPlayer);
				itemStack.setItemMeta(skullMeta);
			}else {
				GameProfile profile = new GameProfile(UUID.randomUUID(), "");
				profile.getProperties().put("textures", new Property("textures", headValue));
				Field profileField;
				try {
					profileField = skullMeta.getClass().getDeclaredField("profile");
					profileField.setAccessible(true);
					profileField.set(skullMeta, profile);
				} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
					e.printStackTrace();
				}
				itemStack.setItemMeta(skullMeta);
			}

		}

	}

}
