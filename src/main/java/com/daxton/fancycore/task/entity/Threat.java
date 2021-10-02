package com.daxton.fancycore.task.entity;

import com.daxton.fancycore.api.aims.entity.GetEntity;
import com.daxton.fancycore.other.task.FancyAction;
import com.daxton.fancycore.other.taskaction.MapGetKey;
import com.daxton.fancycore.other.taskaction.StringToMap;
import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
import io.lumine.xikage.mythicmobs.mobs.MobManager;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class Threat implements FancyAction {

	public void execute(LivingEntity self, LivingEntity target, Map<String, String> action_Map, Location inputLocation, String taskID){
		MapGetKey actionMapHandle = new MapGetKey(action_Map, self, target);

		int amount = actionMapHandle.getInt(new String[]{"amount","a"}, 1);

		//目標
		String targetString = actionMapHandle.getString(new String[]{"targetkey"}, "");
		Map<String, String> targetMap = StringToMap.toTargetMap(targetString);
		List<LivingEntity> livingEntityList = GetEntity.get(self, target, targetMap);

		MythicMobs mythicMobs = MythicMobs.inst();
		MobManager mobManager = mythicMobs.getMobManager();
		AbstractEntity abstractEntity = BukkitAdapter.adapt(self);
		livingEntityList.forEach(livingEntity -> {
			UUID uuid = livingEntity.getUniqueId();
			Optional<ActiveMob> activeMobOptional = mobManager.getActiveMob(uuid);
			if(activeMobOptional.isPresent()) {
				ActiveMob activeMob = activeMobOptional.get();

				activeMob.getThreatTable().threatGain(abstractEntity, amount);

			}
		});

	}


}
