package com.example.videostore.Controller;

import com.example.videostore.Model.*;
import com.example.videostore.SystemBroker.SingletonDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Window;


import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static com.example.videostore.Controller.notificationController.popAdminNotification;
import static com.example.videostore.Controller.notificationController.popMenuNotification;

public class adminController extends adminAddItemDialogController implements Initializable {
    @FXML
    public TextField searchbarItem;
    @FXML
    public TextField searchbarCustomer;
    public Button deleteCustomer;
    public Button deleteItem;

    @FXML
    private Button returnToLoginButton;
    @FXML
    private Button addItemButton;
    @FXML
    private Button updateItemButton;
    @FXML
    private AnchorPane adminPane;


    public void goToLogin(ActionEvent event) throws IOException {
        SceneSwitcher.switchToLogin(event);
    }
    public void goToRegister(ActionEvent event) throws IOException {
        SceneSwitcher.switchToRegister(event);
    }
    @FXML
    private TableColumn<Customer, String> c_accountType;
    @FXML
    private TableColumn<Customer, String> c_address;

    @FXML
    private TableColumn<Customer, Double> c_balance;

    @FXML
    private TableColumn<Customer, String> c_id;

    @FXML
    private TableColumn<Customer, String> c_listRental;

    @FXML
    private TableColumn<Customer, String> c_name;

    @FXML
    private TableColumn<Customer, String> c_password;

    @FXML
    private TableColumn<Customer, String> c_phone;

    @FXML
    private TableColumn<Customer, Integer> c_rewardPoint;

    @FXML
    private TableColumn<Customer, String> c_username;

    @FXML
    private TableColumn<Customer, String> c_numOfReturn;

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

    static ObservableList<Customer> customers = FXCollections.observableArrayList();
    static ObservableList<Item> items = FXCollections.observableArrayList();

    public static ObservableList<Item> getItems() {
        return items;
    }

    public static ObservableList<Customer> getCustomers() {
        return customers;
    }

