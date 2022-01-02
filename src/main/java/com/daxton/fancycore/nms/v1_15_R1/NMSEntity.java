package com.daxton.fancycore.nms.v1_15_R1;

import net.minecraft.server.v1_15_R1.EntityTypes;
import net.minecraft.server.v1_15_R1.IRegistry;
import net.minecraft.server.v1_15_R1.NBTTagCompound;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;

public class NMSEntity {

	//把實體轉成NBT再轉成String
	public static String entityNBTtoString(@NotNull Entity entity){
		net.minecraft.server.v1_15_R1.Entity nmsEntity = ((CraftEntity) entity).getHandle();
		NBTTagCompound nbt = new NBTTagCompound();
		nmsEntity.save(nbt);
		return nbt.toString();
	}

	//獲取生物ID
	public static int getEntityID(String entityTypeString){
		EntityType entityType;
		try {
			entityType = Enum.valueOf(EntityType.class , entityTypeString.toUpperCase());
		}catch (Exception exception){
			entityType = EntityType.PIG;
		}
		try {
			return IRegistry.ENTITY_TYPE.a(EntityTypes.a(entityType.getName()).get());
		}catch (Exception exception){
			return 0;
		}
	}
	//獲取生物ID
	public static int getEntityID(EntityType entityType){
		try {
			return IRegistry.ENTITY_TYPE.a(EntityTypes.a(entityType.getName()).get());
		}catch (Exception exception){
			return 0;
		}
	}
}
