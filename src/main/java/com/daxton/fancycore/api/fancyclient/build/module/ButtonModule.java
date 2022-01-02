package com.daxton.fancycore.api.fancyclient.build.module;

import com.daxton.fancycore.api.fancyclient.json.menu_object.button.ClickButtonJson;
import org.bukkit.entity.LivingEntity;

import java.util.ArrayList;
import java.util.List;

public class ButtonModule implements ModComponent {

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

	private ButtonModule(String object_name, String display_name, int font_size, String image_on, String image_off, int width_on, int height_on, int width_off, int height_off, int position, int x, int y, boolean close, String to_menu, List<String> action_list){
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

	ButtonModule.IPressable onPress;
	public void onPress() {
		if(this.onPress == null){
			return;
		}
		this.onPress.onPress(this);
	}
	public interface IPressable {
		void onPress(ButtonModule clickButton);
	}
	public void onButtonClick(ButtonModule.IPressable onPress){
		this.onPress = onPress;
	}

	//---------------------------------------------------------------------------------------------------//

	public static class Builder {

		//物件名稱
		private String objectID = String.valueOf((int)(Math.random()*100000));
		//顯示名稱
		public String displayName = "";
		//顯示文字大小
		private int fontSize = 1;
		//圖片位址
		private String imageOn = "";
		private String imageOff = "";
		//啟動圖寬度
		private int widthOn = -1;
		//啟動圖高度
		private int heightOn = -1;
		//關閉寬度
		private int widthOff = -1;
		//關閉高度
		private int heightOff = -1;
		//基礎位置
		private int position;
		//X偏移
		private int x;
		//Y偏移
		private int y;
		//按下是否要關閉
		private boolean close;
		//開另外一個介面
		private String toMenu;
		//自訂動作
		private List<String> actionList = new ArrayList<>();

		public static Builder getInstance(){
			return new Builder();
		}

		public Builder setObjectID(String objectID) {
			this.objectID = objectID;
			return this;
		}

		public Builder setDisplayName(String displayName) {
			this.displayName = displayName;
			return this;
		}

		public Builder setFontSize(int fontSize) {
			this.fontSize = fontSize;
			return this;
		}

		public Builder setImageOn(String imageOn) {
			this.imageOn = imageOn;
			return this;
		}

		public Builder setImageOff(String imageOff) {
			this.imageOff = imageOff;
			return this;
		}

		public Builder setWidthOn(int widthOn) {
			this.widthOn = widthOn;
			return this;
		}

		public Builder setHeightOn(int heightOn) {
			this.heightOn = heightOn;
			return this;
		}

		public Builder setWidthOff(int widthOff) {
			this.widthOff = widthOff;
			return this;
		}

		public Builder setHeightOff(int heightOff) {
			this.heightOff = heightOff;
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

		public Builder setClose(boolean close) {
			this.close = close;
			return this;
		}

		public Builder setToMenu(String toMenu) {
			this.toMenu = toMenu;
			return this;
		}

		public Builder setActionList(List<String> actionList) {
			this.actionList = actionList;
			return this;
		}

		public ButtonModule build(){
			return new ButtonModule(objectID, displayName, fontSize, imageOn, imageOff, widthOn, heightOn, widthOff, heightOff, position, x, y, close, toMenu, actionList);
		}

	}

}
