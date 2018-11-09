package com.javafx.main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.swing.*;

public class ClientUIMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("天涯共此时");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 500, 300));
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("login_back.jpg")));
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
}
