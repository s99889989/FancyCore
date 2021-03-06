package com.daxton.fancycore.api.event;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PhysicalDamageEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    final Entity damager;
    final LivingEntity target;
    private double damage;
    private boolean cancelled;
    private String damageType;

    public PhysicalDamageEvent(Entity damager, LivingEntity target, double damage, String damageType){
        this.damager = damager;
        this.target = target;
        this.damage = damage;
        this.damageType = damageType;
        this.cancelled = false;
    }

    public Entity getDamager()
    {
        return damager;
    }

    public LivingEntity getTarget()
    {
        return target;
    }

    public double getDamage()
    {
        return damage;
    }

    public void setDamage(double amount)
    {
        damage = amount;
    }

    @Override
    public boolean isCancelled()
    {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled)
    {
        this.cancelled = cancelled;
    }

    @NotNull
    @Override
    public HandlerList getHandlers()
    {
        return handlers;
    }

    public static HandlerList getHandlerList()
    {
        return handlers;
    }

    public String getDamageType() {
        return damageType;
    }

    public void setDamageType(String damageType) {
        this.damageType = damageType;
    }

}
