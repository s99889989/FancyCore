package com.daxton.fancycore.task.meta;

import com.daxton.fancycore.FancyCore;
import com.daxton.fancycore.manager.TaskActionManager;
import com.daxton.fancycore.other.hologram.FloatMessage;
import com.daxton.fancycore.task.meta.run.FixedPoint;
import com.daxton.fancycore.task.meta.run.Loop;
import com.daxton.fancycore.task.meta.run.OrbitalAction;
import org.bukkit.boss.BossBar;

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
		//清除FloatMessage
		if(TaskActionManager.task_FloatMessage_Map.get(taskID) != null){
			TaskActionManager.task_FloatMessage_Map.get(taskID).delete();
			TaskActionManager.task_FloatMessage_Map.remove(taskID);
		}
		//清除BossBar
		if(TaskActionManager.task_BossBar_Map.get(taskID) != null){
			TaskActionManager.task_BossBar_Map.get(taskID).removeAll();
			TaskActionManager.task_BossBar_Map.remove(taskID);
		}
		//
	}

}
