package com.daxton.fancycore.listener;

import com.daxton.fancycore.FancyCore;
import com.daxton.fancycore.api.event.PlayerPackReceivedEvent;

import com.daxton.fancycore.api.fancyclient.ClientConnect;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.jetbrains.annotations.NotNull;

import java.nio.charset.StandardCharsets;

public class ClientListener implements PluginMessageListener {

	@Override
	public void onPluginMessageReceived(@NotNull String channel, @NotNull Player player, @NotNull byte[] message) {
		String receivedString = read(message);
		String[] strings = receivedString.split(" : ");
		if(strings.length == 2){
			PlayerPackReceivedEvent playerPackReceivedEvent = new PlayerPackReceivedEvent(player, strings[0], strings[1]);
			Bukkit.getPluginManager().callEvent(playerPackReceivedEvent);
		}

	}

	//讀取訊息
	private String read(byte[] array) {
		ByteBuf buf = Unpooled.wrappedBuffer(array);
		if (buf.readUnsignedByte() == ClientConnect.IDX) {
			return buf.toString(StandardCharsets.UTF_8);
		} else throw new RuntimeException();
	}

}
