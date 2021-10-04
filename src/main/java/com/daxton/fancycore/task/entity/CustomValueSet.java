package com.daxton.fancycore.task.entity;

import com.daxton.fancycore.FancyCore;
import com.daxton.fancycore.api.aims.entity.GetEntity;
import com.daxton.fancycore.manager.PlayerManagerCore;
import com.daxton.fancycore.other.playerdata.PlayerDataFancy;
import com.daxton.fancycore.other.task.FancyAction;
import com.daxton.fancycore.other.taskaction.MapGetKey;
import com.daxton.fancycore.other.taskaction.StringToMap;
import com.daxton.fancymobs.api.FancyMob;
import com.daxton.fancymobs.manager.MobManager;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CustomValueSet implements FancyAction {
	//自訂值
	public void execute(LivingEntity self, LivingEntity target, Map<String, String> action_Map, Location inputLocation, String taskID){
		MapGetKey actionMapHandle = new MapGetKey(action_Map, self, target);

		//屬性名稱
		String key = actionMapHandle.getString(new String[]{"key","k"},"");

		//屬性標籤
		String label = actionMapHandle.getString(new String[]{"label"},"core");

		//量
		String value = actionMapHandle.getString(new String[]{"value","v"},"");

		//目標
		String targetString = actionMapHandle.getString(new String[]{"targetkey"}, "@Self");
		Map<String, String> targetMap = StringToMap.toTargetMap(targetString);
		List<LivingEntity> livingEntityList = GetEntity.get(self, target, targetMap);

		livingEntityList.forEach(livingEntity -> {
			UUID uuid = livingEntity.getUniqueId();
			if(livingEntity instanceof Player){
				PlayerDataFancy playerDataFancy = PlayerManagerCore.player_Data_Map.get(uuid);
				if(playerDataFancy != null){
					playerDataFancy.addCustomValue(key, value, label);
				}
			}else {
				FancyMob fancyMob = MobManager.fancy_Mob_Map.get(uuid);
				if(fancyMob != null){
					if(value.isEmpty()){
						fancyMob.setDefaultCustomValue(key);
					}else {
						fancyMob.setCustomValue(key, value);
					}

				}
			}


		});

	}

}
