package com.daxton.fancycore.nms;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import com.daxton.fancycore.other.task.PackEntity;
import com.daxton.fancycore.manager.ProtocolMap;

public class ArmorStandOther {

    //調整盔甲架角度(head, body, leftarm, rightarm, leftleg, rightleg)
    public static void setArmorStandAngle(int entityID, String type, double x, double y, double z){
        String nmsVersion = NMSVersion.getNMSVersion();
        switch (nmsVersion){
            case "v1_13_R1":
                com.daxton.fancycore.nms.v1_13_R1.ArmorStand.setArmorStandAngle(entityID, type, x, y, z);
                break;
            case "v1_13_R2":
                com.daxton.fancycore.nms.v1_13_R2.ArmorStand.setArmorStandAngle(entityID, type, x, y, z);
                break;
            case "v1_14_R1":
                com.daxton.fancycore.nms.v1_14_R1.ArmorStand.setArmorStandAngle(entityID, type, x, y, z);
                break;
            case "v1_15_R1":
                com.daxton.fancycore.nms.v1_15_R1.ArmorStand.setArmorStandAngle(entityID, type, x, y, z);
                break;
            case "v1_16_R1":
                com.daxton.fancycore.nms.v1_16_R1.ArmorStand.setArmorStandAngle(entityID, type, x, y, z);
                break;
            case "v1_16_R2":
                com.daxton.fancycore.nms.v1_16_R2.ArmorStand.setArmorStandAngle(entityID, type, x, y, z);
                break;
            case "v1_16_R3":
                com.daxton.fancycore.nms.v1_16_R3.ArmorStand.setArmorStandAngle(entityID, type, x, y, z);
                break;
            case "v1_17_R1":
                com.daxton.fancycore.nms.v1_17_R1.ArmorStand.setArmorStandAngle(entityID, type, x, y, z);
                break;
            case "v1_18_R1":
                com.daxton.fancycore.nms.v1_18_R1.ArmorStand.setArmorStandAngle(entityID, type, x, y, z);
                break;
            case "v1_18_R2":
                com.daxton.fancycore.nms.v1_18_R2.ArmorStand.setArmorStandAngle(entityID, type, x, y, z);
                break;
        }
    }

    //清除設定
    public static void clear(int entityID){
        PacketContainer packet = ProtocolMap.protocolManager.createPacket(PacketType.Play.Server.ENTITY_METADATA);
        packet.getModifier().writeDefaults();
        packet.getIntegers().write(0, entityID);
        if(NMSVersion.compareNMSVersion("1.17")){
            WrappedDataWatcher metadata = new WrappedDataWatcher();
            metadata.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(15, WrappedDataWatcher.Registry.get(Byte.class)), (byte) (0x00));
            packet.getWatchableCollectionModifier().write(0, metadata.getWatchableObjects());
        }else {
            WrappedDataWatcher watcher = new WrappedDataWatcher();
            WrappedDataWatcher.Serializer serializer = WrappedDataWatcher.Registry.get(Byte.class);
            watcher.setObject(14, serializer, (byte) (0x00));
        }

