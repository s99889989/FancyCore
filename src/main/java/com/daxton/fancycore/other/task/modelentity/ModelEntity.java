package com.daxton.fancycore.other.task.modelentity;

import com.daxton.fancycore.manager.TaskActionManager;
import com.daxton.fancycore.other.entity.BukkitAttributeSet;
import com.ticxo.modelengine.api.ModelEngineAPI;
import com.ticxo.modelengine.api.model.ActiveModel;
import com.ticxo.modelengine.api.model.ModeledEntity;
import com.ticxo.modelengine.api.util.ConfigManager;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Silverfish;

public class ModelEntity {

	public LivingEntity livingEntity;
	public ModeledEntity modeledEntity;
	public ActiveModel activeModel;
	public Location location;
	public String modelId;
	public String stateID;
	int lerpIn = 0;
	int lerpOut = 1;
	double speed = 1;

	public ModelEntity(Location location, String modelId, String stateID){
		this.location = location;
		this.modelId = modelId;
		this.stateID = stateID;
		setEntity();
		setModeledEntity();
	}

	public void setState(String stateID, int lerpIn, int lerpOut, double speed){
		activeModel.addState(stateID, lerpIn, lerpOut, speed);
		modeledEntity.addActiveModel(activeModel);
		modeledEntity.detectPlayers();
	}

	public void teleport(Location location){
		this.location = location;
		livingEntity.teleport(location);

	}

	public void delete(){
		modeledEntity.getAllActiveModel().forEach((s, activeModel) -> activeModel.clearModel());
		modeledEntity.getEntity().remove();
	}

	public void setEntity() {
		livingEntity = (Silverfish) location.getWorld().spawnEntity(location, EntityType.SILVERFISH);

		livingEntity.setCollidable(false);
		livingEntity.setAI(false);
		livingEntity.setCustomName("ModleEngine");
		livingEntity.setSilent(true);
		//livingEntity.setNoDamageTicks(3000);
		//BukkitAttributeSet.addAttribute(livingEntity, "GENERIC_MAX_HEALTH", "ADD_NUMBER", 100, "");
	}

	public void setModeledEntity(){
		modeledEntity = ModelEngineAPI.api.getModelManager().getModeledEntity(livingEntity.getUniqueId());
		if (modeledEntity == null) {
			modeledEntity = ModelEngineAPI.api.getModelManager().createModeledEntity(livingEntity);
		}

		float width = modeledEntity.getWidth();
		float height = modeledEntity.getHeight();
		float eye = modeledEntity.getEye();
		modeledEntity.overrideHitbox(width, height, eye);
		///////
		ConfigManager.AnimationMode animationMode = ConfigManager.AnimationMode.get(stateID);

		activeModel = ModelEngineAPI.api.getModelManager().createActiveModel(modelId);
		activeModel.setAnimationMode(animationMode);
		activeModel.setDamageTint(false);

		modeledEntity.addActiveModel(activeModel);
		modeledEntity.detectPlayers();
		modeledEntity.setInvisible(true);
	}

}
