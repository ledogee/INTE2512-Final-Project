package com.example.videostore.Controller;

import com.example.videostore.Model.SceneSwitcher;
import javafx.event.ActionEvent;

import java.io.IOException;

public class RegisterController {
    public void goToLogin(ActionEvent event) throws IOException {
        SceneSwitcher.switchToLogin(event);
    }
}
