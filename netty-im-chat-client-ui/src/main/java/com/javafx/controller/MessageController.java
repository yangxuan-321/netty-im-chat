package com.javafx.controller;

import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Kevin
 * @Title: MessageController
 * @ProjectName netty-im-chat
 * @Description: TODO
 * @date 2018/11/15 10:23
 */
@FXMLController
public class MessageController extends BaseController implements Initializable {

    public static final String CONTROLLER_CODE = "MessageController";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @Override
    public String getControllerCode() {
        return CONTROLLER_CODE;
    }

    
}
