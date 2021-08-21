package com.daxton.fancycore.other.hologram;

import com.daxton.fancycore.other.task.guise.GuiseEntity;
import com.daxton.fancycore.other.task.guise.GuiseEntityAPI;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class FloatMessage {

	private final List<GuiseEntity> messageList = new ArrayList<>();

	private Location location;

	public FloatMessage(Location inLocation){
		this.location = inLocation.clone().add(0,-0.52,0);
	}

	public static FloatMessage createFloatMessage(Location inLocation){
		return new FloatMessage(inLocation);
	}

	//向下增加文字
	public void addLine(String message){
		double height = 0;
		if(!messageList.isEmpty()){
			height = messageList.size() * -0.25;
		}
		GuiseEntity guiseEntity = GuiseEntityAPI.createGuise(location.clone().add(0,height,0), "ARMOR_STAND", null, false, false, false);
		guiseEntity.holographic(true);
		guiseEntity.setName(message);
		messageList.add(guiseEntity);
	}
	//編輯指定行數
	public void editLine(int line, String message){
		if(line < messageList.size()){
			messageList.get(line).setName(message);
		}
	}

	public void teleport(Location inLocation){
		this.location = inLocation.clone().add(0,-0.52,0);
		for(int i = 0 ; i < messageList.size() ; i++){
			double height = i * -0.25;
			messageList.get(i).teleport(location.clone().add(0,height,0), false, false);
		}
	}

	//移除指定行數
	public void removeLine(int line){
		for(int i = line ; i < messageList.size() ; i++){
			GuiseEntity guiseEntity = messageList.get(i);
			if((i+1) < messageList.size()){
				String nextMessage = messageList.get(i+1).getName();
				guiseEntity.setName(nextMessage);
			}
		}
		int last = messageList.size() - 1;
		messageList.get(last).delete();
		messageList.remove(last);
	}
	//刪除
	public void delete(){
		messageList.forEach(GuiseEntity::delete);
	}

	public Location getLocation() {
		return location.clone().add(0,0.52,0);
	}
}
