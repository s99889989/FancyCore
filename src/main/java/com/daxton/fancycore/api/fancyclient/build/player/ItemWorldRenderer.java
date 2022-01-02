package com.daxton.fancycore.api.fancyclient.build.player;

import com.daxton.fancycore.api.fancyclient.json.player.item.ItemEntityJson;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemWorldRenderer implements RendererComponent{

	//物件名稱
	private String objectName;
	//物品
	private ItemStack itemStack;
	//物品比例
	private float scale;
	//位置高度
	private double locationHeight;
	//位置選轉角度
	private double locationAngle;
	//位置選轉角度距離
	private double locationDistance;
	//旋轉角度
	//旋轉角度
	private float rotateX;
	private float rotateY;
	private float rotateZ;
	//自動旋轉角度
	private int autoRotationX;
	private int autoRotationY;
	private int autoRotationZ;

	private ItemWorldRenderer(String objectName, ItemStack itemStack, float scale, double locationHeight, double locationAngle, double locationDistance, float rotateX, float rotateY, float rotateZ, int autoRotationX, int autoRotationY, int autoRotationZ) {
		this.objectName = objectName;
		this.itemStack = itemStack;
		this.scale = scale;
		this.locationHeight = locationHeight;
		this.locationAngle = locationAngle;
		this.locationDistance = locationDistance;
		this.rotateX = rotateX;
		this.rotateY = rotateY;
		this.rotateZ = rotateZ;
		this.autoRotationX = autoRotationX;
		this.autoRotationY = autoRotationY;
		this.autoRotationZ = autoRotationZ;
	}

	public ItemEntityJson toObject(Player player){
		return new ItemEntityJson(player, objectName, itemStack, scale, locationHeight, locationAngle, locationDistance, rotateX, rotateY, rotateZ, autoRotationX, autoRotationY, autoRotationZ);
	}

	//---------------------------------------------------------------------------------------------------//

	public static class Builder {

		//物件名稱
		private String objectID = String.valueOf((int)(Math.random()*100000));
		//物品NBT字串
		private ItemStack itemStack = new ItemStack(Material.STONE);
		//物品比例
		private float scale;
		//位置高度
		private double locationHeight;
		//位置選轉角度
		private double locationAngle;
		//位置選轉角度距離
		private double locationDistance;
		//旋轉角度
		//旋轉角度
		private float rotateX;
		private float rotateY;
		private float rotateZ;
		//自動旋轉角度
		private int autoRotationX;
		private int autoRotationY;
		private int autoRotationZ;

		public static Builder getInstance(){
			return new Builder();
		}

		public Builder setObjectID(String objectID) {
			this.objectID = objectID;
			return this;
		}

		public Builder setItemStack(ItemStack itemStack) {
			this.itemStack = itemStack;
			return this;
		}

		public Builder setScale(float scale) {
			this.scale = scale;
			return this;
		}

		public Builder setLocationHeight(double locationHeight) {
			this.locationHeight = locationHeight;
			return this;
		}

		public Builder setLocationAngle(double locationAngle) {
			this.locationAngle = locationAngle;
			return this;
		}

		public Builder setLocationDistance(double locationDistance) {
			this.locationDistance = locationDistance;
			return this;
		}

		public Builder setRotateX(float rotateX) {
			this.rotateX = rotateX;
			return this;
		}

		public Builder setRotateY(float rotateY) {
			this.rotateY = rotateY;
			return this;
		}

		public Builder setRotateZ(float rotateZ) {
			this.rotateZ = rotateZ;
			return this;
		}

		public Builder setAutoRotationX(int autoRotationX) {
			this.autoRotationX = autoRotationX;
			return this;
		}

		public Builder setAutoRotationY(int autoRotationY) {
			this.autoRotationY = autoRotationY;
			return this;
		}

		public Builder setAutoRotationZ(int autoRotationZ) {
			this.autoRotationZ = autoRotationZ;
			return this;
		}

		public ItemWorldRenderer build(){
			return new ItemWorldRenderer(objectID, itemStack, scale, locationHeight, locationAngle, locationDistance, rotateX, rotateY, rotateZ, autoRotationX, autoRotationY, autoRotationZ);
		}

	}

}
