package com.daxton.fancycore.other.damageformula;

import com.daxton.fancycore.FancyCore;
import com.daxton.fancycore.api.character.conversion.StringConversion;
import com.daxton.fancycore.config.FileConfig;
import com.daxton.fancycore.manager.PlayerManagerCore;
import com.daxton.fancycore.other.playerdata.PlayerDataFancy;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class DefaultFormula {

	//攻擊速度
	public static void AttackSpeed(LivingEntity self, LivingEntity target){
		FancyCore cd = FancyCore.fancyCore;

		PlayerDataFancy playerDataFancy = PlayerManagerCore.player_Data_Map.get(self.getUniqueId());

		playerDataFancy.attackSpeed = false;
		FileConfiguration coreConfig = FileConfig.config_Map.get("Other/CustomCore.yml");
		String attackSpeedString = coreConfig.getString("Attack_Speed.formula");
		int attackSpeed = StringConversion.getInt(self, target, 0, attackSpeedString);
		attackSpeed = attackSpeed*2;
		BukkitRunnable bukkitRunnable = new BukkitRunnable() {
			@Override
			public void run() {
				playerDataFancy.attackSpeed = true;
			}
		};
		bukkitRunnable.runTaskLater(cd, attackSpeed);
	}

	//命中公式
	public static boolean HitRate(LivingEntity self, LivingEntity target){
		boolean hit = false;

		FileConfiguration coreConfig = FileConfig.config_Map.get("Other/CustomCore.yml");

		int randomNumber = 100;
		int  r = (int)(Math.random()*randomNumber);
		String hitRateString = coreConfig.getString("Hit_Rate.formula");

		if(hitRateString != null){
			double hitRate = StringConversion.getDouble(self,target, 0,hitRateString);

			//CustomDisplay.getCustomDisplay().getLogger().info(hitRateString+" : "+hitRate+" : "+StringConversion.getString(self,target,hitRateString));

			if(hitRate < 0){
				hitRate = 0;
			}
			if(r<hitRate){
				hit = true;
			}
		}

		return hit;
	}

	//格檔公式
	public static boolean BlockRate(LivingEntity self, LivingEntity target){
		boolean block = false;

		FileConfiguration coreConfig = FileConfig.config_Map.get("Other/CustomCore.yml");

		int randomNumber = 100;
		int  r = (int)(Math.random()*randomNumber);
		String blockRateString = coreConfig.getString("Block_Rate.formula");

		if(blockRateString != null){

			double blockRate = StringConversion.getDouble(self,target, 0, blockRateString);

			int max_chance = 95;
			if(blockRate > max_chance){
				blockRate = max_chance;
			}
			if(blockRate < 0){
				blockRate = 0;
			}

			if(r<blockRate){
				block = true;
			}

		}

		return block;
	}

	//爆擊率公式
	public static boolean CritChange(LivingEntity self, LivingEntity target){
		boolean crit = false;

		FileConfiguration coreConfig = FileConfig.config_Map.get("Other/CustomCore.yml");

		int randomNumber2 = 100;
		int  r2 = (int)(Math.random()*randomNumber2);
		String crit_chanceString = coreConfig.getString("Critical_Strike_Chance.formula");

		if(crit_chanceString != null){
			//FancyCore.fancyCore.getLogger().info(crit_chanceString);
			double crit_chance = StringConversion.getDouble(self,target, 0, crit_chanceString);
			//FancyCore.fancyCore.getLogger().info("爆擊率: "+crit_chance);
			int max_chance = 95;
			if(crit_chance > max_chance){
				crit_chance = max_chance;
			}
			if(r2<crit_chance){
				crit = true;
			}

		}


		return crit;
	}

}
