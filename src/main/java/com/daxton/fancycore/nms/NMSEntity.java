package com.daxton.fancycore.nms;

import com.daxton.fancycore.manager.PlayerManagerCore;
import com.daxton.fancycore.other.playerdata.PlayerDataFancy;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class NMSEntity {

	//把實體轉成NBT再轉成String
	public static String entityNBTtoStringClient(@NotNull Entity entity, Player player){


		UUID uuid = player.getUniqueId();
		PlayerDataFancy playerDataFancy = PlayerManagerCore.player_Data_Map.get(uuid);
		String nmsVersion = NMSVersion.versionToNMSVersion(playerDataFancy.client_version);
		switch (nmsVersion){
			case "v1_13_R1":
				return com.daxton.fancycore.nms.v1_13_R1.NMSEntity.entityNBTtoString(entity);
			case "v1_13_R2":
				return com.daxton.fancycore.nms.v1_13_R2.NMSEntity.entityNBTtoString(entity);
			case "v1_14_R1":
				return com.daxton.fancycore.nms.v1_14_R1.NMSEntity.entityNBTtoString(entity);
			case "v1_15_R1":
				return com.daxton.fancycore.nms.v1_15_R1.NMSEntity.entityNBTtoString(entity);
			case "v1_16_R1":
				return com.daxton.fancycore.nms.v1_16_R1.NMSEntity.entityNBTtoString(entity);
			case "v1_16_R2":
				return com.daxton.fancycore.nms.v1_16_R2.NMSEntity.entityNBTtoString(entity);
			case "v1_16_R3":
				return com.daxton.fancycore.nms.v1_16_R3.NMSEntity.entityNBTtoString(entity);
			case "v1_17_R1":
				return com.daxton.fancycore.nms.v1_17_R1.NMSEntity.entityNBTtoString(entity);
			case "v1_18_R1":
				return com.daxton.fancycore.nms.v1_18_R1.NMSEntity.entityNBTtoString(entity);
			case "v1_18_R2":
				return com.daxton.fancycore.nms.v1_18_R2.NMSEntity.entityNBTtoString(entity);
		}

		return "";
	}

	//把實體轉成NBT再轉成String
	public static String entityNBTtoString(@NotNull Entity entity){
		String nmsVersion = NMSVersion.getNMSVersion();
		switch (nmsVersion){
			case "v1_13_R1":
				return com.daxton.fancycore.nms.v1_13_R1.NMSEntity.entityNBTtoString(entity);
			case "v1_13_R2":
				return com.daxton.fancycore.nms.v1_13_R2.NMSEntity.entityNBTtoString(entity);
			case "v1_14_R1":
				return com.daxton.fancycore.nms.v1_14_R1.NMSEntity.entityNBTtoString(entity);
			case "v1_15_R1":
				return com.daxton.fancycore.nms.v1_15_R1.NMSEntity.entityNBTtoString(entity);
			case "v1_16_R1":
				return com.daxton.fancycore.nms.v1_16_R1.NMSEntity.entityNBTtoString(entity);
			case "v1_16_R2":
				return com.daxton.fancycore.nms.v1_16_R2.NMSEntity.entityNBTtoString(entity);
			case "v1_16_R3":
				return com.daxton.fancycore.nms.v1_16_R3.NMSEntity.entityNBTtoString(entity);
			case "v1_17_R1":
				return com.daxton.fancycore.nms.v1_17_R1.NMSEntity.entityNBTtoString(entity);
			case "v1_18_R1":
				return com.daxton.fancycore.nms.v1_18_R1.NMSEntity.entityNBTtoString(entity);
			case "v1_18_R2":
				return com.daxton.fancycore.nms.v1_18_R2.NMSEntity.entityNBTtoString(entity);
		}

		return "";
	}

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
			case "v1_18_R1":
				return com.daxton.fancycore.nms.v1_18_R1.NMSEntity.getEntityID(entityTypeString);
			case "v1_18_R2":
				return com.daxton.fancycore.nms.v1_18_R2.NMSEntity.getEntityID(entityTypeString);
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
			case "v1_18_R1":
				return com.daxton.fancycore.nms.v1_18_R1.NMSEntity.getEntityID(entityType);
			case "v1_18_R2":
				return com.daxton.fancycore.nms.v1_18_R2.NMSEntity.getEntityID(entityType);
		}
		
		return 0;
	}

}
