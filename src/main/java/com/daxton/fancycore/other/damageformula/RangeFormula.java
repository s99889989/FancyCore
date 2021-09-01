package com.daxton.fancycore.other.damageformula;

import com.daxton.fancycore.api.character.conversion.StringConversion;
import com.daxton.fancycore.config.FileConfig;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class RangeFormula {

	//遠距離攻擊
	public static double RangeAttack(LivingEntity self, LivingEntity target){
		double mAttack = 0;
		FileConfiguration coreConfig = FileConfig.config_Map.get("Other/CustomCore.yml");

		String mAtt = coreConfig.getString("CoreAttribute.Range_Physics_Player_Other.formula");
		if(target instanceof Player){
			mAtt = coreConfig.getString("CoreAttribute.Range_Physics_Player_Player.formula");
		}
		if(mAtt != null){
			mAttack = StringConversion.getDouble(self, target, 0, mAtt);
		}


		return mAttack;
	}

	//箭矢攻擊(爆擊)
	public static double CriticalRangeAttack(LivingEntity self, LivingEntity target){
		double mAttack = 0;
		FileConfiguration coreConfig = FileConfig.config_Map.get("Other/CustomCore.yml");

		String mAtt = coreConfig.getString("CoreAttribute.Range_Physics_Critical_Strike_Power_Player_Other.formula");
		if(target instanceof Player){
			mAtt = coreConfig.getString("CoreAttribute.Range_Physics_Critical_Strike_Power_Player_Player.formula");
		}
		if(mAtt != null){
			mAttack = StringConversion.getDouble(self, target, 0, mAtt);
		}


		return mAttack;
	}

}
