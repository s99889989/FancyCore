package com.daxton.fancycore.api.gui2;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

interface PlayerFrom{
	InvSize setPlayer(Player player);
}

interface InvSize{
	InvTitle setSize(int size);
}

interface InvTitle{
	InventoryFrom setTitle(String title);
}

interface InventoryFrom{
	GUICreator setInventory(Inventory inventory);
}

interface GUICreator {

	GUI2 build();
}

