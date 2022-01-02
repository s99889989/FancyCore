package com.daxton.fancycore.api.fancyclient.json.menu_object.choice;


import com.daxton.fancycore.manager.PlayerManagerCore;
import com.daxton.fancycore.other.playerdata.PlayerDataFancy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class ChoiceBoxJson  {

	//類型
	public String type = "Button";
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
	//背景
	private String onBackImage;
	private int onBackWidth;
	private int onBackHeight;
	private String offBackImage;
	private int offBackWidth;
	private int offBackHeight;
	//方向
	private int direction;
	//間距
	private int space;
	//選項
	private List<String> optionList;

	//GUI
	public ChoiceBoxJson(Player player, FileConfiguration config, FileConfiguration object_config, String key, String button_configKey, String button_key){
		this.objectID = key+"."+button_configKey+"."+button_key;
		this.position = config.getInt("ObjectList."+key+".Position");
		this.x = config.getInt("ObjectList."+key+".X");
		this.y = config.getInt("ObjectList."+key+".Y");

		setCommon(player, object_config, button_configKey, button_key);
	}

	//PullPanel
	public ChoiceBoxJson(Player player, FileConfiguration config, FileConfiguration object_config, String key, String button_configKey, String button_key, String pullKey){
		this.objectID = key+"."+button_configKey+"."+button_key;
		this.position = config.getInt(pullKey+".ObjectList."+key+".Position");
		this.x = config.getInt(pullKey+".ObjectList."+key+".X");
		this.y = config.getInt(pullKey+".ObjectList."+key+".Y");

		setCommon(player, object_config, button_configKey, button_key);
	}
	//共通設置
	public void setCommon(Player player, FileConfiguration object_config, String button_configKey, String button_key){
		this.displayFont = object_config.getString(button_key+".DisplayFont");
		UUID uuid = player.getUniqueId();
		PlayerDataFancy playerDataFancy = PlayerManagerCore.player_Data_Map.get(uuid);
		if(playerDataFancy != null){
			String value = playerDataFancy.getGuiValueFile(button_configKey+"_"+button_key);
			if(!value.isEmpty()){
				this.displayFont = value;
			}
		}
		this.fontSize = (float) object_config.getDouble(button_key+".FontSize");
		this.onImage = object_config.getString(button_key+".OnImage");
		this.onWidth = object_config.getInt(button_key+".OnWidth");
		this.onHeight = object_config.getInt(button_key+".OnHeight");
		this.offImage = object_config.getString(button_key+".OffImage");
		this.offWidth = object_config.getInt(button_key+".OffWidth");
		this.offHeight = object_config.getInt(button_key+".OffHeight");
		this.onBackImage = object_config.getString(button_key+".OnBackImage");
		this.onBackWidth = object_config.getInt(button_key+".OnBackWidth");
		this.onBackHeight = object_config.getInt(button_key+".OnBackHeight");
		this.offBackImage = object_config.getString(button_key+".OffBackImage");
		this.offBackWidth= object_config.getInt(button_key+".OffBackWidth");
		this.offBackHeight = object_config.getInt(button_key+".OffBackHeight");
		this.direction = object_config.getInt(button_key+".Direction");
		this.space = object_config.getInt(button_key+".Space");
		this.optionList = object_config.getStringList(button_key+".OptionList");

	}

	//把字串轉成ClickButton
	public static ChoiceBoxJson readJSON(String string) {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		return gson.fromJson(string, ChoiceBoxJson.class);
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

	public String getOnBackImage() {
		return onBackImage;
	}

	public void setOnBackImage(String onBackImage) {
		this.onBackImage = onBackImage;
	}

	public int getOnBackWidth() {
		return onBackWidth;
	}

	public void setOnBackWidth(int onBackWidth) {
		this.onBackWidth = onBackWidth;
	}

	public int getOnBackHeight() {
		return onBackHeight;
	}

	public void setOnBackHeight(int onBackHeight) {
		this.onBackHeight = onBackHeight;
	}

	public String getOffBackImage() {
		return offBackImage;
	}

	public void setOffBackImage(String offBackImage) {
		this.offBackImage = offBackImage;
	}

	public int getOffBackWidth() {
		return offBackWidth;
	}

	public void setOffBackWidth(int offBackWidth) {
		this.offBackWidth = offBackWidth;
	}

	public int getOffBackHeight() {
		return offBackHeight;
	}

	public void setOffBackHeight(int offBackHeight) {
		this.offBackHeight = offBackHeight;
	}

	public int getDirection() {
		if(direction < 0){
			return 1;
		}
		return Math.min(direction, 4);
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getSpace() {
		return space;
	}

	public void setSpace(int space) {
		this.space = space;
	}

	public List<String> getOptionList() {
		return optionList;
	}

	public void setOptionList(List<String> optionList) {
		this.optionList = optionList;
	}


}
