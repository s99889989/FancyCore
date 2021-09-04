package com.daxton.fancycore.api.character.placeholder;

import com.daxton.fancycore.FancyCore;
import com.daxton.fancycore.manager.PlayerManagerCore;
import com.daxton.fancycore.other.playerdata.PlayerDataFancy;
import org.bukkit.entity.LivingEntity;

import java.util.UUID;

public class PlaceholderPlayer {

	public static String valueOf(LivingEntity entity, String inputString){

		UUID uuid = entity.getUniqueId();
		PlayerDataFancy playerDataFancy = PlayerManagerCore.player_Data_Map.get(uuid);

		if(playerDataFancy != null){

			//主手裝備類型
			if(inputString.toLowerCase().contains("<fc_player_mainhand")){
				return playerDataFancy.mainHand;
			}
			//玩家的聊天的訊息
			if(inputString.toLowerCase().contains("<fc_player_chat")){
				return playerDataFancy.player_chat;
			}
			//玩家輸入的指令
			if(inputString.toLowerCase().contains("<fc_player_command")){
				return playerDataFancy.player_command;
			}
			//殺死的怪物類型
			if(inputString.toLowerCase().contains("<fc_player_mob_type")){
				return playerDataFancy.mobType;
			}
			//殺死的MythicMobs怪物ID
			if(inputString.toLowerCase().contains("<fc_player_mob_id")){
				return playerDataFancy.mobID;
			}
			//物品在身上的數量
			if(inputString.toLowerCase().contains("<fc_player_item_amount_")){
				String key = inputString.replace("<fc_player_item_amount_", "");
				return String.valueOf(playerDataFancy.getItemAmount(key));
			}
			//獲得得經驗
			if(inputString.toLowerCase().contains("<fc_player_mob_exp")){
				return String.valueOf(playerDataFancy.exp);
			}
			//獲得的金錢
			if(inputString.toLowerCase().contains("<fc_player_mob_money")){
				return String.valueOf(playerDataFancy.money);
			}
			//獲得的物品
			if(inputString.toLowerCase().contains("<fc_player_mob_item")){
				return playerDataFancy.item;
			}
			//增加經驗類型
			if(inputString.toLowerCase().contains("<fc_player_up_exp_type")){
				return playerDataFancy.addExpType;
			}
			//減少經驗類型
			if(inputString.toLowerCase().contains("<fc_player_down_exp_type")){
				return playerDataFancy.cutExpType;
			}
			//升等的類型
			if(inputString.toLowerCase().contains("<fc_player_up_level_type")){
				return playerDataFancy.upLevelType;
			}
			//降等的類型
			if(inputString.toLowerCase().contains("<fc_player_down_level_type")){
				return playerDataFancy.downLevelType;
			}
			//左鍵點擊的方塊類型
			if(inputString.toLowerCase().contains("<fc_player_left_click_block")){
				return playerDataFancy.left_click_block;
			}
			//右鍵點擊的方塊類型
			if(inputString.toLowerCase().contains("<fc_player_right_click_block")){
				return playerDataFancy.right_click_block;
			}
			//點擊的方塊位置
			if(inputString.toLowerCase().contains("<fc_player_block_location")){
				return playerDataFancy.block_location;
			}
			//玩家踩壓力板的位置
			if(inputString.toLowerCase().contains("<fc_player_plate_location")){
				return playerDataFancy.plate_location;
			}
			//玩家點擊按鈕的位置
			if(inputString.toLowerCase().contains("<fc_player_button_location")){
				return playerDataFancy.button_location;
			}
			//玩家拉拉桿的位置
			if(inputString.toLowerCase().contains("<fc_player_lever_location")){
				return playerDataFancy.lever_location;
			}

		}


		return "";
	}


}
