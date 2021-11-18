package com.daxton.fancycore.api.fancyclient.build.gui;

import com.daxton.fancycore.api.fancyclient.json.menu_object.button.ClickButtonJson;
import org.bukkit.entity.LivingEntity;

import java.util.ArrayList;
import java.util.List;

public class ModButton implements ModComponent{

	//類型
	public String type = "Button";
	//物件名稱
	private String object_name = "";
	//顯示名稱
	public String display_name = "";
	//顯示文字大小
	private int font_size;
	//圖片位址
	private String image_on = "";
	private String image_off = "";
	//啟動圖寬度
	private int width_on;
	//啟動圖高度
	private int height_on;
	//關閉寬度
	private int width_off;
	//關閉高度
	private int height_off;
	//基礎位置
	private int position;
	//X偏移
	private int x;
	//Y偏移
	private int y;
	//按下是否要關閉
	private boolean close;
	//開另外一個介面
	private String to_menu;
	//自訂動作
	private List<String> action_list = new ArrayList<>();

	private ModButton(String object_name, String display_name, int font_size, String image_on, String image_off, int width_on, int height_on, int width_off, int height_off, int position, int x, int y, boolean close, String to_menu, List<String> action_list){
		this.object_name = object_name;
		this.display_name = display_name;
		this.font_size = font_size;
		this.image_on = image_on;
		this.image_off = image_off;
		this.width_on = width_on;
		this.height_on = height_on;
		this.width_off = width_off;
		this.height_off = height_off;
		this.position = position;
		this.x = x;
		this.y = y;
		this.close = close;
		this.to_menu = to_menu;
		this.action_list = action_list;
	}


	public ClickButtonJson toObject(){
		return new ClickButtonJson(object_name, display_name, font_size, image_on, image_off, width_on, height_on, width_off, height_off, position, x, y, close, to_menu, action_list);
	}

	public ClickButtonJson toObject(LivingEntity self, LivingEntity target){
		return new ClickButtonJson(self, target, object_name, display_name, font_size, image_on, image_off, width_on, height_on, width_off, height_off, position, x, y, close, to_menu, action_list);
	}

	ModButton.IPressable onPress;
	public void onPress() {
		if(this.onPress == null){
			return;
		}
		this.onPress.onPress(this);
	}
	public interface IPressable {
		void onPress(ModButton clickButton);
	}
	public void onButtonClick(ModButton.IPressable onPress){
		this.onPress = onPress;
	}

	//---------------------------------------------------------------------------------------------------//

	public static class ModButtonBuilder{

		//物件名稱
		private String object_name = String.valueOf((int)(Math.random()*100000));
		//顯示名稱
		public String display_name = "";
		//顯示文字大小
		private int font_size = 1;
		//圖片位址
		private String image_on = "";
		private String image_off = "";
		//啟動圖寬度
		private int width_on;
		//啟動圖高度
		private int height_on;
		//關閉寬度
		private int width_off;
		//關閉高度
		private int height_off;
		//基礎位置
		private int position;
		//X偏移
		private int x;
		//Y偏移
		private int y;
		//按下是否要關閉
		private boolean close;
		//開另外一個介面
		private String to_menu;
		//自訂動作
		private List<String> action_list = new ArrayList<>();

		public static ModButtonBuilder getInstance(){
			return new ModButtonBuilder();
		}

		public ModButtonBuilder setObject_name(String object_name) {
			this.object_name = object_name;
			return this;
		}

		public ModButtonBuilder setDisplay_name(String display_name) {
			this.display_name = display_name;
			return this;
		}

		public ModButtonBuilder setFont_size(int font_size) {
			this.font_size = font_size;
			return this;
		}

		public ModButtonBuilder setImage_on(String image_on) {
			this.image_on = image_on;
			return this;
		}

		public ModButtonBuilder setImage_off(String image_off) {
			this.image_off = image_off;
			return this;
		}

		public ModButtonBuilder setWidth_on(int width_on) {
			this.width_on = width_on;
			return this;
		}

		public ModButtonBuilder setHeight_on(int height_on) {
			this.height_on = height_on;
			return this;
		}

		public ModButtonBuilder setWidth_off(int width_off) {
			this.width_off = width_off;
			return this;
		}

		public ModButtonBuilder setHeight_off(int height_off) {
			this.height_off = height_off;
			return this;
		}

		public ModButtonBuilder setPosition(int position) {
			this.position = position;
			return this;
		}

		public ModButtonBuilder setX(int x) {
			this.x = x;
			return this;
		}

		public ModButtonBuilder setY(int y) {
			this.y = y;
			return this;
		}

		public ModButtonBuilder setClose(boolean close) {
			this.close = close;
			return this;
		}

		public ModButtonBuilder setTo_menu(String to_menu) {
			this.to_menu = to_menu;
			return this;
		}

		public ModButtonBuilder setAction_list(List<String> action_list) {
			this.action_list = action_list;
			return this;
		}

		public ModButton build(){
			return new ModButton(object_name, display_name, font_size, image_on, image_off, width_on, height_on, width_off, height_off, position, x, y, close, to_menu, action_list);
		}

	}

}
