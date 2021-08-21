package com.daxton.fancycore.other.hologram;

import org.bukkit.Location;

public class FHologramsAPI {

	public static FHologram createHologram(Location location) {
		return new FHologram(location);
	}

}
