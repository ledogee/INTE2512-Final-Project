package com.example.videostore.Controller;

import com.example.videostore.Model.DVD;
import com.example.videostore.Model.Game;
import com.example.videostore.Model.Item;
import com.example.videostore.Model.Movie;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

import static com.example.videostore.Controller.adminController.*;

public class adminUpdateItemDialogController implements Initializable {
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

    public void setItemValue(int i){
        comboBoxRentalType.setValue(items.get(i).getRentalType());
        title.setText(items.get(i).getTitle());
        numOfCopies.setText(String.valueOf(items.get(i).getCopies()));
        rentalFee.setText(String.valueOf(items.get(i).getRentalFee()));
        year.setText(items.get(i).getYear());
        comboBoxLoanType.setValue(items.get(i).getLoanType());
        comboBoxRentalStatus.setValue(items.get(i).isRentalStatus());
    }
    public void setNewLabel(String string) {
        label.setText(string);
    }
    //Update to handle
    boolean isTitleValid = false;
    boolean isNumOfCopiesValid = false;
    boolean isRentalFeeValid = false;
    boolean isYearValid = false;
    boolean isFilled = false;


    public Item processUpdateItem(Item item, ObservableList<Item> itemsDatabase, int selectedIndex){
        isTitleValid = false;
        isNumOfCopiesValid = false;
        isRentalFeeValid = false;
        isYearValid = false;
        isFilled = false;

        String Title = title.getText().trim();
        isTitleValid = checkTitleValidation(Title,selectedIndex);

        Integer RentalType = null;
        RentalType = comboBoxRentalType.getSelectionModel().getSelectedIndex();

        Integer tempLoanType = null;
        tempLoanType = comboBoxLoanType.getSelectionModel().getSelectedIndex();
        String LoanType;
        if(tempLoanType == 0){
            LoanType = "1-week";
        }else{
            LoanType = "2-day";
        }
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


        if(tempRentalStatus >= 0 && RentalType >=0 && tempLoanType >= 0 && ((Genres >= 0 && RentalType > 0)||(RentalType == 0))){
            isFilled = true;
        }
        System.out.println(isFilled);
        if(RentalType == 0 && isNumOfCopiesValid && isRentalFeeValid && isYearValid && isFilled){
            item.setTitle(Title);
            item.setCopies(NumOfCopies);
            item.setRentalStatus(RentalStatus);
            item.setRentalFee(RentalFee);
            item.setLoanType(LoanType);
            item.setRentalFee(RentalFee);
            item.setYear(Year);
            Game game = new Game.GameBuilder(item).build();
            itemsDatabase.set(selectedIndex,game);
            return game;
        }
        else if (RentalType == 1 && isNumOfCopiesValid && isRentalFeeValid && isYearValid && isFilled) {
            item.setTitle(Title);
            item.setCopies(NumOfCopies);
            item.setRentalStatus(RentalStatus);
            item.setRentalFee(RentalFee);
            item.setLoanType(LoanType);
            item.setRentalFee(RentalFee);
            item.setYear(Year);
            DVD dvd = new DVD.DVDBuilder(item).buildGenres(Genres).build();
            itemsDatabase.set(selectedIndex,dvd);
            return dvd;
        }
        else if (RentalType == 2 && isNumOfCopiesValid && isRentalFeeValid && isYearValid && isFilled) {
            item.setTitle(Title);
            item.setCopies(NumOfCopies);
            item.setRentalStatus(RentalStatus);
            item.setRentalFee(RentalFee);
            item.setLoanType(LoanType);
            item.setRentalFee(RentalFee);
            item.setYear(Year);
            Movie movie = new Movie.MovieBuilder(item).buildGenres(Genres).build();
            itemsDatabase.set(selectedIndex,movie);
            return movie;
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

    boolean checkTitleValidation(String string,int selectedIndex){

        ObservableList<Item> temp = getItems();
        for(int i = 0; i < temp.size(); i++){
            if((string.equals(temp.get(i).getTitle())&& i != selectedIndex )|| string.isEmpty()){
                return false;
            }
        }
        return true;
    }
}