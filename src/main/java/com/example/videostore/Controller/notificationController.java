package com.example.videostore.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.io.IOException;
import java.util.Optional;

public class notificationController {
    @FXML
    private Label labelNotification;

    @FXML
    private static DialogPane colorPane;

    public void setLabelNotification(String string, String color) {
        labelNotification.setText(string);
        labelNotification.setTextFill(Color.web(color));
        labelNotification.setFont(Font.font("Tahoma", FontWeight.BOLD, FontPosture.ITALIC, 24));
        labelNotification.setAlignment(Pos.CENTER);

    }

    public static void popAdminNotification(AnchorPane adminVBOX, String string, String color){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(adminVBOX.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(notificationController.class.getResource("/com/example/videostore/notification.fxml"));

        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }
        // Add button

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        notificationController controller = fxmlLoader.getController();
        controller.setLabelNotification(string, color);
        Optional<ButtonType> result = dialog.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK) {

            System.out.println("Ok pressed");

        }
    }

    public static void popMenuNotification(AnchorPane anchorPane, String string, String color){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(anchorPane.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(notificationController.class.getResource("/com/example/videostore/notification.fxml"));

        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }
        // Add button
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        notificationController controller = fxmlLoader.getController();
        controller.setLabelNotification(string, color);
        Optional<ButtonType> result = dialog.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK)
        {

            System.out.println("Ok pressed");

        }

    }
}
