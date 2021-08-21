package com.daxton.fancycore.nms.v1_16_R2;

import com.daxton.fancycore.other.task.PackEntity;
import net.minecraft.server.v1_16_R2.EntityItem;
import net.minecraft.server.v1_16_R2.PacketPlayOutEntityMetadata;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ItemDropMetadata {

	public static void send(int entityID, ItemStack itemStack, Location inputLocation){
		try {
			EntityItem item = new EntityItem(((CraftWorld)inputLocation.getWorld()).getHandle(), 0,0,0);
			item.setLocation(inputLocation.getX(), inputLocation.getY(), inputLocation.getZ(), 0, 0);
			item.setItemStack(CraftItemStack.asNMSCopy(itemStack));
			item.setNoGravity(true);

			Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();
			List<Player> playerList = new ArrayList<>(onlinePlayers);
			playerList.forEach(player -> {
				PacketPlayOutEntityMetadata data = new PacketPlayOutEntityMetadata(entityID, item.getDataWatcher(), true);
				((CraftPlayer) player).getHandle().playerConnection.sendPacket(data);
			});

		}catch (Exception exception){
			//
		}
	}


}
