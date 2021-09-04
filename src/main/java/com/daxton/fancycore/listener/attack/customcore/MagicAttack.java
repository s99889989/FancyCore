package com.daxton.fancycore.listener.attack.customcore;

import com.daxton.fancycore.FancyCore;
import com.daxton.fancycore.api.event.PhysicalDamageEvent;
import com.daxton.fancycore.other.damageformula.MagicFormula;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class MagicAttack implements Listener {

	//魔法攻擊
	@EventHandler(priority = EventPriority.LOW)
	public void onMagic(PhysicalDamageEvent event) {
		String damageType = event.getDamageType();

		//魔法攻擊(倍率)
		if(damageType.equals("MAGIC_MULTIPLY")){
			onMagicMultiply(event);
			return;
		}
		//魔法攻擊(增加)
		if(damageType.equals("MAGIC_ADD")){
			onMagicAdd(event);
			return;
		}
		//魔法攻擊(攻擊)
		if(damageType.equals("MAGIC_ATTACK")){
			onMagicAttack(event);
		}


	}

	//魔法攻擊(倍率)
	public static void onMagicMultiply(PhysicalDamageEvent event){
		if(MainAttack.deBug()){
			FancyCore.fancyCore.getLogger().info("魔法攻擊(倍率)");
		}
		Player player = (Player) event.getDamager();
		LivingEntity target = event.getTarget();
		double attackNumber = event.getDamage();

		attackNumber = MagicFormula.MagicAttack(player, target) * (attackNumber/100);
		event.setDamageType("MAGIC_ATTACK");
		event.setDamage(attackNumber);

	}

	//魔法攻擊(增加)
	public static void onMagicAdd(PhysicalDamageEvent event){
		if(MainAttack.deBug()){
			FancyCore.fancyCore.getLogger().info("魔法攻擊(增加)");
		}

		Player player = (Player) event.getDamager();
		LivingEntity target = event.getTarget();
		double attackNumber = event.getDamage();

		attackNumber += MagicFormula.MagicAttack(player, target);
		event.setDamageType("MAGIC_ATTACK");
		event.setDamage(attackNumber);

	}

	//魔法攻擊(攻擊)
	public static void onMagicAttack(PhysicalDamageEvent event){
		if(MainAttack.deBug()){
			FancyCore.fancyCore.getLogger().info("魔法攻擊(攻擊)");
		}
		double attackNumber = event.getDamage();
		event.setDamageType("MAGIC_ATTACK");
		event.setDamage(attackNumber);
	}

}
