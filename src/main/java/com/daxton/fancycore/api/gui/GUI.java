package com.daxton.fancycore.api.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class GUI {
    //使用玩家UUID字串，判斷是否為GUI
    public static Map<String, Boolean> on_Gui = new HashMap<>();
    //使用玩家UUID字串，把GUI儲存到Map
    public static Map<String, GUI> gui_Map = new HashMap<>();
    //按鍵動作
    private Map<Integer, GuiAction> action_Map = new HashMap<>();
    //按鍵是否可以移動
    private Map<Integer, Boolean> itme_Move = new HashMap<>();
    //使否全部不能移動
    private boolean allMove = false;

    private Inventory inventory;
    //GUI標題
    private String title;

    //建立預設GUI
    public GUI(){
        inventory = Bukkit.createInventory(null, 9 , "");
    }
    //建立自訂GUI
    public GUI(int size, String title){
        this.title = title;
        inventory = Bukkit.createInventory(null, size , title);
    }
    //修改GUI標題
    public void setTitle(String title){
        this.title = title;
        int size = inventory.getSize();
        ItemStack[] itemStacks = inventory.getContents();
        inventory = Bukkit.createInventory(null, size , title);
        inventory.setContents(itemStacks);
    }
    //修改GUI大小
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
    //在指定格數使用動作
    public void setAction(GuiAction guiAction, int vertical, int horizontal){
        action_Map.put(itemLocation(vertical, horizontal), guiAction);
    }
    //獲取指定位置動作
    public GuiAction getAction(int vertical, int horizontal){
        return action_Map.get(itemLocation(vertical, horizontal));
    }
    public void setMove(boolean move, int vertical, int horizontal){
        itme_Move.put(itemLocation(vertical, horizontal), !move);
    }
    //設置全部物品移動
    public void setAllMove(boolean move){
        allMove = !move;
    }

    //指定玩家打開GUI
    public void open(Player player){
        player.openInventory(inventory);
        GUI.on_Gui.put(player.getUniqueId().toString(), true);
    }

    //在指定位置放置物品
    public void setItem(ItemStack itemStack, boolean move, int vertical, int horizontal){
        inventory.setItem(itemLocation(vertical, horizontal), itemStack);
        itme_Move.put(itemLocation(vertical, horizontal), !move);
    }
    //移除指定位置物品
    public void removeItem(int vertical, int horizontal){
        inventory.setItem(itemLocation(vertical, horizontal), null);
    }
    //在指定位置放置物品
    public void addItem(ItemStack itemStack, boolean move, int position){
        int size = inventory.getSize();
        if(position <= size){
            inventory.setItem(position-1, itemStack);
            itme_Move.put(position-1, !move);
        }
    }
    //清除所有物品
    public void clearItem(){
        inventory.clear();
    }
    //從指定位置開始往後清除物品
    public void clearFrom(int index){
        for(int i = index-1; i < 54 ; i++){
            if(i >= inventory.getSize()){
                break;
            }
            inventory.setItem(i, null);
        }
    }

    //給出粽橫GUI位置
    public int itemLocation(int vertical, int horizontal){
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
        if(itemLocation+1 > inventory.getSize()){
            itemLocation = inventory.getSize()-1;
        }
        return itemLocation;
    }

    //**GUI動作監聽
    public static void onInventoryClick(InventoryClickEvent event){

        Player player = (Player) event.getWhoClicked();

        ClickType clickType = event.getClick();
        int rawSlot = event.getRawSlot();
        GUI gui = GUI.gui_Map.get(player.getUniqueId().toString());
        event.setCancelled(gui.allMove);
        if(gui.itme_Move.get(rawSlot) != null){
            event.setCancelled(gui.itme_Move.get(rawSlot));
        }
        if(gui.action_Map.get(rawSlot) != null){
            gui.action_Map.get(rawSlot).execute(clickType);
        }
    }

}
