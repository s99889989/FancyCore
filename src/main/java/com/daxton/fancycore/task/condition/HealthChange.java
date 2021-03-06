package com.daxton.fancycore.task.condition;

import org.bukkit.entity.LivingEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH;

public class HealthChange {

	private boolean result = false;

	private static final Map<UUID,Double> healthMap = new HashMap<>();

	private LivingEntity self;
	private LivingEntity target;
	private String content;

	public HealthChange(LivingEntity self,LivingEntity target, String content, String taskID){
		this.self = self;
		this.target = target;
		this.content = content;

	}

	public void chickHealth(){
		this.result = false;
		if(this.content.contains("self")){
			UUID targetUUID = this.self.getUniqueId();
			double maxHealth = this.self.getAttribute(GENERIC_MAX_HEALTH).getValue();
			double nowHealth = this.self.getHealth();
			if(this.healthMap.get(targetUUID) == null){
				this.healthMap.put(targetUUID,maxHealth);
			}
			if(this.healthMap.get(targetUUID) != null && this.healthMap.get(targetUUID) != nowHealth){
				this.result = true;
				this.healthMap.put(targetUUID,nowHealth);
			}
		}else {
			if(this.target != null){
				UUID targetUUID = this.target.getUniqueId();
				double maxHealth = this.target.getAttribute(GENERIC_MAX_HEALTH).getValue();
				double nowHealth = this.target.getHealth();
				if(this.healthMap.get(targetUUID) == null){
					this.healthMap.put(targetUUID,maxHealth);
				}
				if(this.healthMap.get(targetUUID) != null && this.healthMap.get(targetUUID) != nowHealth){
					this.result = true;
					this.healthMap.put(targetUUID,nowHealth);
				}
			}

		}
	}

	public boolean isResult() {
		return result;
	}

}
