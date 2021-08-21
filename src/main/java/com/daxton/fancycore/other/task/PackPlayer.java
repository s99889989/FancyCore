package com.daxton.fancycore.other.task;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.daxton.fancycore.manager.ProtocolMap;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

public class PackPlayer {

	public static void sendActionBar(Player player, String text, EnumWrappers.TitleAction action, int fadeIn, int time, int fadeOut) {

		PacketContainer packet = ProtocolMap.protocolManager.createPacket(PacketType.Play.Server.TITLE);

		packet.getTitleActions().write(0, action);
		packet.getChatComponents().write(0, WrappedChatComponent.fromText(text));
		packet.getIntegers().write(0, fadeIn);
		packet.getIntegers().write(1, time);
		packet.getIntegers().write(2, fadeOut);

		try {
			ProtocolMap.protocolManager.sendServerPacket(player, packet, false);
		} catch (InvocationTargetException ex) {
			ex.printStackTrace();
		}
	}



}
