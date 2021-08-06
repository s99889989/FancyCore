package com.daxton.fancycore.listener;

import com.daxton.fancycore.manager.MobManager;
import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobDeathEvent;
import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobLootDropEvent;
import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobSpawnEvent;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import java.util.UUID;

public class MythicMobListener implements Listener {

	@EventHandler
	public void onMythicMobSpawn(MythicMobSpawnEvent event){
		ActiveMob activeMob = event.getMob();
		UUID uuid = activeMob.getUniqueId();
		MobManager.mythicMobs_ActiveMob_Map.put(uuid, activeMob);


	}

	@EventHandler(priority = EventPriority.LOW)//當MythicMob的怪物死亡時
	public void onMythicMobDeath(MythicMobDeathEvent event){
		LivingEntity target = (LivingEntity) event.getEntity();
		UUID uuid = target.getUniqueId();
		LivingEntity killer = event.getKiller();

//		ActiveMob activeMob = MobManager.mythicMobs_ActiveMob_Map.get(uuid);
//
//
//
//		Player mostPlayer = null;
//		double most = 0;
//		for(AbstractEntity abstractEntity : activeMob.getThreatTable().getAllThreatTargets()){
//			Entity entity = abstractEntity.getBukkitEntity();
//			if(entity instanceof Player){
//				Player player = (Player) entity;
//				double d = activeMob.getThreatTable().getThreat(abstractEntity);
//				player.sendMessage("仇恨值: "+d);
//				if(d > most){
//					most = d;
//					mostPlayer = player;
//				}
//			}
//		}
//
//		List<ItemStack> itemStacks = event.getDrops();
//		for(ItemStack itemStack : itemStacks) {
//			if (mostPlayer != null) {
//				Inventory inventory =mostPlayer.getInventory();
//				inventory.addItem(itemStack);
//				killer.sendMessage("獲得物品:" + itemStack.getType());
//			}
//		}
//
//		event.getDrops().clear();

	}
	@EventHandler//在生成戰利品表之前調用
	public void onMythicMobLootDrop(MythicMobLootDropEvent event){
		LivingEntity target = (LivingEntity) event.getEntity();
		LivingEntity livingEntity = event.getKiller();
//		if(livingEntity instanceof Player){
//			Player player = (Player) livingEntity;
//
//			player.sendMessage("獲得經驗:"+event.getExp());
//
//			player.sendMessage("獲得金錢:"+event.getMoney());
//		}

	}


}
