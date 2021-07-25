package com.daxton.fancycore.api.task;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.inventory.ClickType;

import java.util.Map;

public interface FancyAction {

    public void execute(LivingEntity self, LivingEntity target, Map<String, String> action_Map, Location inputLocation, String taskID);



}
