package com.daxton.fancycore.nms;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

public class ItemDropMetadata {


	public static void send(int entityID, ItemStack itemStack, Location inputLocation){

		String nmsVersion = NMSVersion.getNMSVersion();
		switch (nmsVersion){
			case "v1_13_R1":
				com.daxton.fancycore.nms.v1_13_R1.ItemDropMetadata.send(entityID, itemStack, inputLocation);
				break;
			case "v1_13_R2":
				com.daxton.fancycore.nms.v1_13_R2.ItemDropMetadata.send(entityID, itemStack, inputLocation);
				break;
			case "v1_14_R1":
				com.daxton.fancycore.nms.v1_14_R1.ItemDropMetadata.send(entityID, itemStack, inputLocation);
				break;
			case "v1_15_R1":
				com.daxton.fancycore.nms.v1_15_R1.ItemDropMetadata.send(entityID, itemStack, inputLocation);
				break;
			case "v1_16_R1":
				com.daxton.fancycore.nms.v1_16_R1.ItemDropMetadata.send(entityID, itemStack, inputLocation);
				break;
			case "v1_16_R2":
				com.daxton.fancycore.nms.v1_16_R2.ItemDropMetadata.send(entityID, itemStack, inputLocation);
				break;
			case "v1_16_R3":
				com.daxton.fancycore.nms.v1_16_R3.ItemDropMetadata.send(entityID, itemStack, inputLocation);
				break;
			case "v1_17_R1":
				com.daxton.fancycore.nms.v1_17_R1.ItemDropMetadata.send(entityID, itemStack, inputLocation);
				break;
		}


	}

}
