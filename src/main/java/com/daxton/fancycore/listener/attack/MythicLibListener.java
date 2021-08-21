package com.daxton.fancycore.listener.attack;

import com.daxton.fancycore.manager.PlayerManagerCore;
import com.daxton.fancycore.other.playerdata.PlayerDataFancy;
import io.lumine.mythic.lib.api.event.PlayerAttackEvent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.UUID;

public class MythicLibListener implements Listener {

	private boolean crit = false;

	@EventHandler(priority = EventPriority.HIGHEST)//攻擊
	public void onPhysicalDamage(PlayerAttackEvent event){
		crit = event.isCrit();


	}

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
		//playerDataFancy.attack_number = damagedNumber;



	}

}
