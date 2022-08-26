package com.example.videostore.SystemBroker;

import com.example.videostore.Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SingletonDatabase {
    private static SingletonDatabase instance = new SingletonDatabase();
    private static String itemFileName = "src/main/java/com/example/videostore/db/items.txt";

    private static String customerFileName = "src/main/java/com/example/videostore/db/customers.txt";
    private static ObservableList<Customer> customers;
    private static ObservableList<Item> items;

    public static SingletonDatabase getInstance(){return instance;}


    public static ObservableList<Item> getItems() {
        return items;
    }
    public static ObservableList<Customer> getCustomers() {return customers;}
    public static void loadItems() throws IOException{
        items= FXCollections.observableArrayList();
        Path path = Paths.get(itemFileName);
        BufferedReader br = Files.newBufferedReader(path);
        String input;
        try{
            while((input = br.readLine()) != null){
                String[] itemPieces = input.split(",");
                String id =  itemPieces[0];
                String title = itemPieces[1];
                String rentType = itemPieces[2];
                String loanType = itemPieces[3];
                int numberOfCopies = Integer.parseInt(itemPieces[4]);
                double rentalFee = Double.parseDouble(itemPieces[5]);
                boolean rentalStatus = Boolean.parseBoolean(itemPieces[6]);
                String year = itemPieces[7];
                String genres = null;
                if(rentType.equals("DVD") || rentType.equals("Record")) {
                    genres = itemPieces[8];
                }


                /*#ID,Title,Rent type,Loan type,Number of copies,rental fee, rental status, year, [genre] if it is a video record or a DVD*/
                switch (rentType){
                    case "Game":
                        Game game = new Game.GameBuilder(id,title,numberOfCopies,loanType, rentalFee, rentalStatus, year).build();
                        items.add(game);
                        break;
                    case "DVD":
                        DVD dvd = new DVD.DVDBuilder(id,title,numberOfCopies,loanType, rentalFee, rentalStatus, year, genres).build();
                        items.add(dvd);
                        break;
                    case "Record":
                        Movie movie = new Movie.MovieBuilder(id,title,numberOfCopies,loanType, rentalFee, rentalStatus, year, genres).build();
                        items.add(movie);
                        break;
                }
            }
        } finally {
            if(br != null) {
                br.close();
            }
        }
    }
    public static List<String> getItemListID(String str) {
        String[] listStr=  str.split(" ");
        List<String> listIdItems = new ArrayList<String>(List.of(listStr));
        return listIdItems;
    }
    public static void loadCustomers() throws IOException {
        customers = FXCollections.observableArrayList();
        Path path = Paths.get(customerFileName);
        BufferedReader br = Files.newBufferedReader(path);
        String input;
        try{
            while((input = br.readLine()) != null){
                String[] customerPieces = input.split(",");
                String id =  customerPieces[0];
                String name = customerPieces[1];
                String address = customerPieces[2];
                String phoneNumber = customerPieces[3];
                String accountType = customerPieces[4];
                String username = customerPieces[5];
                String password = customerPieces[6];
                Double balance = Double.parseDouble(customerPieces[7]);
                List<String> list = getItemListID(customerPieces[8]);
                switch (accountType){
                    case "Guest":
                        Guest guest = new Guest.GuestBuilder(id,name,username,password,balance,list).buildAddress(address).buildPhone(phoneNumber).build();
                        customers.add(guest);
                        break;
                    case "Regular":
                        Regular regular = new Regular.RegularBuilder(id,name,username,password,balance,list).buildAddress(address).buildPhone(phoneNumber).build();
                        customers.add(regular);
                        break;
                    case "VIP":
                        int rewardPoint = Integer.parseInt(customerPieces[9]);
                        Vip vip = new Vip.VipBuilder(id,name,username,password,balance,list).buildAddress(address).buildPhone(phoneNumber).buildRewardPoint(rewardPoint).build();
                        customers.add(vip);
                        break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
            }
        }
    public static void saveCustomers() throws IOException {
        Path path = Paths.get(customerFileName);
        BufferedWriter bw = Files.newBufferedWriter(path);
        for(Customer save:customers){
            switch (save.getAccountType()){
                case "Regular":
                case "Guest":
                    bw.write(save.getId()+','+save.getName()+','+save.getAddress()+','+save.getPhone()+','+save.getAccountType()+','+save.getUsername()+','+save.getPassword()+','+save.saverentals(save.getListRentals())+System.lineSeparator());
                    break;
                case "VIP":
                    bw.write(save.getId()+','+save.getName()+','+save.getAddress()+','+save.getPhone()+','+save.getAccountType()+','+save.getUsername()+','+save.getPassword()+','+save.saverentals(save.getListRentals())+','+((Vip) save).getRewardPoint()+System.lineSeparator());
                    break;
            }
        }
    }
    public static void saveitems() throws IOException {
        Path path = Paths.get(itemFileName);
        BufferedWriter bw = Files.newBufferedWriter(path);
        for(Item save:items){
            switch (save.getRentalType()){
                case "DVD":
                    bw.write(save.getId()+','+save.getTitle()+','+save.getRentalType()+','+save.getLoanType()+','+save.getCopies()+','+save.getRentalFee()+','+save.isRentalStatus()+','+save.getYear()+','+ save.getGenres()+System.lineSeparator());
                break;
                case "Game":
                    bw.write(save.getId()+','+save.getTitle()+','+save.getRentalType()+','+save.getLoanType()+','+save.getCopies()+','+save.getRentalFee()+','+save.isRentalStatus()+','+save.getYear()+System.lineSeparator());
                case "Movie":
                    bw.write(save.getId()+','+save.getTitle()+','+save.getRentalType()+','+save.getLoanType()+','+save.getCopies()+','+save.getRentalFee()+','+save.isRentalStatus()+','+save.getYear()+','+ save.getGenres()+System.lineSeparator());
            }
        }
    }
    }



