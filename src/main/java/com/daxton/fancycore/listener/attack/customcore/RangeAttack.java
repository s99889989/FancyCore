package com.daxton.fancycore.listener.attack.customcore;

import com.daxton.fancycore.FancyCore;
import com.daxton.fancycore.api.event.PhysicalDamageEvent;
import com.daxton.fancycore.manager.PlayerManagerCore;
import com.daxton.fancycore.other.damageformula.DefaultFormula;
import com.daxton.fancycore.other.damageformula.RangeFormula;
import com.daxton.fancycore.other.playerdata.PlayerDataFancy;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class RangeAttack implements Listener {

	//遠距離攻擊
	@EventHandler(priority = EventPriority.LOW)
	public void onPhysical(PhysicalDamageEvent event) {

		String damageType = event.getDamageType();

		//遠距離攻擊(倍率)
		if(damageType.equals("RANGE_MULTIPLY")){
			onRangeMultiply(event);
			return;
		}
		//遠距離攻擊(增加)
		if(damageType.equals("RANGE_ADD")){
			onRangeAdd(event);
			return;
		}
		//遠距離攻擊(攻擊)
		if(damageType.equals("RANGE_ATTACK")){
			onRangeAttack(event);
		}

	}

	//遠距離攻擊(倍率)
	public static void onRangeMultiply(PhysicalDamageEvent event) {
		if(MainAttack.deBug()){
			FancyCore.fancyCore.getLogger().info("遠距離攻擊(倍率)");
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
			attackNumber += RangeFormula.CriticalRangeAttack(player, target);
			event.setDamage(attackNumber);
			return;
		}

		attackNumber = RangeFormula.RangeAttack(player, target) * (attackNumber/100);
		event.setDamageType("RANGE_ATTACK");
		event.setDamage(attackNumber);

	}

	//遠距離攻擊(增加)
	public static void onRangeAdd(PhysicalDamageEvent event) {
		if(MainAttack.deBug()){
			FancyCore.fancyCore.getLogger().info("遠距離攻擊(增加)");
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
			attackNumber += RangeFormula.CriticalRangeAttack(player, target);
			event.setDamage(attackNumber);
			return;
		}

		attackNumber += RangeFormula.RangeAttack(player, target);
		event.setDamageType("RANGE_ATTACK");
		event.setDamage(attackNumber);

	}

	//遠距離攻擊(攻擊)
	public static void onRangeAttack(PhysicalDamageEvent event) {
		if(MainAttack.deBug()){
			FancyCore.fancyCore.getLogger().info("遠距離攻擊(攻擊)");
		}

		Player player = (Player) ((Arrow) event.getDamager()).getShooter();
		LivingEntity target = event.getTarget();
		double attackNumber = event.getDamage();

		PlayerDataFancy playerDataFancy = PlayerManagerCore.player_Data_Map.get(player.getUniqueId());
		//攻速
		if(playerDataFancy.attackSpeed){
			DefaultFormula.AttackSpeed(player, target);

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
				attackNumber += RangeFormula.CriticalRangeAttack(player, target);
				event.setDamage(attackNumber);
				return;
			}

			attackNumber += RangeFormula.RangeAttack(player, target);

		}

		event.setDamageType("RANGE_ATTACK");
		event.setDamage(attackNumber);

	}

}
