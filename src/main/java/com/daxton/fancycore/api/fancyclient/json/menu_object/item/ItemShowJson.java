package com.daxton.fancycore.api.fancyclient.json.menu_object.item;

import com.daxton.fancycore.api.character.conversion.StringConversion;
import com.daxton.fancycore.api.item.CItem;
import com.daxton.fancycore.nms.NMSItem;
import com.daxton.fancyitmes.item.CustomItem;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemShowJson {

	//類型
	public String type = "Item";
	//物件名稱
	private String object_name = "";
	//物品NBT字串
	private String item = "";
	//物品比例
	private float scale;
	//基礎位置
	private int position;
	//X偏移
	private int x;
	//Y偏移
	private int y;

	public ItemShowJson(String object_name, ItemStack itemStack, float scale, int position, int x, int y) {
		this.object_name = object_name;
		this.item = NMSItem.itemNBTtoString(itemStack);
		this.scale = scale;
		this.position = position;
		this.x = x;
		this.y = y;
	}

	public ItemShowJson(Player player, String object_name, ItemStack itemStack, float scale, int position, int x, int y) {
		this.object_name = object_name;
		this.item = NMSItem.itemNBTtoStringClient(itemStack, player);
		this.scale = scale;
		this.position = position;
		this.x = x;
		this.y = y;
	}
	//GUI
	public ItemShowJson(Player player, FileConfiguration config, FileConfiguration object_config, String key, String button_configKey, String button_key){
		this.object_name = key+"."+button_configKey+"."+button_key;
		this.position = config.getInt("ObjectList."+key+".Position");
		this.x = config.getInt("ObjectList."+key+".X");
		this.y = config.getInt("ObjectList."+key+".Y");

		setCommon(player, object_config, button_key);
	}
	//PullPanel
	public ItemShowJson(Player player, FileConfiguration config, FileConfiguration object_config, String key, String button_configKey, String button_key, String pullKey){
		this.object_name = key+"."+button_configKey+"."+button_key;
		this.position = config.getInt(pullKey+".ObjectList."+key+".Position");
		this.x = config.getInt(pullKey+".ObjectList."+key+".X");
		this.y = config.getInt(pullKey+".ObjectList."+key+".Y");

		setCommon(player, object_config, button_key);
	}
	//共通設置
	public void setCommon(Player player, FileConfiguration object_config, String button_key){
		this.scale = (float) object_config.getDouble(button_key+".Scale");

		ItemStack itemStack = new ItemStack(Material.STONE);

		String itemID = object_config.getString(button_key+".Item");
		if(itemID != null){
			String[] itemArray = itemID.split("\\.");
			if(itemArray.length == 1){
				CItem cItem = new CItem(itemArray[0]);
				itemStack = cItem.getItemStack();
			}
			if(itemArray.length == 2){
				if(Bukkit.getPluginManager().isPluginEnabled("FancyItems")){
					itemStack = CustomItem.valueOf(player, itemArray[0], itemArray[1], 1);
				}
			}
		}
		this.item = NMSItem.itemNBTtoStringClient(itemStack, player);
	}
	//把字串轉成Item
	public static ItemShowJson readJSON(String string) {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		return gson.fromJson(string, ItemShowJson.class);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getObject_name() {
		return object_name;
	}

	public void setObject_name(String object_name) {
		this.object_name = object_name;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
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

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}
}
