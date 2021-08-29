package com.daxton.fancycore.other.task;

import com.daxton.fancycore.FancyCore;
import com.daxton.fancycore.nms.NMSVersion;
import org.bukkit.entity.EntityType;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;

public class EntityID {

	public static EntityIdFetcher fetcher;

	public static void setFetcher(){
		fetcher = new EntityIdFetcher("1.16.5");
		FancyCore.fancyCore.getLogger().info("Fetcher1"+NMSVersion.getMinecraftVersion());
		FancyCore.fancyCore.getLogger().info("Fetcher2");

		new BukkitRunnable() {
			public void run() {
				try {
					FancyCore.fancyCore.getLogger().info("Fetcher3");
					fetcher.fetch();
					FancyCore.fancyCore.getLogger().info("Fetcher4: "+EntityID.fetcher.getId(EntityType.SLIME));
				}catch (IOException exception){
					exception.printStackTrace();
				}
			}
		}.runTaskAsynchronously(FancyCore.fancyCore);

	}


}
