package studt.imchat.protocol.entity.packet;

import studt.imchat.protocol.enums.CommandeEnum;
import studt.imchat.util.EnumUtils;

/**
 * @author Kevin
 * @Title: LoginRequestPacket
 * @ProjectName studyjava
 * @Description: TODO
 * @date 2018/9/29 19:06
 */
public class LoginRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return EnumUtils.ByteEnumUtils.getEnumCode(CommandeEnum.LOGIN_REQUEST);
    }

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 用户姓名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
