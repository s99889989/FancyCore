package com.daxton.fancycore.nms.v1_16_R3;

import com.daxton.fancycore.FancyCore;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class NMSItem {

	public static String getNBTTagString(ItemStack itemStack, String key){
		net.minecraft.server.v1_16_R3.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
		NBTTagCompound compound = new NBTTagCompound();
		nmsItemStack.save(compound);
		NBTTagCompound compound2 = compound.getCompound("tag");
		//FancyCore.fancyCore.getLogger().info(compound2.toString());
		return compound2.getString(key);
	}

}
