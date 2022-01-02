package com.daxton.fancycore.api.fancyclient.build.module;

import com.daxton.fancycore.api.fancyclient.json.menu_object.slot.SlotItemJson;
import com.daxton.fancycore.manager.PlayerManagerCore;
import com.daxton.fancycore.nms.NMSItem;
import com.daxton.fancycore.other.playerdata.PlayerDataFancy;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SlotItemModule implements ModComponent{

	//物件名稱
	private String objectID;
	//基礎位置
	private int position;
	//X偏移
	private int x;
	//Y偏移
	private int y;
	//物品曹底圖
	private String image;
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

	private SlotItemModule(String objectID, int position, int x, int y, String image, int objectWidth, int objectHeight, float itemScale, int slotID, boolean custom, int stackAmount) {
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
	}

	public SlotItemJson toObject(LivingEntity self, LivingEntity target){
		PlayerDataFancy playerDataFancy = PlayerManagerCore.player_Data_Map.get(self.getUniqueId());
		ItemStack itemStack = playerDataFancy.itemStackMap.get(objectID);
		if(itemStack == null){
			itemStack = new ItemStack(Material.AIR);
		}
		String itemJson = NMSItem.itemNBTtoStringClient(itemStack, (Player) self);
		return new SlotItemJson(objectID, position, x, y, image, objectWidth, objectHeight, itemScale, slotID, custom, stackAmount, itemJson);
	}

	//---------------------------------------------------------------------------------------------------//

	public static class Builder {

		//物件名稱
		private String objectID = String.valueOf((int)(Math.random()*100000));
		//基礎位置
		private int position = 5;
		//X偏移
		private int x;
		//Y偏移
		private int y;
		//物品曹底圖
		private String image;
		//寬度
		private int objectWidth;
		//高度
		private int objectHeight;
		//物品的縮放
		private float itemScale = 1F;
		//slotID
		private int slotID;
		//是否為自訂物品欄
		private boolean custom;
		//物品堆疊數量
		private int stackAmount;

		public static Builder getInstance(){
			return new Builder();
		}

		public Builder setObjectID(String objectID) {
			this.objectID = objectID;
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

		public Builder setImage(String image) {
			this.image = image;
			return this;
		}

		public Builder setObjectWidth(int objectWidth) {
			this.objectWidth = objectWidth;
			return this;
		}

		public Builder setObjectHeight(int objectHeight) {
			this.objectHeight = objectHeight;
			return this;
		}

		public Builder setItemScale(float itemScale) {
			this.itemScale = itemScale;
			return this;
		}

		public Builder setSlotID(int slotID) {
			this.slotID = slotID;
			return this;
		}

		public Builder setCustom(boolean custom) {
			this.custom = custom;
			return this;
		}

		public Builder setStackAmount(int stackAmount) {
			this.stackAmount = stackAmount;
			return this;
		}

		public SlotItemModule build(){
			return new SlotItemModule(objectID, position, x, y, image, objectWidth, objectHeight, itemScale, slotID, custom, stackAmount);
		}

	}

}
