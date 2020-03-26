package com.controller;

import com.callback.LoginCallBack;
import com.netty.imchat.client.command.receive.handler.LoginResponseHandler;
import com.netty.imchat.util.general.StringUtils;
import com.route.node.NodeRouter;
import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ResourceBundle;

@FXMLController
public class RegisterController extends BaseController implements Initializable {

    private static final Logger log = LoggerFactory.getLogger(RegisterController.class);

    public static final String CONTROLLER_CODE = "RegisterController";

    public RegisterController() {
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
    private Label resultTips;

    @FXML
    protected void checkAccount(KeyEvent event) {
        String userCodeStr = userCode.getText();
        if(StringUtils.isEmpty(userCodeStr)){
            log.info("账号为空");
            return;
        }

        if (!StringUtils.validateLegalString(userCodeStr)){
             log.info("账号含特殊字符");
            resultTips.setText("账号含特殊字符");
             return;
        }

        System.out.println(userCodeStr);
        // 检查是否已经存在此账号


//        CommandFacde.LOGIN_REQUEST_SEND.login(userCodeStr, passwordStr);
    }

    @FXML
    protected void doRegister(ActionEvent event) {
//        String userCodeStr = userCode.getText();
//        String passwordStr = password.getText();
//        if(StringUtils.isEmpty(userCodeStr)){
//            log.info("loginCode不能为空");
//        }
//
//        if(StringUtils.isEmpty(passwordStr)){
//            log.info("password不能为空");
//        }
//        CommandFacde.LOGIN_REQUEST_SEND.login(userCodeStr, passwordStr);
    }

    @FXML
    protected void goLogin(ActionEvent event) {
        NodeRouter.gotoLogin();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("初始化");
    }

//    public void setLoginTips(String tipsMessage){
//        resultTips.setText(tipsMessage);
//    }

}
