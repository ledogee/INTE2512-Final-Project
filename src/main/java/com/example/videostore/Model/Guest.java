package com.example.videostore.Model;

import com.example.videostore.SystemBroker.SingletonDatabase;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;

public class Guest extends Customer {

    // Builder pattern for Constructor
    public Guest(Guest.GuestBuilder builder) {
        this.setName(builder.name);
        this.setAddress(builder.address);
        this.setPhone(builder.phone);
        this.setAccountType(builder.accountType);
        this.setListRentals(builder.listRentals);
        this.setBalance(builder.balance);
        this.setUsername(builder.username);
        this.setPassword(builder.password);
        this.setId(builder.id);
        this.setNumberOfReturn(builder.numOfReturn);

        // Generate ID
        if(this.getId() == null) {
            int id = generateId();
            id++;
            if(id < 10) {
                this.setId("C" + "00" +  id);
            } else if(getIdCount() < 100) {
                this.setId("C" + "0" +  id);
            } else if(getIdCount() <= 999) {
                this.setId("C" +  id);
            } else {
                System.out.println("ID Overflow");
            }
        }
    }

    // Overriding rent Feature
    @Override
    public boolean  rentItem(ObservableList<Item> itemObservableList, ObservableList<Customer> customerObservableList, Button btn, Label balanceLabel, int indexUser, Label rewardLabel) {
        for(int i = 0; i < itemObservableList.size(); i++) {
            if (itemObservableList.get(i).getButtonRent() == btn) {
                Item item = itemObservableList.get(i);
                // Check item price with balance of the user
                if (item.getRentalFee() <= this.getBalance() && item.isRentalStatus() && this.getListRentals().size() < 2) { // Enough balance to rent
                        itemObservableList.set(i, item);
                        this.setBalance(this.getBalance() - item.getRentalFee());
                        List<String> listItems;
                        if(this.getListRentals() == null) {
                            listItems = new ArrayList<>();
                        } else {
                            listItems = this.getListRentals();
                        }
                        listItems.add(item.getId());
                        this.setListRentals(listItems);
                        customerObservableList.set(indexUser, this);
                        String result = String.format("%.2f", this.getBalance());
                        balanceLabel.setText(result + " $");
                        item.setCopies(item.getCopies() - 1);
                        if (item.getCopies() == 0) {
                            btn.setDisable(true);
                            item.setRentalStatus(false);
                            itemObservableList.set(i, item);
                        }
                        return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Guest{" +
                "id='" + super.getId() + '\'' +
                ", name='" + super.getName() + '\'' +
                ", address='" + super.getAddress() + '\'' +
                ", phone='" + super.getPhone() + '\'' +
                ", accountType='" + super.getAccountType() + '\'' +
                ", listRentals=" + super.getListRentals() +
                ", balance=" + super.getBalance() +
                ", username='" + super.getUsername() + '\'' +
                ", password='" + super.getPassword() + '\'' +
                '}';
    }

    // Builder pattern for Constructor
    public static class GuestBuilder {
        private String id;
        private String name;
        private String address;
        private String phone;
        private final String accountType = "Guest";
        private List<String> listRentals;
        private double balance;
        private String username;
        private String password;

        private int numOfReturn = 0;

        public GuestBuilder() {
        }
        public GuestBuilder(Customer cus){
            this.id = cus.getId();
            this.name = cus.getName();
            this.address = cus.getAddress();
            this.phone = cus.getPhone();
            this.listRentals = cus.getListRentals();
            this.balance = cus.getBalance();
            this.username = cus.getUsername();
            this.password = cus.getPassword();
        }
        public GuestBuilder(String id, String name, String address, String phone, List<String> listRentals, float balance, String username, String password) {
            this.id = id;
            this.name = name;
            this.address = address;
            this.phone = phone;
            this.listRentals = listRentals;
            this.balance = balance;
            this.username = username;
            this.password = password;
        }
        public GuestBuilder(String id, String name, String username, String password, double balance, List<String> listRentals) {
            this.id = id;
            this.name = name;
            this.username = username;
            this.password = password;
            this.balance = balance;
            this.listRentals = listRentals;
        }
        public Guest.GuestBuilder buildNumReturn(int num) {
            this.numOfReturn = num;
            return this;
        }

        public Guest.GuestBuilder buildName(String name) {
            this.name = name;
            return this;
        }
        public Guest.GuestBuilder buildAddress(String address) {
            this.address = address;
            return this;
        }
        public Guest.GuestBuilder buildPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Guest.GuestBuilder buildListRentals(List<String> listRentals) {
            this.listRentals = listRentals;
            return this;
        }

        public Guest.GuestBuilder buildBalance(double balance) {
            this.balance = balance;
            return this;
        }

        public Guest.GuestBuilder buildUsername(String username) {
            this.username = username;
            return this;
        }

        public Guest.GuestBuilder buildPassword(String password) {
            this.password = password;
            return this;
        }

        public Guest build() {
            return new Guest(this);
        }
    }
}
