package com.example.videostore.SystemBroker;

import com.example.videostore.Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.layout.Pane;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SingletonDatabase {
    private static SingletonDatabase instance = new SingletonDatabase();
    private static String itemFileName = "items.txt";

    private static String customerFileName = "customers.txt";
    private static ObservableList<Customer> customers;
    private static ObservableList<Item> items;

    public static SingletonDatabase getInstance(){return instance;}

    public static ObservableList<Item> getItems() {
        return items;
    }
    public static ObservableList<Customer> getCustomers() {return customers;}
    public static void loadItems() throws IOException{
        items = FXCollections.observableArrayList();
        Path path = Paths.get(itemFileName);
        BufferedReader br = Files.newBufferedReader(path);
        String input;
        try{
            while((input = br.readLine()) != null){
                String[] itemPieces = input.split("\t");
                String id =  itemPieces[0];
                String title = itemPieces[1];
                String rentType = itemPieces[2];
                int loanType = Integer.parseInt(itemPieces[3]);
                int numberOfCopies = Integer.parseInt(itemPieces[4]);
                double rentalFee = Double.parseDouble(itemPieces[5]);
                boolean rentalStatus = Boolean.parseBoolean(itemPieces[6]);
                String year = itemPieces[7];
                int genres = Integer.parseInt(itemPieces[7]);

                /*#ID,Title,Rent type,Loan type,Number of copies,rental fee, rental status, year, [genre] if it is a video record or a DVD*/
                switch (rentType){
                    case "Game":
                        Game game = new Game.GameBuilder().buildId(id).buildTitle(title).buildLoanType(loanType).buildCopies(numberOfCopies).buildRentalFee(rentalFee).buildRentalStatus(rentalStatus).buildYear(year).build();
                        break;
                    case "DVD":
                        DVD dvd = new DVD.DVDBuilder().buildId(id).buildTitle(title).buildLoanType(loanType).buildCopies(numberOfCopies).buildRentalFee(rentalFee).buildRentalStatus(rentalStatus).buildYear(year).buildGenres(genres).build();
                        break;
                    case "Record":
                        Movie movie = new Movie.MovieBuilder().buildId(id).buildTitle(title).buildLoanType(loanType).buildCopies(numberOfCopies).buildRentalFee(rentalFee).buildRentalStatus(rentalStatus).buildYear(year).buildGenres(genres).build();
                        break;
                }
            }
        } finally {
            if(br != null) {
                br.close();
            }
        }
    }
    public static void loadCustomers() throws IOException {
        ObservableList<Object> customers = FXCollections.observableArrayList();
        Path path = Paths.get(customerFileName);
        BufferedReader br = Files.newBufferedReader(path);
        String input;
        try{
            while((input = br.readLine()) != null){
                String[] itemPieces = input.split("\t");
                String id =  itemPieces[0];
                String title = itemPieces[1];
                String rentType = itemPieces[2];
                int loanType = Integer.parseInt(itemPieces[3]);
                int numberOfCopies = Integer.parseInt(itemPieces[4]);
                Float rentalFee = Float.parseFloat(itemPieces[5]);
            }
        } finally {
            if(br != null) {
                br.close();
            }
        }
    }
}

