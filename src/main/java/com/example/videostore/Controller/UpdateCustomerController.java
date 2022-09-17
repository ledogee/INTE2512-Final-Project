package com.example.videostore.Controller;

import com.example.videostore.Model.Customer;
import com.example.videostore.Model.Guest;
import com.example.videostore.Model.SceneSwitcher;
import com.example.videostore.SystemBroker.SingletonDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.videostore.Controller.CustomerController.user;
import static com.example.videostore.Controller.RegisterController.customerList;
import static com.example.videostore.Controller.RegisterController.isNumber;

public class UpdateCustomerController implements Initializable {
    @FXML
    private TextField name;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField phone;
    @FXML
    private TextField address;
    @FXML
    private Button cancelButton;
    @FXML
    private Button saveButton;
    @FXML
    private Label invalidPhoneLabel;
    @FXML
    private Label invalidUsernameLabel;
    @FXML
    private Label isFilledLabel;

    public void loadPersonalInformation() {

        name.setText(user.getName());
        username.setText(user.getUsername());
        password.setText(user.getPassword());
        phone.setText(user.getPhone());
        address.setText(user.getAddress());
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadPersonalInformation();
    }

    @FXML
    public void cancelUpdateInfo(ActionEvent event) throws IOException {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    public void updateInfo(ActionEvent event) throws IOException{
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();

        customerList = SingletonDatabase.getCustomers();
        name.setText(name.getText());
        username.setText(username.getText());
        password.setText(password.getText());
        phone.setText(phone.getText());
        address.setText(address.getText());

        boolean validUsername = true;
        boolean validPhone = false;
        for (Customer cus : customerList) {
            if (username.getText().equals(cus.getUsername()) && !(user.getUsername().equals(cus.getUsername()))) {
                validUsername = false;
                break;
            }
        }
        if (isNumber(phone.getText())) {
            validPhone = true;
        }
        if (name.getText() != "" && address.getText() != "" && phone.getText() != "" && username.getText() != "" && password.getText() != "") {
            isFilledLabel.setVisible(false);
            if (validUsername && validPhone) {
                invalidUsernameLabel.setVisible(false);
                invalidPhoneLabel.setVisible(false);
                user.setName(name.getText());
                user.setUsername(username.getText());
                user.setPassword(password.getText());
                user.setPhone(phone.getText());
                user.setAddress(address.getText());
                stage.close();
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
        }  else {
            if (validUsername && validPhone) {
                invalidUsernameLabel.setVisible(false);
                invalidPhoneLabel.setVisible(false);
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
            isFilledLabel.setVisible(true);
        }

    }
}
