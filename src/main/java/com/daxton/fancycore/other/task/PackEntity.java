package com.daxton.fancycore.other.task;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.utility.MinecraftReflection;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.Pair;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import com.daxton.fancycore.manager.ProtocolMap;
import com.daxton.fancycore.nms.ItemDropMetadata;
import com.daxton.fancycore.nms.NMSEntity;
import com.daxton.fancycore.nms.NMSVersion;

import org.bukkit.*;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.lang.reflect.InvocationTargetException;
import java.util.*;


public class PackEntity {

    //生出該類型實體
    public static EntityType entitySpawn(int entityID, UUID uuid, String entityTypeString, Location inputLocation, ItemStack itemStack, boolean pitch, boolean yaw){
        PacketContainer packet = ProtocolMap.protocolManager.createPacket(PacketType.Play.Server.SPAWN_ENTITY);
        packet.getModifier().writeDefaults();
        packet.getIntegers().write(0, entityID);
        packet.getUUIDs().write(0, uuid);
        EntityType entityType;
        try {
            entityType = Enum.valueOf(EntityType.class , entityTypeString.toUpperCase());
            packet.getEntityTypeModifier().write(0, entityType);
        }catch (Exception exception){
            entityType = EntityType.ARMOR_STAND;
            packet.getEntityTypeModifier().write(0, entityType);
        }
        packet.getDoubles().write(0, inputLocation.getX());
        packet.getDoubles().write(1, inputLocation.getY());
        packet.getDoubles().write(2, inputLocation.getZ());
        if(pitch){
            packet.getIntegers().write(4, (int) (inputLocation.getPitch() * 256.0F / 360.0F));
        }
        if(yaw){
            packet.getIntegers().write(5, (int) (inputLocation.getYaw() * 256.0F / 360.0F));
        }
        sendPack(packet);
        velocity(entityID, new Vector());
        if(entityType == EntityType.DROPPED_ITEM){
            if(itemStack == null){
                itemStack = new ItemStack(Material.PLAYER_HEAD);
            }
            ItemDropMetadata.send(entityID, itemStack, inputLocation);
        }
        return entityType;
    }

    //生出該類型生命實體
    public static EntityType livingEntitySpawn(int entityID, UUID uuid, String entityTypeString, Location inputLocation, boolean pitch, boolean yaw){
        PacketContainer packet = ProtocolMap.protocolManager.createPacket(PacketType.Play.Server.SPAWN_ENTITY_LIVING);
        packet.getModifier().writeDefaults();
        packet.getIntegers().write(0, entityID);
        packet.getUUIDs().write(0, uuid);
        EntityType entityType;
        try {
            entityType = Enum.valueOf(EntityType.class , entityTypeString.toUpperCase());
        }catch (Exception exception){
            entityType = EntityType.PIG;
        }

        int entityTypeID = NMSEntity.getEntityID(entityType);

        //FancyCore.fancyCore.getLogger().info("ID2: "+entityTypeID);
        packet.getIntegers().write(1, entityTypeID);
        packet.getDoubles().write(0, inputLocation.getX());
        packet.getDoubles().write(1, inputLocation.getY());
        packet.getDoubles().write(2, inputLocation.getZ());
        if(pitch){
            packet.getIntegers().write(4, (int) (inputLocation.getPitch() * 256.0F / 360.0F));
        }
        if(yaw){
            packet.getIntegers().write(5, (int) (inputLocation.getYaw() * 256.0F / 360.0F));
        }
        sendPack(packet);

        return entityType;
    }

    //生出該類型生命實體
    public static EntityType livingEntitySpawn(int entityID, UUID uuid, int entityTypeID, Location inputLocation, boolean pitch, boolean yaw){
        PacketContainer packet = ProtocolMap.protocolManager.createPacket(PacketType.Play.Server.SPAWN_ENTITY_LIVING);
        packet.getModifier().writeDefaults();
        packet.getIntegers().write(0, entityID);
        packet.getUUIDs().write(0, uuid);


        EntityType entityType = EntityType.fromId(entityTypeID);

        packet.getIntegers().write(1, entityTypeID);


        packet.getDoubles().write(0, inputLocation.getX());
        packet.getDoubles().write(1, inputLocation.getY());
        packet.getDoubles().write(2, inputLocation.getZ());
        if(pitch){
            packet.getIntegers().write(4, (int) (inputLocation.getPitch() * 256.0F / 360.0F));
        }
        if(yaw){
            packet.getIntegers().write(5, (int) (inputLocation.getYaw() * 256.0F / 360.0F));
        }
        sendPack(packet);
        return entityType;
    }

