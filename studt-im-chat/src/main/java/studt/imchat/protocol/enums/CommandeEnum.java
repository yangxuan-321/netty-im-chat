package studt.imchat.protocol.enums;

import studt.imchat.protocol.entity.packet.LoginRequestPacket;
import studt.imchat.protocol.entity.packet.Packet;
import studt.imchat.protocol.entity.serializer.Serializer;

/**
 * @author Kevin
 * @Title: CommandeEum
 * @ProjectName studyjava
 * @Description: TODO 命令枚举
 * @date 2018/9/29 19:01
 */
public enum CommandeEnum{
    LOGIN_REQUEST((byte)1, LoginRequestPacket.class);          //登录请求

    //CODE
    private Byte code;
    //类
    private Class<? extends Packet> clazz;

    public Byte getCode() {
        return code;
    }

    public void setCode(Byte code) {
        this.code = code;
    }

    public Class<? extends Packet> getClazz() {
        return clazz;
    }

    public void setClazz(Class<? extends Packet> clazz) {
        this.clazz = clazz;
    }

    CommandeEnum(Byte code, Class<? extends Packet> clazz) {
        this.code = code;
        this.clazz = clazz;
    }

}
