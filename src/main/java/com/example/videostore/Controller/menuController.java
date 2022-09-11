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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class menuController
{
    public static Customer user;
    public Label accountType;
    public AnchorPane menuPane;

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

        //Get accountType
        if(user instanceof Vip) {
            accountType.setText(user.getAccountType());
        } else if(user instanceof Regular) {
            accountType.setText(user.getAccountType());
        } else if(user instanceof Guest) {
            accountType.setText(user.getAccountType());
        }

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
                } else if((user instanceof Vip || user instanceof  Regular) && itemDatabase.get(i).getButtonRent() == btn) {
                    btn.setDisable(false);
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
                System.out.println( "SIZE = " + user.getListRentals().size());
               if(user.rentItem(itemDatabase, customerObservableList, btn, balance, indexUser, rewardPoint)) {
                   showDialog("successNotification.fxml");
               } else {
                   if(user instanceof Guest && user.getListRentals().size() == 2) {
                       showDialog("guestNotification.fxml");
                   } else {
                       showDialog("failNotification.fxml");
                   }
               }
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

    public void showDialog(String fileName) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(menuPane.getScene().getWindow());
        dialog.setTitle("Notification");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/com/example/videostore/" + fileName));
//        URL fxmlLocation = getClass().getResource("addItemDialog.fxml");
//        FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);

        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
//            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("addItemDialog.fxml")));
//            dialog.getDialogPane().setContent(root);

        } catch (IOException e) {
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }

        // Add button
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent()) {
            dialog.onCloseRequestProperty();
        }

    }


}

