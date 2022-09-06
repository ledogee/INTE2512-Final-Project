package com.example.videostore.Controller;

import com.example.videostore.Model.*;
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
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class menuController
{
    public static Customer user;

    @FXML
    private Label username;

    @FXML
    private Label balance;

    @FXML
    private Label rewardPoint;
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
    private TableColumn<Item, Boolean> i_rentalStatus;

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
        CustomerController.user = user;
        SceneSwitcher.switchToCustomer(event);
    }

    public Customer getUser() {
        return user;
    }

    public void setUser(Customer user) {
        this.user = user;
    }

    private int indexUser;

    //observable list to store data
    ObservableList<Item> itemDatabase = FXCollections.observableArrayList();
    ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();
    public void initialize()
    {
        itemDatabase = SingletonDatabase.getItems();

        // Filer the item status = true;
        /*itemDatabase.filtered(item -> item.isRentalStatus());*/


        customerObservableList = SingletonDatabase.getCustomers();
        System.out.println(this.getUser());

        // get the User location in the observableList
        for(int i = 0; i < customerObservableList.size(); i++) {
            if(customerObservableList.get(i).getId().equals(user.getId())) {
                this.indexUser = i;
                break;
            }
        }

        username.setText("WELCOME BACK " + user.getUsername());
        String result = String.format("%.2f", user.getBalance());
        balance.setText(result + " $");
        if(user instanceof Vip) {
            rewardPoint.setText( ((Vip) user).getRewardPoint() + " point");
        } else {
            rewardPoint.setText("0 point");
        }



        i_title.setCellValueFactory(new PropertyValueFactory<Item, String>("title"));
        i_rentalType.setCellValueFactory(new PropertyValueFactory<Item, String>("rentalType"));
        i_loanType.setCellValueFactory(new PropertyValueFactory<Item, String>("loanType"));
        i_numCopies.setCellValueFactory(new PropertyValueFactory<Item, Integer>("copies"));
        i_rentalFee.setCellValueFactory(new PropertyValueFactory<Item, Double>("rentalFee"));
        i_rentalStatus.setCellValueFactory(new PropertyValueFactory<Item, Boolean>("rentalStatus"));
        i_genres.setCellValueFactory(new PropertyValueFactory<Item, String>("genres"));
        i_action.setCellValueFactory(new PropertyValueFactory<Item, Button>("buttonRent"));
        i_tableView.setItems(itemDatabase);

        TableColumn<Item, Button> column = i_action ; // column you want
        List<Button> buttonList = new ArrayList<>();
        for (Item item : i_tableView.getItems()) {
            buttonList.add(i_action.getCellObservableValue(item).getValue());
        }
        this.buttonRents = buttonList;

        System.out.println(buttonRents);

        for(Button btn : buttonRents) {
            // check the copies == 0
            for(int i = 0; i < itemDatabase.size(); i++) {
                if (user instanceof Guest && itemDatabase.get(i).getLoanType().equals("TwoDays") && itemDatabase.get(i).getButtonRent() == btn) {
                    btn.setDisable(true);
                }

                if (itemDatabase.get(i).getButtonRent() == btn && (itemDatabase.get(i).getCopies() == 0 || !itemDatabase.get(i).isRentalStatus())) {
                        System.out.println(i);
                        btn.setDisable(true);
                        itemDatabase.get(i).setRentalStatus(false);
                }
            }
        }


        for(Button btn : buttonRents)
        {
            btn.setOnAction((actionEvent) -> {
                user.rentItem(itemDatabase, customerObservableList, btn, balance, indexUser, rewardPoint);
            });
        }
        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Item> filteredList = new FilteredList<>(itemDatabase, b -> true);

        searchbar.textProperty().addListener((observable, oldValue, newValue )->
        {
            filteredList.setPredicate(item ->
            {
                if(newValue == null || newValue.isEmpty())
                    return true;

                String lowerCAseFilter = newValue.toLowerCase();
                if(item.getTitle().toLowerCase().contains(lowerCAseFilter))
                    return true;
                else return item.getId().toLowerCase().contains(lowerCAseFilter);
            });
        });

        SortedList<Item> sortedList = new SortedList<>(filteredList);

        sortedList.comparatorProperty().bind(i_tableView.comparatorProperty());

        i_tableView.setItems(sortedList);
    }
}
