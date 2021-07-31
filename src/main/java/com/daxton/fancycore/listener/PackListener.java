package com.daxton.fancycore.listener;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.*;
import com.comphenix.protocol.injector.GamePhase;
import com.daxton.fancycore.FancyCore;
import com.daxton.fancycore.api.task.PackEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class PackListener implements Listener {

    private final FancyCore fancyCore = FancyCore.fancyCore;

    public ProtocolManager pm;

    public PackListener(){
        pm = ProtocolLibrary.getProtocolManager();
        pm.addPacketListener(new PacketAdapter(PacketAdapter.params().plugin(fancyCore).clientSide().serverSide().listenerPriority(ListenerPriority.NORMAL).gamePhase(GamePhase.PLAYING).optionAsync().options(ListenerOptions.SKIP_PLUGIN_VERIFIER).types(
                PacketType.Play.Client.POSITION, PacketType.Play.Server.ENTITY, PacketType.Play.Server.ENTITY_TELEPORT, PacketType.Play.Server.ENTITY_LOOK, PacketType.Play.Server.SPAWN_ENTITY , PacketType.Play.Server.ENTITY_HEAD_ROTATION)) {

            @Override//玩家發送出來的封包
            public void onPacketReceiving(PacketEvent event) {
                PacketContainer packet = event.getPacket();
                PacketType packetType = event.getPacketType();
                Player player = event.getPlayer();
                if(packetType.equals(PacketType.Play.Client.POSITION)){
//                    int entityID = packet.getIntegers().read(0);
//                    if(entityID == 1470409847){
//                        fancyCore.getLogger().info("出生 實體ID: "+entityID);
//                    }
                    if(player.getName().equals("s99889989")){
                        PackEntity.teleport(1470409847, player.getLocation().add(0,2,0), true, true);

                    }
                }


            }

            @Override//伺服器發送出來的封包
            public void onPacketSending(PacketEvent event) {
                Player player = event.getPlayer();
                PacketContainer packet = event.getPacket();
                PacketType packetType = event.getPacketType();
                if(packetType.equals(PacketType.Play.Server.ENTITY)){
                    int entityID = packet.getIntegers().read(0);
                    if(entityID == 1470409847){
                        fancyCore.getLogger().info("出生 實體ID: "+entityID);
                    }
                }
                if(packetType.equals(PacketType.Play.Server.SPAWN_ENTITY)){
                    int entityID = packet.getIntegers().read(0);
                    if(entityID == 1470409847){
                        fancyCore.getLogger().info("出生 實體ID: "+entityID);
                    }
                }
                if(packetType.equals(PacketType.Play.Server.ENTITY_TELEPORT)){
                    int entityID = packet.getIntegers().read(0);
                    if(entityID == 1470409847){
                        fancyCore.getLogger().info("傳送 實體ID: "+entityID+" : "+player.getName());
                    }

                }
                if(packetType.equals(PacketType.Play.Server.ENTITY_LOOK)){
//                    if(player.getName().equals("s99889989")){
//                        PackEntity.teleport(1470409847, player.getLocation().add(0,2,0), true, true);
//
//                    }
//                    int entityID = packet.getIntegers().read(0);
//                    if(entityID == 1470409847){
//                        fancyCore.getLogger().info("看 實體ID: "+entityID);
//                    }

                }

                if(packetType.equals(PacketType.Play.Server.ENTITY_HEAD_ROTATION)){
                    int entityID = packet.getIntegers().read(0);
                    if(entityID == 1470409847){
                        fancyCore.getLogger().info("頭轉 實體ID: "+entityID);
                    }

                }


            }

        });



    }

}
