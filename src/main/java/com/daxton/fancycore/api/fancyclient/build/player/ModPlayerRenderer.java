package com.daxton.fancycore.api.fancyclient.build.player;


import com.daxton.fancycore.api.fancyclient.ClientConnect;
import com.daxton.fancycore.api.fancyclient.json.JsonCtrl;
import com.daxton.fancycore.api.fancyclient.json.player.PlayerRenderJson;


import com.daxton.fancycore.api.fancyclient.json.player.image.ImageEntityJson;
import com.daxton.fancycore.api.fancyclient.json.player.item.ItemEntityJson;
import com.daxton.fancycore.api.fancyclient.json.player.text.TextEntityJson;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModPlayerRenderer {

	//物件表
	private Map<String, String> objectList = new HashMap<>();
	//原物件表
	List<RendererComponent> componentList;

	private ModPlayerRenderer(List<RendererComponent> componentList) {
		this.componentList = componentList;
	}

	public ModPlayerRenderer addComponent(RendererComponent component){
		componentList.add(component);
		return this;
	}

	public void render(Player player){
		setObjectList(player,null);
		PlayerRenderJson playerRenderJson = new PlayerRenderJson(objectList);
		ClientConnect.sendMessage(player, "PlayerRender", JsonCtrl.writeJSON(playerRenderJson));
	}

	public void render(Player player, LivingEntity target){
		setObjectList(player,target);
		PlayerRenderJson playerRenderJson = new PlayerRenderJson(objectList);
		ClientConnect.sendMessage(player, "PlayerRender", JsonCtrl.writeJSON(playerRenderJson));
	}

	public void unRender(Player player){
		setObjectList(player,null);
		PlayerRenderJson playerRenderJson = new PlayerRenderJson(objectList);
		ClientConnect.sendMessage(player, "UnPlayerRender", JsonCtrl.writeJSON(playerRenderJson));
	}

	//更新物件內容
	public void setObjectList(Player player, LivingEntity target){
		componentList.forEach(component -> {
			if(component instanceof ModImageRenderer){
				ImageEntityJson imageEntityJson = ((ModImageRenderer) component).toObject(player, target);
				String uuidString = player.getUniqueId().toString();
				imageEntityJson.setUuidString(uuidString);
				objectList.put(imageEntityJson.getType()+imageEntityJson.getObjectName()+uuidString, JsonCtrl.writeJSON(imageEntityJson));
			}
			if(component instanceof ModItemRenderer){
				ItemEntityJson itemEntityJson = ((ModItemRenderer) component).toObject(player);
				String uuidString = player.getUniqueId().toString();
				itemEntityJson.setUuidString(uuidString);
				objectList.put(itemEntityJson.getType()+itemEntityJson.getObjectName()+uuidString, JsonCtrl.writeJSON(itemEntityJson));
			}
			if(component instanceof ModTextRenderer){

				TextEntityJson textEntityJson = ((ModTextRenderer) component).toObject(player, target);
				String uuidString = player.getUniqueId().toString();
				textEntityJson.setUuidString(uuidString);

				objectList.put(textEntityJson.getType()+textEntityJson.getObjectName()+uuidString, JsonCtrl.writeJSON(textEntityJson));
			}


		});
	}

	//---------------------------------------------------------------------------------------------------//

	public static class ModPlayerRendererBuilder{

		//原物件表
		List<RendererComponent> componentList = new ArrayList<>();

		public static ModPlayerRendererBuilder getInstance(){
			return new ModPlayerRendererBuilder();
		}

		public void addComponent(RendererComponent component){
			componentList.add(component);
		}

		public ModPlayerRenderer build(){
			return new ModPlayerRenderer(componentList);
		}

	}

}
