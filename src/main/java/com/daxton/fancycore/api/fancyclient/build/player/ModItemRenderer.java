package com.daxton.fancycore.api.fancyclient.build.player;

import com.daxton.fancycore.api.fancyclient.json.player.item.ItemEntityJson;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ModItemRenderer implements RendererComponent{

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

	private ModItemRenderer(String objectName, ItemStack itemStack, float scale, double locationHeight, double locationAngle, double locationDistance, float rotateX, float rotateY, float rotateZ, int autoRotationX, int autoRotationY, int autoRotationZ) {
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

	public static class ModItemRendererBuilder{

		//物件名稱
		private String objectName = String.valueOf((int)(Math.random()*100000));
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

		public static ModItemRendererBuilder getInstance(){
			return new ModItemRendererBuilder();
		}

		public ModItemRendererBuilder setObjectName(String objectName) {
			this.objectName = objectName;
			return this;
		}

		public ModItemRendererBuilder setItemStack(ItemStack itemStack) {
			this.itemStack = itemStack;
			return this;
		}

		public ModItemRendererBuilder setScale(float scale) {
			this.scale = scale;
			return this;
		}

		public ModItemRendererBuilder setLocationHeight(double locationHeight) {
			this.locationHeight = locationHeight;
			return this;
		}

		public ModItemRendererBuilder setLocationAngle(double locationAngle) {
			this.locationAngle = locationAngle;
			return this;
		}

		public ModItemRendererBuilder setLocationDistance(double locationDistance) {
			this.locationDistance = locationDistance;
			return this;
		}

		public ModItemRendererBuilder setRotateX(float rotateX) {
			this.rotateX = rotateX;
			return this;
		}

		public ModItemRendererBuilder setRotateY(float rotateY) {
			this.rotateY = rotateY;
			return this;
		}

		public ModItemRendererBuilder setRotateZ(float rotateZ) {
			this.rotateZ = rotateZ;
			return this;
		}

		public ModItemRendererBuilder setAutoRotationX(int autoRotationX) {
			this.autoRotationX = autoRotationX;
			return this;
		}

		public ModItemRendererBuilder setAutoRotationY(int autoRotationY) {
			this.autoRotationY = autoRotationY;
			return this;
		}

		public ModItemRendererBuilder setAutoRotationZ(int autoRotationZ) {
			this.autoRotationZ = autoRotationZ;
			return this;
		}

		public ModItemRenderer build(){
			return new ModItemRenderer(objectName, itemStack, scale, locationHeight, locationAngle, locationDistance, rotateX, rotateY, rotateZ, autoRotationX, autoRotationY, autoRotationZ);
		}

	}

}
