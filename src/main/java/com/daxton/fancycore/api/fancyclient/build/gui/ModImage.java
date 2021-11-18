package com.daxton.fancycore.api.fancyclient.build.gui;


import com.daxton.fancycore.api.fancyclient.json.menu_object.image.ImageShowJson;
import org.bukkit.entity.LivingEntity;

public class ModImage implements ModComponent{

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

	private ModImage(String object_name, String image, String width, String height, int position, int x, int y) {
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

	public static class ModImageBuilder{

		//物件名稱
		private String object_name = String.valueOf((int)(Math.random()*100000));
		//圖片位址
		private String image = "";
		//寬度
		private String width = "0";
		//高度
		private String height = "0";
		//基礎位置
		private int position;
		//X偏移
		private int x;
		//Y偏移
		private int y;

		public static ModImageBuilder getInstance(){
			return new ModImageBuilder();
		}

		public ModImageBuilder setObject_name(String object_name) {
			this.object_name = object_name;
			return this;
		}

		public ModImageBuilder setImage(String image) {
			this.image = image;
			return this;
		}

		public ModImageBuilder setWidth(String width) {
			this.width = width;
			return this;
		}

		public ModImageBuilder setHeight(String height) {
			this.height = height;
			return this;
		}

		public ModImageBuilder setPosition(int position) {
			this.position = position;
			return this;
		}

		public ModImageBuilder setX(int x) {
			this.x = x;
			return this;
		}

		public ModImageBuilder setY(int y) {
			this.y = y;
			return this;
		}

		public ModImage build(){
			return new ModImage(object_name, image, width, height, position, x, y);
		}

	}

}
