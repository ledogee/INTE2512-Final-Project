package com.example.videostore.Controller;

import com.example.videostore.Model.*;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;


import javax.security.auth.callback.Callback;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class adminController implements Initializable {
    @FXML
    private Button signOutButton;
    @FXML
    private VBox adminVBOX;

    @FXML
    public void goToLogin(ActionEvent event) throws IOException {
        SceneSwitcher.switchToLogin(event);
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

    @FXML
    private ListView<Item> itemListView;

    ObservableList<Customer> customers = FXCollections.observableArrayList();
    ObservableList<Item> items = FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle resourceBundle) {

        Item item1 = new DVD.DVDBuilder().buildTitle("Rat Race").buildLoanType(1).buildCopies(1).buildRentalFee(1.99).buildGenres(2).buildYear("2015").build();
        Item item2 = new Game.GameBuilder().buildTitle("Medal of Honour").buildLoanType(1).buildCopies(3).buildRentalFee(0.99).buildYear("2001").build();
        Item item3 = new Game.GameBuilder().buildTitle("Gear medal").buildLoanType(4).buildCopies(3).buildRentalFee(120.99).buildYear("2010").build();

        items.addAll(item1, item2, item3);
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


        Customer customer1 = new Vip.VipBuilder().buildName("Quang the Guy").buildAddress("Canada").buildBalance(123).buildPhone("014351").buildUsername("Derrick").buildPassword("CaoNiMa").build();
        Customer customer2 = new Guest.GuestBuilder().buildName("Hong Wang").buildAddress("20 Irwin Street").buildPhone("0424173255").buildUsername("wanghong98").buildPhone("987654").buildBalance(100).build();
        customers.addAll(customer1,customer2);

        c_accountType.setCellValueFactory(new PropertyValueFactory<Customer, String>("accountType"));
        c_id.setCellValueFactory(new PropertyValueFactory<Customer, String>("id"));
        c_address.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
        c_balance.setCellValueFactory(new PropertyValueFactory<Customer, Double>("balance"));
        c_name.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
        c_password.setCellValueFactory(new PropertyValueFactory<Customer, String>("password"));
        c_phone.setCellValueFactory(new PropertyValueFactory<Customer, String>("phone"));
        c_username.setCellValueFactory(new PropertyValueFactory<Customer, String>("username"));
        c_listRental.setCellValueFactory(new PropertyValueFactory<Customer, List<String>>("listRentals"));

        c_tableView.setItems(customers);
    }

    public void showNewItemDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(adminVBOX.getScene().getWindow());
        dialog.setTitle("Add New Item");
        dialog.setHeaderText("Use this dialog to create a new item");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("addItemDialog.fxml"));
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
        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {

            // get the controller of Dialog to call the function processResults
            adminAddItemDialogController controller = fxmlLoader.getController();
            Item newItem = controller.processResults();
/*
            todoListView.getItems().setAll(TodoData.getInstance().getTodoItems()); // update to the main screen
*/
            itemListView.getSelectionModel().select(newItem);

            System.out.println("Ok pressed");
        } else {
            System.out.println("Cancel pressed");
        }
    }
}
