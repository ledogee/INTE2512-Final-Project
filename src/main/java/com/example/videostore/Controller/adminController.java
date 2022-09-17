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

      /*  Item item1 = null;
        item1 = new DVD.DVDBuilder().buildTitle("Rat Race").buildLoanType(1).buildCopies(1).buildRentalFee(1.99).buildGenres(2).buildYear("2015").build();
        Item item2 = new Game.GameBuilder().buildTitle("Medal of Honour").buildLoanType(1).buildCopies(3).buildRentalFee(0.99).buildYear("2001").build();
        Item item3 = new Game.GameBuilder().buildTitle("Gear medal").buildLoanType(4).buildCopies(3).buildRentalFee(120.99).buildYear("2010").build();*/

        items = SingletonDatabase.getItems();
        /*Item itemNew = new Game.GameBuilder().buildTitle("Gear medal").buildLoanType(4).buildCopies(3).buildRentalFee(120.99).buildYear("2010").build();
        items.add(itemNew);*/

        i_id.setCellValueFactory(new PropertyValueFactory<Item, String>("id"));
        menuController.loadTable(i_title, i_rentalType, i_loanType, i_numCopies, i_rentalFee, i_status, i_genres);


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

        deleteItem.setOnAction(e -> {
            items.remove(i_tableView.getSelectionModel().getSelectedItem());
        });

        deleteCustomer.setOnAction(e -> {
            customers.remove(c_tableView.getSelectionModel().getSelectedItem());
        });

    }

    public void displayItemOutOfStock(ActionEvent event) {
        ObservableList<Item> itemsOutOfStock = FXCollections.observableArrayList();

        for(Item item: items) {
            if(item.getCopies() == 0) {
                itemsOutOfStock.add(item);
            }
        }
        i_tableView.setItems(itemsOutOfStock);
    }

    public void displayAllItems(ActionEvent event) {
        i_tableView.setItems(items);
    }
    public void showNewItemDialog()
    {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(adminPane.getScene().getWindow());
        dialog.setTitle("Add New Item");
        dialog.setHeaderText("Use this dialog to create a new item");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/com/example/videostore/addItemDialog.fxml"));
//        URL fxmlLocation = getClass().getResource("addItemDialog.fxml");
//        FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);

        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }

        // Add button
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        adminAddItemDialogController controller = fxmlLoader.getController();
        controller.setNewLabel("");
        Optional<ButtonType> result = dialog.showAndWait();
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
                popAdminNotification(adminPane, "Successfully add new Item", "#008000");
                SingletonDatabase.getItems().add(newItem);
            }

            if(result.get() == ButtonType.OK){ //just for testing
                System.out.println("Ok pressed");
            }else {
                System.out.println("Cancel pressed");
            }

        } else {
            System.out.println("Cancel pressed");
        }
    }

    public void showUpdateItemDialog() {
        int selectedIndex = i_tableView.getSelectionModel().getSelectedIndex();

        if (selectedIndex != -1) {
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
            controller.setItemValue(selectedIndex);
            Window window = dialog.getDialogPane().getScene().getWindow();
            Optional<ButtonType> result = dialog.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                Item newItem = controller.processUpdateItem(items.get(selectedIndex), items, selectedIndex);

                System.out.println(newItem);

                while ((newItem == null && result.get() == ButtonType.OK)) {
                    controller.setItemLabel();
                    result = dialog.showAndWait();
                    newItem = controller.processUpdateItem(items.get(selectedIndex), items, selectedIndex);
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
//        if(result.isPresent() && result.get() == ButtonType.OK) {
//            // get the controller of Dialog to call the function processResults
//
//            Item newItem = controller.processUpdateItem();
//
//            System.out.println(newItem);
//            while((newItem == null && result.get() == ButtonType.OK) ){
//                controller.setItemLabel();
//                result = dialog.showAndWait();
//                newItem = controller.processUpdateItem();
//                if(newItem != null && result.get() == ButtonType.OK){
//                    break;
//                }
//
//            }
//            if(result.get() == ButtonType.OK){
//                popAdminNotification(adminPane, "Successfully update Item", "#008000");
//                SingletonDatabase.getItems().add(newItem);
//            }
//
//            if(result.get() == ButtonType.OK){ //just for testing
//                System.out.println("Ok pressed");
//            }else {
//                System.out.println("Cancel pressed");
//            }
//
//
//        } else {
//            System.out.println("Cancel pressed");
//        }
    }
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

        // Add button
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        adminAddAccountDialogController controller = fxmlLoader.getController();
        controller.setAddAccountLabel("");
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

    public void showUpdateAccountDialog() {
        int selectedIndex = c_tableView.getSelectionModel().getSelectedIndex();

        Customer selectedCus = c_tableView.getSelectionModel().getSelectedItem();

        /*Item selectedItem = c_tableView.getSelectionModel().getSelectedItem()*/

        if (selectedIndex != -1) {
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.initOwner(adminPane.getScene().getWindow());
            dialog.setTitle("Update Account");
            dialog.setHeaderText("Use this dialog to update an account");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/videostore/updateCustomerDialog.fxml"));
//        URL fxmlLocation = getClass().getResource("addItemDialog.fxml");
//        FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);

            try {
                dialog.getDialogPane().setContent(fxmlLoader.load());
            } catch (IOException e) {
                System.out.println("Couldn't load the dialog");
                e.printStackTrace();
                return;
            }

            // Add button
            dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
            adminUpdateAccountDialogController controller = fxmlLoader.getController();
            controller.setCustomerValue(selectedCus);
            Window window = dialog.getDialogPane().getScene().getWindow();
            Optional<ButtonType> result = dialog.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                Customer newAccount = controller.processUpdateAccount(selectedCus, customers);
                if(newAccount != null){
                    newAccount.setCombinedString(newAccount.arraytostring());
                }
                System.out.println(newAccount);

                while ((newAccount == null && result.get() == ButtonType.OK)) {
                    controller.setAccountLabel();
                    result = dialog.showAndWait();

                    newAccount = controller.processUpdateAccount(selectedCus, customers);
                    if ((newAccount != null && result.get() == ButtonType.OK)) {
                        newAccount.setCombinedString(newAccount.arraytostring());
                        break;
                    }
                    window.setOnCloseRequest(event -> window.hide());
                }
                if (result.get() == ButtonType.OK) { //Display notification
                    popAdminNotification(adminPane, "Successfully update Account", "#008000");
                }

                System.out.println("Ok pressed");

            } else {
                System.out.println("Cancel pressed");
            }
        }
    }

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

