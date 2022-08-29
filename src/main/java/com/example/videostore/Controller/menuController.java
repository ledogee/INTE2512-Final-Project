package com.example.videostore.Controller;

import com.example.videostore.Model.Item;
import com.example.videostore.Model.SceneSwitcher;
import com.example.videostore.SystemBroker.SingletonDatabase;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class menuController
{
    @FXML
    private TableColumn<Item, Button> i_action;

    @FXML
    private TableColumn<Item, String> i_genres;

    @FXML
    private TableColumn<Item, String> i_loanType;

    @FXML
    private TableColumn<Item, Integer> i_numCopies;

    @FXML
    private TableColumn<Item, Double> i_rentalFee;

    @FXML
    private TableColumn<Item, String> i_rentalType;

    @FXML
    private TableView<Item> i_tableView;

    @FXML
    private TableColumn<Item, String> i_title;

    @FXML
    private TextField searchbar;
    private List<Button> buttonRents;

    public void goToLogin(ActionEvent event) throws IOException
    {
        SceneSwitcher.switchToLogin(event);
    }
    public void goToCustomer(ActionEvent event) throws IOException
    {
        SceneSwitcher.switchToCustomer(event);
    }

    //observable list to store data
    ObservableList<Item> itemObservableList = FXCollections.observableArrayList();
    public void initialize()
    {
        itemObservableList = SingletonDatabase.getItems();
        i_title.setCellValueFactory(new PropertyValueFactory<Item, String>("title"));
        i_rentalType.setCellValueFactory(new PropertyValueFactory<Item, String>("rentalType"));
        i_loanType.setCellValueFactory(new PropertyValueFactory<Item, String>("loanType"));
        i_numCopies.setCellValueFactory(new PropertyValueFactory<Item, Integer>("copies"));
        i_rentalFee.setCellValueFactory(new PropertyValueFactory<Item, Double>("rentalFee"));
        i_genres.setCellValueFactory(new PropertyValueFactory<Item, String>("genres"));
        i_action.setCellValueFactory(new PropertyValueFactory<Item, Button>("buttonRent"));
        i_tableView.setItems(itemObservableList);

        TableColumn<Item, Button> column = i_action ; // column you want
        List<Button> buttonList = new ArrayList<>();
        for (Item item : i_tableView.getItems()) {
            buttonList.add(i_action.getCellObservableValue(item).getValue());
        }
        this.buttonRents = buttonList;

        System.out.println(buttonRents);

        for(Button btn : buttonRents)
        {
            btn.setOnAction((actionEvent) -> {
                for(int i = 0; i < itemObservableList.size(); i++) {
                    if(itemObservableList.get(i).getButtonRent() == btn) {
                        Item item = itemObservableList.get(i);
                        itemObservableList.set(i, item).setCopies(item.getCopies() - 1);
                        if(item.getCopies() == 0){
                            btn.setDisable(true);
                        } else if(item.getCopies() > 0) {
                            btn.setDisable(false);
                        }
                    }
                }
            });
        }
        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Item> filteredList = new FilteredList<>(itemObservableList, b -> true);

        searchbar.textProperty().addListener((observable, oldValue, newValue )->
        {
            filteredList.setPredicate(item ->
            {
                if(newValue == null || newValue.isEmpty())
                    return true;

                String lowerCAseFilter = newValue.toLowerCase();
                if(item.getTitle().toLowerCase().indexOf(lowerCAseFilter) != -1)
                    return true;
                else if (item.getId().toLowerCase().indexOf(lowerCAseFilter) != -1)
                    return true;
                else return false;
            });
        });

        SortedList<Item> sortedList = new SortedList<>(filteredList);

        sortedList.comparatorProperty().bind(i_tableView.comparatorProperty());

        i_tableView.setItems(sortedList);
    }
}
