package com.controller;

import com.callback.LoginCallBack;
import com.netty.imchat.client.command.receive.handler.LoginResponseHandler;
import com.netty.imchat.client.command.send.CommandFacde;
import com.netty.imchat.common.entity.packet.LoginResponsePacket;
import com.netty.imchat.common.enums.LoginResponseEnum;
import com.netty.imchat.util.general.StringUtils;
import com.route.node.NodeRouter;
import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ResourceBundle;

@FXMLController
public class LoginController extends BaseController implements Initializable {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    public static final String CONTROLLER_CODE = "LoginController";

    public LoginController() {
        LoginResponseHandler.callBack = new LoginCallBack();
    }

    @Override
    public String getControllerCode() {
        return CONTROLLER_CODE;
    }

    @FXML
    private TextField userCode;

    @FXML
    private TextField password;

    @FXML
    private Label userCodeTips;

    @FXML
    private Label passwdTips;

    @FXML
    protected void doLogin(ActionEvent event) {
        // 清除 提示信息
        initTips();

        String loginNameStr = userCode.getText();
        String passwordStr = password.getText();
        if(StringUtils.isEmpty(loginNameStr)){
            log.info("账号不能为空");
            userCodeTips.setText("账号不能为空");
            return;
        }

        if(StringUtils.isEmpty(passwordStr)){
            log.info("密码不能为空");
            passwdTips.setText("密码不能为空");
            return;
        }
        CommandFacde.LOGIN_REQUEST_SEND.login(loginNameStr, passwordStr);
    }

    @FXML
    protected void goRegisterUser(ActionEvent event) {
        NodeRouter.gotoRegisterUser();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("初始化");
    }

    public void setLoginTips(LoginResponsePacket packet){
        if (packet.getCode() == LoginResponseEnum.NONE.ordinal()){
            userCodeTips.setText(packet.getMessage());
            return;
        }

        if (packet.getCode() == LoginResponseEnum.USER_NOT_EXISTS.ordinal()){
            userCodeTips.setText("用户不存在");
            return;
        }

        if (packet.getCode() == LoginResponseEnum.PASSWD_ERROR.ordinal()){
            passwdTips.setText("密码不正确");
            return;
        }
    }

    public void initTips(){
        userCodeTips.setText("");
        passwdTips.setText("");
    }

}
