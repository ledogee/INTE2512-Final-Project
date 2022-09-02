package com.example.videostore.Controller;

import com.example.videostore.Model.DVD;
import com.example.videostore.Model.Game;
import com.example.videostore.Model.Item;
import com.example.videostore.Model.Movie;
import com.example.videostore.SystemBroker.SingletonDatabase;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class adminAddItemDialogController implements Initializable {
    @FXML
    private TextField Title;

    @FXML
    private ComboBox comboBoxRentalType;

    @FXML
    private ComboBox comboBoxLoanType;

    @FXML
    private TextField numOfCopies;

    @FXML
    private TextField rentalFee;

    @FXML
    private ComboBox comboBoxRentalStatus;

    @FXML
    private TextField year;

    @FXML
    private ComboBox comboBoxGenres;

    public Item processItem(){
        String title = Title.getText().trim();
        Integer RentalType = comboBoxRentalType.getSelectionModel().getSelectedIndex();
        Integer LoanType = comboBoxLoanType.getSelectionModel().getSelectedIndex();
        Integer NumOfCopies = Integer.parseInt(numOfCopies.getText().trim());
        Double RentalFee = Double.parseDouble(rentalFee.getText().trim());
        Boolean RentalStatus = comboBoxRentalStatus.getSelectionModel().getSelectedIndex() == 1 ? true : false;
        String Year = year.getText().trim();

        Integer Genres = comboBoxGenres.getSelectionModel().getSelectedIndex();

        if(RentalType == 0){
            Item newItem = new Game.GameBuilder().buildTitle(title).buildLoanType(LoanType).buildCopies(NumOfCopies).buildRentalFee(RentalFee).buildYear(Year).build();
            return newItem;
        }
        else if (RentalType == 1) {
            Item newItem = new DVD.DVDBuilder().buildTitle(title).buildLoanType(LoanType).buildCopies(NumOfCopies).buildRentalFee(RentalFee).buildYear(Year).buildGenres(Genres).build();
            return newItem;
        }
        else if (RentalType == 2) {
            Item newItem = new Movie.MovieBuilder().buildTitle(title).buildLoanType(LoanType).buildCopies(NumOfCopies).buildRentalFee(RentalFee).buildYear(Year).buildGenres(Genres).build();
            return newItem;
        }

        return null;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBoxRentalType.getItems().addAll("Game", "DVD", "Movie");
        comboBoxLoanType.getItems().addAll("2-day", "1-week");
        comboBoxRentalStatus.getItems().addAll("Available", "Unavailable");
        comboBoxGenres.getItems().addAll("Action", "Horror", "Drama", "Comedy");
    }
}
