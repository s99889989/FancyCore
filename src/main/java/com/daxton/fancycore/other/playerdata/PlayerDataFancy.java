package com.daxton.fancycore.other.playerdata;

import com.daxton.fancycore.FancyCore;
import com.daxton.fancycore.api.item.ItemKeySearch;
import com.daxton.fancycore.api.judgment.NumberJudgment;
import com.daxton.fancycore.hook.ProtocolSupport.SetClientVersion;
import com.daxton.fancycore.manager.OtherManager;
import com.daxton.fancycore.other.entity.BukkitAttributeSet;
import com.daxton.fancycore.other.taskaction.StringToMap;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class PlayerDataFancy extends PlayerDataMod{

	public Player player;
	//玩家攻擊數字
	public String attack_number = "";
	public LinkedHashMap<String, String> attack_number2;
	//玩家被攻擊數字
	public double attacked_number;

	//客戶端版本
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

	//攻擊速度限制
	public boolean attackSpeed = true;
	//物品CD
	public Map<String, BukkitRunnable> cd_Left_Run = new HashMap<>();
	public Map<String, BukkitRunnable> cd_Right_Run = new HashMap<>();

	//裝備動作
	public Map<String, Map<String, String>> eqm_Action_Map = new HashMap<>();
	//裝備自訂屬性
	public Map<String, Map<String, String>> eqm_Custom_Value_Map = new HashMap<>();
	//職業被動動作
	public Map<String, List<Map<String, String>>> class_Action_Map = new HashMap<>();

	public PlayerDataFancy(Player player){
		super(player);
		this.player = player;
		attack_number2 = new LinkedHashMap<String, String>() {
			@Override
			protected boolean removeEldestEntry(final Map.Entry eldest) {
				return size() > 50;
			}
		};
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

	//獲取該自訂值
	public String getCustomValue(String key){

		if(eqm_Custom_Value_Map.get(key) != null){
			Map<String, String> string_Map = eqm_Custom_Value_Map.get(key);
			double sum = 0;
			for(String value : string_Map.values()){
				if(NumberJudgment.isNumber(value)){
					sum += Double.parseDouble(value);
				}else {
					return value;
				}
			}
			return String.valueOf(sum);
		}
		if(OtherManager.custom_Value_Default.get(key) != null){

			return OtherManager.custom_Value_Default.get(key);
		}
		return "0";
	}

	//移除裝備部位屬性
	public void removeCustomValue(String eqm){
		eqm_Custom_Value_Map.forEach((s, stringStringMap) -> {
			stringStringMap.keySet().removeIf(key->key.startsWith(eqm));
		});
	}
	//增加攻擊數字
	public void addAttackNumber(String key, String number){
		attack_number2.put(key, number);
	}

	//增加裝備部位屬性
	public void addEqmCustomValue(String eqm, ItemStack itemStack){
		if(itemStack.getType() != Material.AIR){
			ItemKeySearch.getCustomAttributesMap(itemStack).forEach((key, value) -> {

				if(key.startsWith("custom")){
					String[] keyArray = key.split("/");
					if(keyArray.length == 2){
						addCustomValue(keyArray[1], value, eqm+keyArray[0]);
//						if(eqm_Custom_Value_Map.get(keyArray[1]) != null){
//							Map<String, String> string_Map = eqm_Custom_Value_Map.get(keyArray[1]);
//							string_Map.put(eqm+keyArray[0], value);
//							//FancyCore.fancyCore.getLogger().info(keyArray[1]+" : "+eqm+keyArray[0]+" : "+value);
//							eqm_Custom_Value_Map.put(keyArray[1], string_Map);
//						}else {
//							Map<String, String> string_Map = new HashMap<>();
//							string_Map.put(eqm+keyArray[0], value);
//							eqm_Custom_Value_Map.put(keyArray[1], string_Map);
//						}
					}
				}
			});
		}
	}
	//增加自訂值
	public void addCustomValue(String key, String value, String label){
		String nKey = key.toLowerCase();
		if(eqm_Custom_Value_Map.get(nKey) != null){
			Map<String, String> string_Map = eqm_Custom_Value_Map.get(nKey);
			string_Map.put(label, value);
			eqm_Custom_Value_Map.put(nKey, string_Map);
		}else {
			Map<String, String> string_Map = new HashMap<>();
			string_Map.put(label, value);
			eqm_Custom_Value_Map.put(nKey, string_Map);
		}
	}

	//移除裝備部位動作
	public void removeEqmAction(String eqm){
		eqm_Action_Map.keySet().removeIf(value -> value.startsWith(eqm));
//		for(String key : eqm_Action_Map.keySet()){
//			if(key.startsWith(eqm)){
//				eqm_Action_Map.remove(key);
//			}
//		}
	}
	//增加裝備部位動作
	public void addEqmAction(String eqm, ItemStack itemStack){
		if(itemStack != null && itemStack.getType() != Material.AIR){
			ItemKeySearch.getCustomAttributesMap(itemStack).forEach((key, value) -> {
				if(key.startsWith("action")){
					Map<String, String> string_Map = StringToMap.toActionMap(value);
					eqm_Action_Map.put(eqm+key, string_Map);
				}
			});
		}
	}

}
