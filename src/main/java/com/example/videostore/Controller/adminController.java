package com.example.videostore.Controller;

import com.example.videostore.Model.*;
import com.example.videostore.SystemBroker.SingletonDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


import java.io.IOException;
import java.io.ObjectInputFilter;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class adminController extends adminAddItemDialogController implements Initializable {
    @FXML
    private Button returnToLoginButton;
    @FXML
    private Button addItemButton;
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
    private TableColumn<Customer, String> c_listRental;

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

    ObservableList<Customer> customers = FXCollections.observableArrayList();
    ObservableList<Item> items = FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle resourceBundle) {
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
        i_title.setCellValueFactory(new PropertyValueFactory<Item, String>("title"));
        i_rentalType.setCellValueFactory(new PropertyValueFactory<Item, String>("rentalType"));
        i_loanType.setCellValueFactory(new PropertyValueFactory<Item, String>("loanType"));
        i_numCopies.setCellValueFactory(new PropertyValueFactory<Item, Integer>("copies"));
        i_rentalFee.setCellValueFactory(new PropertyValueFactory<Item, Double>("rentalFee"));
        i_status.setCellValueFactory(new PropertyValueFactory<Item, Boolean>("rentalStatus"));
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
        customers = SingletonDatabase.getCustomers();
        c_accountType.setCellValueFactory(new PropertyValueFactory<Customer, String>("accountType"));
        c_id.setCellValueFactory(new PropertyValueFactory<Customer, String>("id"));
        c_address.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
        c_balance.setCellValueFactory(new PropertyValueFactory<Customer, Double>("balance"));
        c_name.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
        c_password.setCellValueFactory(new PropertyValueFactory<Customer, String>("password"));
        c_phone.setCellValueFactory(new PropertyValueFactory<Customer, String>("phone"));
        c_username.setCellValueFactory(new PropertyValueFactory<Customer, String>("username"));
        c_listRental.setCellValueFactory(new PropertyValueFactory<Customer, String>("combinedString"));
        for(Customer customer : customers){
            customer.setCombinedString(customer.arraytostring());
        }
        c_tableView.setItems(customers);
    }

    public void showNewItemDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(adminVBOX.getScene().getWindow());
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
            // get the controller of Dialog to call the function processResults

//            Label label1 = new Label();
//            label1.setText("Hello");
            Item newItem = controller.processItem();
/*
            todoListView.getItems().setAll(TodoData.getInstance().getTodoItems()); // update to the main screen
*/
//            itemListView.getSelectionModel().select(newItem);

            System.out.println(newItem);
//            items.add(newItem);
            while((newItem == null && result.get() == ButtonType.OK) ){
                controller.setLabel();
                result = dialog.showAndWait();
                newItem = controller.processItem();
                if(newItem != null){
                    SingletonDatabase.getItems().add(newItem);
                    break;
                }

            }
//            SingletonDatabase.getItems().add(newItem);



            System.out.println("Ok pressed");

        } else {
            System.out.println("Cancel pressed");
        }
    }
}
