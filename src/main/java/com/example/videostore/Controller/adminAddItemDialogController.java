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
    //Update to handle
    boolean isTitleValid = false;
    boolean isNumOfCopiesValid = false;
    boolean isRentalFeeValid = false;
    boolean isYearValid = false;
    boolean isFilled = false;


    public Item processItem(){
        isTitleValid = false;
        isNumOfCopiesValid = false;
        isRentalFeeValid = false;
        isYearValid = false;
        isFilled = false;

        String Title = title.getText().trim();
        isTitleValid = checkTitleValidation(Title);

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
            RentalStatus = true;
        }else{
            RentalStatus = false;
        }

        System.out.println("-----------");
        System.out.println(NumOfCopies + " " + isNumOfCopiesValid);
        System.out.println(RentalFee + " " + isRentalFeeValid);
        System.out.println(tempRentalStatus + " " + isFilled);
        System.out.println(Genres + " " + isFilled);


        if(tempRentalStatus >= 0 && RentalType >=0 && LoanType >= 0 && ((Genres >= 0 && RentalType > 0)||(RentalType == 0))){
            isFilled = true;
        }

        if(RentalType == 0 && isNumOfCopiesValid && isRentalFeeValid && isYearValid && isFilled){
            return new Game.GameBuilder().buildTitle(Title).buildLoanType(LoanType).buildCopies(NumOfCopies).buildRentalFee(RentalFee).buildRentalStatus(RentalStatus).buildYear(Year).build();
        }
        else if (RentalType == 1 && isNumOfCopiesValid && isRentalFeeValid && isYearValid && isFilled) {
            return new DVD.DVDBuilder().buildTitle(Title).buildLoanType(LoanType).buildCopies(NumOfCopies).buildRentalFee(RentalFee).buildRentalStatus(RentalStatus).buildYear(Year).buildGenres(Genres).build();
        }
        else if (RentalType == 2 && isNumOfCopiesValid && isRentalFeeValid && isYearValid && isFilled) {
            return new Movie.MovieBuilder().buildTitle(Title).buildLoanType(LoanType).buildCopies(NumOfCopies).buildRentalFee(RentalFee).buildRentalStatus(RentalStatus).buildYear(Year).buildGenres(Genres).build();
        }

        return null;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBoxRentalType.getItems().addAll("Game", "DVD", "Movie");
        comboBoxLoanType.getItems().addAll("2-day", "1-week");
        comboBoxRentalStatus.getItems().addAll("Available", "Unavailable");
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
    }



    void setItemLabel() {
        StringBuilder stringBuilder = new StringBuilder();
        if(!isTitleValid){
            stringBuilder.append("Invalid Title.(Title Already Exist or Empty Input)\n");
        }
        if(!isNumOfCopiesValid){
            stringBuilder.append("Invalid Number of Copies.(Only integer value)\n");
        }
        if(!isRentalFeeValid){
            stringBuilder.append("Invalid Rental Fee.(Only numeric value)\n");
        }
        if(!isYearValid){
            stringBuilder.append("Invalid Year value.(Only integer value)\n");
        }
        if(!isFilled){
            stringBuilder.append("Please filled all the choices.\n");
        }
        label.setText(String.valueOf(stringBuilder));
        label.setTextFill(Color.web("#FF0000"));
    }

    boolean checkTitleValidation(String string){

        ObservableList<Item> temp = getItems();
        for(Item item: temp){
            if(string.equals(item.getTitle()) || string.isEmpty()){
                return false;
            }
        }
        return true;
    }
}
