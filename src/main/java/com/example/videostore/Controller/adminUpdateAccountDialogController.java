package com.example.videostore.Controller;

import com.example.videostore.Model.Customer;
import com.example.videostore.Model.Guest;
import com.example.videostore.Model.Regular;
import com.example.videostore.Model.Vip;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.videostore.Controller.adminController.*;

public class adminUpdateAccountDialogController implements Initializable {

    @FXML
    private ComboBox comboBoxAccountType;

    @FXML
    private TextField name;
    @FXML
    private TextField address;
    @FXML
    private TextField phone;
    @FXML
    private TextField balance;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField numOfReturn;
    @FXML
    private TextField listRental;
    @FXML
    private TextField rewardPoint;
    @FXML
    private Label addAccountLabel;

    public void setAddAccountLabel(String string) {
        addAccountLabel.setText(string);
    }
    //Set boolean for validation
    boolean isAccountTypeFilled = false;
    boolean isNameValid = false;
    boolean isAddressValid = false;
    boolean isPhoneValid = false;
    boolean isBalanceValid = false;
    boolean isUsernameValid = true;
    boolean isPasswordValid = false;
    boolean isRewardPointValid = false;

    //function used to update the account info
    public Customer processUpdateAccount(Customer cus, ObservableList<Customer> customersDatabase){
        //reset boolean to false
        isAccountTypeFilled = false;
        isNameValid = false;
        isAddressValid = false;
        isPhoneValid = false;
        isBalanceValid = false;
        isPasswordValid = false;
        isUsernameValid = true;
        isRewardPointValid = false;

        //switch the boolean to true if the in put suit the requirement
        Integer AccountType = null;
        AccountType = comboBoxAccountType.getSelectionModel().getSelectedIndex();
        if(AccountType >= 0){
            isAccountTypeFilled = true;
        }

        String Name = name.getText().trim();
        if(!name.getText().isEmpty()){
            isNameValid = true;
        }

        String Address = address.getText();
        if(!address.getText().isEmpty()){
            isAddressValid = true;
        }

        String Phone = null;
        if(checkPhoneNumber(phone.getText())){
            Phone = phone.getText().trim();
            isPhoneValid = true;
        }

        Double Balance = null;
        if(isDouble(balance.getText()) && Double.parseDouble(balance.getText().trim())>=0){
            Balance = Double.parseDouble(balance.getText().trim());
            isBalanceValid = true;
        }

        String Username = username.getText().trim();
        if(!username.getText().isEmpty()){
            isUsernameValid = checkNewUsernameAvailable(Username, customers, cus);
        }

        String Password = password.getText().trim();
        if(!password.getText().isEmpty()){
            isPasswordValid = true;
        }

        Integer RewardPoint = null;
        if(isInteger(rewardPoint.getText()) && Integer.parseInt(rewardPoint.getText().trim())>= 0 && Integer.parseInt(rewardPoint.getText().trim()) <= 100){
            RewardPoint = Integer.parseInt(rewardPoint.getText().trim());
            isRewardPointValid = true;
        }

        //update account based on account type
        //0 for Guest, 1 for Regular, and 2 for Vip
        if(AccountType == 0 && isAccountTypeFilled && isNameValid && isAddressValid && isPhoneValid && isBalanceValid && isUsernameValid && isPasswordValid){

            cus.setName(Name);
            cus.setAddress(Address);
            cus.setPhone(Phone);
            cus.setBalance(Balance);
            cus.setUsername(Username);
            cus.setPassword(Password);
            Guest guest = new Guest.GuestBuilder(cus).build();

            //Update database
            for(int i = 0; i < customersDatabase.size(); i++) {
                if(customersDatabase.get(i).getId().equals(guest.getId())) {
                    customersDatabase.set(i, guest);
                }
            }
            return guest;

        } else if (AccountType == 1 && isAccountTypeFilled && isNameValid && isAddressValid && isPhoneValid && isBalanceValid && isUsernameValid && isPasswordValid) {
            cus.setName(Name);
            cus.setAddress(Address);
            cus.setPhone(Phone);
            cus.setBalance(Balance);
            cus.setUsername(Username);
            cus.setPassword(Password);
            Regular reg = new Regular.RegularBuilder(cus).build();
            //Update database
            for(int i = 0; i < customersDatabase.size(); i++) {
                if(customersDatabase.get(i).getId().equals(reg.getId())) {
                    customersDatabase.set(i, reg);
                }
            }
            return reg;

        } else if (AccountType == 2 && isAccountTypeFilled && isNameValid && isAddressValid && isPhoneValid && isBalanceValid && isUsernameValid && isPasswordValid && isRewardPointValid) {
            cus.setName(Name);
            cus.setAddress(Address);
            cus.setPhone(Phone);
            cus.setBalance(Balance);
            cus.setUsername(Username);
            cus.setPassword(Password);
            Vip vip = new Vip.VipBuilder(cus).buildRewardPoint(RewardPoint).build();

            // Update the db
            for(int i = 0; i < customersDatabase.size(); i++) {
                if(customersDatabase.get(i).getId().equals(vip.getId())) {
                    customersDatabase.set(i, vip);
                }
            }
            System.out.println(vip);
            return vip;
        }
        //if any input is invalid, the function will return null
        return null;
    }