    //刪除目標生物
    public static void delete(int entityID){
        PacketContainer packet = ProtocolMap.protocolManager.createPacket(PacketType.Play.Server.ENTITY_DESTROY);
        if(NMSVersion.compareNMSVersion("1.17")){
            List<Integer> intList = new ArrayList<>();
            intList.add(entityID);
            packet.getIntLists().write(0, intList);
        }else {
            packet.getIntegerArrays().write(0, new int[]{ entityID });
        }
        sendPack(packet);

//        PacketPlayOutEntityDestroy packetPlayOutEntityDestroy = new PacketPlayOutEntityDestroy(entityID);
//        EntityPlayer entityPlayer = (EntityPlayer) ((CraftPlayer) player).getHandle();
//        entityPlayer.b.sendPacket(packetPlayOutEntityDestroy);
    }

    //改變目標生物向量
    public static void velocity(int entityID, Vector vector){
        PacketContainer packet = ProtocolMap.protocolManager.createPacket(PacketType.Play.Server.ENTITY_VELOCITY);
        packet.getIntegers().write(0, entityID);

        packet.getIntegers().write(1, (int) (vector.getX() * 8000.0));
        packet.getIntegers().write(2, (int) (vector.getY() * 8000.0));
        packet.getIntegers().write(3, (int) (vector.getZ() * 8000.0));

        sendPack(packet);
    }

