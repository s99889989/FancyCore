package com.daxton.fancycore.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PlayerPackReceivedEvent extends Event{

	private static final HandlerList handlers = new HandlerList();

	private Player player;

	private String type;

	private String received;

	public PlayerPackReceivedEvent(Player player, String type, String received){
		this.player = player;
		this.type = type;
		this.received = received;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public String getReceived() {
		return received;
	}

	public void setReceived(String received) {
		this.received = received;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
	@Override
	public @NotNull
	HandlerList getHandlers() {
		return handlers;
	}
}
