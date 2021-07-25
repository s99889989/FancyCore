package com.daxton.fancycore.api.task;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.Pair;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import com.daxton.fancycore.nms.v1_17_R1.ArmorStand;
import com.daxton.fancycore.protocol.ProtocolMap;
import net.minecraft.core.Vector3f;
import net.minecraft.network.protocol.game.PacketPlayOutEntityMetadata;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class ArmorStandEntity {

    private int entityID;
    private Location location;
    private UUID uuid;

    public static ArmorStandEntity createGuise(Location location){
        return new ArmorStandEntity(location);
    }
    //建立目標
    public ArmorStandEntity(Location inputLocation){
        PacketContainer packet = ProtocolMap.protocolManager.createPacket(PacketType.Play.Server.SPAWN_ENTITY);
        packet.getModifier().writeDefaults();
        int entityID = (int)(Math.random() * Integer.MAX_VALUE);

        packet.getIntegers().write(0, entityID);

        UUID uuid = UUID.randomUUID();
        packet.getUUIDs().write(0, uuid);

        packet.getEntityTypeModifier().write(0, EntityType.ARMOR_STAND);

        packet.getDoubles().write(0, inputLocation.getX());
        packet.getDoubles().write(1, inputLocation.getY());
        packet.getDoubles().write(2, inputLocation.getZ());

        packet.getIntegers().write(4, (int) (inputLocation.getPitch() * 256.0F / 360.0F));
        packet.getIntegers().write(5, (int) (inputLocation.getYaw() * 256.0F / 360.0F));

        sendPack(packet);
        this.entityID = entityID;
        this.location = inputLocation;
        this.uuid = uuid;
    }
//    public GuiseEntity(Location inputLocation){
//        PacketContainer packet = ProtocolMap.protocolManager.createPacket(PacketType.Play.Server.SPAWN_ENTITY);
//        int entityID = (int)(Math.random() * Integer.MAX_VALUE);
//        // Entity ID
//        packet.getIntegers().write(0, entityID);
//        // Entity Type
//        //packet.getIntegers().write(6, 78);
//        packet.getEntityTypeModifier().write(0, EntityType.ARMOR_STAND);
//        // Set optional velocity (/8000)
//        packet.getIntegers().write(1, 0);
//        packet.getIntegers().write(2, 0);
//        packet.getIntegers().write(3, 0);
//        // Set yaw pitch
//        packet.getIntegers().write(4, 0);
//        packet.getIntegers().write(5, 0);
//        // Set object data
//        //packet.getIntegers().write(7, 0);
//        // Set location
//        packet.getDoubles().write(0, inputLocation.getX());
//        packet.getDoubles().write(1, inputLocation.getY());
//        packet.getDoubles().write(2, inputLocation.getZ());
//        // Set UUID
//        packet.getUUIDs().write(0, UUID.randomUUID());
//
//        sendPack(packet);
//
//    }


    //裝備目標生物16以上
    public void appendItem16(ItemStack itemStack, String itemSlot){


        EnumWrappers.ItemSlot itemSlot1 = Enum.valueOf(EnumWrappers.ItemSlot.class,itemSlot);
        if(itemStack != null){
            PacketContainer packet = new PacketContainer(PacketType.Play.Server.ENTITY_EQUIPMENT);
            packet.getIntegers().write(0, entityID);

            List<Pair<EnumWrappers.ItemSlot, ItemStack>> pairList = new ArrayList<>();

            pairList.add(new Pair<>(itemSlot1, itemStack));
            packet.getSlotStackPairLists().write(0, pairList);

            sendPack(packet);
        }

    }

    //傳送目標
    public void teleport(Location inputLocation){
        PacketContainer packet = ProtocolMap.protocolManager.createPacket(PacketType.Play.Server.ENTITY_TELEPORT);
        packet.getIntegers().write(0, entityID);

        packet.getDoubles().write(0, inputLocation.getX());
        packet.getDoubles().write(1, inputLocation.getY());
        packet.getDoubles().write(2, inputLocation.getZ());

        packet.getBytes().write(0, (byte)((int)(inputLocation.getYaw() * 256.0F / 360.0F)));
        packet.getBytes().write(1, (byte)((int)(inputLocation.getPitch() * 256.0F / 360.0F)));

        sendPack(packet);
        this.location = inputLocation;
    }
    //修改顯示名稱
    public void setName(String text){
        PacketContainer packet = ProtocolMap.protocolManager.createPacket(PacketType.Play.Server.ENTITY_METADATA);
        packet.getModifier().writeDefaults();
        packet.getIntegers().write(0, entityID);

        WrappedDataWatcher metadata = new WrappedDataWatcher();
        Optional<?> opt = Optional.of(WrappedChatComponent.fromChatMessage(text)[0].getHandle());

        metadata.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(2, WrappedDataWatcher.Registry.getChatComponentSerializer(true)), opt);
        metadata.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(3, WrappedDataWatcher.Registry.get(Boolean.class)), true);

        packet.getWatchableCollectionModifier().write(0, metadata.getWatchableObjects());
        sendPack(packet);
    }
    //改變目標生物向量
    public void velocity(Vector vector){
        PacketContainer packet = ProtocolMap.protocolManager.createPacket(PacketType.Play.Server.ENTITY_VELOCITY);
        packet.getIntegers().write(0, entityID);

        packet.getIntegers().write(1, (int) (vector.getX() * 8000.0));
        packet.getIntegers().write(2, (int) (vector.getY() * 8000.0));
        packet.getIntegers().write(3, (int) (vector.getZ() * 8000.0));

        sendPack(packet);
    }
    //設置目標生物是否隱形
    public void setVisible(boolean b){
        PacketContainer packet = ProtocolMap.protocolManager.createPacket(PacketType.Play.Server.ENTITY_METADATA);
        packet.getIntegers().write(0, entityID);

        WrappedDataWatcher watcher = new WrappedDataWatcher();
        WrappedDataWatcher.Serializer serializer = WrappedDataWatcher.Registry.get(Byte.class);
        //watcher.setEntity(livingEntity);
        if(b){
            watcher.setObject(0, serializer, (byte) (0x20));
        }else {
            watcher.setObject(0, serializer, (byte) (0x00));
        }

        packet.getWatchableCollectionModifier().write(0, watcher.getWatchableObjects());
        sendPack(packet);
    }

    //調整盔甲架角度
    public void setArmorStandAngle(String type, float x, float y, float z){
        PacketContainer packet = ArmorStand.setArmorStandAngle(this.entityID, type, x, y, z);
        sendPack(packet);
    }
    //改為小盔甲架
    public void setArmorStandSmall(boolean small){
        PacketContainer packet = ProtocolMap.protocolManager.createPacket(PacketType.Play.Server.ENTITY_METADATA);
        packet.getIntegers().write(0, entityID);

        WrappedDataWatcher watcher = new WrappedDataWatcher();
        WrappedDataWatcher.Serializer serializer = WrappedDataWatcher.Registry.get(Byte.class);
        //PacketPlayOutEntityMetadata packetPlayOutEntityMetadata;
        watcher.setObject(0, serializer, (byte) (0x20));

        packet.getWatchableCollectionModifier().write(0, watcher.getWatchableObjects());
        sendPack(packet);
    }

    //刪除目標生物
    public void delete(){

        PacketContainer packet = ProtocolMap.protocolManager.createPacket(PacketType.Play.Server.ENTITY_DESTROY);
        List<Integer> intList = new ArrayList<>();
        intList.add(entityID);
        packet.getIntLists().write(0, intList);

        sendPack(packet);

//        for (Object obj : player.getWorld().getPlayers()) {
//            if (obj instanceof EntityPlayer) {
//                EntityPlayer nmsPlayer = (EntityPlayer) obj;
//
//                double distanceSquared = square(nmsPlayer.locX() - player.locX()) + Utils.square(nmsPlayer.locZ() - super.locZ());
//                if (distanceSquared < 8192 && nmsPlayer.b /* playerConnection */ != null) {
//                    nmsPlayer.b.sendPacket(packetPlayOutEntityDestroy);
//                }
//            }
//        }
//        PacketPlayOutEntityDestroy packetPlayOutEntityDestroy = new PacketPlayOutEntityDestroy(entityID);
//        EntityPlayer entityPlayer = (EntityPlayer) ((CraftPlayer) player).getHandle();
//        entityPlayer.b.sendPacket(packetPlayOutEntityDestroy);
    }
    public static double square(double num) {
        return num * num;
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

    public Location getLocation() {
        return location;
    }

}
