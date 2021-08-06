package com.daxton.fancycore.api.item;

import com.daxton.fancycore.FancyCore;
import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

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
				try {
					PlayerProfile playerProfile = Bukkit.createProfile(UUID.randomUUID(), null);
					playerProfile.getProperties().add(new ProfileProperty("textures", headValue));
					skullMeta.setPlayerProfile(playerProfile);
					itemStack.setItemMeta(skullMeta);
				} catch (Exception exception) {
					fancyCore.getLogger().info("頭的值只能在paper伺服器使用。");
					fancyCore.getLogger().info("The value of the header can only be used on the paper server.");
				}
			}

		}

		itemStack.setItemMeta(skullMeta);
	}

}
