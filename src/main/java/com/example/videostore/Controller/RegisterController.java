package com.example.videostore.Controller;

import com.example.videostore.Model.Customer;
import com.example.videostore.Model.Guest;
import com.example.videostore.Model.SceneSwitcher;
import com.example.videostore.SystemBroker.SingletonDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegisterController {
    @FXML
    private TextField name;
    @FXML
    private TextField address;
    @FXML
    private TextField phone;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button signUpButton;

    @FXML
    private Label invalidPhoneLabel;
    @FXML
    private Label invalidUsernameLabel;

    public void goToLogin(ActionEvent event) throws IOException {
        SceneSwitcher.switchToLogin(event);
    }

    ObservableList<Customer> customerList = FXCollections.observableArrayList();
    public void signUp(ActionEvent event) throws IOException {
        customerList = SingletonDatabase.getCustomers();

        boolean validUsername = true;
        boolean validPhone = false;

        if (name.getText() != "" && address.getText() != "" && phone.getText() != "" && username.getText() != "" && password.getText() != "") {
            for (Customer cus : customerList) {
                if (username.getText().equals(cus.getUsername())) {
                    validUsername = false;
                    break;
                }
            }
            if (isNumber(phone.getText())) {
                validPhone = true;
            }
            if (validUsername && validPhone) {
                invalidUsernameLabel.setVisible(false);
                invalidPhoneLabel.setVisible(false);
                Customer customer = new Guest.GuestBuilder().buildName(name.getText()).buildAddress(address.getText())
                        .buildPhone(phone.getText()).buildUsername(username.getText()).buildPassword(password.getText()).build();
                System.out.println("Sign up successfully");
                System.out.println(customer.toString());
                customerList.add(customer);
                try {
                    menuController.user = customer;
                    SceneSwitcher.switchToMenu(event);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else if (!validUsername && validPhone) {
                invalidUsernameLabel.setVisible(true);
                invalidPhoneLabel.setVisible(false);
            } else if (!validPhone && validUsername) {
                invalidPhoneLabel.setVisible(true);
                invalidUsernameLabel.setVisible(false);
            } else {
                invalidUsernameLabel.setVisible(true);
                invalidPhoneLabel.setVisible(true);
            }
        }
    }
    public static boolean isNumber(String string)
    {
        return string.matches("^\\d+$");
    }

}

