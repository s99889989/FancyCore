package com.daxton.fancycore.api.fancyclient.build.hub;

import com.daxton.fancycore.api.fancyclient.ClientConnect;

import com.daxton.fancycore.api.fancyclient.build.gui.ModComponent;
import com.daxton.fancycore.api.fancyclient.build.gui.ModImage;
import com.daxton.fancycore.api.fancyclient.build.gui.ModItem;
import com.daxton.fancycore.api.fancyclient.build.gui.ModText;
import com.daxton.fancycore.api.fancyclient.json.JsonCtrl;
import com.daxton.fancycore.api.fancyclient.json.hub.HubJson;
import com.daxton.fancycore.api.fancyclient.json.menu_object.image.ImageShowJson;
import com.daxton.fancycore.api.fancyclient.json.menu_object.item.ItemShowJson;
import com.daxton.fancycore.api.fancyclient.json.menu_object.text.TextLabelJson;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModHub {

	//物件表
	private Map<String, String> object_list = new HashMap<>();
	//原物件表
	List<ModComponent> modComponentList;

	private ModHub(List<ModComponent> modComponentList) {
		this.modComponentList = modComponentList;
	}

	public ModHub addComponent(ModComponent modComponent){
		modComponentList.add(modComponent);
		return this;
	}

	public void show(Player player){
		setObjectList(player, null);
		HubJson hubJson = new HubJson(object_list);
		ClientConnect.sendMessage(player, "showhub", JsonCtrl.writeJSON(hubJson));
	}

	public void show(Player player, LivingEntity target){
		setObjectList(player, target);
		HubJson hubJson = new HubJson(object_list);
		ClientConnect.sendMessage(player, "showhub", JsonCtrl.writeJSON(hubJson));
	}

	public void hide(Player player){
		setObjectList(player, null);
		HubJson hubJson = new HubJson(object_list);
		ClientConnect.sendMessage(player, "hidehub", JsonCtrl.writeJSON(hubJson));
	}

	//更新物件內容
	public void setObjectList(LivingEntity self, LivingEntity target){

		modComponentList.forEach(modComponent -> {

			if(modComponent instanceof ModText){
				TextLabelJson textLabelJson = ((ModText) modComponent).toObject(self, target);
				object_list.put(textLabelJson.getType()+textLabelJson.getObject_name(), JsonCtrl.writeJSON(textLabelJson));
			}
			if(modComponent instanceof ModImage){
				ImageShowJson imageShowJson = ((ModImage) modComponent).toObject(self, target);
				object_list.put(imageShowJson.getType()+imageShowJson.getObject_name(), JsonCtrl.writeJSON(imageShowJson));
			}
			if(modComponent instanceof ModItem){
				ItemShowJson itemShowJson = ((ModItem) modComponent).toObject();
				object_list.put(itemShowJson.getType()+itemShowJson.getObject_name(), JsonCtrl.writeJSON(itemShowJson));
			}
		});

	}

//---------------------------------------------------------------------------------------------------//


	public static class ModHubBuilder{

		//原物件表
		List<ModComponent> modComponentList = new ArrayList<>();

		public static ModHubBuilder getInstance(){
			return new ModHubBuilder();
		}

		public ModHubBuilder addComponent(ModComponent modComponent){
			modComponentList.add(modComponent);
			return this;
		}

		public ModHub build(){
			return new ModHub(modComponentList);
		}

	}

}
