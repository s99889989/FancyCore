package com.daxton.fancycore.nms.v1_17_R1;

import net.minecraft.nbt.NBTTagCompound;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class NMSItem {

	public static String getNBTTagString(ItemStack itemStack, String key){
		net.minecraft.world.item.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
		NBTTagCompound compound = new NBTTagCompound();
		nmsItemStack.save(compound);
		NBTTagCompound compound2 = compound.getCompound("tag");
		return compound2.getString(key);
	}

}
