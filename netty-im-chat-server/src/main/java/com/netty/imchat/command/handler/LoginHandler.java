package com.netty.imchat.command.handler;

import com.netty.imchat.common.entity.packet.ConnectResponsePacket;
import com.netty.imchat.common.entity.packet.LoginRequestPacket;
import com.netty.imchat.common.entity.packet.LoginResponsePacket;
import com.netty.imchat.common.entity.packet.Packet;
import com.netty.imchat.common.entity.vo.UserInfoVO;
import com.netty.imchat.common.enums.CommandEnum;
import com.netty.imchat.common.util.PacketCodeUtil;
import com.netty.imchat.common.util.PacketWriteUtil;
import com.netty.imchat.constant.Constants;
import com.netty.imchat.util.constant.Constant;
import com.netty.imchat.util.constant.HttpStatus;
import com.netty.imchat.util.digest.Base64Utils;
import com.netty.imchat.util.digest.Md5Utils;
import com.netty.imchat.util.digest.RSAUtils;
import com.netty.imchat.util.exception.AppException;
import com.netty.imchat.util.general.StringUtils;
import com.netty.imchat.util.network.IPUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpResponseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Kevin
 * @Title: LoginHandler
 * @ProjectName netty-im-chat
 * @Description: TODO 服务端处理登录 的 处理类
 * @date 2018/11/13 11:05
 */
@Component("com.netty.imchat.command.handler.LoginHandler")
public class LoginHandler extends AbstractServerCmdHandler {

    public static final Logger log = LoggerFactory.getLogger(LoginHandler.class);

    @Override
    public Byte getCode() {
        return CommandEnum.LOGIN_REQUEST.getCode();
    }

    @Override
    protected void executeCust(ChannelHandlerContext ctx, Object msg, Packet packet) {
        UserInfoVO userInfoVO = null;
        LoginResponsePacket responsePacket = new LoginResponsePacket();
        try {
            if(!(packet instanceof LoginRequestPacket)){
                throw new AppException("包结构不能被处理"); //不能处理
            }

            LoginRequestPacket loginPacket = (LoginRequestPacket) packet;

            //获取ip
            String clientIp = IPUtils.obtainNettyClientIP(ctx);
            String loginInfo = MessageFormat.format("-----登录操作:user->{0}, password->{1}, ip->{2}----",
                    loginPacket.getLoginCode(), loginPacket.getPassword(), clientIp);

            log.info(loginInfo);

            //1.解密出用户和密码
            Map<String, String> loginInfoMap = decryptLoginInfo(loginPacket);

            //2.做登录
            userInfoVO = doLogin(loginInfoMap);
            responsePacket.setUserInfoVO(userInfoVO);
            responsePacket.setCode(HttpStatus.SC_OK);
        }catch (Exception e){
            responsePacket.setCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            if(e instanceof AppException){
                responsePacket.setMessage(e.getMessage());
            }else{
                responsePacket.setMessage("未知错误");
            }

            //日志打印
            log.error("登录发生错误:" + e.getMessage());
        }

        //3.将响应信息回写 客户端
        PacketWriteUtil.writeRes(responsePacket, ctx);
    }

    /**
     * 做登录
     * @param loginInfoMap
     * @return
     */
    private UserInfoVO doLogin(Map<String, String> loginInfoMap){
        if(null == loginInfoMap){
            //return null;
            throw new AppException("登录信息为空");
        }

        if(!"admin".equals(loginInfoMap.get("loginCode"))){
            throw new AppException("用户不存在");
        }

        if(!"123".equals(loginInfoMap.get("password"))){
            throw new AppException("密码不正确");
        }

        return new UserInfoVO(loginInfoMap.get("loginCode"), loginInfoMap.get("password"), "青萍剑客");
    }

    /**
     * 解密登录信息 -- RSA加解密
     * @param packet
     * @return
     */
    private Map<String, String> decryptLoginInfo(LoginRequestPacket packet){
        Map<String, String> loginInfo = null;
        try {
            byte[] decodeBase64 = Base64Utils.decode(packet.getPassword());
            byte[] passwordBytes = RSAUtils.decryptByPrivateKey(decodeBase64, Constants.PRIVATE_KEY);

            //校验密码是否正确 并获取
            String password = obtainAndCheckPasssWd(new String(passwordBytes, Constants.ENCODING_UTF8));
            if(StringUtils.isEmpty(password)){
                throw new AppException("信息或被人篡改");
            }

            loginInfo = new HashMap<String, String>();
            loginInfo.put("password", password);
            loginInfo.put("loginCode", packet.getLoginCode());
        }catch (Exception e){
            log.info(e.getMessage());
        }

        return loginInfo;
    }

    private String obtainAndCheckPasssWd(String passwdStr){
        int strLen = StringUtils.strLen(passwdStr);
        if(strLen <= 32){
            return null;
        }

        // 后32位 是 前面字符串的hash值
        // 如 字符串分为两部分 A+B
        // A为原字符串 B为对A进行散列之后的值 长度是32
        String password = passwdStr.substring(0, strLen - 32);
        if(Md5Utils.hash(password).equals(passwdStr.substring(strLen - 32, strLen))){
            return password;
        }

        //不成功
        return null;
    }
}
