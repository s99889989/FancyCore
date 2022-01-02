package com.daxton.fancycore.listener;

import com.daxton.fancycore.FancyCore;
import com.daxton.fancycore.api.fancyclient.ClientConnect;
import com.daxton.fancycore.config.FileConfig;
import com.daxton.fancycore.manager.PlayerManagerCore;
import com.daxton.fancycore.other.playerdata.ItemCD;
import com.daxton.fancycore.other.playerdata.PlayerDataFancy;
import com.daxton.fancycore.other.task.PackEntity;
import com.daxton.fancycore.other.task.guise.GuiseEntity;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class PlayerListener implements Listener {

    //public static GuiseEntity guiseEntity;

    @EventHandler//當玩家登入
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        PlayerManagerCore.player_Data_Map.putIfAbsent(uuid, new PlayerDataFancy(player));
        PlayerDataFancy playerDataFancy = PlayerManagerCore.player_Data_Map.get(uuid);
        //檢測玩家版本，並記錄
        playerDataFancy.setPlayer_version();
        //清除玩家有關屬性
        playerDataFancy.removeAllAttribute();

    }

    @EventHandler(priority = EventPriority.MONITOR)//玩家登出
    public void onPlayerQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        //儲存物品欄
        PlayerManagerCore.player_Data_Map.get(uuid).saveSlotItem();
        //從Map移除玩家資料
        new BukkitRunnable() {
            @Override
            public void run() {
                PlayerManagerCore.player_Data_Map.remove(uuid);
            }
        }.runTaskLater(FancyCore.fancyCore, 2);



    }

    //當玩家聊天
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onChat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        String chatMessage = event.getMessage();
        //玩家的聊天訊息
        PlayerDataFancy playerDataFancy = PlayerManagerCore.player_Data_Map.get(uuid);
        playerDataFancy.player_chat = chatMessage;

    }

    @EventHandler(priority = EventPriority.LOW)//當玩家點擊時
    public void onPlayerInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        Action action = event.getAction();
        Block block = event.getClickedBlock();
        EquipmentSlot equipmentSlot = event.getHand();

        PlayerDataFancy playerDataFancy = PlayerManagerCore.player_Data_Map.get(uuid);

        //左鍵點擊方塊
        if(action == Action.LEFT_CLICK_BLOCK){
            if(block != null){
                String worldName = block.getWorld().getName();
                int x = (int) block.getLocation().getX();
                int y = (int) block.getLocation().getY();
                int z = (int) block.getLocation().getZ();
                //點擊的方塊位置
                playerDataFancy.block_location = worldName+x+y+z;
                //玩家左鍵點擊的方塊
                playerDataFancy.left_click_block = block.getType().name();
            }
            return;
        }
        //確定是主手
        if(equipmentSlot == EquipmentSlot.HAND){
            //右鍵點擊方塊
            if(action == Action.RIGHT_CLICK_BLOCK){
                if(block != null){
                    if(block.getType().toString().endsWith("_BUTTON")){
                        String worldName = block.getWorld().getName();
                        int x = (int) block.getLocation().getX();
                        int y = (int) block.getLocation().getY();
                        int z = (int) block.getLocation().getZ();
                        //玩家點擊按鈕位置
                        playerDataFancy.button_location = worldName+x+y+z;
                        return;
                    }
                    if(block.getType() == Material.LEVER){
                        String worldName = block.getWorld().getName();
                        int x = (int) block.getLocation().getX();
                        int y = (int) block.getLocation().getY();
                        int z = (int) block.getLocation().getZ();
                        //玩家點擊拉桿位置
                        playerDataFancy.lever_location = worldName+x+y+z;
                        return;
                    }
                    String worldName = block.getWorld().getName();
                    int x = (int) block.getLocation().getX();
                    int y = (int) block.getLocation().getY();
                    int z = (int) block.getLocation().getZ();
                    //點擊的方塊位置
                    playerDataFancy.block_location = worldName+x+y+z;
                    //玩家右鍵點擊的方塊
                    playerDataFancy.right_click_block = block.getType().name();
                    return;
                }
            }
        }

        if(action == Action.PHYSICAL){
            if (block != null) {
                if(block.getType().toString().endsWith("_PRESSURE_PLATE")){
                    String worldName = block.getWorld().getName();
                    int x = (int) block.getLocation().getX();
                    int y = (int) block.getLocation().getY();
                    int z = (int) block.getLocation().getZ();
                    //玩家踩壓力板的位置
                    playerDataFancy.plate_location = worldName+x+y+z;
                }
            }
        }

    }

    //當玩家回血
    @EventHandler
    public void onRegainHealth(EntityRegainHealthEvent event){
        Entity entity = event.getEntity();
        if(entity instanceof Player){
            FileConfiguration coreConfig = FileConfig.config_Map.get("Other/CustomCore.yml");
            boolean hungerReg = coreConfig.getBoolean("Health_Hunger_Regeneration.enable");
            if(!hungerReg){
                if(event.getRegainReason() == EntityRegainHealthEvent.RegainReason.SATIATED){
                    event.setCancelled(true);
                }
            }
            //Player player = (Player) entity;
            //player.sendMessage("回血: "+event.getRegainReason().name());
        }
    }

//    //當玩家飢餓度下降
//    @EventHandler
//    public void onHunger(FoodLevelChangeEvent event){
//        Player player = (Player) event.getEntity();
//        player.sendMessage("飢餓下降"+event.getFoodLevel());
//
//    }

}
