package com.netty.imchat.common.util;

import com.netty.imchat.common.entity.packet.Packet;
import com.netty.imchat.common.enums.CommandEnum;
import com.netty.imchat.common.factory.SerializerFactory;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import com.netty.imchat.common.entity.serializer.Serializer;
import com.netty.imchat.common.enums.SerializerEnum;

/**
 * @author Kevin
 * @Title: PacketCode
 * @ProjectName studyjava
 * @Description: TODO 将一个对象 转化为 可以使用Netty发送的 ByteBuf、、、、、、以及反转化
 * @date 2018/9/29 20:54
 */
public class PacketCodeUtil {
    /**
     * 魔数  代表协议版本
     */
    public static final int MAGIC_NUMBER = 0x12345678;

    public static ByteBuf encode(Packet packet) {
        Serializer serializer = SerializerFactory.defaultSeralizer();
        // 1. 创建 ByteBuf 对象
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();
        // 2. 序列化 Java 对象
        byte[] bytes = serializer.serialize(packet);

        // 3. 实际编码过程
        //3.1.魔数
        byteBuf.writeInt(MAGIC_NUMBER);
        //3.2.版本
        byteBuf.writeByte(packet.getVersion());
        //3.3.算法协议
        byteBuf.writeByte(serializer.getSerializerAlgorithm());
        //3.4.命令
        byteBuf.writeByte(packet.getCommand());
        //3.5.数据包长度
        byteBuf.writeInt(bytes.length);
        //3.6.数据
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }

    public static Packet decode(ByteBuf byteBuf) {
        // 跳过 魔数 magic number
        byteBuf.skipBytes(4);

        // 跳过版本号
        byteBuf.skipBytes(1);

        // 序列化算法标识
        byte serializeAlgorithm = byteBuf.readByte();

        // 指令
        byte command = byteBuf.readByte();

        // 数据包长度
        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Class<? extends Packet> packetClazz = getCommandType(command);
        Serializer serializer = SerializerFactory.createSeralizer(getSerializer(serializeAlgorithm));

        if (packetClazz != null && serializer != null) {
            return serializer.deserialize(packetClazz, bytes);
        }

        return null;
    }

    public static Class<? extends Packet> getCommandType(byte command){
        CommandEnum[] values = CommandEnum.values();
        for (CommandEnum commandEnum : values) {
            if(commandEnum.getCode() == command){
                return commandEnum.getClazz();
            }
        }

        return null;
    }

    public static SerializerEnum getSerializer(byte serializeAlgorithm){
        SerializerEnum[] values = SerializerEnum.values();
        for (SerializerEnum serializerEnum: values) {
            if(serializerEnum.getCode() == serializeAlgorithm){
                return serializerEnum;
            }
        }

        return null;
    }
}
