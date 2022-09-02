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
import java.util.*;
import java.util.stream.Collectors;

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
        Set<Item> itemSet = new HashSet<>();
        List<Item> itemList = new ArrayList<>();

        for(String id : user.getListRentals()) {
            for(Item item : itemsDatabase) {
                if(id.equals(item.getId())) {
                    itemList.add(item);
                }
            }
        }

        for(Item itemDup : itemList) {
            itemDup.setQuantity(Collections.frequency(itemList, itemDup));
            itemSet.add(itemDup);
        }

        ObservableList<Item> listRentals = FXCollections.observableArrayList(itemSet);

        title.setCellValueFactory(new PropertyValueFactory<Item, String>("title"));
        rentalType.setCellValueFactory(new PropertyValueFactory<Item, String>("rentalType"));
        loanType.setCellValueFactory(new PropertyValueFactory<Item, String>("loanType"));
        numOfCopies.setCellValueFactory(new PropertyValueFactory<Item, Integer>("quantity"));
        tableView.setItems(listRentals);

        System.out.println(rentalType.getCellFactory());
        System.out.println(itemsDatabase);
    }

}
