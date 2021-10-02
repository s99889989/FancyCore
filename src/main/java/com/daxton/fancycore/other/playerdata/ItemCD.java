package com.daxton.fancycore.other.playerdata;

import com.daxton.fancycore.FancyCore;
import com.daxton.fancycore.api.character.conversion.StringConversion;
import com.daxton.fancycore.api.item.ItemKeySearch;
import com.daxton.fancycore.manager.PlayerManagerCore;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class ItemCD {

	//左鍵CD
	public static boolean getLeft(Player player){
		boolean cdB = false;
		ItemStack itemStack = player.getInventory().getItemInMainHand();
		if(itemStack.getType() != Material.AIR){

			String coolDownString = ItemKeySearch.getCustomAttributes(itemStack, "CoolDownLeftClick");
			String itemID = ItemKeySearch.getCustomAttributes(itemStack, "itemID");

			if(!coolDownString.isEmpty() && !itemID.isEmpty()){



				PlayerDataFancy playerDataFancy = PlayerManagerCore.player_Data_Map.get(player.getUniqueId());

				if(playerDataFancy.cd_Left_Run.get(itemID) == null){
					int coolDown = StringConversion.getInt(player, null, 0, coolDownString);
					if(coolDown > 0){
						player.setCooldown(itemStack.getType(), coolDown);

						playerDataFancy.cd_Left_Run.put(itemID, new BukkitRunnable() {
							@Override
							public void run() {
								cancel();
								playerDataFancy.cd_Left_Run.remove(itemID);
							}
						});

						playerDataFancy.cd_Left_Run.get(itemID).runTaskLater(FancyCore.fancyCore, coolDown);
					}
				}else {
					cdB = true;
				}


			}

		}
		return cdB;
	}

	//右鍵CD
	public static boolean getRight(Player player){
		boolean cdB = false;

		ItemStack itemStack = player.getInventory().getItemInMainHand();
		if(itemStack.getType() != Material.AIR){

			String coolDownString = ItemKeySearch.getCustomAttributes(itemStack, "CoolDownRightClick");
			String itemID = ItemKeySearch.getCustomAttributes(itemStack, "itemID");

			if(!coolDownString.isEmpty() && !itemID.isEmpty()){

				PlayerDataFancy playerDataFancy = PlayerManagerCore.player_Data_Map.get(player.getUniqueId());

				if(playerDataFancy.cd_Right_Run.get(itemID) == null){
					int coolDown = StringConversion.getInt(player, null, 0, coolDownString);
					if(coolDown > 0){
						player.setCooldown(itemStack.getType(), coolDown);
						playerDataFancy.cd_Right_Run.put(itemID, new BukkitRunnable() {
							@Override
							public void run() {
								cancel();
								playerDataFancy.cd_Right_Run.remove(itemID);
							}
						});

						playerDataFancy.cd_Right_Run.get(itemID).runTaskLater(FancyCore.fancyCore, coolDown);
					}
				}else {
					cdB = true;
				}

			}

		}

		return cdB;
	}

	//判斷物品是否有取消攻擊
	public static boolean attackCan(Player player){
		boolean outB = false;
		ItemStack itemStack = player.getInventory().getItemInMainHand();
		if(itemStack.getType() != Material.AIR){
			String disableAttack = ItemKeySearch.getCustomAttributes(itemStack, "DisableAttack");
			if(!disableAttack.isEmpty()){
				outB = Boolean.parseBoolean(disableAttack);
			}
		}
		return outB;
	}

}
