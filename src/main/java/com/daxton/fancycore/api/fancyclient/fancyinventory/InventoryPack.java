package com.daxton.fancycore.api.fancyclient.fancyinventory;

import com.daxton.fancycore.FancyCore;
import com.daxton.fancycore.manager.PlayerManagerCore;
import com.daxton.fancycore.nms.NMSItem;
import com.daxton.fancycore.other.playerdata.PlayerDataFancy;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class InventoryPack {

	public static void slotAction(Player player, String receivedString){
		if(receivedString.startsWith("Delete")){
			player.getOpenInventory().getTopInventory().setItem(1, null);
		}
		if(receivedString.startsWith("Place")){
			String[] placeItem = receivedString.split("\\.");
			int place = Integer.parseInt(placeItem[1]);
			ItemStack itemStack = NMSItem.jsonStringToItemStack(placeItem[2], player);

			if(place >=9 && place < 45){
				int np = bag(place);
				player.getInventory().setItem(np, itemStack);
				return;
			}
			if(place <= 4){
				player.getOpenInventory().getTopInventory().setItem(place, itemStack);
				return;
			}
			if(place == 5){
				player.getInventory().setHelmet(itemStack);
			}
			if(place == 6){
				player.getInventory().setChestplate(itemStack);
			}
			if(place == 7){
				player.getInventory().setLeggings(itemStack);
			}
			if(place == 8){
				player.getInventory().setBoots(itemStack);
			}
			if(place == 45){
				player.getInventory().setItemInOffHand(itemStack);
			}
		}
		//放置到自訂格子
		if(receivedString.startsWith("CustomPlace")){
			String[] placeItem = receivedString.split("\\|");
			String objectID = placeItem[1];
			ItemStack itemStack = NMSItem.jsonStringToItemStack(placeItem[2], player);
			UUID uuid = player.getUniqueId();
			PlayerDataFancy playerDataFancy = PlayerManagerCore.player_Data_Map.get(uuid);
			playerDataFancy.itemStackMap.put(objectID, itemStack);

		}
	}



	public static int bag(int index){
		if(index >= 36){
			index -= 36;
		}
		return index;
	}

}
