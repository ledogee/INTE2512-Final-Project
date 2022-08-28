package com.example.videostore.Model;

import com.example.videostore.SystemBroker.SingletonDatabase;
import javafx.collections.ObservableList;

import java.util.List;
public abstract class Customer  {
    enum AccountType {
        Guest,
        Regular,
        Vip
    }
    private static int idCount = 0;
    private String id;
    private String name;
    private String address;
    private String phone;
    private String accountType;
    private List<String> listRentals;
    private double balance;
    private String username;
    private String password;



    private String combinedString;

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", accountType='" + accountType + '\'' +
                ", listRentals=" + listRentals +
                ", balance=" + balance +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


    public boolean rentItem(Item item, ObservableList<Item> observableList) {
        if(this.balance >= item.getRentalFee() && item.getCopies() != 0) {
            double currentBalance = this.balance;
            int currentCopies = item.getCopies();
            item.setCopies(item.getCopies() - 1);
            System.out.println("Rent successfully!");
            this.balance = this.balance - item.getRentalFee();
            System.out.println("Your balance change from " + currentBalance + " to " + this.balance);
            this.listRentals.add(item.getId());
            System.out.println("Your rented items has add new one item!");
            System.out.println(this.getListRentals());
            System.out.println("Item copies change from " + currentCopies+ " to " + item.getCopies());
            return true;
        } else {
            System.out.println("You don't have enough money to rent");
            return false;
        }
    }
    public String arraytostring (){
        if(this.listRentals==null||this.listRentals.isEmpty()){
            return "";
        }
        return  String.join(",",this.listRentals);
    }
    public String saverentals (List<String> listRentals){
        return  String.join(" ",listRentals);

    }

    public Customer() {
        idCount++;
    }

    public static int getIdCount() {
        return idCount;
    }

    public static void setIdCount(int idCount) {
        Customer.idCount = idCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if(this.id == null) {
            this.id = id;
        } else {
            System.out.println("You cannot set the ID because it is unique!");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public List<String> getListRentals() {
        return listRentals;
    }

    public void setListRentals(List<String> listRentals) {
        this.listRentals = listRentals;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public static int generateId() {
        ObservableList<Customer> customer = SingletonDatabase.getCustomers();
        System.out.println(customer.size());
        if(customer.size() > 0) {
            Customer lastCustomer = customer.get(customer.size() - 1);
            String substring = lastCustomer.getId().substring(1, 4);
            System.out.println(substring);
            return Integer.parseInt(substring);
        }
        return 0;
    }
    public String getCombinedString() {
        return combinedString;
    }

    public void setCombinedString(String combinedString) {
        this.combinedString = combinedString;
    }
}

