package com.daxton.fancycore.api.gui;

import com.daxton.fancycore.FancyCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class GUI extends GUISlotItem {

    //使用玩家UUID字串，把GUI儲存到Map
    public static Map<String, GUI> guiID_gui_Map = new HashMap<>();
    //public static Map<String, List<String>> uuid_GuiID_Map = new HashMap<>();
    //按鍵動作
    private GuiCloseAction guiCloseAction;
    //玩家
    private Player player;
    //GUI標題
    private String title;
    //開關狀態
    private boolean open = false;

    //建立自訂GUI，大小為9 18 27 36 45 54
    public static GUI createGui(Player player, int size, String title){
        return new GUI(player, size, title);
    }

    //建立自訂GUI，大小為9 18 27 36 45 54
    public GUI(Player player, int size, String title){
        this.player = player;

        String uuidString = player.getUniqueId().toString();

        this.title = title;
        inventory = Bukkit.createInventory(null, size , title);

        guiID_gui_Map.put(uuidString, this);
    }
    //修改GUI標題
    public void setTitle(String title){
        this.title = title;
        int size = inventory.getSize();
        ItemStack[] itemStacks = inventory.getContents();
        inventory = Bukkit.createInventory(null, size , title);
        inventory.setContents(itemStacks);
    }
    //修改GUI大小，大小為9 18 27 36 45 54
    public void setSize(int size){
        ItemStack[] itemStacks = inventory.getContents();
        ItemStack[] newItemStacks = new ItemStack[size];
        for(int i = 0; i < size; i++){
            if(i >= itemStacks.length){
                break;
            }
            newItemStacks[i] = itemStacks[i];
        }
        inventory = Bukkit.createInventory(null, size , title);
        inventory.setContents(newItemStacks);
    }

    //指定玩家打開GUI
    public void open(GUI gui){
        String uuidString = player.getUniqueId().toString();
        guiID_gui_Map.put(uuidString, gui);
        player.openInventory(inventory);
        open = true;
        //GUI.on_Gui.put(player.getUniqueId().toString(), true);
    }

    public void close(){
        if(player != null){
            player.closeInventory();
        }
    }

    //-----------------------------------------------------------------------------------------------//
    //動作
    //-----------------------------------------------------------------------------------------------//
    //設置關閉GUI時的動作
    public void setCloseAction(GuiCloseAction guiAction){
        this.guiCloseAction = guiAction;
    }
    public void removeCloseAction(){ this.guiCloseAction = null; }
    public GuiCloseAction getCloseAction(){
        return this.guiCloseAction;
    }

    //在指定格數使用動作，vertical為1~6，horizontal為1~9
    public void setAction(GuiAction guiAction, int vertical, int horizontal){
        action_Map.put(itemLocation(vertical, horizontal, 54), guiAction);
    }
    //在指定格數使用動作，位置為1~81。
    public void setAction(GuiAction guiAction, int place){
        if(place < 1)
            place = 1;
        if(place > 90)
            place = 90;
        action_Map.put(place-1, guiAction);
    }
    //指定範範圍的空位置，插入動作，
    public void addAction(GuiAction guiAction, int head, int tail, List<Integer> ignore){
        if(tail > head){
            if( checkInventorySize(head-1, inventory.getSize()+36) && checkInventorySize(tail-1, inventory.getSize()+36) ){
                for(int i = head; i < tail+1 ; i++){
                    if(getAction(i) == null && !ignore.contains(i)){
                        //FancyCore.fancyCore.getLogger().info("XX: "+i);
                        setAction(guiAction, i);
                        break;
                    }
                }
            }
        }
    }
    //移除指定位置動作，vertical為1~6，horizontal為1~9
    public void removeAction(int vertical, int horizontal){
        action_Map.remove(itemLocation(vertical, horizontal, 54));
    }
    //移除指定位置動作，位置為1~54。
    public void removeAction(int place){
        if(place < 1)
            place = 1;
        if(place > 54)
            place =54;
        action_Map.remove(place-1);
    }
    //獲取指定位置動作，vertical為1~6，horizontal為1~9
    public GuiAction getAction(int vertical, int horizontal){
        return action_Map.get(itemLocation(vertical, horizontal, 54));
    }
    //獲取指定位置動作，位置為1~54。
    public GuiAction getAction(int place){
        if(place < 1)
            place = 1;
        if(place > 90)
            place = 90;
        return action_Map.get(place-1);
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isOpen() {
        return open;
    }
}
