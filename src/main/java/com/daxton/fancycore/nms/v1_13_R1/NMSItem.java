package com.daxton.fancycore.nms.v1_13_R1;

import net.minecraft.server.v1_13_R1.NBTTagCompound;
import org.bukkit.craftbukkit.v1_13_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class NMSItem {

	public static String getNBTTagString(ItemStack itemStack, String key){
		net.minecraft.server.v1_13_R1.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
		NBTTagCompound compound = new NBTTagCompound();
		nmsItemStack.save(compound);
		NBTTagCompound compound2 = compound.getCompound("tag");
		return compound2.getString(key);
	}

}
