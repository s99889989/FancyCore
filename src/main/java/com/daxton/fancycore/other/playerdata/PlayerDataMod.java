package com.daxton.fancycore.other.playerdata;

import com.daxton.fancycore.FancyCore;
import com.daxton.fancycore.api.fancyclient.ClientConnect;

import com.daxton.fancycore.config.FileConfig;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

import static com.daxton.fancycore.config.FileConfig.languageConfig;

public class PlayerDataMod {

	public Player player;

	public UUID uuid;

	//玩家是否裝模組
	public boolean player_have_mod;
	//玩家模組版本是否正確
	public boolean player_version_mod;

	public PlayerDataMod(Player player) {
		this.player = player;
		this.uuid = player.getUniqueId();
		sendFirst();
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
