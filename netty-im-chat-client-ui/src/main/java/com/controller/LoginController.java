package com.controller;

import com.callback.LoginCallBack;
import com.netty.imchat.client.command.receive.handler.LoginResponseHandler;
import com.netty.imchat.client.command.send.CommandFacde;
import com.netty.imchat.util.general.StringUtils;
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
    private TextField loginName;

    @FXML
    private TextField password;

    @FXML
    private Label resultTips;

    @FXML
    protected void doLogin(ActionEvent event) {
        String loginNameStr = loginName.getText();
        String passwordStr = password.getText();
        if(StringUtils.isEmpty(loginNameStr)){
            log.info("loginCode不能为空");
        }

        if(StringUtils.isEmpty(passwordStr)){
            log.info("password不能为空");
        }
        CommandFacde.LOGIN_REQUEST_SEND.login(loginNameStr, passwordStr);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("初始化");
    }

    public void setLoginTips(String tipsMessage){
        resultTips.setText(tipsMessage);
    }

}
