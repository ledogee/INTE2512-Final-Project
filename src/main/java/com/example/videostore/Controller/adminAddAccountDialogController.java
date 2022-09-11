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
    boolean isPhoneValid = false;
    boolean isBalanceValid = false;
    boolean isUsernameValid = false;
    boolean isPasswordValid = false;
    boolean isNumOfReturnValid = false;
    boolean isListRentalValid = false;
    public Customer processAccount(){
        isAccountTypeFilled = false;
        isNameValid = false;
        isPhoneValid = false;
        isBalanceValid = false;
        isUsernameValid = false;
        isPasswordValid = false;
        isNumOfReturnValid = false;
        isListRentalValid = false;

        Integer AccountType = null;
        AccountType = comboBoxAccountType.getSelectionModel().getSelectedIndex();

        String Name = name.getText().trim();

        String Address = address.getText().trim();

        String Phone = phone.getText().trim();

        Double Balance = null;
        if(isDouble(balance.getText()) && Double.parseDouble(balance.getText().trim())>=0){
            Balance = Double.parseDouble(balance.getText().trim());
            //true false
        }

        String Username = username.getText().trim();

        String Password = password.getText().trim();

        Integer NumOfReturn = null;
        if(isInteger(numOfReturn.getText()) && Integer.parseInt(numOfReturn.getText().trim())>=0){
            NumOfReturn = Integer.parseInt(numOfReturn.getText().trim());
            //true false
        }

//        String ListRental = listRental.getText().trim();
        List<String> ListRental = null;

        System.out.println(AccountType + " Test ");

        if(AccountType == 0){
            return new Guest.GuestBuilder().buildName(Name).buildAddress(Address).buildPhone(Phone).buildBalance(Balance).buildUsername(Username).buildPassword(Password).buildNumReturn(NumOfReturn).buildListRentals(ListRental).build();
        } else if (AccountType == 1) {
            return new Regular.RegularBuilder().buildName(Name).buildAddress(Address).buildPhone(Phone).buildBalance(Balance).buildUsername(Username).buildPassword(Password).buildNumReturn(NumOfReturn).buildListRentals(ListRental).build();
        } else if (AccountType == 2) {
            return new Vip.VipBuilder().buildName(Name).buildAddress(Address).buildPhone(Phone).buildBalance(Balance).buildUsername(Username).buildPassword(Password).buildNumReturn(NumOfReturn).buildListRentals(ListRental).build();
        }
        return null;
    }








    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBoxAccountType.getItems().addAll("Guest", "Regular", "Vip");
    }
}
