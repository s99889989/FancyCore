package com.daxton.fancycore.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PlayerKeyInputEvent extends Event {

	private static final HandlerList handlers = new HandlerList();

	private final Player player;

	private final int keyID;

	private final int keyAction;

	private final String keyName;

	public PlayerKeyInputEvent(Player player, String receivedString){
		this.player = player;
		String[] keyBoardArray = receivedString.split("\\.");
		this.keyID = Integer.parseInt(keyBoardArray[0]);
		this.keyAction = Integer.parseInt(keyBoardArray[1]);
		this.keyName = keyBoardArray[2];

	}

	public Player getPlayer() {
		return player;
	}

	public int getKeyID() {
		return keyID;
	}

	public int getKeyAction() {
		return keyAction;
	}

	public String getKeyName() {
		return keyName;
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
