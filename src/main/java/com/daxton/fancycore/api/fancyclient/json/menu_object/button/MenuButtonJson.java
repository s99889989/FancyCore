package com.daxton.fancycore.api.fancyclient.json.menu_object.button;

import com.daxton.fancycore.api.fancyclient.json.JsonCtrl;
import com.daxton.fancycore.api.fancyclient.json.menu_object.image.ImageShowJson;
import com.daxton.fancycore.api.fancyclient.json.menu_object.item.ItemShowJson;
import com.daxton.fancycore.api.fancyclient.json.menu_object.slot.SlotItemJson;
import com.daxton.fancycore.api.fancyclient.json.menu_object.text.TextLabelJson;
import com.daxton.fancycore.api.fancyclient.json.menu_object.textfield.TextFieldJson;
import com.daxton.fancygui.config.FileConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MenuButtonJson  {

	//類型
	public String type = "MenuButton";
	//物件名稱
	private String objectID;
	//基礎位置
	private int position;
	//X偏移
	private int x;
	//Y偏移
	private int y;

	//顯示名稱
	private String displayFont;
	//顯示文字大小
	private float fontSize;
	//開啟
	private String onImage;
	private int onWidth;
	private int onHeight;
	//關閉
	private String offImage;
	private int offWidth;
	private int offHeight;
	//物件表
	private Map<String, String> objectMap = new HashMap<>();

	//GUI
	public MenuButtonJson(Player player, FileConfiguration config, FileConfiguration object_config, String inputKey, String button_configKey, String button_key){
		this.objectID = inputKey+"."+button_configKey+"."+button_key;
		this.position = config.getInt("ObjectList."+inputKey+".Position");
		this.x = config.getInt("ObjectList."+inputKey+".X");
		this.y = config.getInt("ObjectList."+inputKey+".Y");

		setCommon(player, object_config, button_key);
	}

	//PullPanel
	public MenuButtonJson(Player player, FileConfiguration config, FileConfiguration object_config, String key, String button_configKey, String button_key, String pullKey){
		this.objectID = key+"."+button_configKey+"."+button_key;
		this.position = config.getInt(pullKey+".ObjectList."+key+".Position");
		this.x = config.getInt(pullKey+".ObjectList."+key+".X");
		this.y = config.getInt(pullKey+".ObjectList."+key+".Y");

		setCommon(player, object_config, button_key);
	}

	//共通設置
	public void setCommon(Player player, FileConfiguration object_config, String button_key){
		this.displayFont = object_config.getString(button_key+".DisplayFont");
		this.fontSize = (float) object_config.getDouble(button_key+".FontSize");
		this.onImage = object_config.getString(button_key+".OnImage");
		this.onWidth = object_config.getInt(button_key+".OnWidth");
		this.onHeight = object_config.getInt(button_key+".OnHeight");
		this.offImage = object_config.getString(button_key+".OffImage");
		this.offWidth = object_config.getInt(button_key+".OffWidth");
		this.offHeight = object_config.getInt(button_key+".OffHeight");

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
							objectMap.put(type+key, JsonCtrl.writeJSON(clickButtonJson));
							continue;
						}
						if(type.equalsIgnoreCase("TextField")){
							TextFieldJson textFieldJson = new TextFieldJson(player, object_config, pull_config, key, keys[0], keys[1], button_key);
							objectMap.put(type+key, JsonCtrl.writeJSON(textFieldJson));
							continue;
						}
						if(type.equalsIgnoreCase("Image")){
							ImageShowJson imageShowJson = new ImageShowJson(player, object_config, pull_config, key, keys[0], keys[1], button_key);
							objectMap.put(type+key, JsonCtrl.writeJSON(imageShowJson));
							continue;
						}
						if(type.equalsIgnoreCase("Text")){
							TextLabelJson textLabelJson = new TextLabelJson(player, object_config, pull_config, key, keys[0], keys[1], button_key);
							objectMap.put(type+key, JsonCtrl.writeJSON(textLabelJson));
							continue;
						}
						if(type.equalsIgnoreCase("Item")){
							ItemShowJson itemShowJson = new ItemShowJson(player, object_config, pull_config, key, keys[0], keys[1], button_key);
							objectMap.put(type+key, JsonCtrl.writeJSON(itemShowJson));

						}
					}
				}
			}
		}

	}

	//把字串轉成ClickButton
	public static MenuButtonJson readJSON(String string) {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		return gson.fromJson(string, MenuButtonJson.class);
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

	public String getDisplayFont() {
		return displayFont;
	}

	public void setDisplayFont(String displayFont) {
		this.displayFont = displayFont;
	}

	public float getFontSize() {
		return fontSize;
	}

	public void setFontSize(float fontSize) {
		this.fontSize = fontSize;
	}

	public String getOnImage() {
		return onImage;
	}

	public void setOnImage(String onImage) {
		this.onImage = onImage;
	}

	public int getOnWidth() {
		return onWidth;
	}

	public void setOnWidth(int onWidth) {
		this.onWidth = onWidth;
	}

	public int getOnHeight() {
		return onHeight;
	}

	public void setOnHeight(int onHeight) {
		this.onHeight = onHeight;
	}

	public String getOffImage() {
		return offImage;
	}

	public void setOffImage(String offImage) {
		this.offImage = offImage;
	}

	public int getOffWidth() {
		return offWidth;
	}

	public void setOffWidth(int offWidth) {
		this.offWidth = offWidth;
	}

	public int getOffHeight() {
		return offHeight;
	}

	public void setOffHeight(int offHeight) {
		this.offHeight = offHeight;
	}

	public Map<String, String> getObjectMap() {
		return objectMap;
	}

	public void setObjectMap(Map<String, String> objectMap) {
		this.objectMap = objectMap;
	}

}
