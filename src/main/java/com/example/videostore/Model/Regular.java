package com.example.videostore.Model;

import java.util.List;

public class Regular extends Customer {

    // Builder pattern for Constructor
    public Regular(Regular.RegularBuilder builder) {
        this.setName(builder.name);
        this.setAddress(builder.address);
        this.setPhone(builder.phone);
        this.setAccountType(builder.accountType);
        this.setListRentals(builder.listRentals);
        this.setBalance(builder.balance);
        this.setUsername(builder.username);
        this.setPassword(builder.password);
        this.setNumberOfReturn(builder.numOfReturn);
        this.setId(builder.id);

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

    @Override
    public String toString() {
        return "Regular{" +
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
    public static class RegularBuilder {
        private String id;
        private String name;
        private String address;
        private String phone;
        private final String accountType = "Regular";
        private List<String> listRentals;
        private double balance;
        private String username;
        private String password;
        private int numOfReturn = 0;

        public RegularBuilder(){

        }

        public RegularBuilder(String id, String name, String username, String password, Double balance, List<String> listRentals ){
            this.id = id;
            this.name = name;
            this.listRentals = listRentals;
            this.balance = balance;
            this.username = username;
            this.password = password;
        }
        public RegularBuilder(Customer cus){
            this.id = cus.getId();
            this.name = cus.getName();
            this.address = cus.getAddress();
            this.phone = cus.getPhone();
            this.listRentals = cus.getListRentals();
            this.balance = cus.getBalance();
            this.username = cus.getUsername();
            this.password = cus.getPassword();
        }

        public Regular.RegularBuilder buildNumReturn(int numOfReturn) {
            this.numOfReturn = numOfReturn;
            return this;
        }

        public Regular.RegularBuilder buildName(String name) {
            this.name = name;
            return this;
        }
        public Regular.RegularBuilder buildAddress(String address) {
            this.address = address;
            return this;
        }
        public Regular.RegularBuilder buildPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Regular.RegularBuilder buildListRentals(List<String> listRentals) {
            this.listRentals = listRentals;
            return this;
        }

        public Regular.RegularBuilder buildBalance(double balance) {
            this.balance = balance;
            return this;
        }

        public Regular.RegularBuilder buildUsername(String username) {
            this.username = username;
            return this;
        }

        public Regular.RegularBuilder buildPassword(String password) {
            this.password = password;
            return this;
        }

        public Regular build() {
            return new Regular(this);
        }
    }
    
}
