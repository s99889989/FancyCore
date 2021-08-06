package com.daxton.fancycore.listener;

import com.daxton.fancycore.FancyCore;
import com.daxton.fancycore.manager.MobManager;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class AttackListener implements Listener {

	@EventHandler(priority = EventPriority.LOW)//攻擊
	public void onPhysicalDamage(EntityDamageByEntityEvent event){
		Entity attacker = event.getDamager();
		Entity attacked = event.getEntity();

//		UUID uuid = attacked.getUniqueId();
//		if(MobManager.mythicMobs_ActiveMob_Map.get(uuid) != null){
//			ActiveMob activeMob = MobManager.mythicMobs_ActiveMob_Map.get(uuid);
//
//			new BukkitRunnable() {
//				@Override
//				public void run() {
//					attacker.sendMessage("仇恨");
//					activeMob.getThreatTable().getAllThreatTargets().forEach(abstractEntity -> {
//						String name = abstractEntity.getName();
//						double d = activeMob.getThreatTable().getThreat(abstractEntity);
//
//						attacker.sendMessage(name+"仇恨值2: "+d);
//					});
//				}
//			}.runTaskLater(FancyCore.fancyCore, 10);
//
//		}


	}

}