    //display the information of item and customer data to the tables
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        c_listRental.setCellFactory(tc -> {
            TableCell<Customer, String> cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(c_listRental.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell ;
        });

        //add item data to the table
        items = SingletonDatabase.getItems();
        i_id.setCellValueFactory(new PropertyValueFactory<Item, String>("id"));
        menuController.loadTable(i_title, i_rentalType, i_loanType, i_numCopies, i_rentalFee, i_status, i_genres);
        i_tableView.setItems(items);

        //create search bar for item
        FilteredList<Item> filteredList = new FilteredList<>(items, b -> true);
        searchbarItem.textProperty().addListener((observable, oldValue, newValue )->
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


        //add customer info to the table
        customers = SingletonDatabase.getCustomers();
        c_accountType.setCellValueFactory(new PropertyValueFactory<Customer, String>("accountType"));
        c_id.setCellValueFactory(new PropertyValueFactory<Customer, String>("id"));
        c_address.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
        c_balance.setCellValueFactory(new PropertyValueFactory<Customer, Double>("balance"));
        c_name.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
        c_password.setCellValueFactory(new PropertyValueFactory<Customer, String>("password"));
        c_phone.setCellValueFactory(new PropertyValueFactory<Customer, String>("phone"));
        c_rewardPoint.setCellValueFactory(new PropertyValueFactory<>("rewardPoint"));
        c_username.setCellValueFactory(new PropertyValueFactory<Customer, String>("username"));
        c_listRental.setCellValueFactory(new PropertyValueFactory<Customer, String>("combinedString"));
        c_numOfReturn.setCellValueFactory(new PropertyValueFactory<Customer, String>("numberOfReturn"));
        for(Customer customer : customers){
            customer.setCombinedString(customer.arraytostring());
        }
        c_tableView.setItems(customers);

        //create search bar for account
        FilteredList<Customer> filteredList2 = new FilteredList<>(customers, b -> true);

        searchbarCustomer.textProperty().addListener((observable, oldValue, newValue )->
        {
            filteredList2.setPredicate(item ->
            {
                if(newValue == null || newValue.isEmpty())
                    return true;

                String lowerCAseFilter = newValue.toLowerCase();
                if(item.getName().toLowerCase().contains(lowerCAseFilter))
                    return true;
                else return item.getId().toLowerCase().contains(lowerCAseFilter);
            });
        });

        SortedList<Customer> sortedList2 = new SortedList<>(filteredList2);

        sortedList2.comparatorProperty().bind(c_tableView.comparatorProperty());
        c_tableView.setItems(sortedList2);

        //Allows users select multiple rows at once
        i_tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        c_tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        //Delete the selected item
        deleteItem.setOnAction(e -> {
            items.remove(i_tableView.getSelectionModel().getSelectedItem());
        });
        //Delete the selected customer
        deleteCustomer.setOnAction(e -> {
            customers.remove(c_tableView.getSelectionModel().getSelectedItem());
        });

    }

    //Display the item out of stock to the table
    public void displayItemOutOfStock(ActionEvent event) {
        ObservableList<Item> itemsOutOfStock = FXCollections.observableArrayList();

        for(Item item: items) {
            if(item.getCopies() == 0) {
                itemsOutOfStock.add(item);
            }
        }
        i_tableView.setItems(itemsOutOfStock);
    }

    //Display all item in the table
    public void displayAllItems(ActionEvent event) {
        i_tableView.setItems(items);
    }

    //display the dialog for the admin to add a new item
    public void showNewItemDialog()
    {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(adminPane.getScene().getWindow());
        dialog.setTitle("Add New Item");
        dialog.setHeaderText("Use this dialog to create a new item");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/com/example/videostore/addItemDialog.fxml"));

        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }

        // Add button OK and Cancel
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        adminAddItemDialogController controller = fxmlLoader.getController();
        controller.setNewLabel("");
        //show the dialog and wait for response (click Okay or Cancel)
        Optional<ButtonType> result = dialog.showAndWait();

        //create Item process
        if(result.isPresent() && result.get() == ButtonType.OK) {
            Item newItem = controller.processItem();

            System.out.println(newItem);
            while((newItem == null && result.get() == ButtonType.OK) ){
                controller.setItemLabel();
                result = dialog.showAndWait();
                newItem = controller.processItem();
                if(newItem != null && result.get() == ButtonType.OK){
                    break;
                }

            }
            if(result.get() == ButtonType.OK){
                //Display notification
                popAdminNotification(adminPane, "Successfully add new Item", "#008000");
                SingletonDatabase.getItems().add(newItem);
            }

        } else {
            System.out.println("Cancel pressed");
        }
    }
    //display the dialog for the admin to update a selected item
    public void showUpdateItemDialog() {
        //save the selected item
        Item selectedItem = i_tableView.getSelectionModel().getSelectedItem();
        int itemIndex = 0;
        //use try catch to handle error when item is not selected
        try {
            for(int i = 0; i < items.size(); i++){
                if (selectedItem.getId().equals(items.get(i).getId())){
                    itemIndex = i;
                }
            }
            if (selectedItem != null) {
                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.initOwner(adminPane.getScene().getWindow());
                dialog.setTitle("Update Item");
                dialog.setHeaderText("Use this dialog to update an item");
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/videostore/updateItemDialog.fxml"));

                try {
                    dialog.getDialogPane().setContent(fxmlLoader.load());
                } catch (IOException e) {
                    System.out.println("Couldn't load the dialog");
                    e.printStackTrace();
                    return;
                }

                dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
                adminUpdateItemDialogController controller = fxmlLoader.getController();
                controller.setItemValue(itemIndex);
                Window window = dialog.getDialogPane().getScene().getWindow();
                Optional<ButtonType> result = dialog.showAndWait();

                //update Item process
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    Item newItem = controller.processUpdateItem(selectedItem, items, itemIndex);

                    System.out.println(newItem);

                    while ((newItem == null && result.get() == ButtonType.OK)) {
                        controller.setItemLabel();
                        result = dialog.showAndWait();
                        newItem = controller.processUpdateItem(selectedItem, items, itemIndex);
                        if ((newItem != null && result.get() == ButtonType.OK)) {
                            break;
                        }
                        window.setOnCloseRequest(event -> window.hide());
                    }
                    if (result.get() == ButtonType.OK) { //Display notification
                        popAdminNotification(adminPane, "Successfully update Item", "#008000");
                    }

                    System.out.println("Ok pressed");

                } else {
                    System.out.println("Cancel pressed");
                }
            }
        } catch (NullPointerException e) {
            popMenuNotification(adminPane, "Please select row", "#FF0000");
        }
    }
    //display dialog for admin to create new account
    public void showNewAccountDialog(){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(adminPane.getScene().getWindow());
        dialog.setTitle("Add New Customer Info");
        dialog.setHeaderText("Use this dialog to add a new customer");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/com/example/videostore/addCustomerDialog.fxml"));

        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }

        // Add button OK and Cancel
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        adminAddAccountDialogController controller = fxmlLoader.getController();
        controller.setAddAccountLabel("");
        //show the dialog and wait for response (click Okay or Cancel)
        Optional<ButtonType> result = dialog.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK) {
            Customer newAccount = controller.processAccount();

            System.out.println(newAccount);

            while((newAccount == null && result.get() == ButtonType.OK) ){
                controller.setAccountLabel();
                result = dialog.showAndWait();

                newAccount = controller.processAccount();
                if(newAccount != null && result.get() == ButtonType.OK){
                    break;
                }
            }
            if(newAccount != null && result.get() == ButtonType.OK){
                SingletonDatabase.getCustomers().add(newAccount);
                popAdminNotification(adminPane, "Successfully add new Account", "#008000");
            }

            System.out.println("Ok pressed");

        } else {
            System.out.println("Cancel pressed");
        }
    }
    //display the dialog for the admin to update a selected account
    public void showUpdateAccountDialog() {
        //save the selected customer
        int selectedIndex = c_tableView.getSelectionModel().getSelectedIndex();
        Customer selectedCus = c_tableView.getSelectionModel().getSelectedItem();

        //check if the select index appear (!= -1)
        if (selectedIndex != -1) {
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.initOwner(adminPane.getScene().getWindow());
            dialog.setTitle("Update Account");
            dialog.setHeaderText("Use this dialog to update an account");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/videostore/updateCustomerDialog.fxml"));

            try {
                dialog.getDialogPane().setContent(fxmlLoader.load());
            } catch (IOException e) {
                System.out.println("Couldn't load the dialog");
                e.printStackTrace();
                return;
            }

            // Add Okay and Cancel button
            dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
            adminUpdateAccountDialogController controller = fxmlLoader.getController();
            controller.setCustomerValue(selectedCus);
            Window window = dialog.getDialogPane().getScene().getWindow();
            //display dialog pane and wait for response
            Optional<ButtonType> result = dialog.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                Customer newAccount = controller.processUpdateAccount(selectedCus, customers);
                if(newAccount != null){
                    newAccount.setCombinedString(newAccount.arraytostring());
                }
                System.out.println(newAccount);

                //run while loop if newAccount return null (incorrect input)
                while ((newAccount == null && result.get() == ButtonType.OK)) {
                    controller.setAccountLabel();
                    result = dialog.showAndWait();

                    newAccount = controller.processUpdateAccount(selectedCus, customers);
                    //break the loop if the processUpdateAccount can return an update
                    if ((newAccount != null && result.get() == ButtonType.OK)) {
                        newAccount.setCombinedString(newAccount.arraytostring());
                        break;
                    }
                    window.setOnCloseRequest(event -> window.hide());
                }
                if (result.get() == ButtonType.OK) { //Display notification
                    popAdminNotification(adminPane, "Successfully update Account", "#008000");
                }

            } else {
                System.out.println("");
            }
        } else {
            popMenuNotification(adminPane, "Please select row", "#FF0000");
        }
    }
    //check for a string in text field if it is a double
    public static boolean isDouble(String stringFromTextField) {
        if (stringFromTextField == null) { //Check if the text field is empty
            return false;
        }
        try {
            double d = Double.parseDouble(stringFromTextField); //Check if the value in the text field is a double
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    //check for a string in text field if it is an integer
    public static boolean isInteger(String stringFromTextField) {
        if (stringFromTextField == null) { //Check if the text field is empty
            return false;
        }
        try {
            int d = Integer.parseInt(stringFromTextField); //Check if the value in the text field is an integer
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}

