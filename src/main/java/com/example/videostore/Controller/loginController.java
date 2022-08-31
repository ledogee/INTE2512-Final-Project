package com.example.videostore.Controller;

import com.example.videostore.Model.Customer;
import com.example.videostore.Model.SceneSwitcher;
import com.example.videostore.SystemBroker.SingletonDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class loginController {
    @FXML
    private Button submitBtn;
    @FXML
    private TextField usernameInput;

    @FXML
    private PasswordField passwordInput;

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
        } else
            login(event);
    }

    ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();

    private void login(ActionEvent event) throws IOException
    {
        BufferedReader br = null;
        try
        {
            br = new BufferedReader(new FileReader("src/main/java/com/example/videostore/db/customers.txt"));
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        if(br != null)
        {
            String data;
            while ((data = br.readLine()) != null)
            {
                String[] split = data.split(",");
                if(usernameInput.getText().equals(split[5]) && passwordInput.getText().equals(split[6]))
                {
                    SceneSwitcher.switchToMenu(event);
                }
            }
        }
    }
    public void initialize()
    {
        customerObservableList = SingletonDatabase.getCustomers();
    }
}
