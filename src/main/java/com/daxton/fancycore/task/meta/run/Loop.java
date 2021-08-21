package com.daxton.fancycore.task.meta.run;

import com.daxton.fancycore.FancyCore;
import com.daxton.fancycore.other.task.FancyAction;
import com.daxton.fancycore.other.taskaction.MapGetKey;
import com.daxton.fancycore.manager.TaskActionManager;
import com.daxton.fancycore.task.meta.StartAction;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Loop extends BukkitRunnable implements FancyAction{

	private final FancyCore fancyCore = FancyCore.fancyCore;

	private int ticksRun = 0;
	private int period = 1;
	private int duration = 0;

	private LivingEntity self;
	private LivingEntity target;
	private String taskID = "";

	private List<Map<String, String>> onTime = new ArrayList<>();
	private List<Map<String, String>> onEnd = new ArrayList<>();

	private boolean unlimited = false;

	public void execute(LivingEntity self, LivingEntity target, Map<String, String> action_Map, Location inputLocation, String inTaskID){
		this.self = self;
		this.target = target;
		this.taskID = inTaskID;
		MapGetKey actionMapHandle = new MapGetKey(action_Map, self, target);
		//開始時執行動作
		List<Map<String, String>> onStart = actionMapHandle.getActionMapList(new String[]{"os","onstart"},null);
		//持續執行動作
		this.onTime = actionMapHandle.getActionMapList(new String[]{"ot","ontime"},null);
		//最後執行動作
		this.onEnd = actionMapHandle.getActionMapList(new String[]{"oe","onend"},null);
		//動作間隔
		this.period = actionMapHandle.getInt(new String[]{"p","period"},1);
		//動作持續時間
		this.duration = actionMapHandle.getInt(new String[]{"d","duration"},20);

		//如果間隔小於等於0，間隔等於1
		if(this.period <= 0){
			this.period = 1;
		}
		//如果持續時間為0，就執行時間就為無限。
		if(this.duration == 0){
			this.unlimited = true;
		}


		StartAction.execute(self, target, onStart, taskID);
		this.runTaskTimer(fancyCore,0, this.period);

	}
	//執行
	public void run(){
		ticksRun = ticksRun + period;
		StartAction.execute(self, target, this.onTime, taskID);

		if(!unlimited){
			if(ticksRun > duration){
				StartAction.execute(self, target, this.onEnd, taskID);
				cancel();
				TaskActionManager.task_Loop_Map.remove(this.taskID);
			}
		}

	}

}
