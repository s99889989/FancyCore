package com.daxton.fancycore.listener.attack.customcore;

import com.daxton.fancycore.FancyCore;
import com.daxton.fancycore.api.event.PhysicalDamageEvent;
import com.daxton.fancycore.manager.PlayerManagerCore;
import com.daxton.fancycore.other.damageformula.DefaultFormula;
import com.daxton.fancycore.other.damageformula.MeleeFormula;
import com.daxton.fancycore.other.playerdata.PlayerDataFancy;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class MeleeAttack implements Listener {

	//近距離攻擊
	@EventHandler(priority = EventPriority.LOW)
	public void onPhysical(PhysicalDamageEvent event) {

		String damageType = event.getDamageType();

		//近距離攻擊(倍率)
		if(damageType.equals("MELEE_MULTIPLY")){
			onMeleeMultiply(event);
			return;
		}
		//近距離攻擊(增加)
		if(damageType.equals("MELEE_ADD")){
			onMeleeAdd(event);
			return;
		}
		//近距離攻擊(攻擊)
		if(damageType.equals("MELEE_ATTACK")){
			onMeleeAttack(event);
		}

	}

	//近距離攻擊(倍率)
	public static void onMeleeMultiply(PhysicalDamageEvent event){
		if(MainAttack.deBug()){
			FancyCore.fancyCore.getLogger().info("近距離攻擊(倍率)");
		}
		Player player = (Player) event.getDamager();
		LivingEntity target = event.getTarget();

		double attackNumber = event.getDamage();
		//命中
		boolean hit = DefaultFormula.HitRate(player, target);
		if (!(hit)) {
			event.setDamageType("PHYSICAL_MISS");
			event.setCancelled(true);
			return;
		}

		//目標格檔
		boolean block = DefaultFormula.BlockRate(player, target);
		if (block) {
			event.setDamageType("PHYSICAL_BLOCK");
			event.setCancelled(true);
			return;
		}

		//爆擊
		boolean crit = DefaultFormula.CritChange(player, target);
		if (crit) {
			event.setDamageType("PHYSICAL_CRITICAL");
			attackNumber += MeleeFormula.CriticalMeleeAttack(player, target);
			event.setDamage(attackNumber);
			return;
		}

		attackNumber = MeleeFormula.MeleeAttack(player, target) * (attackNumber/100);

		event.setDamageType("Melee_ATTACK");

		event.setDamage(attackNumber);

	}

	//近距離攻擊(增加)
	public static void onMeleeAdd(PhysicalDamageEvent event){
		if(MainAttack.deBug()){
			FancyCore.fancyCore.getLogger().info("近距離攻擊(增加)");
		}

		Player player = (Player) event.getDamager();
		LivingEntity target = event.getTarget();
		double attackNumber = event.getDamage();

		//命中
		boolean hit = DefaultFormula.HitRate(player, target);
		if (!(hit)) {
			event.setDamageType("PHYSICAL_MISS");
			event.setCancelled(true);
			return;
		}

		//目標格檔
		boolean block = DefaultFormula.BlockRate(player, target);
		if (block) {
			event.setDamageType("PHYSICAL_BLOCK");
			event.setCancelled(true);
			return;
		}

		//爆擊
		boolean crit = DefaultFormula.CritChange(player, target);
		if (crit) {
			event.setDamageType("PHYSICAL_CRITICAL");
			attackNumber += MeleeFormula.CriticalMeleeAttack(player, target);
			event.setDamage(attackNumber);
			return;
		}

		attackNumber += MeleeFormula.MeleeAttack(player, target);
		event.setDamageType("Melee_ATTACK");
		event.setDamage(attackNumber);

	}


	//近距離攻擊(攻擊)
	public static void onMeleeAttack(PhysicalDamageEvent event){
		if(MainAttack.deBug()){
			FancyCore.fancyCore.getLogger().info("近距離攻擊(攻擊)");
		}

		double attackNumber = event.getDamage();
		event.setDamageType("Melee_ATTACK");
		event.setDamage(attackNumber);

	}

}
