package com.example.videostore.Controller;

import com.example.videostore.Main;
import com.example.videostore.Model.*;
import com.example.videostore.SystemBroker.SingletonDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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
    public Label numOfReturn;
    public AnchorPane menuPane;
    private List<Button> buttonRents;
    private List<Button> buttonReturns;

    public void loadPersonalInformation() {
        name.setText(user.getName());
        username.setText(user.getUsername());
        password.setText(user.getPassword());
        phone.setText(user.getPhone());
        address.setText(user.getAddress());
    }

    public Label accountType;
    public Label balance;
    public Label rewardPoint;

    public void loadYourAccount() {
        accountType.setText(user.getAccountType());

        String result = String.format("%.2f", user.getBalance());
        balance.setText("$" + result);


        if(user instanceof Vip) {
            rewardPoint.setText(String.valueOf(((Vip) user).getRewardPoint()));
        } else {
            rewardPoint.setText("0");
        }
        numOfReturn.setText(String.valueOf(user.getNumberOfReturn()));
    }


    @FXML
    private TableColumn<Item, String> loanType;

    @FXML
    private TableColumn<Item, Integer> numOfCopies;

    @FXML
    private TableColumn<Item, String> rentalType;

    @FXML
    private TableColumn<Item, String> title;
    
    @FXML
    private TableColumn<Item, Button> action;

    @FXML
    private TableView<Item> tableView;
    ObservableList<Item> itemsDatabase = FXCollections.observableArrayList();
    ObservableList<Customer> customersDatabase = FXCollections.observableArrayList();

    public void goToLogin(ActionEvent event) throws IOException {SceneSwitcher.switchToLogin(event);
    }

    public void goToMenu(ActionEvent event) throws IOException {
        menuController.user = user;
        SceneSwitcher.switchToMenu(event);
    }

    public void UpdateInfoButtonHandler(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader =  new FXMLLoader(Main.class.getResource("updateInfo.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        loadPersonalInformation();
    }

    public void initialize() {

        System.out.println(user.getName());
        itemsDatabase = SingletonDatabase.getItems();
        customersDatabase = SingletonDatabase.getCustomers();
        Set<Item> itemSet = new HashSet<>();
        List<Item> itemList = new ArrayList<>();

        loadPersonalInformation();
        loadYourAccount();


        if (user.getListRentals() != null) {
            for (String id : user.getListRentals()) {
                for (Item item : itemsDatabase) {
                    if (id.equals(item.getId())) {
                        itemList.add(item);
                    }
                }
            }


            for (Item itemDup : itemList) {
                itemDup.setQuantity(Collections.frequency(itemList, itemDup));
                itemSet.add(itemDup);
            }
            ObservableList<Item> listRentals = FXCollections.observableArrayList(itemSet);

            title.setCellValueFactory(new PropertyValueFactory<Item, String>("title"));
            rentalType.setCellValueFactory(new PropertyValueFactory<Item, String>("rentalType"));
            loanType.setCellValueFactory(new PropertyValueFactory<Item, String>("loanType"));
            numOfCopies.setCellValueFactory(new PropertyValueFactory<Item, Integer>("quantity"));
            action.setCellValueFactory(new PropertyValueFactory<Item, Button>("buttonReturn"));
            tableView.setItems(listRentals);

            TableColumn<Item, Button> column = action; // column you want
            List<Button> buttonList = new ArrayList<>();
            for (Item item : tableView.getItems()) {
                buttonList.add(action.getCellObservableValue(item).getValue());
            }
            this.buttonReturns = buttonList;
            for (Button btn : buttonList) {
                btn.setOnAction((actionEvent) -> {
                    user.returnItem(itemsDatabase, listRentals, btn);
                    user = checkpromotion(user, customersDatabase);
                    tableView.setItems(listRentals);
                    tableView.refresh();
                });
            }
            System.out.println(rentalType.getCellFactory());
            System.out.println(itemsDatabase);

        }
    }
    public Customer checkpromotion(Customer cus, ObservableList<Customer> customersDatabase){
        if(cus.getNumberOfReturn()> 3 && cus.getAccountType().equals("Guest")){
            cus.setAccountType("Regular");
            Regular reg = new Regular.RegularBuilder(cus).build(); // all alltribute of the cus paste to reg ( accounttype = regular as a string)

            // Update the db
            for(int i = 0; i < customersDatabase.size(); i++) {
                if(customersDatabase.get(i).getId().equals(reg.getId())) {
                    customersDatabase.set(i, reg);
                    System.out.println(customersDatabase.get(i).getAccountType());
                }
            }
            notificationController.popMenuNotification(menuPane ,"Promote to REGULAR!","#008000");
            return reg;
        } else if(cus.getNumberOfReturn() > 5 && cus.getAccountType().equals("Regular")) {
            cus.setAccountType("VIP");
            Vip vip = new Vip.VipBuilder(cus).build();

            // Update the db
            for(int i = 0; i < customersDatabase.size(); i++) {
                if(customersDatabase.get(i).getId().equals(vip.getId())) {
                    customersDatabase.set(i, vip);
                    System.out.println(customersDatabase.get(i).getAccountType());
                }
            }
            notificationController.popMenuNotification(menuPane ,"Promote to VIP!","#008000");
            return vip;
        }
        return cus;
    }
}
