package com.javafx.controller;

import com.netty.imchat.client.command.send.CommandFacde;
import com.netty.imchat.client.command.send.LoginRequestSend;
import com.netty.imchat.util.general.StringUtils;
import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;

@FXMLController
public class LoginController {

    @FXML
    private TextField loginName;

    @FXML
    private TextField password;

    @FXML
    protected void doLogin(ActionEvent event) {
        String loginNameStr = loginName.getText();
        String passwordStr = password.getText();
        if(StringUtils.isEmpty(loginNameStr)){
            System.out.println("loginCode不能为空");
        }

        if(StringUtils.isEmpty(passwordStr)){
            System.out.println("password不能为空");
        }

        CommandFacde.LOGIN_REQUEST_SEND.login(loginNameStr, passwordStr);
    }
}
