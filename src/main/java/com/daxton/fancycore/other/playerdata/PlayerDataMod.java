package com.daxton.fancycore.other.playerdata;

import com.daxton.fancycore.FancyCore;
import com.daxton.fancycore.api.fancyclient.ClientConnect;

import com.daxton.fancycore.config.FileConfig;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.daxton.fancycore.config.FileConfig.languageConfig;

//模組有關
public class PlayerDataMod {

	public Player player;

	public UUID uuid;

	//玩家是否裝模組
	public boolean player_have_mod;
	//玩家模組版本是否正確
	public boolean player_version_mod;
	//玩家的自訂物品欄
	public Map<String, ItemStack> itemStackMap = new HashMap<>();
	//模組GUI顯示的值
	public Map<String, String> guiValueMap = new HashMap<>();
	//拿起的物品
	public ItemStack mouseItemStack = new ItemStack(Material.AIR);

	public PlayerDataMod(Player player) {
		this.player = player;
		this.uuid = player.getUniqueId();
		//設置登入初始動作
		sendFirst();
		//讀取自訂格子
		loadSlotItem();
	}
	//從Core變量獲取值
	public String getGuiValue(String inputKey){
		String key = inputKey.replace(" ","").replace("<fc_gui_value_","");
		if(guiValueMap.containsKey(key)){
			return guiValueMap.get(key);
		}
		return "";
	}
	//從檔案獲取值
	public String getGuiValueFile(String inputKey){
		if(guiValueMap.containsKey(inputKey)){
			return guiValueMap.get(inputKey);
		}
		return "";
	}
	public void setGuiValue(String inputString){
		String[] key = inputString.split("\\|");
		if(key.length == 2){
			setGuiValue(key[0], key[1]);
		}
	}
	//設置介面變量
	public void setGuiValue(String inputKey, String value){
		String[] key = inputKey.split("\\.");
		if(key.length > 2){
			String file = key[key.length-2];
			String name = key[key.length-1];
			guiValueMap.put(file+"_"+name, value);
		}

	}

	//讀取自訂格子
	public void loadSlotItem(){
		File itemStackFile = new File(FancyCore.fancyCore.getDataFolder(), "PlayerData/"+uuid.toString()+".yml");
		if(!itemStackFile.exists()){
			try {
				if(itemStackFile.createNewFile()){
					FileConfiguration itemStackConfig = YamlConfiguration.loadConfiguration(itemStackFile);
					FileConfig.config_Map.put("PlayerData/"+uuid.toString()+".yml", itemStackConfig);
				}
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}

		FileConfiguration itemStackConfig = FileConfig.config_Map.get("PlayerData/"+uuid.toString()+".yml");
		if(itemStackConfig.contains("SlotItem")){
			itemStackConfig.getConfigurationSection("SlotItem").getKeys(false).forEach(slotKey -> {
				ItemStack itemStack = itemStackConfig.getItemStack("SlotItem."+slotKey);
				itemStackMap.put(slotKey, itemStack);
			});
		}

	}
	//儲存物品欄
	public void saveSlotItem(){
		FileConfiguration itemStackConfig = FileConfig.config_Map.get("PlayerData/"+uuid.toString()+".yml");
		itemStackMap.forEach((slotKey, itemStack) -> {
			itemStackConfig.set("SlotItem."+slotKey, itemStack);
		});
		File itemStackFile = new File(FancyCore.fancyCore.getDataFolder(), "PlayerData/"+uuid.toString()+".yml");

		try {
			itemStackConfig.save(itemStackFile);
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	//設置登入初始動作
	public void sendFirst(){
		FileConfiguration config = FileConfig.config_Map.get("config.yml");

		int preload_delay = config.getInt("ClientMod.determine_delay");
		if(preload_delay < 0){
			preload_delay = 1;
		}

		boolean kick_no_mod = config.getBoolean("ClientMod.kick_no_mod");
		boolean kick_mod_version_wrong = config.getBoolean("ClientMod.kick_mod_version_wrong");

		String neeVersion = config.getString("ClientMod.need_mod_version");
		if(neeVersion == null){
			neeVersion = "1.2";
		}
		ClientConnect.sendMessage(player, "version", neeVersion);

		new BukkitRunnable() {
			@Override
			public void run() {
				if(kick_no_mod){
					//FancyCore.sendLogger("是否: "+player_have_mod);
					if(!player_have_mod){
						player.kickPlayer(""+languageConfig.getString("ModMessage.KickNoMod"));
					}

				}

				if(kick_mod_version_wrong){
					if(!player_version_mod){
						String kickMessage = ""+languageConfig.getString("ModMessage.KickModVersionWrong");
						player.kickPlayer(kickMessage.replace("{version}", "1.2.x"));
					}
				}

			}
		}.runTaskLater(FancyCore.fancyCore, 20L * (preload_delay+1));
	}


}
