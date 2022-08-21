package com.example.videostore.SystemBroker;

import com.example.videostore.Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private static ObservableList<Customer> accounts;
    private static ObservableList<Item> items;

    public static SingletonDatabase getInstance(){return instance;}

    public static ObservableList<Item> getItems() {
        return items;
    }
    public static ObservableList<Customer> getAccount() {return accounts;}
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
                Float rentalFee = Float.parseFloat(itemPieces[5]);

                switch (rentType){
                    case "Game":
                        Game game = new Game.GameBuilder(id,title,rentType,numberOfCopies,rentalFee).build();
                        break;
                    case "DVD":
                        String genres = itemPieces[6];
                        DVD dvd = new DVD.DVDBuilder(id,title,rentType,numberOfCopies,rentalFee,genres).build();
                        break;
                    case "Record":
                        String genres1 = itemPieces[6];
                        Movie Movie = new Movie.MovieBuilder(id, title, rentType, numberOfCopies, rentalFee, genres1).build();
                        break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void loadAccounts() throws IOException {
        ObservableList<Object> accounts = FXCollections.observableArrayList();
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
                Float rentalFee = Float.parseFloat(itemPieces[5]);


                }
            } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }
}

