package com.daxton.fancycore.task.player;

import com.daxton.fancycore.FancyCore;
import com.daxton.fancycore.api.aims.entity.GetEntity;
import com.daxton.fancycore.api.item.CItem;
import com.daxton.fancycore.other.task.FancyAction;
import com.daxton.fancycore.other.taskaction.MapGetKey;
import com.daxton.fancycore.other.taskaction.StringToMap;
import com.daxton.fancyitmes.item.CustomItem;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;

public class ItemGive implements FancyAction {

	public void execute(LivingEntity self, LivingEntity target, Map<String, String> action_Map, Location inputLocation, String taskID){
		MapGetKey actionMapHandle = new MapGetKey(action_Map, self, target);
		//是否要移除
		boolean remove = actionMapHandle.getBoolean(new String[]{"remove","r"}, false);
		//物品ID
		String itemID = actionMapHandle.getString(new String[]{"ItemID","iid"},"");
		//數量
		int amount = actionMapHandle.getInt(new String[]{"amount","a"},1);
		if(amount < 1){
			amount = 1;
		}

		String[] itemArray = itemID.split("\\.");
		ItemStack itemStack = new ItemStack(Material.STONE, amount);

		if(itemArray.length == 1){
			CItem cItem = new CItem(itemArray[0]);
			cItem.setAmount(amount);
			itemStack = cItem.getItemStack();
		}

		//目標
		String targetString = actionMapHandle.getString(new String[]{"targetkey"}, "@Self");
		Map<String, String> targetMap = StringToMap.toTargetMap(targetString);
		List<LivingEntity> livingEntityList = GetEntity.get(self, target, targetMap);
		for(LivingEntity livingEntity : livingEntityList){
			if(livingEntity instanceof Player){
				Player player = (Player) livingEntity;
				if(itemArray.length == 2){
					if(Bukkit.getPluginManager().isPluginEnabled("FancyItems")){
						itemStack = CustomItem.valueOf(player, itemArray[0], itemArray[1], amount);
					}
				}
				if(remove){
					player.getInventory().remove(itemStack);
				}else {
					player.getInventory().addItem(itemStack);
				}
			}
		}

	}


}