    //修改顯示名稱
    public static void setName(int entityID, String text, Boolean always){
        PacketContainer packet = ProtocolMap.protocolManager.createPacket(PacketType.Play.Server.ENTITY_METADATA);
        packet.getModifier().writeDefaults();
        packet.getIntegers().write(0, entityID);

        WrappedDataWatcher metadata = new WrappedDataWatcher();
        Optional<?> opt = Optional.of(WrappedChatComponent.fromChatMessage(text)[0].getHandle());

        metadata.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(2, WrappedDataWatcher.Registry.getChatComponentSerializer(true)), opt);
        metadata.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(3, WrappedDataWatcher.Registry.get(Boolean.class)), always);

        packet.getWatchableCollectionModifier().write(0, metadata.getWatchableObjects());
        sendPack(packet);
    }

    //騎乘目標
    public static void mount(int entityID, int targetID){
        PacketContainer packet = ProtocolMap.protocolManager.createPacket(PacketType.Play.Server.MOUNT);
        packet.getIntegers().write(0, targetID);
        packet.getIntegerArrays().write(0, new int[]{ entityID });
        sendPack(packet);
        //PacketPlayOutMount packetPlayOutMount;
    }
    //騎乘目標
    public static void mount(int[] entityIDArray, int targetID){
        PacketContainer packet = ProtocolMap.protocolManager.createPacket(PacketType.Play.Server.MOUNT);
        packet.getIntegers().write(0, targetID);
        packet.getIntegerArrays().write(0, entityIDArray);
        sendPack(packet);
        //PacketPlayOutMount packetPlayOutMount;
    }

    //傳送目標
    public static void teleport(int entityID, Location inputLocation, boolean pitch, boolean yaw){
        PacketContainer packet = ProtocolMap.protocolManager.createPacket(PacketType.Play.Server.ENTITY_TELEPORT);
        packet.getIntegers().write(0, entityID);

        packet.getDoubles().write(0, inputLocation.getX());
        packet.getDoubles().write(1, inputLocation.getY());
        packet.getDoubles().write(2, inputLocation.getZ());

        if(yaw){
            packet.getBytes().write(0, (byte)((int)(inputLocation.getYaw() * 256.0F / 360.0F)));
        }
        if(pitch){
            packet.getBytes().write(1, (byte)((int)(inputLocation.getPitch() * 256.0F / 360.0F)));
        }
        sendPack(packet);
    }

    //修改目標方向
    public static void headRotation(int entityID, double yawAdd){
        PacketContainer packet = ProtocolMap.protocolManager.createPacket(PacketType.Play.Server.ENTITY_HEAD_ROTATION);
        packet.getIntegers().write(0, entityID);
        packet.getBytes().write(0, (byte)((int)(yawAdd * 256.0F / 360.0F)));
        sendPack(packet);

        //PacketPlayOutEntity.PacketPlayOutEntityLook packetPlayOutEntityLook;
    }
    //修改觀看方向
    public static void look(int entityID, double yawAdd){
        PacketContainer packet = ProtocolMap.protocolManager.createPacket(PacketType.Play.Server.ENTITY_LOOK);
        packet.getIntegers().write(0, entityID);
        packet.getBytes().write(0, (byte)((int)(yawAdd * 256.0F / 360.0F)));
        sendPack(packet);
    }

    public static void entityRotation(int entityID, double yawAdd){
        PacketContainer packet = ProtocolMap.protocolManager.createPacket(PacketType.Play.Server.ENTITY_HEAD_ROTATION);
        packet.getIntegers().write(0, entityID);
        packet.getBytes().write(0, (byte)((int)(yawAdd * 256.0F / 360.0F)));
        sendPack(packet);
    }

    //修改目標方向
    public static void direction(int entityID, double pitchAdd, double yawAdd, boolean pitch, boolean yaw){
        PacketContainer packet = ProtocolMap.protocolManager.createPacket(PacketType.Play.Server.ENTITY_TELEPORT);
        packet.getIntegers().write(0, entityID);
        //PacketPlayOutEntityTeleport packetPlayOutEntityTeleport;
        if(yaw){
            packet.getBytes().write(0, (byte)((int)(yawAdd * 256.0F / 360.0F)));
        }
        if(pitch){
            packet.getBytes().write(1, (byte)((int)(pitchAdd * 256.0F / 360.0F)));
        }
        sendPack(packet);
    }

    //讓目標帶著鞘翅飛翔
    public static void entityFlyingElytra(int entityID) {
        PacketContainer packet = ProtocolMap.protocolManager.createPacket(PacketType.Play.Server.ENTITY_METADATA);
        packet.getIntegers().write(0, entityID);
        WrappedDataWatcher watcher = new WrappedDataWatcher();
        WrappedDataWatcher.Serializer serializer = WrappedDataWatcher.Registry.get(Byte.class);
        watcher.setObject(0, serializer, (byte) (0x80));
        packet.getWatchableCollectionModifier().write(0, watcher.getWatchableObjects());
        sendPack(packet);
    }

    //沒有AI
    public static void noAI(int entityID) {
        PacketContainer packet = ProtocolMap.protocolManager.createPacket(PacketType.Play.Server.ENTITY_METADATA);
        packet.getIntegers().write(0, entityID);
        WrappedDataWatcher metadata = new WrappedDataWatcher();
        metadata.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(15, WrappedDataWatcher.Registry.get(Byte.class)), (byte) (0x01));
        packet.getWatchableCollectionModifier().write(0, metadata.getWatchableObjects());
        sendPack(packet);
    }

    //設置史萊姆大小
    public static void slimeSize(int entityID, int size) {
        PacketContainer packet = ProtocolMap.protocolManager.createPacket(PacketType.Play.Server.ENTITY_METADATA);
        packet.getIntegers().write(0, entityID);
        WrappedDataWatcher metadata = new WrappedDataWatcher();
        metadata.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(15, WrappedDataWatcher.Registry.get(Integer.class)), size);
        packet.getWatchableCollectionModifier().write(0, metadata.getWatchableObjects());
        sendPack(packet);
    }

    //讓目標發光
    public static void entityGlowingEffect(int entityID) {
        PacketContainer packet = ProtocolMap.protocolManager.createPacket(PacketType.Play.Server.ENTITY_METADATA);
        packet.getIntegers().write(0, entityID);
        WrappedDataWatcher watcher = new WrappedDataWatcher();
        WrappedDataWatcher.Serializer serializer = WrappedDataWatcher.Registry.get(Byte.class);
        watcher.setObject(0, serializer, (byte) (0x40));
        packet.getWatchableCollectionModifier().write(0, watcher.getWatchableObjects());
        sendPack(packet);
    }

    //讓目標游泳
    public static void entitySwimming(int entityID) {
        PacketContainer packet = ProtocolMap.protocolManager.createPacket(PacketType.Play.Server.ENTITY_METADATA);
        packet.getIntegers().write(0, entityID);
        WrappedDataWatcher watcher = new WrappedDataWatcher();
        WrappedDataWatcher.Serializer serializer = WrappedDataWatcher.Registry.get(Byte.class);
        watcher.setObject(0, serializer, (byte) (0x10));
        packet.getWatchableCollectionModifier().write(0, watcher.getWatchableObjects());
        sendPack(packet);
    }

    //讓目標衝刺
    public static void entitySprinting(int entityID) {
        PacketContainer packet = ProtocolMap.protocolManager.createPacket(PacketType.Play.Server.ENTITY_METADATA);
        packet.getIntegers().write(0, entityID);
        WrappedDataWatcher watcher = new WrappedDataWatcher();
        WrappedDataWatcher.Serializer serializer = WrappedDataWatcher.Registry.get(Byte.class);
        watcher.setObject(0, serializer, (byte) (0x08));
        packet.getWatchableCollectionModifier().write(0, watcher.getWatchableObjects());
        sendPack(packet);
    }

    //讓目標著火
    public static void entityFire(int entityID) {
        PacketContainer packet = ProtocolMap.protocolManager.createPacket(PacketType.Play.Server.ENTITY_METADATA);
        packet.getIntegers().write(0, entityID);
        WrappedDataWatcher watcher = new WrappedDataWatcher();
        WrappedDataWatcher.Serializer serializer = WrappedDataWatcher.Registry.get(Byte.class);
        watcher.setObject(0, serializer, (byte) (0x01));
        packet.getWatchableCollectionModifier().write(0, watcher.getWatchableObjects());
        sendPack(packet);
    }

    //讓目標蹲下
    public static void entityCrouching(int entityID) {
        PacketContainer packet = ProtocolMap.protocolManager.createPacket(PacketType.Play.Server.ENTITY_METADATA);
        packet.getIntegers().write(0, entityID);
        WrappedDataWatcher watcher = new WrappedDataWatcher();
        WrappedDataWatcher.Serializer serializer = WrappedDataWatcher.Registry.get(Byte.class);
        //watcher.setEntity(livingEntity);
        watcher.setObject(0, serializer, (byte) (0x02));
        packet.getWatchableCollectionModifier().write(0, watcher.getWatchableObjects());
        sendPack(packet);
    }

    //設定裝備顯示(部位:HEAD,MAINHAND,OFFHAND,CHEST,LEGS,FEET)
    public static void equipmentInvisible(int entityID, ItemStack itemStack, String itemSlot, Player inPlayer){
        PacketContainer packet = ProtocolMap.protocolManager.createPacket(PacketType.Play.Server.ENTITY_EQUIPMENT);
        EnumWrappers.ItemSlot itemSlot1;
        try {
            itemSlot1 = Enum.valueOf(EnumWrappers.ItemSlot.class, itemSlot.toUpperCase());
        }catch (Exception exception){
            itemSlot1 = EnumWrappers.ItemSlot.HEAD;
        }

        if(NMSVersion.compareNMSVersion("1.16")){
            packet.getIntegers().write(0, entityID);
            List<Pair<EnumWrappers.ItemSlot, ItemStack>> pairList = new ArrayList<>();
            pairList.add(new Pair<>(itemSlot1, itemStack));
            packet.getSlotStackPairLists().write(0, pairList);
        }else {
            packet.getIntegers().write(0, entityID);
            packet.getItemSlots().write(0, itemSlot1);
            packet.getItemModifier().write(0, itemStack);
        }

        sendPackRemoveSelf(packet, inPlayer);
    }

    //讓目標(一些狀態還原，像是隱形 飛行 等等)
    public static void entityEmpty(int entityID) {
        PacketContainer packet = ProtocolMap.protocolManager.createPacket(PacketType.Play.Server.ENTITY_METADATA);
        packet.getIntegers().write(0, entityID);
        WrappedDataWatcher watcher = new WrappedDataWatcher();
        WrappedDataWatcher.Serializer serializer = WrappedDataWatcher.Registry.get(Byte.class);
        watcher.setObject(0, serializer, (byte) (0x20));
        packet.getWatchableCollectionModifier().write(0, watcher.getWatchableObjects());
        sendPack(packet);
    }

    //讓目標隱形
    public static void entityInvisible(int entityID) {
        PacketContainer packet = ProtocolMap.protocolManager.createPacket(PacketType.Play.Server.ENTITY_METADATA);
        packet.getIntegers().write(0, entityID);
        WrappedDataWatcher watcher = new WrappedDataWatcher();
        WrappedDataWatcher.Serializer serializer = WrappedDataWatcher.Registry.get(Byte.class);
        watcher.setObject(0, serializer, (byte) (0x20));
        packet.getWatchableCollectionModifier().write(0, watcher.getWatchableObjects());
        sendPack(packet);
    }

    //建立隊伍
    public static void createTeam(UUID uuid, ChatColor color, String teamName){
        PacketContainer packet = ProtocolMap.protocolManager.createPacket( PacketType.Play.Server.SCOREBOARD_TEAM );

        packet.getStrings().write(0, teamName);
        packet.getIntegers().write(0, 0);

        packet.getEnumModifier(ChatColor.class, MinecraftReflection.getMinecraftClass("EnumChatFormat")).write(0, color);
        packet.getStrings().write(1, "ALWAYS");
        packet.getStrings().write(2, "never");
        packet.getSpecificModifier(Collection.class).write(0, Arrays.asList( new String[]{uuid.toString()} ));
        //ScoreboardTeam scoreboardTeam;
        sendPack(packet);

    }

    //標記目標，使目標發光
    public static void setGlowing(int entityID) {

        PacketContainer packet = ProtocolMap.protocolManager.createPacket(PacketType.Play.Server.ENTITY_METADATA);
        packet.getIntegers().write(0, entityID);

        WrappedDataWatcher watcher = new WrappedDataWatcher();
        WrappedDataWatcher.Serializer serializer = WrappedDataWatcher.Registry.get(Byte.class);
        //watcher.setEntity(livingEntity);
        watcher.setObject(0, serializer, (byte) (0x40));
        packet.getWatchableCollectionModifier().write(0, watcher.getWatchableObjects());

        sendPack(packet);

    }

    //將生物添加到隊伍中
    public static void addEntity(UUID uuid, ChatColor color, String teamName){
        PacketContainer packet = ProtocolMap.protocolManager.createPacket( PacketType.Play.Server.SCOREBOARD_TEAM );

        packet.getStrings().write(0, teamName);
        packet.getIntegers().write(0, 3);

        packet.getEnumModifier(ChatColor.class, MinecraftReflection.getMinecraftClass("EnumChatFormat")).write(0, color);
        packet.getStrings().write(1, "ALWAYS");
        packet.getSpecificModifier(Collection.class).write(0, Arrays.asList( new String[]{uuid.toString()} ));
        sendPack(packet);
    }

    //更新隊伍訊息
    public static void upTeam(UUID uuid, ChatColor color, String teamName){
        PacketContainer packet = ProtocolMap.protocolManager.createPacket( PacketType.Play.Server.SCOREBOARD_TEAM );

        packet.getStrings().write(0, teamName);
        packet.getIntegers().write(0, 2);

        packet.getEnumModifier(ChatColor.class, MinecraftReflection.getMinecraftClass("EnumChatFormat")).write(0, color);
        packet.getStrings().write(1, "ALWAYS");
        packet.getStrings().write(2, "never");
        packet.getSpecificModifier(Collection.class).write(0, Arrays.asList( new String[]{uuid.toString()} ));
        sendPack(packet);
    }

    //發送封包
    public static void sendPack(PacketContainer packet){
        Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();
        List<Player> playerList = new ArrayList<>(onlinePlayers);
        playerList.forEach(player -> {
            try {
                ProtocolMap.protocolManager.sendServerPacket( player, packet );
            } catch ( InvocationTargetException exception ) {
                exception.printStackTrace();
            }
        });

    }

    //發送封包
    public static void sendPackRemoveSelf(PacketContainer packet, Player inPlayer){
        Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();
        List<Player> playerList = new ArrayList<>(onlinePlayers);
        if(inPlayer != null){
            playerList.remove(inPlayer);
        }
        playerList.forEach(player -> {
            try {
                ProtocolMap.protocolManager.sendServerPacket( player, packet );
            } catch ( InvocationTargetException exception ) {
                exception.printStackTrace();
            }
        });

    }

}
