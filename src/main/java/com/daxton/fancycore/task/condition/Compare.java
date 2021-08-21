package com.daxton.fancycore.task.condition;

import com.daxton.fancycore.api.character.conversion.StringConversion;
import org.bukkit.entity.LivingEntity;

public class Compare {

	public static boolean valueOf(LivingEntity self, LivingEntity target, String content, String taskID){
		boolean result = false;

		String stringLeft = "";
		String stringRight = "";

		if(content.contains(">")){
			stringLeft = content.substring(0, content.indexOf(">"));
			stringRight = content.substring(content.indexOf(">")+1);

			double numberLeft = StringConversion.getDouble(self,target,0,stringLeft);
			double numberRight = StringConversion.getDouble(self,target,0,stringRight);
			if(numberLeft > numberRight){
				result = true;
			}
		}else if(content.contains("<")){
			stringLeft = content.substring(0, content.indexOf("<"));
			stringRight = content.substring(content.indexOf("<")+1);

			double numberLeft = StringConversion.getDouble(self,target,0,stringLeft);
			double numberRight = StringConversion.getDouble(self,target,0,stringRight);
			if(numberLeft < numberRight){
				result = true;
			}
		}else if(content.contains("~")){

			stringLeft = content.substring(0, content.indexOf("~"));
			stringRight = content.substring(content.indexOf("~")+1);

			double numberLeft = StringConversion.getDouble(self,target,0,stringLeft);
			double numberRight = StringConversion.getDouble(self,target,0,stringRight);
			if(numberLeft == numberRight){
				result = true;
			}


		}
		return result;
	}

}
