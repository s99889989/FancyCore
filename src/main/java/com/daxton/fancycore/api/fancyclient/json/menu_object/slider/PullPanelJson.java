package com.daxton.fancycore.api.fancyclient.json.menu_object.slider;

import com.daxton.fancycore.api.fancyclient.json.JsonCtrl;
import com.daxton.fancycore.api.fancyclient.json.menu_object.button.ClickButtonJson;
import com.daxton.fancycore.api.fancyclient.json.menu_object.image.ImageShowJson;
import com.daxton.fancycore.api.fancyclient.json.menu_object.item.ItemShowJson;
import com.daxton.fancycore.api.fancyclient.json.menu_object.slot.SlotItemJson;
import com.daxton.fancycore.api.fancyclient.json.menu_object.text.TextLabelJson;
import com.daxton.fancycore.api.fancyclient.json.menu_object.textfield.TextFieldJson;
import com.daxton.fancygui.config.FileConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PullPanelJson {

	//類型
	public String type = "PullPanel";
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
	//物件表
	private Map<String, String> objectList = new HashMap<>();


	public PullPanelJson(LivingEntity self, LivingEntity target, String objectID, String image, int width, int height, int displayWidth, int displayHeight, int startX, int startY, int position, int x, int y, Map<String, String> objectList) {
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
		this.objectList = objectList;
	}

	//建立
	public PullPanelJson(Player player, FileConfiguration config, FileConfiguration object_config, String inputKey, String button_configKey, String button_key){
		this.objectID = inputKey+"."+button_configKey+"."+button_key;
		this.position = config.getInt("ObjectList."+inputKey+".Position");
		this.x = config.getInt("ObjectList."+inputKey+".X");
		this.y = config.getInt("ObjectList."+inputKey+".Y");

		this.image = object_config.getString(button_key+".Image");
		this.width = object_config.getInt(button_key+".Width");
		this.height = object_config.getInt(button_key+".Height");
		this.displayWidth = object_config.getInt(button_key+".DisplayWidth");
		this.displayHeight = object_config.getInt(button_key+".DisplayHeight");
		this.startX = object_config.getInt(button_key+".StartX");
		this.startY = object_config.getInt(button_key+".StartY");

		Set<String> object_key = object_config.getConfigurationSection(button_key+".ObjectList").getKeys(false);
		for(String key : object_key){
			String objectString = object_config.getString(button_key+".ObjectList."+key+".Object");
			String[] keys = new String[0];
			if (objectString != null) {
				keys = objectString.split("\\.");
			}
			if(keys.length == 2){
				FileConfiguration pull_config = FileConfig.config_Map.get("mod_object/"+keys[0]+".yml");
				if(pull_config != null){
					String type = pull_config.getString(keys[1]+".Type");
					if(type != null){
						if(type.equalsIgnoreCase("Button")){
							ClickButtonJson clickButtonJson = new ClickButtonJson(player, object_config, pull_config, key, keys[0], keys[1], button_key);
							objectList.put(type+key, JsonCtrl.writeJSON(clickButtonJson));
							continue;
						}
						if(type.equalsIgnoreCase("TextField")){
							TextFieldJson textFieldJson = new TextFieldJson(player, object_config, pull_config, key, keys[0], keys[1], button_key);
							objectList.put(type+key, JsonCtrl.writeJSON(textFieldJson));
							continue;
						}
						if(type.equalsIgnoreCase("Image")){
							ImageShowJson imageShowJson = new ImageShowJson(player, object_config, pull_config, key, keys[0], keys[1], button_key);
							objectList.put(type+key, JsonCtrl.writeJSON(imageShowJson));
							continue;
						}
						if(type.equalsIgnoreCase("Text")){
							TextLabelJson textLabelJson = new TextLabelJson(player, object_config, pull_config, key, keys[0], keys[1], button_key);
							objectList.put(type+key, JsonCtrl.writeJSON(textLabelJson));
							continue;
						}
						if(type.equalsIgnoreCase("Item")){
							ItemShowJson itemShowJson = new ItemShowJson(player, object_config, pull_config, key, keys[0], keys[1], button_key);
							objectList.put(type+key, JsonCtrl.writeJSON(itemShowJson));
							continue;
						}
						if(type.equalsIgnoreCase("SlotItem")){
							SlotItemJson slotItemJson = new SlotItemJson(player, object_config, pull_config, key, keys[0], keys[1], button_key);
							objectList.put(type+key, JsonCtrl.writeJSON(slotItemJson));
						}

					}
				}
			}
		}
	}



	//把字串轉成ClickButton
	public static PullPanelJson readJSON(String string) {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		return gson.fromJson(string, PullPanelJson.class);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getObjectID() {
		return objectID;
	}

	public void setObjectID(String objectID) {
		this.objectID = objectID;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getDisplayWidth() {
		return displayWidth;
	}

	public void setDisplayWidth(int displayWidth) {
		this.displayWidth = displayWidth;
	}

	public int getDisplayHeight() {
		return displayHeight;
	}

	public void setDisplayHeight(int displayHeight) {
		this.displayHeight = displayHeight;
	}

	public int getStartX() {
		return startX;
	}

	public void setStartX(int startX) {
		this.startX = startX;
	}

	public int getStartY() {
		return startY;
	}

	public void setStartY(int startY) {
		this.startY = startY;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Map<String, String> getObjectList() {
		return objectList;
	}

	public void setObjectList(Map<String, String> objectList) {
		this.objectList = objectList;
	}
}
