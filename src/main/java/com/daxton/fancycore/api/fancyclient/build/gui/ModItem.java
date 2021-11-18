package com.daxton.fancycore.api.fancyclient.build.gui;

import com.daxton.fancycore.api.fancyclient.json.menu_object.item.ItemShowJson;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ModItem implements ModComponent{

	//玩家
	private Player player;
	//物件名稱
	private String object_name;
	//物品
	private ItemStack itemStack;
	//物品比例
	private float scale;
	//基礎位置
	private int position;
	//X偏移
	private int x;
	//Y偏移
	private int y;

	private ModItem(Player player, String object_name, ItemStack itemStack, float scale, int position, int x, int y) {
		this.player = player;
		this.object_name = object_name;
		this.itemStack = itemStack;
		this.scale = scale;
		this.position = position;
		this.x = x;
		this.y = y;
		if(itemStack == null){
			this.itemStack = new ItemStack(Material.STONE);
		}
	}

	public ItemShowJson toObject(){
		if(player == null){
			return new ItemShowJson(object_name, itemStack, scale, position, x, y);
		}
		return new ItemShowJson(player, object_name, itemStack, scale, position, x, y);
	}

	//---------------------------------------------------------------------------------------------------//

	public static class ModItemBuilder{

		//玩家
		private Player player;
		//物件名稱
		private String object_name = String.valueOf((int)(Math.random()*100000));
		//物品
		private ItemStack itemStack = new ItemStack(Material.STONE);
		//物品比例
		private float scale = 1F;
		//基礎位置
		private int position;
		//X偏移
		private int x;
		//Y偏移
		private int y;

		public static ModItemBuilder getInstance(){
			return new ModItemBuilder();
		}

		public ModItemBuilder setPlayer(Player player) {
			this.player = player;
			return this;
		}

		public ModItemBuilder setObjectName(String object_name) {
			this.object_name = object_name;
			return this;
		}

		public ModItemBuilder setScale(float scale) {
			this.scale = scale;
			return this;
		}

		public ModItemBuilder setItemStack(ItemStack itemStack) {
			this.itemStack = itemStack;
			return this;
		}

		public ModItemBuilder setPosition(int position) {
			this.position = position;
			return this;
		}

		public ModItemBuilder setX(int x) {
			this.x = x;
			return this;
		}

		public ModItemBuilder setY(int y) {
			this.y = y;
			return this;
		}

		public ModItem build(){
			return new ModItem(player, object_name, itemStack, scale, position, x, y);
		}

	}

}
