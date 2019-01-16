package com;

import com.netty.imchat.client.NettyClient;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;
import java.util.concurrent.CompletableFuture;

@SpringBootApplication
public class ClientUIMain extends Application {

    public static ConfigurableApplicationContext applicationContext;

    @Override
    public void init() throws Exception {
        CompletableFuture.supplyAsync(() -> {
            ConfigurableApplicationContext ctx = SpringApplication.run(this.getClass());
            NettyClient.run();
            return ctx;
        }).thenAccept(this::launchApplicationView);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/com/javafx/main/message.fxml"));
        primaryStage.setTitle("天涯共此时");
        primaryStage.setResizable(false);
//        primaryStage.setScene(new Scene(root, 500, 300));
        primaryStage.setScene(new Scene(root, 700, 600));
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/login_back.jpg")));
        primaryStage.setOnCloseRequest(event -> System.exit(0));
        primaryStage.show();
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
