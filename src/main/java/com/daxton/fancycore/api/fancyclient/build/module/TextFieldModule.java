package com.daxton.fancycore.api.fancyclient.build.module;

import com.daxton.fancycore.api.fancyclient.json.menu_object.textfield.TextFieldJson;
import org.bukkit.entity.LivingEntity;

public class TextFieldModule implements ModComponent {

	//物件名稱
	private String object_name;
	//圖片位址
	private String image;
	//寬度
	private int width;
	//高度
	private int height;
	//基礎位置
	private int position;
	//X偏移
	private int x;
	//Y偏移
	private int y;
	//輸入內容
	private String text = "";

	private TextFieldModule(String object_name, String image, int width, int height, int position, int x, int y) {
		this.object_name = object_name;
		this.image = image;
		this.width = width;
		this.height = height;
		this.position = position;
		this.x = x;
		this.y = y;
	}

	public String getText() {
		return text;
	}

	public TextFieldJson toObject(){
		return new TextFieldJson(object_name, image, width, height, position, x, y);
	}

	public TextFieldJson toObject(LivingEntity self, LivingEntity target){
		return new TextFieldJson(self, target, object_name, image, width, height, position, x, y);
	}

	TextFieldModule.Enter onEnd;
	public void onEnd(String text) {
		if(this.onEnd == null){
			return;
		}
		this.text = text;
		this.onEnd.onEnd(this);
	}
	public interface Enter {
		void onEnd(TextFieldModule textFieldModule);
	}
	public void onEndInput(TextFieldModule.Enter onInput){
		this.onEnd = onInput;
	}

	//---------------------------------------------------------------------------------------------------//


	public static class Builder {

		//物件名稱
		private String objectID = String.valueOf((int)(Math.random()*100000));
		//圖片位址
		private String image = "Non";
		//寬度
		private int width = -1;;
		//高度
		private int height = -1;;
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

		public Builder setWidth(int width) {
			this.width = width;
			return this;
		}

		public Builder setHeight(int height) {
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

		public TextFieldModule build(){
			return new TextFieldModule(objectID, image, width, height, position, x, y);
		}

	}


}
