package com.example.videostore.Model;

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
    }

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

        /*public Vip.VipBuilder buildAccountType(int num) {
            Account.AccountType enumAccountType = null;
            if(num == 0) {
                enumAccountType = AccountType.Vip;
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
