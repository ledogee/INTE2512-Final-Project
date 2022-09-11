package com.example.videostore.Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ChargeControl {
    public TextField balanceInput;
    public static double balance = 0;
    public Label validation;

    public void TopUp(ActionEvent evt) {
        String getInput = balanceInput.getText();


    }


    public void topUpBalance(ActionEvent event) {
        try {
            balance = Double.parseDouble(balanceInput.getText());
            menuController.user.setBalance(  menuController.user.getBalance() + balance);
            if(balance < 0) {
                validation.setText("In Valid Input");
                balance = 0;
                menuController.topUp = 0;
            }
        } catch (Exception e) {
            validation.setText("In Valid Input");
            balance = 0;
            menuController.topUp = 0;
        }
        System.out.println("Balance in top up is: " + balance);
    }
}
