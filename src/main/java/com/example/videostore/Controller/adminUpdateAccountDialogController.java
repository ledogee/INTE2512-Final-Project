package com.example.videostore.Controller;

import com.example.videostore.Model.Customer;
import com.example.videostore.Model.Guest;
import com.example.videostore.Model.Regular;
import com.example.videostore.Model.Vip;
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

    public Customer processUpdateAccount(Customer cus, ObservableList<Customer> customersDatabase, int selectedIndex){
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
        if(checkNewUsernameAvailable(Username, selectedIndex)){
            isUsernameValid = true;
        }

        String Password = password.getText().trim();
        if(!password.getText().isEmpty()){
            isPasswordValid = true;
        }

        if(AccountType == 0 && isAccountTypeFilled && isNameValid && isAddressValid && isPhoneValid && isBalanceValid && isUsernameValid && isPasswordValid){

            cus.setName(Name);
            cus.setAddress(Address);
            cus.setPhone(Phone);
            cus.setBalance(Balance);
            cus.setUsername(Username);
            cus.setPassword(Password);
            Guest guest = new Guest.GuestBuilder(cus).build();
            //Update database
            customersDatabase.set(selectedIndex, guest);
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
            customersDatabase.set(selectedIndex, reg);
            return reg;

        } else if (AccountType == 2 && isAccountTypeFilled && isNameValid && isAddressValid && isPhoneValid && isBalanceValid && isUsernameValid && isPasswordValid) {
            cus.setName(Name);
            cus.setAddress(Address);
            cus.setPhone(Phone);
            cus.setBalance(Balance);
            cus.setUsername(Username);
            cus.setPassword(Password);
            Vip vip = new Vip.VipBuilder(cus).build();

            // Update the db
            customersDatabase.set(selectedIndex, vip);
            System.out.println(vip);
            return vip;
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
            stringBuilder.append("Invalid phone number or Empty Input\n");
        }
        if(!isBalanceValid){
            stringBuilder.append(("Invalid balance.(Only numeric value)\n"));
        }
        if(!isUsernameValid){
            stringBuilder.append("Username Already Exist or Empty Input\n");
        }
        if(!isPasswordValid){
            stringBuilder.append("Please enter a password\n");
        }

        addAccountLabel.setText(String.valueOf(stringBuilder));
        addAccountLabel.setTextFill(Color.web("#FF0000"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBoxAccountType.getItems().addAll("Guest", "Regular", "Vip");
    }

    public boolean checkNewUsernameAvailable(String string, int index){
        if(string.equals(customers.get(index).getUsername())){
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

    public void setCustomerValue(int i) {
        comboBoxAccountType.setValue(customers.get(i).getAccountType());
        name.setText(customers.get(i).getName());
        address.setText(customers.get(i).getAddress());
        phone.setText(customers.get(i).getPhone());
        balance.setText(String.valueOf(customers.get(i).getBalance()));
        username.setText(customers.get(i).getUsername());
        password.setText(customers.get(i).getPassword());
    }

}
