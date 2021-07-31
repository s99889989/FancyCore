package com.daxton.fancycore.api.gui;

import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;

public interface GuiAction {

    public void execute(ClickType clickType, InventoryAction inventoryAction, int slot);
}
