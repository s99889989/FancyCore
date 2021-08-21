package com.daxton.fancycore.listener.attack;


import com.daxton.fancycore.manager.PlayerManagerCore;
import com.daxton.fancycore.other.playerdata.PlayerDataFancy;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.UUID;

public class AttackListener implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)//攻擊
	public void onPhysicalDamage(EntityDamageByEntityEvent event){
		Entity attacker = event.getDamager();
		Entity attacked = event.getEntity();

		if(!(attacker instanceof Player)){
			return;
		}
		Player killer = (Player) attacker;
		UUID killerUUID = killer.getUniqueId();
		double damagedNumber = event.getFinalDamage();
		//玩家攻擊數字
		PlayerDataFancy playerDataFancy = PlayerManagerCore.player_Data_Map.get(killerUUID);

		if (event.isCancelled()) {
			playerDataFancy.attack_number = "Miss";
		}else {
			playerDataFancy.attack_number = String.valueOf(damagedNumber);
		}



	}

}
