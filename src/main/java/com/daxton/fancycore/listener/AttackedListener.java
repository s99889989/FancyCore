package com.daxton.fancycore.listener;

import com.daxton.fancycore.FancyCore;
import com.daxton.fancycore.manager.PlayerManagerCore;
import com.daxton.fancycore.other.playerdata.PlayerDataFancy;
import net.citizensnpcs.api.CitizensAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.UUID;

public class AttackedListener implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)//被攻擊
	public void onPhysicalDamage(EntityDamageByEntityEvent event){
		Entity attacker = event.getDamager();
		Entity attacked = event.getEntity();
		if(Bukkit.getServer().getPluginManager().getPlugin("Citizens") !=null){
			if(CitizensAPI.getNPCRegistry().isNPC(attacked)){
				return;
			}
		}
		if(attacked.getCustomName() != null && attacked.getCustomName().equals("ModleEngine")){
			return;
		}
		if(!(attacked instanceof Player)){
			return;
		}

		Player attackedPlayer = (Player) attacked;
		UUID attackedPlayerUUID = attackedPlayer.getUniqueId();
		double damageNumber = event.getFinalDamage();

		//玩家被攻擊數字
		PlayerDataFancy playerDataFancy = PlayerManagerCore.player_Data_Map.get(attackedPlayerUUID);
		playerDataFancy.attacked_number = damageNumber;


	}


}
