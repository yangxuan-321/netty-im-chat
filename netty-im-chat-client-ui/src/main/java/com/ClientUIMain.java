package com;

import com.route.node.NodeRouter;
import com.cache.BaseUIInfo;
import com.netty.imchat.client.NettyClient;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;
import java.util.concurrent.CompletableFuture;

@SpringBootApplication
public class ClientUIMain extends AbstractJavaFxApplicationSupport {

    public static ConfigurableApplicationContext applicationContext;

    public static Stage stage;

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
        NodeRouter.gotoLogin();
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
