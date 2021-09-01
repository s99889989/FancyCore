package com.daxton.fancycore.listener.attack.customcore;

import com.daxton.fancycore.FancyCore;
import com.daxton.fancycore.api.event.PhysicalDamageEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import static org.bukkit.entity.EntityType.ARMOR_STAND;

public class MainAttack {

	//攻擊分類
	@EventHandler(priority = EventPriority.LOW)
	public void onPhysicalDamage(EntityDamageByEntityEvent event){

		if(!(event.getDamager() instanceof Player) || !(event.getEntity() instanceof LivingEntity) || event.getEntity().getType() == ARMOR_STAND){
			return;
		}
		Player player = (Player) event.getDamager();
		double damageNumber = event.getDamage();

		if(deBug()){
			FancyCore.fancyCore.getLogger().info("傷害條件判斷: "+damageNumber);
		}
		//遠距離攻擊(倍率)
		if(String.valueOf(damageNumber).contains(".3444")){
			SetAttack(event, "RANGE_MULTIPLY");
			return;
		}
		//遠距離攻擊(增加)
		if(String.valueOf(damageNumber).contains(".3333")){
			SetAttack(event, "RANGE_ADD");
			return;
		}
		//遠距離攻擊(攻擊)
		if(String.valueOf(damageNumber).contains(".3222")){
			SetAttack(event, "RANGE_ATTACK");
			return;
		}

		//魔法攻擊(倍率)
		if(String.valueOf(damageNumber).contains(".2444")){
			SetAttack(event, "MAGIC_MULTIPLY");
			return;
		}
		//魔法攻擊(增加)
		if(String.valueOf(damageNumber).contains(".2333")){
			SetAttack(event, "MAGIC_ADD");
			return;
		}
		//魔法攻擊(攻擊)
		if(String.valueOf(damageNumber).contains(".2222")){
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
		//判斷物品是否有取消攻擊
		if(attackCan(player)){
			event.setCancelled(true);
			return;
		}
		//近距離攻擊(攻擊)
		SetAttack(event, "MELEE_ATTACK");

	}

	//攻擊重發
	public void SetAttack(EntityDamageByEntityEvent event, String damageType){
		PhysicalDamageEvent e = new PhysicalDamageEvent(event.getDamager(), (LivingEntity) event.getEntity(), event.getFinalDamage(), damageType);
		Bukkit.getPluginManager().callEvent(e);
		event.setDamage(e.getDamage());
		event.setCancelled(e.isCancelled());
	}
	//判斷物品是否有取消攻擊
	public boolean attackCan(Player player){
		boolean outB = false;
		ItemStack itemStack = player.getInventory().getItemInMainHand();
		if(itemStack.getType() != Material.AIR){
			String disableAttack = itemStack.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(FancyCore.fancyCore, "DisableAttack"), PersistentDataType.STRING);
			if(disableAttack != null){
				outB = Boolean.parseBoolean(disableAttack);
			}
		}
		return outB;
	}
	public static boolean deBug(){
		return false;
	}

}
