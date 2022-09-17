package com.example.videostore.Controller;

import com.example.videostore.Model.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class welcomeController {

    public void goToLogin(ActionEvent event) throws IOException {
        SceneSwitcher.switchToLogin(event);
    }

    public void goToRegister(ActionEvent event) throws IOException {
        SceneSwitcher.switchToRegister(event);
    }

}
