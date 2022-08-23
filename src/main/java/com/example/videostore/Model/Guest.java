package com.example.videostore.Model;

import java.util.LinkedList;
import java.util.List;

public class Guest extends Customer {
    public Guest(GuestBuilder builder) {
        this.setName(builder.name);
        this.setAddress(builder.address);
        this.setPhone(builder.phone);
        this.setAccountType(builder.accountType);
        this.setListRentals(builder.listRentals);
        this.setBalance(builder.balance);
        this.setUsername(builder.username);
        this.setPassword(builder.password);
        if(getIdCount() < 10) {
            this.setId("C" + "00" +  getIdCount());
        } else if(getIdCount() < 100) {
            this.setId("C" + "0" +  getIdCount());
        } else if(getIdCount() <= 999) {
            this.setId("C" +  getIdCount());
        } else {
            System.out.println("ID Overflow");
        }
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


        public GuestBuilder(String id, String name, String address, String phone, LinkedList listRentals, float balance, String username, String password) {
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

        /*public Guest.GuestBuilder buildAccountType(int num) {
            Account.AccountType enumAccountType = null;
            if(num == 0) {
                enumAccountType = AccountType.Guest;
            } else if(num == 1) {
                enumAccountType = AccountType.Regular;
            } else if(num == 2) {
                enumAccountType = AccountType.Vip;
            } else {
                System.out.println("Enum out of bound");

            }
            this.accountType = String.valueOf(enumAccountType);
            return this;
        }*/

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
