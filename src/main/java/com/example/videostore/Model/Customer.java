package com.example.videostore.Model;

import java.util.List;

public class Customer {
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
    private List<Item> listRentals;
    private double balance;
    private String username;
    private String password;

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

    public boolean rentItem(Item item) {
        if(this.balance >= item.getRentalFee()) {
            double currentBalance = this.balance;
            System.out.println("Rent successfully!");
            this.balance = this.balance - item.getRentalFee();
            System.out.println("Your balance change from " + currentBalance + " to " + this.balance);
            this.listRentals.add(item);
            System.out.println("Your rented items has add new one item!");
            System.out.println(this.getListRentals());
            return true;
        } else {
            System.out.println("You don't have enough money to rent");
            return false;
        }
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
        this.id = id;
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

    public List<Item> getListRentals() {
        return listRentals;
    }

    public void setListRentals(List<Item> listRentals) {
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
}