        PackEntity.sendPack(packet);
    }
    //標記
    public static void marker(int entityID){
        PacketContainer packet = ProtocolMap.protocolManager.createPacket(PacketType.Play.Server.ENTITY_METADATA);

        packet.getModifier().writeDefaults();
        packet.getIntegers().write(0, entityID);

        if(NMSVersion.compareNMSVersion("1.17")){
            WrappedDataWatcher metadata = new WrappedDataWatcher();
            metadata.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(15, WrappedDataWatcher.Registry.get(Byte.class)), (byte) (0x10));
            packet.getWatchableCollectionModifier().write(0, metadata.getWatchableObjects());
        }else {
            WrappedDataWatcher watcher = new WrappedDataWatcher();
            WrappedDataWatcher.Serializer serializer = WrappedDataWatcher.Registry.get(Byte.class);
            watcher.setObject(14, serializer, (byte) (0x10));
            packet.getWatchableCollectionModifier().write(0, watcher.getWatchableObjects());
        }

        PackEntity.sendPack(packet);
    }
    //沒有底板
    public static void noBasePlate(int entityID){
        PacketContainer packet = ProtocolMap.protocolManager.createPacket(PacketType.Play.Server.ENTITY_METADATA);
        packet.getModifier().writeDefaults();
        packet.getIntegers().write(0, entityID);
        if(NMSVersion.compareNMSVersion("1.17")){
            WrappedDataWatcher metadata = new WrappedDataWatcher();
            metadata.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(15, WrappedDataWatcher.Registry.get(Byte.class)), (byte) (0x08));
            packet.getWatchableCollectionModifier().write(0, metadata.getWatchableObjects());
        }else {
            WrappedDataWatcher watcher = new WrappedDataWatcher();
            WrappedDataWatcher.Serializer serializer = WrappedDataWatcher.Registry.get(Byte.class);
            watcher.setObject(14, serializer, (byte) (0x08));
            packet.getWatchableCollectionModifier().write(0, watcher.getWatchableObjects());
        }

        PackEntity.sendPack(packet);
    }
    //有手臂
    public static void hasArms(int entityID){
        PacketContainer packet = ProtocolMap.protocolManager.createPacket(PacketType.Play.Server.ENTITY_METADATA);
        packet.getModifier().writeDefaults();
        packet.getIntegers().write(0, entityID);
        if(NMSVersion.compareNMSVersion("1.17")){
            WrappedDataWatcher metadata = new WrappedDataWatcher();
            metadata.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(15, WrappedDataWatcher.Registry.get(Byte.class)), (byte) (0x04));
            packet.getWatchableCollectionModifier().write(0, metadata.getWatchableObjects());
        }else {
            WrappedDataWatcher watcher = new WrappedDataWatcher();
            WrappedDataWatcher.Serializer serializer = WrappedDataWatcher.Registry.get(Byte.class);
            watcher.setObject(14, serializer, (byte) (0x04));
            packet.getWatchableCollectionModifier().write(0, watcher.getWatchableObjects());
        }

        PackEntity.sendPack(packet);
    }
    //小盔甲架
    public static void small(int entityID){
        PacketContainer packet = ProtocolMap.protocolManager.createPacket(PacketType.Play.Server.ENTITY_METADATA);
        packet.getModifier().writeDefaults();
        packet.getIntegers().write(0, entityID);
        if(NMSVersion.compareNMSVersion("1.17")){
            WrappedDataWatcher metadata = new WrappedDataWatcher();
            metadata.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(15, WrappedDataWatcher.Registry.get(Byte.class)), (byte) (0x01));
            packet.getWatchableCollectionModifier().write(0, metadata.getWatchableObjects());
        }else {
            WrappedDataWatcher watcher = new WrappedDataWatcher();
            WrappedDataWatcher.Serializer serializer = WrappedDataWatcher.Registry.get(Byte.class);
            watcher.setObject(14, serializer, (byte) (0x01));
            packet.getWatchableCollectionModifier().write(0, watcher.getWatchableObjects());
        }
        PackEntity.sendPack(packet);
    }

    public static void equipment(int entityID, boolean small){
        PacketContainer packet = ProtocolMap.protocolManager.createPacket(PacketType.Play.Server.ENTITY_METADATA);
        packet.getModifier().writeDefaults();
        packet.getIntegers().write(0, entityID);

        byte all =  (byte) 0x04 | 0x08;
        if(small){
            all =  (byte) 0x01 | 0x04 | 0x08;
        }
        if(NMSVersion.compareNMSVersion("1.17")){
            WrappedDataWatcher metadata = new WrappedDataWatcher();
            metadata.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(15, WrappedDataWatcher.Registry.get(Byte.class)), all);
           // metadata.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(0, WrappedDataWatcher.Registry.get(Byte.class)), (byte) (0x20));
            packet.getWatchableCollectionModifier().write(0, metadata.getWatchableObjects());
        }else {
            WrappedDataWatcher watcher = new WrappedDataWatcher();
            WrappedDataWatcher.Serializer serializer = WrappedDataWatcher.Registry.get(Byte.class);
            watcher.setObject(14, serializer, all);
            //watcher.setObject(0, serializer, (byte) (0x20));
            packet.getWatchableCollectionModifier().write(0, watcher.getWatchableObjects());
        }
        PackEntity.sendPack(packet);
    }

    public static void holographic(int entityID, boolean marker){
        PacketContainer packet = ProtocolMap.protocolManager.createPacket(PacketType.Play.Server.ENTITY_METADATA);
        packet.getModifier().writeDefaults();
        packet.getIntegers().write(0, entityID);

        byte all =  (byte) 0x01 | 0x08;

        if(marker){
            all =  (byte) 0x01 | 0x08 | 0x10;
        }

        if(NMSVersion.compareNMSVersion("1.17")){
            WrappedDataWatcher metadata = new WrappedDataWatcher();
            metadata.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(15, WrappedDataWatcher.Registry.get(Byte.class)), all);
            metadata.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(0, WrappedDataWatcher.Registry.get(Byte.class)), (byte) (0x20));
            packet.getWatchableCollectionModifier().write(0, metadata.getWatchableObjects());
        }else {
            WrappedDataWatcher watcher = new WrappedDataWatcher();
            WrappedDataWatcher.Serializer serializer = WrappedDataWatcher.Registry.get(Byte.class);
            watcher.setObject(14, serializer, all);
            watcher.setObject(0, serializer, (byte) (0x20));
            packet.getWatchableCollectionModifier().write(0, watcher.getWatchableObjects());
        }
        PackEntity.sendPack(packet);
    }

}
