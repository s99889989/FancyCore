package com.daxton.fancycore.api.fancyclient.build.module;


import com.daxton.fancycore.api.fancyclient.json.menu_object.image.ImageShowJson;
import org.bukkit.entity.LivingEntity;

public class ImageModule implements ModComponent {

	//物件名稱
	private String object_name;
	//圖片位址
	private String image;
	//寬度
	private String width;
	//高度
	private String height;
	//基礎位置
	private int position;
	//X偏移
	private int x;
	//Y偏移
	private int y;

	private ImageModule(String object_name, String image, String width, String height, int position, int x, int y) {
		this.object_name = object_name;
		this.image = image;
		this.width = width;
		this.height = height;
		this.position = position;
		this.x = x;
		this.y = y;
	}

	public ImageShowJson toObject(){
		return new ImageShowJson(object_name, image, width, height, position, x, y);
	}

	public ImageShowJson toObject(LivingEntity self, LivingEntity target){
		return new ImageShowJson(self, target, object_name, image, width, height, position, x, y);
	}

	//---------------------------------------------------------------------------------------------------//

	public static class Builder {

		//物件名稱
		private String objectID = String.valueOf((int)(Math.random()*100000));
		//圖片位址
		private String image = "";
		//寬度
		private String width = "-1";
		//高度
		private String height = "-1";
		//基礎位置
		private int position;
		//X偏移
		private int x;
		//Y偏移
		private int y;

		public static Builder getInstance(){
			return new Builder();
		}

		public Builder setObjectID(String objectID) {
			this.objectID = objectID;
			return this;
		}

		public Builder setImage(String image) {
			this.image = image;
			return this;
		}

		public Builder setWidth(String width) {
			this.width = width;
			return this;
		}

		public Builder setHeight(String height) {
			this.height = height;
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

		public ImageModule build(){
			return new ImageModule(objectID, image, width, height, position, x, y);
		}

	}

}
