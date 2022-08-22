package com.example.videostore.Controller;

import com.example.videostore.Model.*;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


import javax.security.auth.callback.Callback;
import java.util.List;

public class adminController {
    private TableColumn<Customer, String> c_accountType;
    @FXML
    private TableColumn<Customer, String> c_address;

    @FXML
    private TableColumn<Customer, Integer> c_balance;

    @FXML
    private TableColumn<Customer, String> c_id;

    @FXML
    private TableColumn<Customer, List<String>> c_listRental;

    @FXML
    private TableColumn<Customer, String> c_name;

    @FXML
    private TableColumn<Customer, String> c_password;

    @FXML
    private TableColumn<Customer, String> c_phone;

    @FXML
    private TableColumn<Customer, String> c_username;

    @FXML
    private TableColumn<Item, String> i_genres;

    @FXML
    private TableColumn<Item, String> i_id;

    @FXML
    private TableColumn<Item, String> i_loanType;

    @FXML
    private TableColumn<Item, Integer> i_numCopies;

    @FXML
    private TableColumn<Item, Double> i_rentalFee;

    @FXML
    private TableColumn<Item, String> i_rentalType;

    @FXML
    private TableColumn<Item, Boolean> i_status;

    @FXML
    private TableColumn<Item, String> i_title;

    @FXML
    private TableView<Customer> c_tableView;

    @FXML
    private TableView<Item> i_tableView;

    ObservableList<Item> items = FXCollections.observableArrayList();



    public void initialize() {

        Item item1 = new DVD.DVDBuilder().buildTitle("Rat Race").buildLoanType(1).buildCopies(1).buildRentalFee(1.99).buildGenres(2).buildYear("2015").build();
        Item item2 = new Game.GameBuilder().buildTitle("Medal of Honour").buildLoanType(1).buildCopies(3).buildRentalFee(0.99).buildYear("2001").buildImage("asdasd").build();

        items.addAll(item1, item2);
        i_id.setCellValueFactory(new PropertyValueFactory<Item, String>("id"));
        i_title.setCellValueFactory(new PropertyValueFactory<Item, String>("title"));
        i_rentalType.setCellValueFactory(new PropertyValueFactory<Item, String>("rentalType"));


        i_loanType.setCellValueFactory(new PropertyValueFactory<Item, String>("loanType"));
        i_numCopies.setCellValueFactory(new PropertyValueFactory<Item, Integer>("copies"));
        i_rentalFee.setCellValueFactory(new PropertyValueFactory<Item, Double>("rentalFee"));
        i_status.setCellValueFactory(new PropertyValueFactory<Item, Boolean>("rentalFee"));
        i_genres.setCellValueFactory(new PropertyValueFactory<Item, String>("genres"));

/*
        i_genres.setCellValueFactory(new PropertyValueFactory<Item, String>("genres"));
*/


/*
            i_genres.setCellValueFactory(new PropertyValueFactory<Item, String>("genres"));
*/

        i_tableView.setItems(items);
    /*    for(int i = 0; i < items.size(); i++) {
            if(!i_rentalType.getCellData(i).equals("Game")) {

                i_genres.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getGenres()));
            }
        }*/
        System.out.println(i_rentalType.getCellFactory());
        System.out.println(items);
    }


}
