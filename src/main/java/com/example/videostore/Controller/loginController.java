package com.example.videostore.Controller;

import com.example.videostore.Model.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class loginController {
    @FXML
    private Button submitBtn;
    @FXML
    private TextField usernameInput;

    @FXML
    private PasswordField passwordInput;

    public void goToRegister(ActionEvent event) throws IOException {
        SceneSwitcher.switchToRegister(event);
    }

    public void checkInput(ActionEvent event) throws IOException {
        if(usernameInput.getText().equals("admin") && passwordInput.getText().equals("12345")) {
            System.out.println(usernameInput.getText());
            System.out.println(passwordInput.getText());
            SceneSwitcher.switchAdmin(event);
        }
    }

}
