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

public class DefaultAttack implements Listener {

	//技能預設攻擊
	@EventHandler(priority = EventPriority.LOW)
	public void onPhysical(PhysicalDamageEvent event) {

		String damageType = event.getDamageType();

		if(damageType.equals("Default")){
			onDefaultAttack(event);
		}

	}

	//近距離攻擊(攻擊)
	public static void onDefaultAttack(PhysicalDamageEvent event){
		if(MainAttack.deBug()){
			FancyCore.fancyCore.getLogger().info("預設(攻擊)");
		}

		Player player = (Player) event.getDamager();
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
				attackNumber += MeleeFormula.CriticalMeleeAttack(player, target);
				event.setDamage(attackNumber);
				return;
			}

			attackNumber += MeleeFormula.MeleeAttack(player, target);
		}

		event.setDamageType("Melee_ATTACK");
		event.setDamage(attackNumber);

	}


}
