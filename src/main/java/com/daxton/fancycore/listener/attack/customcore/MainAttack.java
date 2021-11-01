package com.daxton.fancycore.listener.attack.customcore;

import com.daxton.fancyclasses.api.dataplayer.PlayerClassData;
import com.daxton.fancyclasses.manager.ClassesManager;
import com.daxton.fancycore.FancyCore;
import com.daxton.fancycore.api.event.PhysicalDamageEvent;
import com.daxton.fancycore.api.item.ItemKeySearch;
import com.daxton.fancycore.api.other.DigitConversion;
import com.daxton.fancycore.other.playerdata.ItemCD;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.bukkit.entity.EntityType.ARMOR_STAND;

public class MainAttack implements Listener {

	//攻擊分類
	@EventHandler(priority = EventPriority.LOW)
	public void onPhysicalDamage(EntityDamageByEntityEvent event){

		if(!(event.getDamager() instanceof Player) || !(event.getEntity() instanceof LivingEntity) || event.getEntity().getType() == ARMOR_STAND){
			return;
		}
		if(event.getEntity().getCustomName() != null && event.getEntity().getCustomName().equals("ModleEngine")){
			return;
		}
		Player player = (Player) event.getDamager();
		UUID uuid = player.getUniqueId();
		double damageNumber = event.getDamage();

		damageNumber = DigitConversion.NumberUtilNumber(damageNumber, "0.0000");

		if(deBug()){
			FancyCore.fancyCore.getLogger().info("傷害條件判斷: "+damageNumber);
		}
		if (Bukkit.getServer().getPluginManager().getPlugin("FancyClasses") != null || Bukkit.getPluginManager().isPluginEnabled("FancyClasses")){
			PlayerClassData playerClassData = ClassesManager.player_ClassData_Map.get(uuid);
			ItemStack mainItem = player.getInventory().getItemInMainHand();
			//職業需求
			List<String> tagList = ItemKeySearch.getTagList(mainItem, "needclasses");
			if(!tagList.isEmpty()){
				if(!tagList.contains(playerClassData.className)){
					event.setCancelled(true);
					return;
				}
			}
			//等級判定
			Map<String, String> levelMap = ItemKeySearch.getTagMap(mainItem, "needlevel");
			if(!levelMap.isEmpty()){
				for(String key : levelMap.keySet()){
					int nowLevel = playerClassData.getLevel(key);
					int needLevel = Integer.parseInt(levelMap.get(key));
					if(nowLevel < needLevel){
						event.setCancelled(true);
						return;
					}
				}

			}
		}
		//遠距離攻擊(倍率)
		if(String.valueOf(damageNumber).contains(".3333")){
			SetAttack(event, "RANGE_MULTIPLY");
			return;
		}
		//遠距離攻擊(增加)
		if(String.valueOf(damageNumber).contains(".3222")){
			SetAttack(event, "RANGE_ADD");
			return;
		}
		//遠距離攻擊(攻擊)
		if(String.valueOf(damageNumber).contains(".3111")){
			SetAttack(event, "RANGE_ATTACK");
			return;
		}

		//魔法攻擊(倍率)
		if(String.valueOf(damageNumber).contains(".2333")){
			SetAttack(event, "MAGIC_MULTIPLY");
			return;
		}
		//魔法攻擊(增加)
		if(String.valueOf(damageNumber).contains(".2222")){
			SetAttack(event, "MAGIC_ADD");
			return;
		}
		//魔法攻擊(攻擊)
		if(String.valueOf(damageNumber).contains(".2111")){
			SetAttack(event, "MAGIC_ATTACK");
			return;
		}
		//近距離攻擊(倍率)
		if(String.valueOf(damageNumber).contains(".1333")){
			SetAttack(event, "MELEE_MULTIPLY");
			return;
		}
		//近距離攻擊(增加)
		if(String.valueOf(damageNumber).contains(".1222")){
			SetAttack(event, "MELEE_ADD");
			return;
		}
		//近距離攻擊(增加)
		if(String.valueOf(damageNumber).contains(".1111")){
			SetAttack(event, "MELEE_ATTACK");
			return;
		}
		//判斷物品是否有取消攻擊
		if(ItemCD.attackCan(player)){
			event.setCancelled(true);
			return;
		}
		//近距離攻擊(攻擊)
		SetAttack(event, "Default");

	}

	//攻擊重發
	public void SetAttack(EntityDamageByEntityEvent event, String damageType){
		PhysicalDamageEvent e = new PhysicalDamageEvent(event.getDamager(), (LivingEntity) event.getEntity(), event.getFinalDamage(), damageType);
		Bukkit.getPluginManager().callEvent(e);
		event.setDamage(e.getDamage());
		event.setCancelled(e.isCancelled());
	}

	public static boolean deBug(){
		return false;
	}

}
