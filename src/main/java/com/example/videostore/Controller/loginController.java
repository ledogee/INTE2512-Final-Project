package com.example.videostore.Controller;

import com.example.videostore.Model.*;
import com.example.videostore.SystemBroker.SingletonDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class loginController {
    @FXML
    private Button submitBtn;
    @FXML
    private TextField usernameInput;

    @FXML
    private PasswordField passwordInput;

    @FXML
    private Label incorrectUsernameLabel;

    @FXML
    private Label incorrectPasswordLabel;


    public void goToRegister(ActionEvent event) throws IOException
    {
        SceneSwitcher.switchToRegister(event);
    }
    public void checkInput(ActionEvent event) throws IOException
    {
        if(usernameInput.getText().equals("admin") && passwordInput.getText().equals("12345")) {
            System.out.println(usernameInput.getText());
            System.out.println(passwordInput.getText());
            SceneSwitcher.switchAdmin(event);
        } else if(usernameInput.getText().equals("admin") && !(passwordInput.getText().equals("12345"))) {
            incorrectPasswordLabel.setVisible(true);
        } else
            login(event);
    }

    ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();

    private void login(ActionEvent event) throws IOException
    {
        customerObservableList = SingletonDatabase.getCustomers();
        for(Customer customer : customerObservableList) {
            if(usernameInput.getText().equals(customer.getUsername()) && passwordInput.getText().equals(customer.getPassword())) {
                incorrectUsernameLabel.setVisible(false);
                incorrectPasswordLabel.setVisible(false);
                menuController.user = customer;
                SceneSwitcher.switchToMenu(event);
            } else if (usernameInput.getText().equals(customer.getUsername()) && !(passwordInput.getText().equals(customer.getPassword()))) {
                incorrectUsernameLabel.setVisible(false);
                incorrectPasswordLabel.setVisible(true);
                break;
            } else {
                incorrectUsernameLabel.setVisible(true);
                incorrectPasswordLabel.setVisible(false);
            }
        }

    }
    public void initialize()
    {
        customerObservableList = SingletonDatabase.getCustomers();
    }
}
