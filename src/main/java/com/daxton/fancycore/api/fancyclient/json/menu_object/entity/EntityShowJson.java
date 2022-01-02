package com.daxton.fancycore.api.fancyclient.json.menu_object.entity;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class EntityShowJson {

	//類型
	public String type = "Entity";
	//物件名稱
	private String objectID;
	//基礎位置
	private int position;
	//X偏移
	private int x;
	//Y偏移
	private int y;
	//大小
	private float size;
	//模型調整方式(0=跟隨滑鼠,1=固定,2=玩家手動調整)
	private int modelAdjustment;
	//頭轉向
	private float headYaw;
	//頭仰角
	private float headPitch;
	//身體轉向
	private float bodyYaw;

	//GUI
	public EntityShowJson(Player player, FileConfiguration config, FileConfiguration object_config, String key, String button_configKey, String button_key){
		this.objectID = key+"."+button_configKey+"."+button_key;
		this.position = config.getInt("ObjectList."+key+".Position");
		this.x = config.getInt("ObjectList."+key+".X");
		this.y = config.getInt("ObjectList."+key+".Y");

		setCommon(player, object_config, button_key);
	}

	//PullPanel
	public EntityShowJson(Player player, FileConfiguration config, FileConfiguration object_config, String key, String button_configKey, String button_key, String pullKey){
		this.objectID = key+"."+button_configKey+"."+button_key;
		this.position = config.getInt(pullKey+".ObjectList."+key+".Position");
		this.x = config.getInt(pullKey+".ObjectList."+key+".X");
		this.y = config.getInt(pullKey+".ObjectList."+key+".Y");

		setCommon(player, object_config, button_key);
	}
	//共通設置
	public void setCommon(Player player, FileConfiguration object_config, String button_key){
		this.size = (float) object_config.getDouble(button_key+".Size");
		this.modelAdjustment = object_config.getInt(button_key+".ModelAdjustment");
		this.headYaw = (float) object_config.getDouble(button_key+".HeadYaw");
		this.headPitch = (float) object_config.getDouble(button_key+".HeadPitch");
		this.bodyYaw = (float) object_config.getDouble(button_key+".BodyYaw");
	}

	//把字串轉成ClickButton
	public static EntityShowJson readJSON(String string) {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		return gson.fromJson(string, EntityShowJson.class);
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

	public float getSize() {
		return size;
	}

	public void setSize(float size) {
		this.size = size;
	}

	public int getModelAdjustment() {
		return modelAdjustment;
	}

	public void setModelAdjustment(int modelAdjustment) {
		this.modelAdjustment = modelAdjustment;
	}

	public float getHeadYaw() {
		return headYaw;
	}

	public void setHeadYaw(float headYaw) {
		this.headYaw = headYaw;
	}

	public float getHeadPitch() {
		return headPitch;
	}

	public void setHeadPitch(float headPitch) {
		this.headPitch = headPitch;
	}

	public float getBodyYaw() {
		return bodyYaw;
	}

	public void setBodyYaw(float bodyYaw) {
		this.bodyYaw = bodyYaw;
	}
}
