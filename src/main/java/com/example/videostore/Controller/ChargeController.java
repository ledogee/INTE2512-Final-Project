package com.example.videostore.Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ChargeController {
    public TextField balanceInput;
    public static double balance = 0;
    public Label validation;
    public Button topUpBtn;

    public void TopUp(ActionEvent event) {
        String getInput = balanceInput.getText();

    }

    public void topUpBalance(ActionEvent event) {
        try {
            balance = Double.parseDouble(balanceInput.getText());
            if(balance < 0)
            {
                validation.setText("Insufficient Balance");
                validation.setStyle("-fx-text-fill: red");
                balanceInput.setText("");
                balance = 0;
                menuController.topUp = 0;

            }
            else
            {
                validation.setText("Transaction Successfully");
                validation.setStyle("-fx-text-fill: green");
                balanceInput.setText("");
                menuController.user.setBalance(  menuController.user.getBalance() + balance);

            }
        } catch (Exception e) {
            validation.setText("Invalid Input");
            validation.setStyle("-fx-text-fill: red");
            balanceInput.setText("");
            balance = 0;
            menuController.topUp = 0;
        }
        System.out.println("Balance in top up is: " + balance);
    }
}
