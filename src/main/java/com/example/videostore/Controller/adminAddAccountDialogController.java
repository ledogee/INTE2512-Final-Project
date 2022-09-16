package com.example.videostore.Controller;

import com.example.videostore.Model.*;
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
    private Label addAccountLabel;

    public void setAddAccountLabel(String string) {
        addAccountLabel.setText(string);
    }

    boolean isAccountTypeFilled = false;
    boolean isNameValid = false;
    boolean isAddressValid = false;
    boolean isPhoneValid = false;
    boolean isBalanceValid = false;
    boolean isUsernameValid = false;
    boolean isPasswordValid = false;
    public Customer processAccount(){
        isAccountTypeFilled = false;
        isNameValid = false;
        isAddressValid = false;
        isPhoneValid = false;
        isBalanceValid = false;
        isUsernameValid = false;
        isPasswordValid = false;

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
        if(!password.getText().isEmpty() && password.getText().length() >= 8){
            isPasswordValid = true;
        }

        System.out.println("------------------------------"); //for testing
        System.out.println(AccountType + " " + isAccountTypeFilled);
        System.out.println(Address + " " + isAddressValid);
        System.out.println(Phone + " " +  isPhoneValid);
        System.out.println(Balance + " " + isBalanceValid);

        List<String> listId = new ArrayList<>();
        if(AccountType == 0 && isAccountTypeFilled && isNameValid && isAddressValid && isPhoneValid && isBalanceValid && isUsernameValid && isPasswordValid){
            return new Guest.GuestBuilder().buildName(Name).buildAddress(Address).buildPhone(Phone).buildBalance(Balance).buildUsername(Username).buildPassword(Password).buildNumReturn(0).buildListRentals(listId).build();
        } else if (AccountType == 1 && isAccountTypeFilled && isNameValid && isAddressValid && isPhoneValid && isBalanceValid && isUsernameValid && isPasswordValid) {
            return new Regular.RegularBuilder().buildName(Name).buildAddress(Address).buildPhone(Phone).buildBalance(Balance).buildUsername(Username).buildPassword(Password).buildNumReturn(0).buildListRentals(listId).build();
        } else if (AccountType == 2 && isAccountTypeFilled && isNameValid && isAddressValid && isPhoneValid && isBalanceValid && isUsernameValid && isPasswordValid) {
            return new Vip.VipBuilder().buildName(Name).buildAddress(Address).buildPhone(Phone).buildBalance(Balance).buildUsername(Username).buildPassword(Password).buildNumReturn(0).buildListRentals(listId).build();
        }
        return null;
    }


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

    public void setAccountLabel(){
        StringBuilder stringBuilder = new StringBuilder();
        if(!isAccountTypeFilled){
            stringBuilder.append("Please select an Account Type\n");
        }
        if(!isNameValid){
            stringBuilder.append("Invalid name.(Please fill a name)\n");
        }
        if(!isAddressValid){
            stringBuilder.append("Invalid address.(Please fill an address)\n");
        }
        if(!isPhoneValid){
            stringBuilder.append("Unrecognized phone number or Empty Input\n");
        }
        if(!isBalanceValid){
            stringBuilder.append(("Invalid balance.(Only numeric value)\n"));
        }
        if(!isUsernameValid){
            stringBuilder.append("Username Already Exist or Empty Input\n");
        }
        if(!isPasswordValid){
            stringBuilder.append("Password should be at least 8 characters\n");
        }

        addAccountLabel.setText(String.valueOf(stringBuilder));
        addAccountLabel.setTextFill(Color.web("#FF0000"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBoxAccountType.getItems().addAll("Guest", "Regular", "Vip");
    }

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
