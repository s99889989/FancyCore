package com.daxton.fancycore.listener.attack;


import com.daxton.fancycore.FancyCore;
import com.daxton.fancycore.api.aims.entity.Convert;
import com.daxton.fancycore.manager.PlayerManagerCore;
import com.daxton.fancycore.other.playerdata.ItemCD;
import com.daxton.fancycore.other.playerdata.PlayerDataFancy;
import net.citizensnpcs.api.CitizensAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.UUID;

public class AttackListener implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)//攻擊
	public void onPhysicalDamage(EntityDamageByEntityEvent event){
		Entity attacker = Convert.convertEntity(event.getDamager());
		Entity attacked = event.getEntity();
		if(Bukkit.getServer().getPluginManager().getPlugin("Citizens") !=null){
			if(CitizensAPI.getNPCRegistry().isNPC(event.getEntity())){
				return;
			}
		}
		if(attacked.getCustomName() != null && attacked.getCustomName().equals("ModleEngine")){
			return;
		}
		if(!(attacked instanceof LivingEntity) || !(attacker instanceof Player)){
			return;
		}

		Player killer = (Player) attacker;
		String targetUUIDString = event.getEntity().getUniqueId().toString();
		UUID killerUUID = killer.getUniqueId();
		double damageNumber = event.getFinalDamage();
		//玩家攻擊數字
		PlayerDataFancy playerDataFancy = PlayerManagerCore.player_Data_Map.get(killerUUID);
		if (event.isCancelled()) {
			playerDataFancy.addAttackNumber(targetUUIDString, "Miss");
		}else {
			playerDataFancy.addAttackNumber(targetUUIDString, String.valueOf(damageNumber));
		}

	}

}
