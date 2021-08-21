package com.daxton.fancycore.other.playerdata;

import com.daxton.fancycore.hook.ProtocolSupport.SetClientVersion;
import com.daxton.fancycore.other.entity.BukkitAttributeSet;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class PlayerDataFancy {

	public Player player;
	//玩家攻擊數字
	public String attack_number;
	//玩家被攻擊數字
	public double attacked_number;

	//客戶端版本Map
	public String client_version = "";
	//是否移除其他ActionBar
	public boolean actionBar_remove;
	//移除粒子效果列表
	public Set<String> particles_remove = new HashSet<>();

	//主手裝備類型
	public String mainHand = "";

	//殺死的怪物類型
	public String mobType = "";
	//殺死的MythicMobs怪物ID
	public String mobID = "";
	//獲得得經驗
	public double exp;
	//獲得的金錢
	public double money;
	//獲得的物品
	public String item = "";
	//增加經驗類型
	public String addExpType = "";
	//減少經驗類型
	public String cutExpType = "";
	//升等的類型
	public String upLevelType = "";
	//降等的類型
	public String downLevelType = "";

	//玩家左鍵點擊的方塊類型
	public String left_click_block = "";
	//玩家右鍵點擊的方塊類型
	public String right_click_block = "";
	//點擊的方塊位置
	public String block_location = "";
	//玩家踩壓力板的位置
	public String plate_location = "";
	//玩家點擊按鈕的位置
	public String button_location = "";
	//玩家點擊拉桿的位置
	public String lever_location = "";

	//玩家的聊天的訊息
	public String player_chat = "";

	//玩家輸入的指令
	public String player_command = "";
	//玩家的F狀態
	public boolean player_F;

	public Inventory inventory;

	public PlayerDataFancy(Player player){
		this.player = player;
	}
	//檢測玩家版本，並記錄
	public void setPlayer_version(){
		client_version = SetClientVersion.toMap(player);
	}
	//清除屬性
	public void removeAllAttribute(){
		BukkitAttributeSet.removeAllAttribute(player);
	}
	//獲取身上物品數量
	public int getItemAmount(String itemID){
		List<ItemStack> bagList = Arrays.asList(player.getInventory().getExtraContents());
		List<ItemStack> eqmList = Arrays.asList(player.getInventory().getArmorContents());
		bagList.addAll(eqmList);
		int amount = 0;
		for(ItemStack itemStack : bagList){
			ItemMeta itemMeta = itemStack.getItemMeta();
			if(itemMeta != null){
				amount = 1;
			}
		}
		return amount;
	}


}
