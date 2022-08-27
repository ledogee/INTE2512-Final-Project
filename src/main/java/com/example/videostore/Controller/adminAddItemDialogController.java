package com.example.videostore.Controller;

import com.example.videostore.Model.DVD;
import com.example.videostore.Model.Game;
import com.example.videostore.Model.Item;
import com.example.videostore.Model.Movie;
import com.example.videostore.SystemBroker.SingletonDatabase;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class adminAddItemDialogController {
    @FXML
    private TextField Title;

    @FXML
    private TextField rentalType;

    @FXML
    private TextField loanType;

    @FXML
    private TextField numOfCopies;

    @FXML
    private TextField rentalFee;

    @FXML
    private TextField rentalStatus;

    @FXML
    private TextField year;

    @FXML
    private TextField genres;

    public Item processItem(){
        String title = Title.getText().trim();
        String RentalType = rentalType.getText().trim();
        Integer LoanType = Integer.parseInt(loanType.getText().trim());
        Integer NumOfCopies = Integer.parseInt(numOfCopies.getText().trim());
        Double RentalFee = Double.parseDouble(rentalFee.getText().trim());
        Boolean RentalStatus = Boolean.parseBoolean(rentalStatus.getText().trim());
        String Year = year.getText().trim();
        Integer Genres = Integer.parseInt(genres.getText().trim());

        if(RentalType.equals("Game")){
            Item newItem = new Game.GameBuilder().buildTitle(title).buildLoanType(LoanType).buildCopies(NumOfCopies).buildRentalFee(RentalFee).buildRentalStatus(RentalStatus).buildYear(Year).build();
            return newItem;
        }
        else if (RentalType.equals("DVD")) {
            Item newItem = new DVD.DVDBuilder().buildTitle(title).buildLoanType(LoanType).buildCopies(NumOfCopies).buildRentalFee(RentalFee).buildRentalStatus(RentalStatus).buildYear(Year).buildGenres(Genres).build();
            return newItem;
        }
        else if (RentalType.equals("Movie")) {
            Item newItem = new Movie.MovieBuilder().buildTitle(title).buildLoanType(LoanType).buildCopies(NumOfCopies).buildRentalFee(RentalFee).buildRentalStatus(RentalStatus).buildYear(Year).buildGenres(Genres).build();
            return newItem;
        }

        return null;
    }
}
