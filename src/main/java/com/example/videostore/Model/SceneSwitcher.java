package com.example.videostore.Model;

import com.example.videostore.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneSwitcher {
    private static Stage stage;
    private static Scene scene;
    private static Parent root;

    // SWITCH SCENES FUNCTIONS
    public static void switchToLogin(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Main.class.getResource("login.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public static void switchToRegister(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Main.class.getResource("register.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public static void switchAdmin(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Main.class.getResource("admin.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public static void switchToCustomer(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Main.class.getResource("customer.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public static void switchToMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Main.class.getResource("menu.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

}
