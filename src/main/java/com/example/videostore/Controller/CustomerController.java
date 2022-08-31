package com.example.videostore.Controller;

import com.example.videostore.Model.*;
import com.example.videostore.SystemBroker.SingletonDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerController {

    public static Customer user;
    public Label name;
    public Label username;
    public Label password;
    public Label phone;
    public Label address;
    public Label accountType;
    public Label balance;
    public Label rewardPoint;
    @FXML
    private TableColumn<Item, String> loanType;

    @FXML
    private TableColumn<Item, Integer> numOfCopies;

    @FXML
    private TableColumn<Item, String> rentalType;

    @FXML
    private TableColumn<Item, String> title;

    @FXML
    private TableView<Item> tableView;


    ObservableList<Item> itemsDatabase = FXCollections.observableArrayList();


    public void goToLogin(ActionEvent event) throws IOException {
        SceneSwitcher.switchToLogin(event);
    }

    public void goToMenu(ActionEvent event) throws IOException {
        SceneSwitcher.switchToMenu(event);
    }

    public void UpdateInfoButtonHandler(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("updateInfo.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void initialize() {

        System.out.println(user.getName());
        itemsDatabase = SingletonDatabase.getItems();

        Map<String, Item> itemMap = new HashMap<>();

        /*List<String> itemList = user.getListRentals();*/


        /*for(String id : user.getListRentals()) {
            for(Item item : itemsDatabase) {
                if(id.equals(item.getId()) ) {
                    Item newItem = item;
                    // add to the map key by id
                    if(itemMap.containsKey(id)) {
                        itemMap.get(id).setQuantity(itemMap.get(id).getQuantity() + 1);
                    } else {
                        itemMap.put(newItem.getId(), newItem);
                    }
                }
            }
        }*/
        System.out.println(itemMap);

        title.setCellValueFactory(new PropertyValueFactory<Item, String>("title"));
        rentalType.setCellValueFactory(new PropertyValueFactory<Item, String>("rentalType"));
        loanType.setCellValueFactory(new PropertyValueFactory<Item, String>("loanType"));
        numOfCopies.setCellValueFactory(new PropertyValueFactory<Item, Integer>("copies"));


        tableView.setItems(itemsDatabase);

        System.out.println(rentalType.getCellFactory());
        System.out.println(itemsDatabase);
    }

}
