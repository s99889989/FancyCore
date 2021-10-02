package com.daxton.fancycore.other.damageformula;

import com.daxton.fancycore.FancyCore;
import com.daxton.fancycore.api.character.conversion.StringConversion;
import com.daxton.fancycore.config.FileConfig;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class MeleeFormula {

	//近距離攻擊
	public static double MeleeAttack(LivingEntity self, LivingEntity target){
		double mAttack = 0;
		FileConfiguration coreConfig = FileConfig.config_Map.get("Other/CustomCore.yml");

		String mAtt = coreConfig.getString("Melee_Physics.formula");
		//FancyCore.fancyCore.getLogger().info(mAtt);
		if(mAtt != null){
			mAttack = StringConversion.getDouble(self, target, 0, mAtt);
		}
		//FancyCore.fancyCore.getLogger().info(mAttack+"");
		return mAttack;
	}

	//普通攻擊(爆擊)
	public static double CriticalMeleeAttack(LivingEntity self, LivingEntity target){
		double mAttack = 0;
		FileConfiguration coreConfig = FileConfig.config_Map.get("Other/CustomCore.yml");

		String mAtt = coreConfig.getString("Melee_Physics.critical-formula");

		if(mAtt != null){
			mAttack = StringConversion.getDouble(self, target, 0, mAtt);
		}

		return mAttack;
	}

}
