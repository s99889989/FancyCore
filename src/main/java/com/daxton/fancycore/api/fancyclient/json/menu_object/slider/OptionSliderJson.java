package com.daxton.fancycore.api.fancyclient.json.menu_object.slider;

import com.daxton.fancycore.manager.PlayerManagerCore;
import com.daxton.fancycore.other.playerdata.PlayerDataFancy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class OptionSliderJson {

	//類型
	public String type = "OptionSlider";
	//物件名稱
	private String objectID;
	//基礎位置
	private int position;
	//X偏移
	private int x;
	//Y偏移
	private int y;

	//背景
	private String backImage;
	private int backWidth;
	private int backHeight;

	//拉條
	private String pullImage;
	private int pullWidth;
	private int pullHeight;

	//顯示文字大小
	private float fontSize;
	//目前選擇的選項
	private String selectString;
	//選項
	private List<String> optionList;

	//GUI
	public OptionSliderJson(Player player, FileConfiguration config, FileConfiguration object_config, String key, String button_configKey, String button_key){
		this.objectID = key+"."+button_configKey+"."+button_key;
		this.position = config.getInt("ObjectList."+key+".Position");
		this.x = config.getInt("ObjectList."+key+".X");
		this.y = config.getInt("ObjectList."+key+".Y");

		setCommon(player, object_config, button_configKey, button_key);
	}

	//PullPanel
	public OptionSliderJson(Player player, FileConfiguration config, FileConfiguration object_config, String key, String button_configKey, String button_key, String pullKey){
		this.objectID = key+"."+button_configKey+"."+button_key;
		this.position = config.getInt(pullKey+".ObjectList."+key+".Position");
		this.x = config.getInt(pullKey+".ObjectList."+key+".X");
		this.y = config.getInt(pullKey+".ObjectList."+key+".Y");

		setCommon(player, object_config, button_configKey, button_key);
	}
	//共通設置
	public void setCommon(Player player, FileConfiguration object_config, String button_configKey, String button_key){


		this.backImage =  object_config.getString(button_key+".BackImage");
		this.backWidth =  object_config.getInt(button_key+".BackWidth");
		this.backHeight =  object_config.getInt(button_key+".BackHeight");
		this.pullImage =  object_config.getString(button_key+".PullImage");
		this.pullWidth =  object_config.getInt(button_key+".PullWidth");
		this.pullHeight =  object_config.getInt(button_key+".PullHeight");

		this.fontSize = (float) object_config.getDouble(button_key+".FontSize");
		this.optionList =  object_config.getStringList(button_key+".OptionList");
		if(!optionList.isEmpty()){
			this.selectString = this.optionList.get(0);
		}
		UUID uuid = player.getUniqueId();
		PlayerDataFancy playerDataFancy = PlayerManagerCore.player_Data_Map.get(uuid);
		if(playerDataFancy != null){
			String value = playerDataFancy.getGuiValueFile(button_configKey+"_"+button_key);
			if(!value.isEmpty()){
				this.selectString = value;
			}
		}
	}

	//把字串轉成ClickButton
	public static OptionSliderJson readJSON(String string) {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		return gson.fromJson(string, OptionSliderJson.class);
	}

	public String getSelectString() {
		return selectString;
	}

	public void setSelectString(String selectString) {
		this.selectString = selectString;
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

	public String getBackImage() {
		return backImage;
	}

	public void setBackImage(String backImage) {
		this.backImage = backImage;
	}

	public int getBackWidth() {
		return backWidth;
	}

	public void setBackWidth(int backWidth) {
		this.backWidth = backWidth;
	}

	public int getBackHeight() {
		return backHeight;
	}

	public void setBackHeight(int backHeight) {
		this.backHeight = backHeight;
	}

	public String getPullImage() {
		return pullImage;
	}

	public void setPullImage(String pullImage) {
		this.pullImage = pullImage;
	}

	public int getPullWidth() {
		return pullWidth;
	}

	public void setPullWidth(int pullWidth) {
		this.pullWidth = pullWidth;
	}

	public int getPullHeight() {
		return pullHeight;
	}

	public void setPullHeight(int pullHeight) {
		this.pullHeight = pullHeight;
	}

	public float getFontSize() {
		return fontSize;
	}

	public void setFontSize(float fontSize) {
		this.fontSize = fontSize;
	}

	public List<String> getOptionList() {
		return optionList;
	}

	public void setOptionList(List<String> optionList) {
		this.optionList = optionList;
	}
}
