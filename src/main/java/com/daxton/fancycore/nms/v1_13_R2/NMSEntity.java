package com.daxton.fancycore.nms.v1_13_R2;

import net.minecraft.server.v1_13_R2.EntityTypes;
import net.minecraft.server.v1_13_R2.IRegistry;
import org.bukkit.entity.EntityType;

public class NMSEntity {
	//獲取生物ID
	public static int getEntityID(String entityTypeString){
		EntityType entityType;
		try {
			entityType = Enum.valueOf(EntityType.class , entityTypeString.toUpperCase());
		}catch (Exception exception){
			entityType = EntityType.PIG;
		}
		try {
			return IRegistry.ENTITY_TYPE.a(EntityTypes.a(entityType.getName()));
		}catch (Exception exception){
			return 0;
		}
	}
	//獲取生物ID
	public static int getEntityID(EntityType entityType){
		try {
			return IRegistry.ENTITY_TYPE.a(EntityTypes.a(entityType.getName()));
		}catch (Exception exception){
			return 0;
		}
	}
}
