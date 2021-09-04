package com.daxton.fancycore.task.location;

import com.daxton.fancycore.FancyCore;
import com.daxton.fancycore.api.aims.location.GetLocation;
import com.daxton.fancycore.manager.TaskActionManager;
import com.daxton.fancycore.other.task.FancyAction;
import com.daxton.fancycore.other.task.guise.GuiseEntity;
import com.daxton.fancycore.other.taskaction.MapGetKey;
import com.daxton.fancycore.other.taskaction.StringToMap;
import com.daxton.fancyitmes.item.CustomItem;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class Guise implements FancyAction {

	public void execute(LivingEntity self, LivingEntity target, Map<String, String> action_Map, Location inputLocation, String taskID){

		MapGetKey actionMapHandle = new MapGetKey(action_Map, self, target);
		//標記名稱
		String mark = actionMapHandle.getString(new String[]{"mark","mk"},"");
		//實體的類型
		String entityType = actionMapHandle.getString(new String[]{"entitytype","et"},"ARMOR_STAND");
		//實體的顯示名稱
		String entityName = actionMapHandle.getString(new String[]{"entityname","en"},null);
		//實體是否看的見
		boolean visible = actionMapHandle.getBoolean(new String[]{"visible"},false);
		//獲得物品的ID
		String itemID = actionMapHandle.getString(new String[]{"itemid"},null);
		//要裝備的部位
		String equipmentSlot = actionMapHandle.getString(new String[]{"equipmentslot","es"},"HEAD");
		//是否要移動
		boolean teleport = actionMapHandle.getBoolean(new String[]{"teleport","tp"}, false);
		//顯示的時間
		int duration = actionMapHandle.getInt(new String[]{"duration","dt"},0);
		//是否刪除
		boolean delete = actionMapHandle.getBoolean(new String[]{"delete","d"}, false);
		//目標
		String targetString = actionMapHandle.getString(new String[]{"targetkey"}, "");
		Map<String, String> targetMap = StringToMap.toTargetMap(targetString);

		if(inputLocation == null && TaskActionManager.task_GuiseEntity_Map.get(taskID+mark) != null){
			inputLocation = TaskActionManager.task_GuiseEntity_Map.get(taskID+mark).getLocation();
		}

		Location location = GetLocation.getOne(self, target, targetMap, inputLocation);

		if(TaskActionManager.task_GuiseEntity_Map.get(taskID+mark) == null){
			if(location != null && !delete){
				GuiseEntity guiseEntity = new GuiseEntity(location, entityType, null, false, false, false);

				guiseEntity.setVisible(!visible);

				setAng(guiseEntity, actionMapHandle);
				if(itemID != null){
					if(Bukkit.getServer().getPluginManager().getPlugin("FancyItems") != null && Bukkit.getPluginManager().isPluginEnabled("FancyItems")){
						String[] itemString = itemID.split("\\.");
						if(itemString.length == 2){

							if(self instanceof Player){
								Player player = (Player) self;
								ItemStack itemStack = CustomItem.valueOf(player, itemString[0], itemString[1], 1);
								guiseEntity.equipment(itemStack, equipmentSlot);
							}else {
								ItemStack itemStack = CustomItem.valueOf3(itemString[0], itemString[1], 1);
								guiseEntity.equipment(itemStack, equipmentSlot);
							}

						}

					}
				}

				TaskActionManager.task_GuiseEntity_Map.put(taskID+mark, guiseEntity);
			}
		}else {
			GuiseEntity guiseEntity = TaskActionManager.task_GuiseEntity_Map.get(taskID+mark);

			if(teleport && location != null){
				guiseEntity.teleport(location, false, false);
			}
			setAng(guiseEntity, actionMapHandle);
			if(itemID != null){
				if(Bukkit.getServer().getPluginManager().getPlugin("FancyItems") != null && Bukkit.getPluginManager().isPluginEnabled("FancyItems")){
					String[] itemString = itemID.split("\\.");
					if(itemString.length == 2){

						if(self instanceof Player){
							Player player = (Player) self;
							ItemStack itemStack = CustomItem.valueOf(player, itemString[0], itemString[1], 1);
							guiseEntity.equipment(itemStack, equipmentSlot);
						}else {
							ItemStack itemStack = CustomItem.valueOf3(itemString[0], itemString[1], 1);
							guiseEntity.equipment(itemStack, equipmentSlot);
						}

					}

				}
			}




			if(delete){
				guiseEntity.delete();
				TaskActionManager.task_GuiseEntity_Map.remove(taskID+mark);
			}

		}

	}

	public void setAng(GuiseEntity guiseEntity, MapGetKey actionMapHandle){

		String[] HeadAddLoc = actionMapHandle.getStringList(new String[]{"HeadAddLoc","hal"},new String[]{"false","false"},"\\|",2);
		boolean headPitch = Boolean.parseBoolean(HeadAddLoc[0]);
		boolean headYaw = Boolean.parseBoolean(HeadAddLoc[1]);

		//頭的角度
		float headX = 0;
		float headY = 0;
		float headZ = 0;
		String[] headAngle = actionMapHandle.getStringList(new String[]{"head","h"},new String[]{"0","0","0"},"\\|",3);
		if(headAngle.length == 3){

			try {
				headX = Float.parseFloat(headAngle[0]);
				headY = Float.parseFloat(headAngle[1]);
				headZ = Float.parseFloat(headAngle[2]);
			}catch (NumberFormatException exception){
				headX = 0;
				headY = 0;
				headZ = 0;
			}
		}

		Location location = guiseEntity.getLocation();
		if(headPitch){
			headX += location.getPitch();
		}
		if(headYaw){
			headY += location.getYaw();
		}


		guiseEntity.setArmorStandAngle("head",headX,headY,headZ);
		//身體的角度
		float bodyX = 0;
		float bodyY = 0;
		float bodyZ = 0;
		String[] bodyAngle = actionMapHandle.getStringList(new String[]{"body","b"},new String[]{"0","0","0"},"\\|",3);
		if(bodyAngle.length == 3){

			try {
				bodyX = Float.parseFloat(bodyAngle[0]);
				bodyY = Float.parseFloat(bodyAngle[1]);
				bodyZ = Float.parseFloat(bodyAngle[2]);
			}catch (NumberFormatException exception){
				bodyX = 0;
				bodyY = 0;
				bodyZ = 0;
			}
		}
		guiseEntity.setArmorStandAngle("body",bodyX,bodyY,bodyZ);
		//左手的角度
		float leftArmX = 0;
		float leftArmY = 0;
		float leftArmZ = 0;
		String[] leftArmAngle = actionMapHandle.getStringList(new String[]{"leftarm","lar"},new String[]{"0","0","0"},"\\|",3);
		if(leftArmAngle.length == 3){

			try {
				leftArmX = Float.parseFloat(leftArmAngle[0]);
				leftArmY = Float.parseFloat(leftArmAngle[1]);
				leftArmZ = Float.parseFloat(leftArmAngle[2]);
			}catch (NumberFormatException exception){
				leftArmX = 0;
				leftArmY = 0;
				leftArmZ = 0;
			}
		}
		guiseEntity.setArmorStandAngle("leftarm",leftArmX,leftArmY,leftArmZ);
		//右手的角度
		float rightArmX = 0;
		float rightArmY = 0;
		float rightArmZ = 0;
		String[] rightArmAngle = actionMapHandle.getStringList(new String[]{"rightarm","rar"},new String[]{"0","0","0"},"\\|",3);
		if(rightArmAngle.length == 3){

			try {
				rightArmX = Float.parseFloat(rightArmAngle[0]);
				rightArmY = Float.parseFloat(rightArmAngle[1]);
				rightArmZ = Float.parseFloat(rightArmAngle[2]);
			}catch (NumberFormatException exception){
				rightArmX = 0;
				rightArmY = 0;
				rightArmZ = 0;
			}
		}
		guiseEntity.setArmorStandAngle("rightarm",rightArmX,rightArmY,rightArmZ);
		//左腿的角度
		float leftLegX = 0;
		float leftLegY = 0;
		float leftLegZ = 0;
		String[] leftLegAngle = actionMapHandle.getStringList(new String[]{"leftleg","llg"},new String[]{"0","0","0"},"\\|",3);
		if(leftLegAngle.length == 3){

			try {
				leftLegX = Float.parseFloat(leftLegAngle[0]);
				leftLegY = Float.parseFloat(leftLegAngle[1]);
				leftLegZ = Float.parseFloat(leftLegAngle[2]);
			}catch (NumberFormatException exception){
				leftLegX = 0;
				leftLegY = 0;
				leftLegZ = 0;
			}
		}
		guiseEntity.setArmorStandAngle("leftleg",leftLegX,leftLegY,leftLegZ);
		//右腿的角度
		float rightLegX = 0;
		float rightLegY = 0;
		float rightLegZ = 0;
		String[] rightLegAngle = actionMapHandle.getStringList(new String[]{"rightleg","rlg"},new String[]{"0","0","0"},"\\|",3);
		if(rightLegAngle.length == 3){

			try {
				rightLegX = Float.parseFloat(rightLegAngle[0]);
				rightLegY = Float.parseFloat(rightLegAngle[1]);
				rightLegZ = Float.parseFloat(rightLegAngle[2]);
			}catch (NumberFormatException exception){
				rightLegX = 0;
				rightLegY = 0;
				rightLegZ = 0;
			}
		}
		guiseEntity.setArmorStandAngle("rightleg",rightLegX,rightLegY,rightLegZ);
	}

}
