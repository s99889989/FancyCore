package com.daxton.fancycore.api.fancyclient.build.player;


import com.daxton.fancycore.api.fancyclient.json.player.image.ImageEntityJson;
import org.bukkit.entity.LivingEntity;

public class ModImageRenderer implements RendererComponent{

	//物件名稱
	private String objectName = "";
	//圖片位址
	private String image = "";
	//縮放
	private float scale;
	//圖片寬度
	private float width;
	//圖片高度
	private float height;
	//位置高度
	private double locationHeight;
	//位置選轉角度
	private double locationAngle;
	//位置選轉角度距離
	private double locationDistance;
	//方向(1~4)
	private int direction;
	//旋轉角度
	private float rotateX;
	private float rotateY;
	private float rotateZ;
	//自動旋轉角度
	private int autoRotationX;
	private int autoRotationY;
	private int autoRotationZ;

	public ModImageRenderer(String objectName, String image, float scale, float width, float height, double locationHeight, double locationAngle, double locationDistance, int direction, float rotateX, float rotateY, float rotateZ, int autoRotationX, int autoRotationY, int autoRotationZ) {
		this.objectName = objectName;
		this.image = image;
		this.scale = scale;
		this.width = width;
		this.height = height;
		this.locationHeight = locationHeight;
		this.locationAngle = locationAngle;
		this.locationDistance = locationDistance;
		this.direction = direction;
		this.rotateX = rotateX;
		this.rotateY = rotateY;
		this.rotateZ = rotateZ;
		this.autoRotationX = autoRotationX;
		this.autoRotationY = autoRotationY;
		this.autoRotationZ = autoRotationZ;
	}

	public ImageEntityJson toObject(LivingEntity self, LivingEntity target){
		return new ImageEntityJson(self, target,objectName, image, scale, width, height, locationHeight, locationAngle, locationDistance, direction, rotateX, rotateY, rotateZ, autoRotationX, autoRotationY, autoRotationZ);
	}

	//---------------------------------------------------------------------------------------------------//

	public static class ModImageRendererBuilder{

		//物件名稱
		private String objectName = String.valueOf((int)(Math.random()*100000));
		//圖片位址
		private String image = "https://i.imgur.com/5B9nsam.png";
		//縮放
		private float scale = 1;
		//圖片寬度
		private float width = 1;
		//圖片高度
		private float height = 1;
		//位置高度
		private double locationHeight;
		//位置選轉角度
		private double locationAngle;
		//位置選轉角度距離
		private double locationDistance;
		//方向(1~4)
		private int direction;
		//旋轉角度
		private float rotateX;
		private float rotateY;
		private float rotateZ;
		//自動旋轉角度
		private int autoRotationX;
		private int autoRotationY;
		private int autoRotationZ;

		public static ModImageRendererBuilder getInstance(){
			return new ModImageRendererBuilder();
		}

		public ModImageRendererBuilder setObjectName(String objectName) {
			this.objectName = objectName;
			return this;
		}

		public ModImageRendererBuilder setImage(String image) {
			this.image = image;
			return this;
		}

		public ModImageRendererBuilder setScale(float scale) {
			this.scale = scale;
			return this;
		}

		public ModImageRendererBuilder setWidth(float width) {
			this.width = width;
			return this;
		}

		public ModImageRendererBuilder setHeight(float height) {
			this.height = height;
			return this;
		}

		public ModImageRendererBuilder setLocationHeight(double locationHeight) {
			this.locationHeight = locationHeight;
			return this;
		}

		public ModImageRendererBuilder setLocationAngle(double locationAngle) {
			this.locationAngle = locationAngle;
			return this;
		}

		public ModImageRendererBuilder setLocationDistance(double locationDistance) {
			this.locationDistance = locationDistance;
			return this;
		}

		public ModImageRendererBuilder setDirection(int direction) {
			this.direction = direction;
			return this;
		}

		public ModImageRendererBuilder setRotateX(float rotateX) {
			this.rotateX = rotateX;
			return this;
		}

		public ModImageRendererBuilder setRotateY(float rotateY) {
			this.rotateY = rotateY;
			return this;
		}

		public ModImageRendererBuilder setRotateZ(float rotateZ) {
			this.rotateZ = rotateZ;
			return this;
		}

		public ModImageRendererBuilder setAutoRotationX(int autoRotationX) {
			this.autoRotationX = autoRotationX;
			return this;
		}

		public ModImageRendererBuilder setAutoRotationY(int autoRotationY) {
			this.autoRotationY = autoRotationY;
			return this;
		}

		public ModImageRendererBuilder setAutoRotationZ(int autoRotationZ) {
			this.autoRotationZ = autoRotationZ;
			return this;
		}

		public ModImageRenderer build(){
			return new ModImageRenderer(objectName, image, scale, width, height, locationHeight, locationAngle, locationDistance, direction, rotateX, rotateY, rotateZ, autoRotationX, autoRotationY, autoRotationZ);
		}


	}

}
