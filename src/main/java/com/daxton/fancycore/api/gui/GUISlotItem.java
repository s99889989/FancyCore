package com.daxton.fancycore.api.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GUISlotItem{

    //包包
    protected Inventory inventory;
    //按鍵是否可以移動
    protected Map<Integer, Boolean> itme_Move = new ConcurrentHashMap<>();
    //按鍵動作
    protected Map<Integer, GuiAction> action_Map = new HashMap<>();
    //是否全部不能移動
    protected boolean allMove = false;

    //設置該位置是否可以移動，vertical為1~6，horizontal為1~9
    public void setMove(boolean move, int vertical, int horizontal){
        itme_Move.put(itemLocation(vertical, horizontal, 54), !move);
    }
    //設置該位置是否可以移動，位置為1~54。
    public void setMove(boolean move, int place){
        if(checkInventorySize(place-1, 54)){
            itme_Move.put(place-1, !move);
        }
    }
    public void setMoveAll(boolean move){
        allMove = !move;
    }
    //在指定位置放置物品，並設置是否可以移動，vertical為1~6，horizontal為1~9
    public void setItem(ItemStack itemStack, boolean move, int vertical, int horizontal){
        if(checkInventorySize(itemLocation(vertical, horizontal, inventory.getSize()), inventory.getSize())){
            inventory.setItem(itemLocation(vertical, horizontal, inventory.getSize()), itemStack);
            itme_Move.put(itemLocation(vertical, horizontal, 54), !move);
        }

    }
    //在指定位置放置物品，並設置是否可以移動
    public void setItem(ItemStack itemStack, boolean move, int place){
        if(checkInventorySize(place-1, inventory.getSize())){
            inventory.setItem(place-1, itemStack);
            itme_Move.put(place-1, !move);
        }
    }
    //指定範範圍的空位置，插入物品，
    public void addItem(ItemStack itemStack, boolean move, int head, int tail){
        if(tail > head){
            if( checkInventorySize(head-1, inventory.getSize()) && checkInventorySize(tail-1, inventory.getSize()) ){
                for(int i = head-1; i < tail ; i++){
                    if(inventory.getItem(i) == null){
                        inventory.setItem(i, itemStack);
                        break;
                    }
                }
            }
        }
    }
    //移除指定位置物品，vertical為1~6，horizontal為1~9
    public void removeItem(boolean move, int vertical, int horizontal){
        if(checkInventorySize(itemLocation(vertical, horizontal, inventory.getSize()), inventory.getSize())){
            inventory.setItem(itemLocation(vertical, horizontal, inventory.getSize()), null);
            itme_Move.put(itemLocation(vertical, horizontal, 54), !move);
        }
    }
    //移除指定位置物品，位置為1~54。
    public void removeItem(boolean move, int place){
        if(checkInventorySize(place-1, inventory.getSize())){
            inventory.setItem(place-1, null);
        }
    }
    //確定設置的位置是否不超過指定大小
    public boolean checkInventorySize(int place, int size){
        return place < size && place >= 0;
    }

    //清除所有物品
    public void clearItem(){
        inventory.clear();
    }
    //從指定位置開始往後清除物品，位置為1~54。
    public void clearFrom(int head, int tail){
        if(tail > head){
            if( checkInventorySize(head-1, inventory.getSize()) && checkInventorySize(tail-1, inventory.getSize()) ){
                for(int i = head-1; i < tail ; i++){
                    inventory.setItem(i, null);
                }
            }
        }
    }
    //獲取指定位置物品，vertical為1~6，horizontal為1~9
    public ItemStack getItem(int vertical, int horizontal){
        ItemStack itemStack = null;
        if(checkInventorySize(itemLocation(vertical, horizontal, inventory.getSize()), inventory.getSize())){
            itemStack = inventory.getItem(itemLocation(vertical, horizontal, inventory.getSize()));
        }
        return itemStack;
    }
    //獲取指定位置物品，位置為1~54。
    public ItemStack getItem(int place){
        ItemStack itemStack = null;
        if(checkInventorySize(place-1, inventory.getSize())){
            itemStack = inventory.getItem(place-1);
        }
        return itemStack;
    }
    //給出粽橫GUI位置
    public int itemLocation(int vertical, int horizontal,int size){
        if(vertical < 1){
            vertical = 1;
        }
        if(vertical > 6){
            vertical = 6;
        }
        if(horizontal < 1){
            horizontal = 1;
        }
        if(horizontal > 9){
            horizontal = 9;
        }

        int itemLocation = ((vertical-1)*9)+(horizontal-1);
        if(itemLocation+1 > size){
            itemLocation = size-1;
        }
        return itemLocation;
    }



    //**GUI動作監聽
    public static void onInventoryClick(InventoryClickEvent event){

        Player player = (Player) event.getWhoClicked();
        String uuidString = player.getUniqueId().toString();
        ClickType clickType = event.getClick();
        InventoryAction action = event.getAction();
        int slot = event.getRawSlot();
        //FancyCore.fancyCore.getLogger().info(slot+"");
        if(GUI.uuid_GuiID_Map.get(uuidString) != null){
            GUI.uuid_GuiID_Map.get(uuidString).forEach(guiID -> {
                GUI gui = GUI.guiID_gui_Map.get(guiID);
                event.setCancelled(gui.allMove);
                if(gui.itme_Move.get(slot) != null){
                    event.setCancelled(gui.itme_Move.get(slot));
                }
                if(gui.action_Map.get(slot) != null){
                    gui.action_Map.get(slot).execute(clickType, action, slot);
                }
            });

        }


    }

    //**GUI關閉監聽
    public static void onInventoryClose(InventoryCloseEvent event){
        Player player = (Player) event.getPlayer();

        String uuidString = player.getUniqueId().toString();
        if(GUI.uuid_GuiID_Map.get(uuidString) != null){
            GUI.uuid_GuiID_Map.get(uuidString).forEach(guiID -> {
                GUI gui = GUI.guiID_gui_Map.get(guiID);
                if(gui.getCloseAction() != null){
                    gui.getCloseAction().execute();
                }
            });
        }

    }

}
