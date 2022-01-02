package com.daxton.fancycore.listener;

import com.daxton.fancycore.FancyCore;
import com.daxton.fancycore.api.event.PlayerKeyInputEvent;
import com.daxton.fancycore.api.event.PlayerPackReceivedEvent;

import com.daxton.fancycore.api.fancyclient.fancyinventory.InventoryPack;
import com.daxton.fancycore.config.FileConfig;
import com.daxton.fancycore.manager.PlayerManagerCore;
import com.daxton.fancycore.nms.NMSItem;
import com.daxton.fancycore.other.playerdata.PlayerDataFancy;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class ModListener implements Listener {

	@EventHandler
	public void onPlayerPackReceived(PlayerPackReceivedEvent event){
		Player player = event.getPlayer();
		String type = event.getType();
		String receivedString = event.getReceived();

		//判定有無裝模組 和 模組版本正確與否
		if(type.equalsIgnoreCase("version")){
			version(player, receivedString);
			return;
		}
		//按鍵監聽
		if(type.equalsIgnoreCase("keyboard")){
			PlayerKeyInputEvent playerKeyInputEvent = new PlayerKeyInputEvent(player, receivedString);
			Bukkit.getPluginManager().callEvent(playerKeyInputEvent);

		}
		//GUI變量設置
		if(type.equalsIgnoreCase("input")){
			UUID uuid = player.getUniqueId();
			PlayerDataFancy playerDataFancy = PlayerManagerCore.player_Data_Map.get(uuid);
			playerDataFancy.setGuiValue(receivedString);
			return;
		}
		//物品欄動作
		if(type.equalsIgnoreCase("SlotActionType")){
			InventoryPack.slotAction(player, receivedString);
		}

	}



	//判定有無裝模組 和 模組版本正確與否
	public void version(Player player, String receivedString){
		//FancyCore.sendLogger(player.getName()+" : "+receivedString);
		UUID uuid = player.getUniqueId();
		FileConfiguration config = FileConfig.config_Map.get("config.yml");
		String neeVersion = config.getString("ClientMod.need_mod_version");
		if(neeVersion == null){
			neeVersion = "1.2";
		}
		PlayerManagerCore.player_Data_Map.get(uuid).player_have_mod = true;
		if(receivedString.startsWith(neeVersion)){
			PlayerManagerCore.player_Data_Map.get(uuid).player_version_mod = true;
		}
	}

}
