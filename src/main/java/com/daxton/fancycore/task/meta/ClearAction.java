package com.daxton.fancycore.task.meta;

import com.daxton.fancycore.manager.TaskActionManager;
import com.daxton.fancycore.other.hologram.FloatMessage;
import com.daxton.fancycore.other.task.guise.GuiseEntity;
import com.daxton.fancycore.other.task.modelentity.ModelEntity;
import com.daxton.fancycore.task.meta.run.FixedPoint;
import com.daxton.fancycore.task.meta.run.LocPng;
import com.daxton.fancycore.task.meta.run.Loop;
import com.daxton.fancycore.task.meta.run.OrbitalAction;
import org.bukkit.Bukkit;
import org.bukkit.boss.BossBar;

import java.util.Iterator;

public class ClearAction {
	//清除全部
	public static void all(){
		//清除Loop
		for(Loop loop : TaskActionManager.task_Loop_Map.values()){
			if(!loop.isCancelled()){
				loop.cancel();
			}
		}
		TaskActionManager.task_Loop_Map.clear();
		//清除OrbitalAction
		for(OrbitalAction orbitalAction : TaskActionManager.task_OrbitalAction_Map.values()){
			if(!orbitalAction.isCancelled()){
				orbitalAction.cancel();
			}
		}
		TaskActionManager.task_OrbitalAction_Map.clear();
		//清除FixedPoint
		for(FixedPoint fixedPoint : TaskActionManager.task_FixedPoint_Map.values()){
			if(!fixedPoint.isCancelled()){
				fixedPoint.cancel();
			}
		}
		TaskActionManager.task_FixedPoint_Map.clear();
		//清除LocPng
		for(LocPng locPng : TaskActionManager.task_LocPng_Map.values()){
			if(!locPng.isCancelled()){
				locPng.cancel();
			}
		}
		TaskActionManager.task_LocPng_Map.clear();
		//清除FloatMessage
		for(FloatMessage floatMessage : TaskActionManager.task_FloatMessage_Map.values()){
			floatMessage.delete();
		}
		TaskActionManager.task_FloatMessage_Map.clear();
		//清除BossBar
		for(BossBar bossBar : TaskActionManager.task_BossBar_Map.values()){
			bossBar.removeAll();
		}
		TaskActionManager.task_BossBar_Map.clear();
		//清除GuiseEntity
		for(GuiseEntity guiseEntity : TaskActionManager.task_GuiseEntity_Map.values()){
			guiseEntity.delete();
		}
		TaskActionManager.task_GuiseEntity_Map.clear();
		//清除ModelEntity
		if (Bukkit.getServer().getPluginManager().getPlugin("ModelEngine") != null){
			for(ModelEntity modelEntity : TaskActionManager.task_ModelEntity_Map.values()){
				modelEntity.delete();
			}
			TaskActionManager.task_ModelEntity_Map.clear();
		}
		//
	}
	//依據taskID
	public static void taskID(String taskID){
		//FancyCore.fancyCore.getLogger().info("清除: "+taskID);
		//清除Loop
		if(TaskActionManager.task_Loop_Map.get(taskID) != null){
			TaskActionManager.task_Loop_Map.get(taskID).cancel();
			TaskActionManager.task_Loop_Map.remove(taskID);
		}
		//清除OrbitalAction
		if(TaskActionManager.task_OrbitalAction_Map.get(taskID) != null){
			TaskActionManager.task_OrbitalAction_Map.get(taskID).cancel();
			TaskActionManager.task_OrbitalAction_Map.remove(taskID);
		}
		//清除FixedPoint
		if(TaskActionManager.task_FixedPoint_Map.get(taskID) != null){
			TaskActionManager.task_FixedPoint_Map.get(taskID).cancel();
			TaskActionManager.task_FixedPoint_Map.remove(taskID);
		}
		//清除LocPng
		if(TaskActionManager.task_LocPng_Map.get(taskID) != null){
			TaskActionManager.task_LocPng_Map.get(taskID).cancel();
			TaskActionManager.task_LocPng_Map.remove(taskID);
		}
		//清除FloatMessage
		Iterator<String> ifm = TaskActionManager.task_FloatMessage_Map.keySet().iterator();
		while (ifm.hasNext()){
			String key = ifm.next();
			if(key.startsWith(taskID)){
				FloatMessage floatMessage = TaskActionManager.task_FloatMessage_Map.get(key);
				floatMessage.delete();
				ifm.remove();
			}

		}
		//清除BossBar
		Iterator<String> ibb = TaskActionManager.task_BossBar_Map.keySet().iterator();
		while (ibb.hasNext()){
			String key = ibb.next();
			if(key.startsWith(taskID)){
				BossBar bossBar = TaskActionManager.task_BossBar_Map.get(key);
				bossBar.removeAll();
				ibb.remove();
			}
		}
		//清除GuiseEntity
		Iterator<String> ige = TaskActionManager.task_GuiseEntity_Map.keySet().iterator();
		while (ige.hasNext()){
			String key = ige.next();
			if(key.startsWith(taskID)){
				GuiseEntity guiseEntity = TaskActionManager.task_GuiseEntity_Map.get(key);
				guiseEntity.delete();
				ige.remove();
			}
		}
		//清除ModelEntity
		if (Bukkit.getServer().getPluginManager().getPlugin("ModelEngine") != null){
			Iterator<String> im = TaskActionManager.task_ModelEntity_Map.keySet().iterator();
			while (im.hasNext()){
				String key = im.next();
				if(key.startsWith(taskID)){
					ModelEntity modelEntity = TaskActionManager.task_ModelEntity_Map.get(key);
					modelEntity.delete();
					im.remove();
				}
			}
		}
		//
	}

}
