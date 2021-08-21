package com.daxton.fancycore.other.hologram;

import com.daxton.fancycore.FancyCore;
import com.daxton.fancycore.other.task.guise.GuiseEntity;
import com.daxton.fancycore.other.task.guise.GuiseEntityAPI;
import org.bukkit.Location;

import org.bukkit.entity.EntityType;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class FHologram {

	private List<GuiseEntity> guiseEntityList = new ArrayList<>();

	private List<FString> fStringsList = new ArrayList<>();

	private Location location;

	public FHologram(Location inlocation){
		this.location = inlocation.clone().add(0,-0.52,0);
		Location location = this.location.clone();
		GuiseEntity guiseEntity = GuiseEntityAPI.createGuise(location, "ARMOR_STAND", null, false, false, false);
		guiseEntity.holographic(true);
		guiseEntityList.add(guiseEntity);
	}

	//增加訊息
	public void addLine(String message){
		FString fString = new FString(message);

		fStringsList.add(null);
		int i = fStringsList.size()-1;
		if(i == 0){
			fString.height = -0.25;
			guiseEntityList.get(0).setName(message);
		}else {
			double previousHeight = fStringsList.get(i-1).height;
			fString.height = previousHeight - 0.25;
			GuiseEntity guiseEntity2 = GuiseEntityAPI.createGuise(location.clone().add(0,previousHeight,0), "ARMOR_STAND", null, false, false, false);
			guiseEntity2.holographic(true);
			guiseEntity2.setName(message);
			guiseEntityList.add(guiseEntity2);
		}

	}

	//增加物品
	public void addItem(ItemStack itemStack){
		FString fString = new FString(itemStack);
		fStringsList.add(fString);

		int i = fStringsList.size()-1;
		if(i == 0){
			fString.height = -0.48;
			if(guiseEntityList.get(0).getEntityType() == EntityType.ARMOR_STAND){
				guiseEntityList.get(0).delete();
				GuiseEntity guiseEntity = GuiseEntityAPI.createGuise(location, "DROPPED_ITEM", itemStack, false, false, false);
				guiseEntityList.set(0, guiseEntity);
			}
		}else {
			double previousHeight = fStringsList.get(i-1).height;
			fString.height = previousHeight - 0.48;
			GuiseEntity guiseEntity = GuiseEntityAPI.createGuise(location.clone().add(0,previousHeight,0), "DROPPED_ITEM", itemStack, false, false, false);
			guiseEntityList.add(guiseEntity);
		}

	}

	//刪除指定行數
	public void removeLine(int line){
		int si = fStringsList.size();
		List<FString> addList = new ArrayList<>();
		for(int i = line; i < si ;i ++){
			guiseEntityList.get(line).delete();
			guiseEntityList.remove(line);
			if(i != line){
				if(fStringsList.get(line).isItem){
					ItemStack itemStack = fStringsList.get(line).itemStack;
					FString fString = new FString(itemStack);
					addList.add(fString);
				}else {
					String message = fStringsList.get(line).message;
					FString fString = new FString(message);
					addList.add(fString);
				}

			}
			fStringsList.remove(line);
		}
		addList.forEach(fString -> {
			if(fString.isItem){
				addItem(fString.itemStack);
			}else {
				addLine(fString.message);
			}
		});
	}
	//縮行
	public void indent(int line){

	}

	//移動
	public void teleport(Location inlocation){
		this.location = inlocation;
		Location location = inlocation.clone();
		for(int i = 0; i < guiseEntityList.size();i++){
			if(i > 0){
				location.add(0,-0.25,0);
			}
			GuiseEntity guiseEntity = guiseEntityList.get(i);
			guiseEntity.teleport(location, false, false);

		}
	}
	//編輯指定行數
	public void editLine(int line, String message){
		fStringsList.forEach(fString -> FancyCore.fancyCore.getLogger().info("高1: "+fString.height));

		if(fStringsList.get(line).isItem){
			//刪除舊的
			guiseEntityList.get(line).delete();

			FString fString = new FString(message);
			double previousHeight = - 0.25;
			fString.height = previousHeight;
			if(line != 0){
				previousHeight = fStringsList.get(line-1).height;
				fString.height = previousHeight - 0.25;
			}
			fStringsList.set(line, fString);

			GuiseEntity guiseEntity2 = GuiseEntityAPI.createGuise(location.clone().add(0,previousHeight,0), "ARMOR_STAND", null, false, false, false);
			guiseEntity2.holographic(true);
			guiseEntity2.setName(message);

			guiseEntityList.set(line, guiseEntity2);

		}else {
			guiseEntityList.get(line).setName(message);
		}

		fStringsList.forEach(fString -> FancyCore.fancyCore.getLogger().info("高2: "+fString.height));
	}

	//編輯指定位置物品
	public void editItem(int line, String message){

	}

	//刪除
	public void delete(){
		guiseEntityList.forEach(GuiseEntity::delete);

	}

	public Location getLocation() {
		return location;
	}
}
