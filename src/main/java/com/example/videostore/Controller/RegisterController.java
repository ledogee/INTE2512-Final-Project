package com.example.videostore.Controller;

import com.example.videostore.Model.Customer;
import com.example.videostore.Model.Guest;
import com.example.videostore.Model.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegisterController {
    @FXML
    private TextField name;
    @FXML
    private TextField address;
    @FXML
    private TextField phone;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button signUpButton;

    public void goToLogin(ActionEvent event) throws IOException {
        SceneSwitcher.switchToLogin(event);
    }

    public void createCustomer(ActionEvent event) throws IOException {
        List<Customer> customerList = new ArrayList<Customer>();
        Customer cus1 = new Guest.GuestBuilder().buildName("Linh").buildAddress("RMIT University").buildPhone("01234567").buildUsername("plinhxinh").buildPassword("hihihi").build();
        Customer cus2 = new Guest.GuestBuilder().buildName("Khang").buildAddress("Sai Gon").buildPhone("034537812").buildUsername("sieunhantim").buildPassword("abc").build();
        Customer cus3 = new Guest.GuestBuilder().buildName("Chau Anh").buildAddress("Ha Noi").buildPhone("052374192").buildUsername("chauxauxi").buildPassword("hehehe").build();
        customerList.add(cus1);
        customerList.add(cus2);
        customerList.add(cus3);

        boolean validUsername = true;
        boolean validPhone = false;

        if (name.getText() != "" && address.getText() != "" && phone.getText() != "" && username.getText() != "" && password.getText() != "") {
            for (Customer cus : customerList) {
                if (username.getText().equals(cus.getUsername())) {
                    validUsername = false;
                    break;
                }
            }
            if (isNumber(phone.getText())) {
                validPhone = true;
            }
            if (validUsername == true && validPhone == true) {
                signUpButton.setOnAction((actionEvent) -> {
                    Customer customer = new Guest.GuestBuilder().buildName(name.getText()).buildAddress(address.getText())
                            .buildPhone(phone.getText()).buildUsername(username.getText()).buildPassword(password.getText()).build();
                    System.out.println("Sign up successfully");
                    System.out.println(customer.toString());
                    try {
                        SceneSwitcher.switchToMenu(event);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            } else if (validUsername == false) {
                System.out.println("Username not unique!");
            } else if (validPhone == false) {
                System.out.println("Invalid phone number!");
            }
        }
    }
    public static boolean isNumber(String string)
    {
        return string.matches("^\\d+$");
    }

}

