package com.daxton.fancycore.other.task;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

import java.util.Map;

public interface FancyAction {

    public void execute(LivingEntity self, LivingEntity target, Map<String, String> action_Map, Location inputLocation, String taskID);



}
