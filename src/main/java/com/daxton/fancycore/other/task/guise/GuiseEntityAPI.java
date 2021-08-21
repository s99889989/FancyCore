package com.daxton.fancycore.other.task.guise;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

public class GuiseEntityAPI {

	public static GuiseEntity createGuise(Location location, String entityTypeName, ItemStack itemStack, boolean living, boolean pitch, boolean yaw){
		return new GuiseEntity(location, entityTypeName, itemStack, living, pitch, yaw);
	}

}
