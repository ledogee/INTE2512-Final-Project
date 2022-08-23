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
import java.util.ArrayList;
import java.util.List;

public class SingletonDatabase {
    private static SingletonDatabase instance = new SingletonDatabase();
    private static String itemFileName = "items.txt";
    private static String accountFileName = "account.txt";
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
                        items.add(game);
                        break;
                    case "DVD":
                        String genres = itemPieces[6];
                        DVD dvd = new DVD.DVDBuilder(id,title,rentType,numberOfCopies,rentalFee,genres).build();
                        items.add(dvd);
                        break;
                    case "Record":
                        String genres1 = itemPieces[6];
                        Movie movie = new Movie.MovieBuilder(id, title, rentType, numberOfCopies, rentalFee, genres1).build();
                        items.add(movie);
                        break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static List<String> getItemListID(String str) {
        String[] listStr=  str.split(" ");
        List<String> listIdItems = new ArrayList<String>(List.of(listStr));
        return listIdItems;
    }
    public static void loadAccounts() throws IOException {
        ObservableList<Object> accounts = FXCollections.observableArrayList();
        Path path = Paths.get(accountFileName);
        BufferedReader br = Files.newBufferedReader(path);
        String input;
        try{
            while((input = br.readLine()) != null){
                String[] customerPieces = input.split("\t");
                String id =  customerPieces[0];
                String name = customerPieces[1];
                String accountType = customerPieces[2];
                String username = customerPieces[3];
                String password = customerPieces[4];
                Double balance = Double.parseDouble(customerPieces[5]);
                List<String> list = getItemListID(customerPieces[6]);
                switch (accountType){
                    case "Guest":
                        Guest guest = new Guest.GuestBuilder(id,name,username,password,balance,list).build();
                        accounts.add(guest);
                        break;
                    case "Regular":
                        Regular regular = new Regular.RegularBuilder(id,name,username,password,balance,list).build();
                        accounts.add(regular);
                        break;
                    case "VIP":
                        Vip vip = new Vip.VipBuilder(id,name,username,password,balance,list).build();
                        accounts.add(vip);
                        break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
            }
        }
    }



