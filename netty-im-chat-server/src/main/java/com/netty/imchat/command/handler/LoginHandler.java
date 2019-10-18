package com.netty.imchat.command.handler;

import com.netty.imchat.common.entity.packet.LoginRequestPacket;
import com.netty.imchat.common.entity.packet.LoginResponsePacket;
import com.netty.imchat.common.entity.packet.Packet;
import com.netty.imchat.common.entity.vo.UserInfoVO;
import com.netty.imchat.common.enums.CommandEnum;
import com.netty.imchat.common.util.PacketWriteUtil;
import com.netty.imchat.pojo.constant.Constants;
import com.netty.imchat.util.constant.Constant;
import com.netty.imchat.util.constant.HttpStatus;
import com.netty.imchat.util.digest.Base64Utils;
import com.netty.imchat.util.digest.Md5Utils;
import com.netty.imchat.util.digest.RSAUtils;
import com.netty.imchat.util.exception.AppException;
import com.netty.imchat.util.general.StringUtils;
import com.netty.imchat.util.login.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
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

        if(!(packet instanceof LoginRequestPacket)){
            throw new AppException("包结构不能被处理"); //不能处理
        }

        LoginRequestPacket loginPacket = (LoginRequestPacket) packet;

        String loginInfo = MessageFormat.format("-----登录操作:user->{0}, ip->{1}----",
                loginPacket.getLoginCode(), loginPacket.getPassword());

        log.info(loginInfo);

        //1.解密出用户和密码
        Map<String, String> loginInfoMap = decryptLoginInfo(loginPacket);

        //2.做登录
        try {
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
        }

        //3.标记此Channel登陆成功
        LoginUtil.markAsLogin(ctx.channel());

        //3.将响应信息回写 客户端
        PacketWriteUtil.writeRes(responsePacket, ctx);
    }

    @Override
    protected void after() {
        super.after();
    }

    /**
     * 做登录
     * @param loginInfoMap
     * @return
     */
    private UserInfoVO doLogin(Map<String, String> loginInfoMap){
        if(null == loginInfoMap){
            throw new AppException("用户不存在");
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
        Map<String, String> loginInfo = new HashMap<String, String>();
        try {
//            byte[] loginCodeBytes = RSAUtils.decryptByPublicKey(packet.getLoginCode().getBytes("UTF-8"), Constants.PRIVATE_KEY);
            String password = packet.getPassword();
            if(StringUtils.isEmpty(password) || StringUtils.isEmpty(packet.getLoginCode())){
                log.info("用户名或者密码为空");
                throw new AppException("用户名或者密码为空");
            }

            //至少是32位
            if(password.length() < 32){
                log.info("-----传输数据长度小于32位，传输数据被篡改-----");
                throw new AppException("传输数据被篡改");
            }
            String passwdContent = password.substring(0, password.length()- Constant.MD5_LENGTH);
            String hash = password.substring(password.length()- Constant.MD5_LENGTH);
            if(!Md5Utils.hash(passwdContent).equals(hash)){
                log.info("-----数据校验出错，传输数据被篡改-----");
                throw new AppException("传输数据被篡改");
            }

            byte[] passwordBytes = RSAUtils.decryptByPrivateKey(Base64Utils.decode(passwdContent), Constants.PRIVATE_KEY);
            loginInfo.put("loginCode", packet.getLoginCode());
            loginInfo.put("password", new String(passwordBytes, Constants.ENCODING_UTF8));

            return loginInfo;
        }catch (Exception e){
            log.info(e.getMessage());
        }

        return null;
    }
}
