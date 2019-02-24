package com.controller;

import com.netty.imchat.client.command.send.CommandFacde;
import com.netty.imchat.client.pojo.dto.SingChatMessageInfo;
import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

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

    @FXML
    private TextArea messageContent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @Override
    public String getControllerCode() {
        return CONTROLLER_CODE;
    }

    @FXML
    protected void sendMessage(ActionEvent event){
        String text = messageContent.getText();
        SingChatMessageInfo messageInfo = new SingChatMessageInfo();
        messageInfo.setMessageContent(text);
        messageInfo.setUserId(123);
        CommandFacde.MESSAGE_REQUEST_SEND.sendSingChatMessage(messageInfo);
    }

}
