package com.daxton.fancycore.api.fancyclient.build.module;

import com.daxton.fancycore.api.fancyclient.json.menu_object.item.ItemShowJson;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemModule implements ModComponent {

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

	private ItemModule(Player player, String object_name, ItemStack itemStack, float scale, int position, int x, int y) {
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

	public static class Builder {

		//玩家
		private Player player;
		//物件名稱
		private String objectID = String.valueOf((int)(Math.random()*100000));
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

		public static Builder getInstance(){
			return new Builder();
		}

		public Builder setPlayer(Player player) {
			this.player = player;
			return this;
		}

		public Builder setObjectName(String object_name) {
			this.objectID = object_name;
			return this;
		}

		public Builder setScale(float scale) {
			this.scale = scale;
			return this;
		}

		public Builder setItemStack(ItemStack itemStack) {
			this.itemStack = itemStack;
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

		public ItemModule build(){
			return new ItemModule(player, objectID, itemStack, scale, position, x, y);
		}

	}

}
