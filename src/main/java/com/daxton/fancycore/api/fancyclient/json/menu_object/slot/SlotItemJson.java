package com.daxton.fancycore.api.fancyclient.json.menu_object.slot;

import com.daxton.fancycore.FancyCore;
import com.daxton.fancycore.manager.PlayerManagerCore;
import com.daxton.fancycore.nms.NMSItem;
import com.daxton.fancycore.other.playerdata.PlayerDataFancy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SlotItemJson {

	//類型
	public String type = "SlotItem";
	//物件名稱
	private String objectID = "";
	//基礎位置
	private int position;
	//X偏移
	private int x;
	//Y偏移
	private int y;
	//物品曹底圖
	private String image = "";
	//寬度
	private int objectWidth;
	//高度
	private int objectHeight;
	//物品的縮放
	private float itemScale;
	//slotID
	private int slotID;
	//是否為自訂物品欄
	private boolean custom;
	//物品堆疊數量
	private int stackAmount;
	//自訂物品欄上面的物品Json
	private String itemJson = "";

	public SlotItemJson(String objectID, int position, int x, int y, String image, int objectWidth, int objectHeight, float itemScale, int slotID, boolean custom, int stackAmount, String itemJson) {
		this.objectID = objectID;
		this.position = position;
		this.x = x;
		this.y = y;
		this.image = image;
		this.objectWidth = objectWidth;
		this.objectHeight = objectHeight;
		this.itemScale = itemScale;
		this.slotID = slotID;
		this.custom = custom;
		this.stackAmount = stackAmount;
		this.itemJson = itemJson;
	}
	//GUI
	public SlotItemJson(Player player, FileConfiguration config, FileConfiguration object_config, String key, String button_configKey, String button_key){
		this.objectID = key+"."+button_configKey+"."+button_key;
		this.position = config.getInt("ObjectList."+key+".Position");
		this.x = config.getInt("ObjectList."+key+".X");
		this.y = config.getInt("ObjectList."+key+".Y");

		setCommon(player, object_config, button_key);

	}
	//PullPanel
	public SlotItemJson(Player player, FileConfiguration config, FileConfiguration object_config, String key, String button_configKey, String button_key, String pullKey){
		this.objectID = key+"."+button_configKey+"."+button_key;
		this.position = config.getInt(pullKey+".ObjectList."+key+".Position");
		this.x = config.getInt(pullKey+".ObjectList."+key+".X");
		this.y = config.getInt(pullKey+".ObjectList."+key+".Y");

		setCommon(player, object_config, button_key);

	}
	//共通設置
	public void setCommon(Player player, FileConfiguration object_config, String button_key){
		this.image = object_config.getString(button_key+".Image");
		this.objectWidth = object_config.getInt(button_key+".Width");
		this.objectHeight = object_config.getInt(button_key+".Height");
		this.itemScale = (float) object_config.getDouble(button_key+".Scale");
		this.custom = object_config.getBoolean(button_key+".Custom");

		if(this.custom){
			this.stackAmount = object_config.getInt(button_key+".StackAmount");
			PlayerDataFancy playerDataFancy = PlayerManagerCore.player_Data_Map.get(player.getUniqueId());
			ItemStack itemStack = playerDataFancy.itemStackMap.get(button_key);
			if(itemStack == null){
				itemStack = new ItemStack(Material.AIR);
				playerDataFancy.itemStackMap.put(objectID, itemStack);
			}
			this.itemJson = NMSItem.itemNBTtoStringClient(itemStack, player);
		}else {
			this.stackAmount = 64;
			this.slotID = object_config.getInt(button_key+".SlotID");
		}
	}
	//把字串轉成ClickButton
	public static SlotItemJson readJSON(String string) {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		return gson.fromJson(string, SlotItemJson.class);
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}


	public int getObjectWidth() {
		return objectWidth;
	}

	public void setObjectWidth(int objectWidth) {
		this.objectWidth = objectWidth;
	}

	public int getObjectHeight() {
		return objectHeight;
	}

	public void setObjectHeight(int objectHeight) {
		this.objectHeight = objectHeight;
	}

	public int getSlotID() {
		return slotID;
	}

	public void setSlotID(int slotID) {
		this.slotID = slotID;
	}

	public float getItemScale() {
		return itemScale;
	}

	public void setItemScale(float itemScale) {
		this.itemScale = itemScale;
	}

	public boolean isCustom() {
		return custom;
	}

	public void setCustom(boolean custom) {
		this.custom = custom;
	}

	public int getStackAmount() {
		return stackAmount;
	}

	public void setStackAmount(int stackAmount) {
		this.stackAmount = stackAmount;
	}

	public String getItemJson() {
		return itemJson;
	}

	public void setItemJson(String itemJson) {
		this.itemJson = itemJson;
	}

}