    //Check if the entered phone number is valid (not empty and contain number only)
    public boolean checkPhoneNumber(String string){
        boolean isCorrect = true;
        if(string.isEmpty()){
            return isCorrect = false;
        }
        for(int i = 0; i < string.length(); i++){
            if(string.charAt(i) >= '0' && string.charAt(i) <= '9'){
                continue;
            }else {
                isCorrect = false;
                break;
            }
        }
        return isCorrect;
    }

    //Set the error to the label for displaying to the admin, base on the boolean for validation
    public void setAccountLabel(){
        StringBuilder stringBuilder = new StringBuilder();
        if(!isAccountTypeFilled){
            stringBuilder.append("Please select an Account Type\n");
        }
        else if(!isNameValid){
            stringBuilder.append("Invalid name.(Please fill a name)\n");
        }
        else if(!isAddressValid){
            stringBuilder.append("Invalid address.(Please fill an address)\n");
        }
        else if(!isPhoneValid){
            stringBuilder.append("Invalid phone number or Empty Input\n");
        }
        else if(!isBalanceValid){
            stringBuilder.append(("Invalid balance.(Only numeric value)\n"));
        }
        else if(!isUsernameValid){
            stringBuilder.append("Username Already Exist or Empty Input\n");
        }
        else if(!isPasswordValid){
            stringBuilder.append("Please enter a password\n");
        }
        else if(!isRewardPointValid){
            stringBuilder.append("Invalid reward point.(Only numeric value 0-100)\n");
        }

        addAccountLabel.setText(String.valueOf(stringBuilder));
        addAccountLabel.setTextFill(Color.web("#daac89"));
    }

    //This helps initialize and set the value choice for combo box and lock the setting for reward point if user is not Vip
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBoxAccountType.getItems().addAll("Guest", "Regular", "Vip");

        comboBoxAccountType.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                if (!t1.equals("Vip")){
                    rewardPoint.setDisable(true);
                }else{
                    rewardPoint.setDisable(false);
                }
            }
        });
    }

    //Set the customer info value to the dialog when pop up
    public void setCustomerValue(Customer customer) {
        comboBoxAccountType.setValue(customer.getAccountType());
        name.setText(customer.getName());
        address.setText(customer.getAddress());
        phone.setText(customer.getPhone());
        balance.setText(String.valueOf(customer.getBalance()));
        username.setText(customer.getUsername());
        password.setText(customer.getPassword());
        if(customer.getAccountType().equals("Vip")){
            rewardPoint.setText(String.valueOf(customer.getRewardPoint()));
        }
    }

    //check if the username is already available and return a boolean
    public boolean checkNewUsernameAvailable(String string, ObservableList<Customer> customersDatabase, Customer selectCus){
        if(selectCus.getUsername().equals(string)){
            return true;
        }
        int count = 0;
        System.out.println("count == " + count);
        for(Customer customer: customers){
            if(string.equals(customer.getUsername())){
                count++;
            }
        }
        System.out.println("count == " + count);
        if(count >=1 || string.isEmpty()){
            return false;
        }
        return true;
    }
}
