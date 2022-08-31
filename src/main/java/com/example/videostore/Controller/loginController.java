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
        } else
            login(event);
    }

    ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();

    private void login(ActionEvent event) throws IOException
    {
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
       /* BufferedReader br = null;
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
                String[] customerPieces = data.split(",");
                if(usernameInput.getText().equals(customerPieces[5]) && passwordInput.getText().equals(customerPieces[6]))
                {
                    menuController menu = new menuController();
                    String id = customerPieces[0];
                    String name = customerPieces[1];
                    String address = customerPieces[2];
                    String phoneNumber = customerPieces[3];
                    String accountType = customerPieces[4];
                    String username = customerPieces[5];
                    String password = customerPieces[6];
                    double balance = Double.parseDouble(customerPieces[7]);
                    List list = SingletonDatabase.getItemListID(customerPieces[8]);
                    switch(accountType){
                        case "Guest":
                            Guest guest = new Guest.GuestBuilder(id, name, username, password, balance,list).buildAddress(customerPieces[2]).buildPhone(customerPieces[3]).build();
                            menu.setUser(guest);
                            break;
                        case "Regular":
                            Regular regular = new Regular.RegularBuilder(id, name, username, password, balance, list).buildAddress(address).buildPhone(phoneNumber).build();
                            menu.setUser(regular);
                            break;
                        case "Vip":
                            int rewardPoint = Integer.parseInt(customerPieces[9]);
                            Vip vip = new Vip.VipBuilder(id, name, username, password, balance,list).buildAddress(address).buildPhone(phoneNumber).buildRewardPoint(rewardPoint).build();
                            menu.setUser(vip);
                            break;
                    }



                    SceneSwitcher.switchToMenu(event);
                }
            }
        }*/
    }
    public void initialize()
    {
        customerObservableList = SingletonDatabase.getCustomers();
    }
}
