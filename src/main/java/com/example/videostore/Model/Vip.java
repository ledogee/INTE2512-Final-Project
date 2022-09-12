package com.example.videostore.Model;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;

public class Vip extends Customer {
    private int rewardPoint = 0;
    public Vip(Vip.VipBuilder builder) {
        this.setName(builder.name);
        this.setAddress(builder.address);
        this.setPhone(builder.phone);
        this.setAccountType(builder.accountType);
        this.setListRentals(builder.listRentals);
        this.setBalance(builder.balance);
        this.setUsername(builder.username);
        this.setPassword(builder.password);
        this.setRewardPoint(builder.rewardPoint);
        this.setNumberOfReturn(builder.numOfReturn);
        if(this.getId() == null) {
            if(getIdCount() < 10) {
                this.setId("C" + "00" +  getIdCount());
            } else if(getIdCount() < 100) {
                this.setId("C" + "0" +  getIdCount());
            } else if(getIdCount() <= 999) {
                this.setId("C" +  getIdCount());
            } else {
                System.out.println("ID Overflow");
            }
        } else {
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
        if(this.getId() == null) {
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

    }

    public int getRewardPoint() {
        return rewardPoint;
    }

    public void setRewardPoint(int rewardPoint) {
        this.rewardPoint = rewardPoint;
    }

    @Override
    public String toString() {
        return "Vip{" +
                "id='" + super.getId() + '\'' +
                ", name='" + super.getName() + '\'' +
                ", address='" + super.getAddress() + '\'' +
                ", phone='" + super.getPhone() + '\'' +
                ", accountType='" + super.getAccountType() + '\'' +
                ", listRentals=" + super.getListRentals() +
                ", balance=" + super.getBalance() +
                ", username='" + super.getUsername() + '\'' +
                ", password='" + super.getPassword() + '\'' +
                ", rewardPoint =" + getRewardPoint() + '\'' +
                '}';
    }

    @Override
    public boolean rentItem(ObservableList<Item> itemObservableList, ObservableList<Customer> customerObservableList, Button btn, Label balanceLabel, int indexUser, Label rewardLabel) {
        for(int i = 0; i < itemObservableList.size(); i++) {
            if (itemObservableList.get(i).getButtonRent() == btn) {
                Item item = itemObservableList.get(i);

                // Check item price with balance of the user
                if (item.getRentalFee() <= this.getBalance()) { // Enough balance to rent
                    item.setCopies(item.getCopies() - 1);
                    if (item.getCopies() == 0) {
                        btn.setDisable(true);
                        item.setRentalStatus(false);
                        itemObservableList.set(i, item);
                        return false;
                    } else if (item.getCopies() > 0) {
                        itemObservableList.set(i, item);
                        // Condition for fee rent
                        if(this.getRewardPoint() == 100) {
                            this.setRewardPoint(0);
                            rewardLabel.setText(this.getRewardPoint() + " point");
                            return true;
                        } else {
                            this.setBalance(this.getBalance() - item.getRentalFee());
                            this.setRewardPoint(this.getRewardPoint() + 10);
                            List<String> listItems;
                            if(this.getListRentals() == null) {
                                listItems = new ArrayList<>();
                            } else {
                                listItems = this.getListRentals();
                            }
                            listItems.add(item.getId());
                            this.setListRentals(listItems);
                            System.out.println(this.getListRentals());

                            customerObservableList.set(indexUser, this);
                            String result = String.format("%.2f", this.getBalance());
                            balanceLabel.setText(result + " $");
                            rewardLabel.setText(this.getRewardPoint() + " point");
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /*  @Override
    public boolean rentItem(Item item) {
        if(this.getRewardPoint() == 100) {
            System.out.println("You able to rent a free item!");
            item.setCopies(item.getCopies() - 1);
            System.out.println("Rent successfully!");
            this.setRewardPoint(0);
            System.out.println("Your reward point change from 100 to " + this.getRewardPoint());

            List<String> listRentals = super.getListRentals();
            listRentals.add(item.getId());
            super.setListRentals(listRentals);
            System.out.println("Your rented items has add new one item!");
            System.out.println(this.getListRentals());
            return true;
        }

        if(super.getBalance() >= item.getRentalFee()) {
            double currentBalance = super.getBalance();
            int currentRewardPoint = this.getRewardPoint();
            item.setCopies(item.getCopies() - 1);
            System.out.println("Rent successfully!");
            super.setBalance(super.getBalance() - item.getRentalFee());
            System.out.println("Your balance change from " + currentBalance + " to " + super.getBalance());

            this.setRewardPoint(this.getRewardPoint() + 10);
            System.out.println("Your reward point change from " + currentRewardPoint + " to " + this.getRewardPoint());

            List<String> listRentals = super.getListRentals();
            listRentals.add(item.getId());
            super.setListRentals(listRentals);
            System.out.println("Your rented items has add new one item!");
            System.out.println(this.getListRentals());


            return true;
        } else {
            System.out.println("You don't have enough money to rent");
            return false;
        }
    }*/

    public static class VipBuilder {
        private String id;
        private String name;
        private String address;
        private String phone;
        private final String accountType = "Vip";
        private List<String> listRentals;
        private double balance;
        private String username;
        private String password;

        private int rewardPoint = 0;

        private int numOfReturn = 0;

        public VipBuilder(String id, String name,String username, String password, double balance, List<String> listRentals ) {
            this.id = id;
            this.name = name;
            this.listRentals = listRentals;
            this.balance = balance;
            this.username = username;
            this.password = password;
        }
        public int getRewardPoint(){
            return this.rewardPoint;
        }
        public VipBuilder() {

        }
        public VipBuilder(Customer cus){
            this.id = cus.getId();
            this.name = cus.getName();
            this.address = cus.getAddress();
            this.phone = cus.getPhone();
            this.listRentals = cus.getListRentals();
            this.balance = cus.getBalance();
            this.username = cus.getUsername();
            this.password = cus.getPassword();
        }
        public Vip.VipBuilder buildNumReturn(int numOfReturn) {
            this.numOfReturn = numOfReturn;
            return this;
        }

        public Vip.VipBuilder buildId(String id) {
            this.id = id;
            return this;
        }

        public Vip.VipBuilder buildName(String name) {
            this.name = name;
            return this;
        }
        public Vip.VipBuilder buildAddress(String address) {
            this.address = address;
            return this;
        }
        public Vip.VipBuilder buildPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Vip.VipBuilder buildListRentals(List<String> listRentals) {
            this.listRentals = listRentals;
            return this;
        }

        public Vip.VipBuilder buildBalance(double balance) {
            this.balance = balance;
            return this;
        }

        public Vip.VipBuilder buildUsername(String username) {
            this.username = username;
            return this;
        }

        public Vip.VipBuilder buildPassword(String password) {
            this.password = password;
            return this;
        }

        public Vip.VipBuilder buildRewardPoint(int rewardPoint) {
            if(rewardPoint < 0) {
                this.rewardPoint = 0;
            } else {
                this.rewardPoint = rewardPoint;
            }
            return this;
        }


        public Vip build() {
            return new Vip(this);
        }
    }
}
