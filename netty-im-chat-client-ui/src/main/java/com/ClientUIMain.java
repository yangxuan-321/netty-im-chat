package com;

import com.javafx.controller.BaseUIInfo;
import com.javafx.controller.LoginController;
import com.javafx.controller.MessageController;
import com.netty.imchat.client.NettyClient;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;
import java.io.InputStream;
import java.util.concurrent.CompletableFuture;

@SpringBootApplication
public class ClientUIMain extends Application {

    public static ConfigurableApplicationContext applicationContext;

    private Stage stage;

    @Override
    public void init() throws Exception {
        BaseUIInfo.application = this;

        CompletableFuture.supplyAsync(() -> {
            ConfigurableApplicationContext ctx = SpringApplication.run(this.getClass());
            NettyClient.run();
            return ctx;
        }).thenAccept(this::launchApplicationView);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.stage = primaryStage;
        primaryStage.setTitle("天涯共此时");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/login_back.jpg")));
        primaryStage.setOnCloseRequest(event -> System.exit(0));
        gotoLogin();
        primaryStage.show();
    }

    public void gotoLogin(){
        try {
            LoginController login = (LoginController) replaceSceneContent("/com/javafx/main/login.fxml", 500, 300);
            login.setApp(this);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void gotoMessage(){
        try {
            MessageController message = (MessageController) replaceSceneContent("/com/javafx/main/message.fxml", 735, 615);
            message.setApp(this);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private Initializable replaceSceneContent(String fxml, int width, int height) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = ClientUIMain.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(ClientUIMain.class.getResource(fxml));
        Pane page;
        try {
            page = (Pane) loader.load(in);
        } finally {
            in.close();
        }
        Scene scene = new Scene(page, width, height);
        stage.setScene(scene);
        stage.sizeToScene();
        return (Initializable) loader.getController();
    }


    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        launch(args);
    }

    private void launchApplicationView(ConfigurableApplicationContext ctx) {
        ClientUIMain.applicationContext = ctx;
    }
}
