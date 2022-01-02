package com.daxton.fancycore.api.fancyclient.build.gui;

import com.daxton.fancycore.api.fancyclient.ClientConnect;
import com.daxton.fancycore.api.fancyclient.build.module.*;
import com.daxton.fancycore.api.fancyclient.json.JsonCtrl;
import com.daxton.fancycore.api.fancyclient.json.menu.MenuJson;
import com.daxton.fancycore.api.fancyclient.json.menu_object.button.ClickButtonJson;
import com.daxton.fancycore.api.fancyclient.json.menu_object.image.ImageShowJson;
import com.daxton.fancycore.api.fancyclient.json.menu_object.item.ItemShowJson;
import com.daxton.fancycore.api.fancyclient.json.menu_object.slider.PullPanelJson;
import com.daxton.fancycore.api.fancyclient.json.menu_object.text.TextLabelJson;
import com.daxton.fancycore.api.fancyclient.json.menu_object.textfield.TextFieldJson;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.*;

public class ModGUI {

	//圖片位址
	private String image;
	//Gui名稱
	private String gui_id;
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
	//物件表
	private Map<String, String> object_list;
	//原物件表
	List<ModComponent> modComponentList = new ArrayList<>();
	//按鈕動作
	public Map<String, ButtonModule> modButtonMap = new HashMap<>();
	//輸入框動作
	public Map<String, TextFieldModule> modTextFieldMap = new HashMap<>();

	public Map<String, TextModule> modTextMap = new HashMap<>();

	private ModGUI(String gui_id, String image, int width, int height, int position, int x, int y, Map<String, String> object_list){
		this.image = image;
		this.gui_id = gui_id;
		this.width = width;
		this.height = height;
		this.position = position;
		this.x = x;
		this.y = y;
		this.object_list = object_list;
	}

	//增加GUI組件
	public ModGUI addComponent(ModComponent modComponent){
		modComponentList.add(modComponent);
		return this;
	}

	//打開ModGUI
	public void open(Player player){
		setObjectList(player, null);
		MenuJson menuJson = new MenuJson(gui_id, image, width, height, position, x, y, object_list);
		ClientConnect.sendMessage(player, "MenuOpen", JsonCtrl.writeJSON(menuJson));

		UUID uuid = player.getUniqueId();

		object_list.clear();
	}

	//關閉ModGUI
	public void close(Player player){
		ClientConnect.sendMessage(player, "menuclose", "");
		modButtonMap.clear();
		modTextFieldMap.clear();
	}
	//更新ModGUI
	public void updata(Player player){
		setObjectList(player, null);
		MenuJson menuJson = new MenuJson(gui_id, image, width, height, position, x, y, object_list);
		ClientConnect.sendMessage(player, "updatamenu", JsonCtrl.writeJSON(menuJson));
	}
	//更新物件內容
	public void setObjectList(LivingEntity self, LivingEntity target){
		modButtonMap.clear();
		modTextFieldMap.clear();
		modComponentList.forEach(modComponent -> {
			if(modComponent instanceof ButtonModule){
				ClickButtonJson clickButtonJson = ((ButtonModule) modComponent).toObject(self, target);
				modButtonMap.put(clickButtonJson.getObject_name(), (ButtonModule) modComponent);
				object_list.put(clickButtonJson.getType()+clickButtonJson.getObject_name(), JsonCtrl.writeJSON(clickButtonJson));
			}
			if(modComponent instanceof TextModule){
				TextLabelJson textLabelJson = ((TextModule) modComponent).toObject(self, target);
				modTextMap.put(textLabelJson.getObject_name(), (TextModule) modComponent);
				object_list.put(textLabelJson.getType()+textLabelJson.getObject_name(), JsonCtrl.writeJSON(textLabelJson));
			}
			if(modComponent instanceof ImageModule){
				ImageShowJson imageShowJson = ((ImageModule) modComponent).toObject(self, target);
				object_list.put(imageShowJson.getType()+imageShowJson.getObject_name(), JsonCtrl.writeJSON(imageShowJson));
			}
			if(modComponent instanceof TextFieldModule){
				TextFieldJson textFieldJson = ((TextFieldModule) modComponent).toObject(self, target);
				modTextFieldMap.put(textFieldJson.getObject_name(), (TextFieldModule) modComponent);
				object_list.put(textFieldJson.getType()+textFieldJson.getObject_name(), JsonCtrl.writeJSON(textFieldJson));
			}
			if(modComponent instanceof ItemModule){
				ItemShowJson itemShowJson = ((ItemModule) modComponent).toObject();
				object_list.put(itemShowJson.getType()+itemShowJson.getObject_name(), JsonCtrl.writeJSON(itemShowJson));
			}
			if(modComponent instanceof PullPanelModule){
				PullPanelModule pullPanelModule = (PullPanelModule) modComponent;
				PullPanelJson pullPanelJson = pullPanelModule.toObject(self, target);
				object_list.put(pullPanelJson.getType()+pullPanelJson.getObjectID(), JsonCtrl.writeJSON(pullPanelJson));
				pullPanelModule.getModComponentList().forEach(modComponent1 -> {
					if(modComponent1 instanceof ButtonModule){
						ClickButtonJson clickButtonJson = ((ButtonModule) modComponent1).toObject(self, target);
						modButtonMap.put(clickButtonJson.getObject_name(), (ButtonModule) modComponent1);
					}
					if(modComponent1 instanceof TextModule){
						TextLabelJson textLabelJson = ((TextModule) modComponent1).toObject(self, target);
						modTextMap.put(textLabelJson.getObject_name(), (TextModule) modComponent1);
					}
					if(modComponent1 instanceof TextFieldModule){
						TextFieldJson textFieldJson = ((TextFieldModule) modComponent1).toObject(self, target);
						modTextFieldMap.put(textFieldJson.getObject_name(), (TextFieldModule) modComponent1);
					}
				});
			}
		});

	}

	//---------------------------------------------------------------------------------------------------//

	public static class ModGUIBuilder{

		//圖片位址
		private String image = "";
		//Gui名稱
		private String gui_id = String.valueOf((int)(Math.random()*100000));
		//寬度
		private int width = -1;
		//高度
		private int height = -1;
		//基礎位置
		private int position;
		//X偏移
		private int x;
		//Y偏移
		private int y;
		//物件表
		private Map<String, String> object_list = new HashMap<>();

		public static ModGUIBuilder getInstance(){
			return new ModGUIBuilder();
		}

		public ModGUIBuilder setImage(String image) {
			this.image = image;
			return this;
		}

		public ModGUIBuilder setGui_id(String gui_id) {
			this.gui_id = gui_id;
			return this;
		}

		public ModGUIBuilder setWidth(int width) {
			this.width = width;
			return this;
		}

		public ModGUIBuilder setHeight(int height) {
			this.height = height;
			return this;
		}

		public ModGUIBuilder setPosition(int position) {
			this.position = position;
			return this;
		}

		public ModGUIBuilder setX(int x) {
			this.x = x;
			return this;
		}

		public ModGUIBuilder setY(int y) {
			this.y = y;
			return this;
		}

		public ModGUI build(){
			return new ModGUI(gui_id, image, width, height, position, x, y, object_list);
		}


	}



}
