package com.daxton.fancycore.nms.v1_13_R1;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import com.daxton.fancycore.other.task.PackEntity;
import net.minecraft.server.v1_13_R1.Vector3f;
import org.bukkit.util.EulerAngle;

public class ArmorStand {

    public ArmorStand(){

    }

    //調整盔甲架角度
    public static void setArmorStandAngle(int entityID, String type, double x, double y, double z){
        PacketContainer packet = new PacketContainer(PacketType.Play.Server.ENTITY_METADATA);
        packet.getModifier().writeDefaults();
        packet.getIntegers().write(0, entityID);

        WrappedDataWatcher dataWatcher = new WrappedDataWatcher(packet.getWatchableCollectionModifier().read(0));
        WrappedDataWatcher.Serializer serializer = WrappedDataWatcher.Registry.get(Vector3f.class);

        EulerAngle eulerAngle = new EulerAngle(Math.toRadians(x), Math.toRadians(y), Math.toRadians(z));

        Vector3f vector3f = getVector3f(eulerAngle);

        switch (type.toLowerCase()){
            case "head":
                dataWatcher.setObject(15, serializer, vector3f);
                break;
            case "body":
                dataWatcher.setObject(16, serializer, vector3f);
                break;
            case "leftarm":
                dataWatcher.setObject(17, serializer, vector3f);
                break;
            case "rightarm":
                dataWatcher.setObject(18, serializer, vector3f);
                break;
            case "leftleg":
                dataWatcher.setObject(19, serializer, vector3f);
                break;
            case "rightleg":
                dataWatcher.setObject(20, serializer, vector3f);
                break;
        }

        packet.getWatchableCollectionModifier().write(0, dataWatcher.getWatchableObjects());

        PackEntity.sendPack(packet);
    }

    public static Vector3f getVector3f(EulerAngle eulerAngle) {
        return new Vector3f((float)Math.toDegrees(eulerAngle.getX()), (float)Math.toDegrees(eulerAngle.getY()), (float)Math.toDegrees(eulerAngle.getZ()));
    }

}
