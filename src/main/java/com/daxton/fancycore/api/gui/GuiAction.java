package com.daxton.fancycore.api.gui;

import org.bukkit.event.inventory.ClickType;

public interface GuiAction {

    public void execute(ClickType clickType, int slot);
}
