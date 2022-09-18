package com.example.videostore.Controller;

import com.example.videostore.Model.*;
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

public class adminAddAccountDialogController implements Initializable {

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
    boolean isUsernameValid = false;
    boolean isPasswordValid = false;
    boolean isRewardPointValid = false;

    //function used to create a new account
    public Customer processAccount(){
        //reset boolean to false
        isAccountTypeFilled = false;
        isNameValid = false;
        isAddressValid = false;
        isPhoneValid = false;
        isBalanceValid = false;
        isUsernameValid = false;
        isPasswordValid = false;
        isRewardPointValid = false;

        //validate input from admin and switch the boolean to true if the input meets the requirement
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
        if(checkUsernameAvailable(Username)){
            isUsernameValid = true;
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

        List<String> listId = new ArrayList<>();

        //create new account from the input
        if(AccountType == 0 && isAccountTypeFilled && isNameValid && isAddressValid && isPhoneValid && isBalanceValid && isUsernameValid && isPasswordValid){
            return new Guest.GuestBuilder().buildName(Name).buildAddress(Address).buildPhone(Phone).buildBalance(Balance).buildUsername(Username).buildPassword(Password).buildNumReturn(0).buildListRentals(listId).build();
        } else if (AccountType == 1 && isAccountTypeFilled && isNameValid && isAddressValid && isPhoneValid && isBalanceValid && isUsernameValid && isPasswordValid) {
            return new Regular.RegularBuilder().buildName(Name).buildAddress(Address).buildPhone(Phone).buildBalance(Balance).buildUsername(Username).buildPassword(Password).buildNumReturn(0).buildListRentals(listId).build();
        } else if (AccountType == 2 && isAccountTypeFilled && isNameValid && isAddressValid && isPhoneValid && isBalanceValid && isUsernameValid && isPasswordValid && isRewardPointValid) {
            return new Vip.VipBuilder().buildName(Name).buildAddress(Address).buildPhone(Phone).buildBalance(Balance).buildUsername(Username).buildPassword(Password).buildNumReturn(0).buildListRentals(listId).buildRewardPoint(RewardPoint).build();
        }
        return null;
    }

    //validate input phone number
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
    //set label new error label to the dialog pane ì the user enter incorrectly
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
            stringBuilder.append("Please fill in a password\n");
        }
        else if(!isRewardPointValid){
            stringBuilder.append("Invalid reward point.(Only numeric value 0-100)\n");
        }

        addAccountLabel.setText(String.valueOf(stringBuilder));
        addAccountLabel.setTextFill(Color.web("#daac89"));
    }
    //This helps initialize and set the value choice for combo box and lock the setting for reward point if the new account is Vip
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

    //Check if the username overlapped or empty
    public boolean checkUsernameAvailable(String string){
        ObservableList<Customer> temp = getCustomers();
        for(Customer customer: temp){
            if(string.equals(customer.getUsername()) || string.isEmpty()){
                return false;
            }
        }
        return true;
    }
}
