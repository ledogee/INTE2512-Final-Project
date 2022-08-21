package com.example.videostore.Controller;

import com.example.videostore.Model.SceneSwitcher;
import javafx.event.ActionEvent;

import java.io.IOException;

public class loginController {
    public void goToRegister(ActionEvent event) throws IOException {
        SceneSwitcher.switchToRegister(event);
    }
}
