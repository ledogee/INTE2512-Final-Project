package com.example.videostore.Controller;

import com.example.videostore.Model.DVD;
import com.example.videostore.Model.Game;
import com.example.videostore.Model.Item;
import com.example.videostore.Model.Movie;
import com.example.videostore.SystemBroker.SingletonDatabase;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

import static com.example.videostore.Controller.adminController.*;

public class adminAddItemDialogController implements Initializable {
    @FXML
    private TextField title;

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
    //Set boolean for validation
    boolean isTitleValid = false;
    boolean isNumOfCopiesValid = false;
    boolean isRentalFeeValid = false;
    boolean isYearValid = false;
    boolean isFilled = false;

    //function used to create a new item
    public Item processItem(){
        //reset boolean to false
        isTitleValid = false;
        isNumOfCopiesValid = false;
        isRentalFeeValid = false;
        isYearValid = false;
        isFilled = false;

        //switch the boolean to true if the input suit the requirement
        String Title = title.getText().trim();
        if(!Title.isEmpty()){
            isTitleValid = true;
        }

        Integer RentalType = null;
        RentalType = comboBoxRentalType.getSelectionModel().getSelectedIndex();

        Integer LoanType = null;
        LoanType = comboBoxLoanType.getSelectionModel().getSelectedIndex();

        Integer NumOfCopies = null;
        if(isInteger(numOfCopies.getText()) && Integer.parseInt(numOfCopies.getText().trim())>=0){
            NumOfCopies = Integer.parseInt(numOfCopies.getText().trim());
            isNumOfCopiesValid = true;
        }

        Double RentalFee = null;
        if(isDouble(rentalFee.getText()) && Double.parseDouble(rentalFee.getText().trim())>=0){
            RentalFee = Double.parseDouble(rentalFee.getText().trim());
            isRentalFeeValid = true;
        }

        String Year = null;
        if(isInteger(year.getText())){
            Year = year.getText().trim();
            isYearValid = true;
        }
        Integer Genres = comboBoxGenres.getSelectionModel().getSelectedIndex();

        Integer tempRentalStatus = null;
        tempRentalStatus = comboBoxRentalStatus.getSelectionModel().getSelectedIndex();
        boolean RentalStatus;
        if(tempRentalStatus == 0){
            RentalStatus = false;
        }else{
            RentalStatus = true;
        }

        //Check if the combo box value is selected
        if(tempRentalStatus >= 0 && RentalType >=0 && LoanType >= 0 && ((Genres >= 0 && RentalType > 0)||(RentalType == 0))){
            isFilled = true;
        }

        //create new item based on the input value
        if(RentalType == 0 && isTitleValid && isNumOfCopiesValid && isRentalFeeValid && isYearValid && isFilled){
            return new Game.GameBuilder().buildTitle(Title).buildLoanType(LoanType).buildCopies(NumOfCopies).buildRentalFee(RentalFee).buildRentalStatus(RentalStatus).buildYear(Year).build();
        }
        else if (RentalType == 1 && isTitleValid && isNumOfCopiesValid && isRentalFeeValid && isYearValid && isFilled) {
            return new DVD.DVDBuilder().buildTitle(Title).buildLoanType(LoanType).buildCopies(NumOfCopies).buildRentalFee(RentalFee).buildRentalStatus(RentalStatus).buildYear(Year).buildGenres(Genres).build();
        }
        else if (RentalType == 2 && isTitleValid && isNumOfCopiesValid && isRentalFeeValid && isYearValid && isFilled) {
            return new Movie.MovieBuilder().buildTitle(Title).buildLoanType(LoanType).buildCopies(NumOfCopies).buildRentalFee(RentalFee).buildRentalStatus(RentalStatus).buildYear(Year).buildGenres(Genres).build();
        }
        //if the input value is incorrect or insufficient, return null
        return null;
    }

    //This helps initialize and set the value choice for combo box and lock the setting for genres if the item is Game
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBoxRentalType.getItems().addAll("Game", "DVD", "Movie");
        comboBoxLoanType.getItems().addAll("2-day", "1-week");
        comboBoxRentalStatus.getItems().addAll("Unavailable", "Available");
        comboBoxGenres.getItems().addAll( "Action", "Horror", "Drama", "Comedy");
        comboBoxRentalType.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                if (t1.equals("Game")){
                    comboBoxGenres.setDisable(true);
                }else{
                    comboBoxGenres.setDisable(false);
                }
            }
        });

        numOfCopies.textProperty().addListener((observable, oldvalue, newvalue) -> {
            try {
                if(Integer.parseInt(newvalue) == 0) {
                    comboBoxRentalStatus.getItems().remove("Available");
                } else if((Integer.parseInt(newvalue) >= 0 || Integer.parseInt(newvalue) != 0)) {
                    comboBoxRentalStatus.getItems().remove("Available");
                    comboBoxRentalStatus.getItems().add("Available");
                }
            } catch (Exception e) {
            }
        } );
    }


    //Set text to the label to display error to the admin
    void setItemLabel() {
        StringBuilder stringBuilder = new StringBuilder();
        if(!isTitleValid){
            stringBuilder.append("Please input a title\n");
        }
        else if(!isNumOfCopiesValid){
            stringBuilder.append("Invalid Number of Copies.(Only integer value)\n");
        }
        else if(!isRentalFeeValid){
            stringBuilder.append("Invalid Rental Fee.(Only numeric value)\n");
        }
        else if(!isYearValid){
            stringBuilder.append("Invalid Year value.(Only integer value)\n");
        }
        else if(!isFilled){
            stringBuilder.append("Please filled all the choices.\n");
        }
        label.setText(String.valueOf(stringBuilder));
        label.setTextFill(Color.web("#daac89"));
    }

}
