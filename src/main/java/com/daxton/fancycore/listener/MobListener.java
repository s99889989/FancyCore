package com.daxton.fancycore.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class MobListener implements Listener {

	@EventHandler//生物死亡
	public void onMobDeath(EntityDeathEvent event){
		if(event.getEntity().getCustomName() != null && event.getEntity().getCustomName().equals("ModleEngine")){
			event.setDroppedExp(0);
		}


	}

}
