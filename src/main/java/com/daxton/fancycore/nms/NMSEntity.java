package com.daxton.fancycore.nms;

import org.bukkit.entity.EntityType;

public class NMSEntity {

	public static int getEntityID(String entityTypeString){

		String nmsVersion = NMSVersion.getNMSVersion();
		switch (nmsVersion){
			case "v1_13_R1":
				return com.daxton.fancycore.nms.v1_13_R1.NMSEntity.getEntityID(entityTypeString);
			case "v1_13_R2":
				return com.daxton.fancycore.nms.v1_13_R2.NMSEntity.getEntityID(entityTypeString);
			case "v1_14_R1":
				return com.daxton.fancycore.nms.v1_14_R1.NMSEntity.getEntityID(entityTypeString);
			case "v1_15_R1":
				return com.daxton.fancycore.nms.v1_15_R1.NMSEntity.getEntityID(entityTypeString);
			case "v1_16_R1":
				return com.daxton.fancycore.nms.v1_16_R1.NMSEntity.getEntityID(entityTypeString);
			case "v1_16_R2":
				return com.daxton.fancycore.nms.v1_16_R2.NMSEntity.getEntityID(entityTypeString);
			case "v1_16_R3":
				return com.daxton.fancycore.nms.v1_16_R3.NMSEntity.getEntityID(entityTypeString);
			case "v1_17_R1":
				return com.daxton.fancycore.nms.v1_17_R1.NMSEntity.getEntityID(entityTypeString);
		}

		return 0;
	}

	//獲取生物ID
	public static int getEntityID(EntityType entityType){

		String nmsVersion = NMSVersion.getNMSVersion();
		switch (nmsVersion){
			case "v1_13_R1":
				return com.daxton.fancycore.nms.v1_13_R1.NMSEntity.getEntityID(entityType);
			case "v1_13_R2":
				return com.daxton.fancycore.nms.v1_13_R2.NMSEntity.getEntityID(entityType);
			case "v1_14_R1":
				return com.daxton.fancycore.nms.v1_14_R1.NMSEntity.getEntityID(entityType);
			case "v1_15_R1":
				return com.daxton.fancycore.nms.v1_15_R1.NMSEntity.getEntityID(entityType);
			case "v1_16_R1":
				return com.daxton.fancycore.nms.v1_16_R1.NMSEntity.getEntityID(entityType);
			case "v1_16_R2":
				return com.daxton.fancycore.nms.v1_16_R2.NMSEntity.getEntityID(entityType);
			case "v1_16_R3":
				return com.daxton.fancycore.nms.v1_16_R3.NMSEntity.getEntityID(entityType);
			case "v1_17_R1":
				return com.daxton.fancycore.nms.v1_17_R1.NMSEntity.getEntityID(entityType);
		}
		
		return 0;
	}

}
