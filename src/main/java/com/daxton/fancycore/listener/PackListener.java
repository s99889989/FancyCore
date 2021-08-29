package com.daxton.fancycore.listener;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.*;
import com.comphenix.protocol.injector.GamePhase;
import com.daxton.fancycore.FancyCore;
import com.daxton.fancycore.manager.PlayerManagerCore;
import com.daxton.fancycore.manager.ProtocolMap;
import com.daxton.fancycore.other.playerdata.PlayerDataFancy;
import net.minecraft.server.v1_16_R3.PacketPlayOutEntity;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

import static com.comphenix.protocol.wrappers.EnumWrappers.ChatType.GAME_INFO;

public class PackListener implements Listener {

    private final FancyCore fancyCore = FancyCore.fancyCore;

    public ProtocolManager pm;

    public PackListener(){
        pm = ProtocolLibrary.getProtocolManager();
        pm.addPacketListener(new PacketAdapter(PacketAdapter.params().plugin(fancyCore).clientSide().serverSide().listenerPriority(ListenerPriority.NORMAL).gamePhase(GamePhase.PLAYING).optionAsync().options(ListenerOptions.SKIP_PLUGIN_VERIFIER).types(
            PacketType.Play.Server.CHAT, PacketType.Play.Server.WORLD_PARTICLES)) {

            @Override//玩家發送出來的封包
            public void onPacketReceiving(PacketEvent event) {
                PacketContainer packet = event.getPacket();
                PacketType packetType = event.getPacketType();
                Player player = event.getPlayer();


            }

            //傳送目標
            public void teleport(Location inputLocation, Player player){
                PacketContainer packet = ProtocolMap.protocolManager.createPacket(PacketType.Play.Client.POSITION);
                packet.getIntegers().write(0, 123456);

                packet.getDoubles().write(0, inputLocation.getX());
                packet.getDoubles().write(1, inputLocation.getY());
                packet.getDoubles().write(2, inputLocation.getZ());

                try {
                    ProtocolMap.protocolManager.sendServerPacket( player, packet );
                } catch ( InvocationTargetException exception ) {
                    exception.printStackTrace();
                }

            }

            @Override//伺服器發送出來的封包
            public void onPacketSending(PacketEvent event) {
                Player player = event.getPlayer();
                PacketContainer packet = event.getPacket();
                PacketType packetType = event.getPacketType();

                //攔截ActionBar
                if(packetType.equals(PacketType.Play.Server.CHAT)){
                    if(packet.getChatTypes().read(0) == GAME_INFO){
                        UUID uuid = player.getUniqueId();
                        PlayerDataFancy playerDataFancy = PlayerManagerCore.player_Data_Map.get(uuid);
                        event.setCancelled(playerDataFancy.actionBar_remove);
                    }
                }
                //攔截粒子
                if(packetType.equals(PacketType.Play.Server.WORLD_PARTICLES)){
                    Particle type = packet.getNewParticles().read(0).getParticle();
                    UUID uuid = player.getUniqueId();
                    PlayerDataFancy playerDataFancy = PlayerManagerCore.player_Data_Map.get(uuid);
                    if(!playerDataFancy.particles_remove.isEmpty()){
                        if(playerDataFancy.particles_remove.contains(type.toString())){
                            event.setCancelled(true);
                        }
                    }
                }
            }

        });



    }

}
