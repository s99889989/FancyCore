package com.daxton.fancycore.task.entity;

import com.daxton.fancycore.api.aims.entity.GetEntity;
import com.daxton.fancycore.other.task.FancyAction;
import com.daxton.fancycore.other.task.PackEntity;
import com.daxton.fancycore.other.taskaction.MapGetKey;
import com.daxton.fancycore.other.taskaction.StringToMap;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

import java.util.List;
import java.util.Map;

public class GlowSet implements FancyAction {

	public GlowSet(){

	}

	public void execute(LivingEntity self, LivingEntity target, Map<String, String> action_Map, Location inputLocation, String taskID){
		MapGetKey actionMapHandle = new MapGetKey(action_Map, self, target);
		//顏色
		ChatColor color = actionMapHandle.getChatColor(new String[]{"color","c"},"WHITE");

		//目標
		String targetString = actionMapHandle.getString(new String[]{"targetkey"}, "");
		Map<String, String> targetMap = StringToMap.toTargetMap(targetString);
		List<LivingEntity> livingEntityList = GetEntity.get(self, target, targetMap);


		livingEntityList.forEach(livingEntity -> {
			PackEntity.createTeam(livingEntity.getUniqueId(), color, taskID);
			PackEntity.setGlowing(livingEntity.getEntityId());
			PackEntity.addEntity(livingEntity.getUniqueId(), color, taskID);
			PackEntity.upTeam(livingEntity.getUniqueId(), color, taskID);
		});

	}

}
