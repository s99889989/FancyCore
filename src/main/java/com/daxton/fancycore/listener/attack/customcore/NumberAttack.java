package com.daxton.fancycore.listener.attack.customcore;

import com.daxton.fancycore.api.aims.entity.Convert;
import com.daxton.fancycore.api.event.PhysicalDamageEvent;
import com.daxton.fancycore.manager.PlayerManagerCore;
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

import static org.bukkit.entity.EntityType.ARMOR_STAND;

public class NumberAttack implements Listener {

	//攻擊監聽
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPhysicalDamageListener(PhysicalDamageEvent event){
		if(event.getTarget().getType() == ARMOR_STAND){
			return;
		}
		if(Bukkit.getServer().getPluginManager().getPlugin("Citizens") !=null){
			if(CitizensAPI.getNPCRegistry().isNPC(event.getTarget())){
				return;
			}
		}

		if(event.getDamager() instanceof Player){

			double damageNumber = event.getDamage();
			Player player = (Player) event.getDamager();

			String damageType = event.getDamageType();

			PlayerDataFancy playerDataFancy = PlayerManagerCore.player_Data_Map.get(player.getUniqueId());

			if(damageType.contains("PHYSICAL_MISS")){
				playerDataFancy.attack_number = "Miss";
				return;
			}
			if(damageType.contains("PHYSICAL_BLOCK")){
				playerDataFancy.attack_number = "Block";
				return;
			}
			if(damageType.contains("PHYSICAL_CRITICAL")){
				playerDataFancy.attack_number = String.valueOf(damageNumber);
				return;
			}
			if(damageType.contains("Melee_ATTACK")){
				playerDataFancy.attack_number = String.valueOf(damageNumber);
				return;
			}
			if(damageType.contains("RANGE_ATTACK")){
				playerDataFancy.attack_number = String.valueOf(damageNumber);
				return;
			}
			if(damageType.contains("MAGIC_ATTACK")){
				playerDataFancy.attack_number = String.valueOf(damageNumber);
			}

		}

	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onAttack(EntityDamageByEntityEvent event){

		if(!(event.getEntity() instanceof LivingEntity) || event.getEntity().getType() == ARMOR_STAND){
			return;
		}
		if(Bukkit.getServer().getPluginManager().getPlugin("Citizens") !=null){
			if(CitizensAPI.getNPCRegistry().isNPC(event.getEntity())){
				return;
			}
		}

		double damageNumber = event.getFinalDamage();
		LivingEntity target = (LivingEntity) event.getEntity();
		Entity entity = Convert.convertEntity(event.getDamager());

		if(entity instanceof Player){
			Player player = (Player) entity;
			if(target.getCustomName() != null && target.getCustomName().equals("ModleEngine")){
				return;
			}
			PlayerDataFancy playerDataFancy = PlayerManagerCore.player_Data_Map.get(player.getUniqueId());

			if (event.isCancelled()) {
				playerDataFancy.attack_number = "Miss";
			}else {
				playerDataFancy.attack_number = String.valueOf(damageNumber);
			}


		}



	}

}
