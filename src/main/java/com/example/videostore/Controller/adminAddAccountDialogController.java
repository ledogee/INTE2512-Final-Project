package com.example.videostore.Controller;

import com.example.videostore.Model.Customer;
import com.example.videostore.Model.Guest;
import com.example.videostore.Model.Regular;
import com.example.videostore.Model.Vip;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.videostore.Controller.adminController.isDouble;
import static com.example.videostore.Controller.adminController.isInteger;

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
    boolean isNumOfReturnValid = false;
    public Customer processAccount(){
        isAccountTypeFilled = false;
        isNameValid = false;
        isAddressValid = false;
        isPhoneValid = false;
        isBalanceValid = false;
        isUsernameValid = false;
        isPasswordValid = false;
        isNumOfReturnValid = false;

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
            isUsernameValid = true;
        }

        String Password = password.getText().trim();
        if(!password.getText().isEmpty() && password.getText().length() >= 8){
            isPasswordValid = true;
        }

        Integer NumOfReturn = null;
        if(isInteger(numOfReturn.getText()) && Integer.parseInt(numOfReturn.getText().trim())>=0){
            NumOfReturn = Integer.parseInt(numOfReturn.getText().trim());
            isNumOfReturnValid = true;
        }

//        String ListRental = listRental.getText().trim();
        List<String> ListRental = null;
        //Remember to check true false and add it to checking when create object

        System.out.println("------------------------------");
        System.out.println(AccountType + " " + isAccountTypeFilled);
        System.out.println(Address + " " + isAddressValid);
        System.out.println(Phone + " " +  isPhoneValid);
        System.out.println(Balance + " " + isBalanceValid);

        if(AccountType == 0 && isAccountTypeFilled && isNameValid && isAddressValid && isPhoneValid && isBalanceValid && isUsernameValid && isPasswordValid && isNumOfReturnValid){
            return new Guest.GuestBuilder().buildName(Name).buildAddress(Address).buildPhone(Phone).buildBalance(Balance).buildUsername(Username).buildPassword(Password).buildNumReturn(NumOfReturn).build();
        } else if (AccountType == 1 && isAccountTypeFilled && isNameValid && isAddressValid && isPhoneValid && isBalanceValid && isUsernameValid && isPasswordValid && isNumOfReturnValid) {
            return new Regular.RegularBuilder().buildName(Name).buildAddress(Address).buildPhone(Phone).buildBalance(Balance).buildUsername(Username).buildPassword(Password).buildNumReturn(NumOfReturn).build();
        } else if (AccountType == 2 && isAccountTypeFilled && isNameValid && isAddressValid && isPhoneValid && isBalanceValid && isUsernameValid && isPasswordValid && isNumOfReturnValid) {
            return new Vip.VipBuilder().buildName(Name).buildAddress(Address).buildPhone(Phone).buildBalance(Balance).buildUsername(Username).buildPassword(Password).buildNumReturn(NumOfReturn).build();
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
            stringBuilder.append("Invalid phone number.(Unrecognized phone number or Empty Input\n");
        }
        if(!isBalanceValid){
            stringBuilder.append(("Invalid balance.(Only numeric value)\n"));
        }
        if(!isUsernameValid){
            stringBuilder.append("Invalid username.(Username Already Exist or Empty Input)\n");
        }
        if(!isPasswordValid){
            stringBuilder.append("Invalid Password.(Please enter a password with at least 8 characters)\n");
        }
        if(!isNumOfReturnValid){
            stringBuilder.append("Invalid Number of Rental. (Only integer value)\n");
        }
        addAccountLabel.setText(String.valueOf(stringBuilder));
        addAccountLabel.setTextFill(Color.web("#FF0000"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBoxAccountType.getItems().addAll("Guest", "Regular", "Vip");
    }
}
