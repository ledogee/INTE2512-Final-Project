package com.example.videostore.Controller;

import com.example.videostore.Model.DVD;
import com.example.videostore.Model.Game;
import com.example.videostore.Model.Item;
import com.example.videostore.Model.SceneSwitcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerController {
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

    ObservableList<Item> items = FXCollections.observableArrayList();

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

        Item item1 = new DVD.DVDBuilder().buildTitle("Rat Race").buildLoanType(1).buildCopies(1).build();
        Item item2 = new Game.GameBuilder().buildTitle("Medal of Honour").buildLoanType(1).buildCopies(3).build();

        items.addAll(item1, item2);
        title.setCellValueFactory(new PropertyValueFactory<Item, String>("title"));
        rentalType.setCellValueFactory(new PropertyValueFactory<Item, String>("rentalType"));
        loanType.setCellValueFactory(new PropertyValueFactory<Item, String>("loanType"));
        numOfCopies.setCellValueFactory(new PropertyValueFactory<Item, Integer>("copies"));

        tableView.setItems(items);

        System.out.println(rentalType.getCellFactory());
        System.out.println(items);
    }

}
