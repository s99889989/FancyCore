package com.daxton.fancycore.api.fancyclient.build.module;


import com.daxton.fancycore.api.fancyclient.json.JsonCtrl;
import com.daxton.fancycore.api.fancyclient.json.menu_object.button.ClickButtonJson;
import com.daxton.fancycore.api.fancyclient.json.menu_object.image.ImageShowJson;
import com.daxton.fancycore.api.fancyclient.json.menu_object.item.ItemShowJson;
import com.daxton.fancycore.api.fancyclient.json.menu_object.slider.PullPanelJson;
import com.daxton.fancycore.api.fancyclient.json.menu_object.text.TextLabelJson;
import com.daxton.fancycore.api.fancyclient.json.menu_object.textfield.TextFieldJson;
import org.bukkit.entity.LivingEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PullPanelModule implements ModComponent {

	//物件名稱
	private String objectID;
	//圖片位址
	private String image;
	//寬度
	private int width;
	//高度
	private int height;
	//顯示寬度
	private int displayWidth;
	//顯示高度
	private int displayHeight;
	//起始X位置
	private int startX;
	//起始Y位置
	private int startY;
	//基礎位置
	private int position;
	//X偏移
	private int x;
	//Y偏移
	private int y;
	//原物件表
	List<ModComponent> modComponentList;

	private PullPanelModule(String objectID, String image, int width, int height, int displayWidth, int displayHeight, int startX, int startY, int position, int x, int y, List<ModComponent> modComponentList) {
		this.objectID = objectID;
		this.image = image;
		this.width = width;
		this.height = height;
		this.displayWidth = displayWidth;
		this.displayHeight = displayHeight;
		this.startX = startX;
		this.startY = startY;
		this.position = position;
		this.x = x;
		this.y = y;
		this.modComponentList = modComponentList;
	}

	public List<ModComponent> getModComponentList() {
		return modComponentList;
	}

	public PullPanelModule addComponent(ModComponent modComponent){
		modComponentList.add(modComponent);
		return this;
	}

	public PullPanelJson toObject(LivingEntity self, LivingEntity target){
		Map<String, String> objectList = setObjectList(self, target);
		return new PullPanelJson(self, target, objectID, image, width, height, displayWidth, displayHeight, startX, startY, position, x, y, objectList);
	}

	//更新物件內容
	public Map<String, String> setObjectList(LivingEntity self, LivingEntity target){
		Map<String, String> objectList = new HashMap<>();
		modComponentList.forEach(modComponent -> {
			if(modComponent instanceof ButtonModule){
				ClickButtonJson clickButtonJson = ((ButtonModule) modComponent).toObject(self, target);
				objectList.put(clickButtonJson.getType()+clickButtonJson.getObject_name(), JsonCtrl.writeJSON(clickButtonJson));
			}
			if(modComponent instanceof TextModule){
				TextLabelJson textLabelJson = ((TextModule) modComponent).toObject(self, target);
				objectList.put(textLabelJson.getType()+textLabelJson.getObject_name(), JsonCtrl.writeJSON(textLabelJson));
			}
			if(modComponent instanceof ImageModule){
				ImageShowJson imageShowJson = ((ImageModule) modComponent).toObject(self, target);
				objectList.put(imageShowJson.getType()+imageShowJson.getObject_name(), JsonCtrl.writeJSON(imageShowJson));
			}
			if(modComponent instanceof TextFieldModule){
				TextFieldJson textFieldJson = ((TextFieldModule) modComponent).toObject(self, target);
				objectList.put(textFieldJson.getType()+textFieldJson.getObject_name(), JsonCtrl.writeJSON(textFieldJson));
			}
			if(modComponent instanceof ItemModule){
				ItemShowJson itemShowJson = ((ItemModule) modComponent).toObject();
				objectList.put(itemShowJson.getType()+itemShowJson.getObject_name(), JsonCtrl.writeJSON(itemShowJson));
			}
		});
		return objectList;
	}

	//---------------------------------------------------------------------------------------------------//

	public static class Builder {

		//物件名稱
		private String objectID = String.valueOf((int)(Math.random()*100000));
		//圖片位址
		private String image = "https://imgur.com/Rf9Tizv";
		//寬度
		private int width = -1;
		//高度
		private int height = -1;
		//顯示寬度
		private int displayWidth = -1;;
		//顯示高度
		private int displayHeight = -1;;
		//起始X位置
		private int startX;
		//起始Y位置
		private int startY;
		//基礎位置
		private int position;
		//X偏移
		private int x;
		//Y偏移
		private int y;
		//原物件表
		List<ModComponent> modComponentList = new ArrayList<>();

		public static Builder getInstance(){
			return new Builder();
		}

		public Builder addComponent(ModComponent modComponent){
			modComponentList.add(modComponent);
			return this;
		}

		public Builder setObjectID(String objectID) {
			this.objectID = objectID;
			return this;
		}

		public Builder setImage(String image) {
			this.image = image;
			return this;
		}

		public Builder setWidth(int width) {
			this.width = width;
			return this;
		}

		public Builder setHeight(int height) {
			this.height = height;
			return this;
		}

		public Builder setDisplayWidth(int displayWidth) {
			this.displayWidth = displayWidth;
			return this;
		}

		public Builder setDisplayHeight(int displayHeight) {
			this.displayHeight = displayHeight;
			return this;
		}

		public Builder setStartX(int startX) {
			this.startX = startX;
			return this;
		}

		public Builder setStartY(int startY) {
			this.startY = startY;
			return this;
		}

		public Builder setPosition(int position) {
			this.position = position;
			return this;
		}

		public Builder setX(int x) {
			this.x = x;
			return this;
		}

		public Builder setY(int y) {
			this.y = y;
			return this;
		}

		public PullPanelModule build(){
			return new PullPanelModule(objectID, image, width, height, displayWidth, displayHeight, startX, startY, position, x, y, modComponentList);
		}

	}

}
