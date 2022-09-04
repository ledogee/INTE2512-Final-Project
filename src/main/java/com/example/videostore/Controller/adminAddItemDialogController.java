package com.example.videostore.Controller;

import com.example.videostore.Model.DVD;
import com.example.videostore.Model.Game;
import com.example.videostore.Model.Item;
import com.example.videostore.Model.Movie;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

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

    @FXML
    private Label label;

    public void setNewLabel(String string) {
        label.setText(string);
    }

    boolean isTitleValid = false;
    boolean isNumOfCopiesValid = false;
    boolean isRentalFeeValid = false;
    boolean isYearValid = false;
    public Item processItem(){

        String title = Title.getText().trim();
        Integer RentalType = comboBoxRentalType.getSelectionModel().getSelectedIndex();

        Integer LoanType = comboBoxLoanType.getSelectionModel().getSelectedIndex();

        Integer NumOfCopies = null;
        if(isInteger(numOfCopies.getText())){
            NumOfCopies = Integer.parseInt(numOfCopies.getText().trim());
            isNumOfCopiesValid = true;
        }

        Double RentalFee = null;
        if(isDouble(rentalFee.getText())){
            RentalFee = Double.parseDouble(rentalFee.getText().trim());
            isRentalFeeValid = true;
        }

        String Year = null;
        if(isInteger(year.getText())){
            Year = year.getText().trim();
            isYearValid = true;
        }

        Boolean RentalStatus = comboBoxRentalStatus.getSelectionModel().getSelectedIndex() == 1 ? false : true;

        Integer Genres = comboBoxGenres.getSelectionModel().getSelectedIndex();

        if(RentalType == 0 && isNumOfCopiesValid && isRentalFeeValid && isYearValid){
            Item newItem = new Game.GameBuilder().buildTitle(title).buildLoanType(LoanType).buildCopies(NumOfCopies).buildRentalFee(RentalFee).buildRentalStatus(RentalStatus).buildYear(Year).build();
            return newItem;
        }
        else if (RentalType == 1 && isNumOfCopiesValid && isRentalFeeValid && isYearValid) {
            Item newItem = new DVD.DVDBuilder().buildTitle(title).buildLoanType(LoanType).buildCopies(NumOfCopies).buildRentalFee(RentalFee).buildRentalStatus(RentalStatus).buildYear(Year).buildGenres(Genres).build();
            return newItem;
        }
        else if (RentalType == 2 && isNumOfCopiesValid && isRentalFeeValid && isYearValid) {
            Item newItem = new Movie.MovieBuilder().buildTitle(title).buildLoanType(LoanType).buildCopies(NumOfCopies).buildRentalFee(RentalFee).buildRentalStatus(RentalStatus).buildYear(Year).buildGenres(Genres).build();
            return newItem;
        }

        return null;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBoxRentalType.getItems().addAll("Game", "DVD", "Movie");
        comboBoxLoanType.getItems().addAll("2-day", "1-week");
        comboBoxRentalStatus.getItems().addAll("Available", "Unavailable");
        comboBoxGenres.getItems().addAll( "Action", "Horror", "Drama", "Comedy");
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

    void setLabel() {
        StringBuilder stringBuilder = new StringBuilder();
        if(!isNumOfCopiesValid){
            stringBuilder.append("Invalid Number of Copies.(Only integer value)\n");
        }
        if(!isRentalFeeValid){
            stringBuilder.append("Invalid Rental Fee.(Only numeric value)\n");
        }
        if(!isYearValid){
            stringBuilder.append("Invalid Year value.(Only integer value)\n");
        }
        label.setText(String.valueOf(stringBuilder));
        label.setTextFill(Color.web("#FF0000"));
    }
}
