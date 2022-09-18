package com.example.videostore.Model;

import com.example.videostore.SystemBroker.SingletonDatabase;
import javafx.collections.ObservableMap;

public class Game extends Item{

    // Builder pattern for Constructor
    private Game(Game.GameBuilder builder) {
        this.setTitle(builder.title);
        this.setRentalType(builder.rentalType);
        this.setLoanType(builder.loanType);
        this.setCopies(builder.copies);
        this.setRentalFee(builder.rentalFee);
        this.setRentalStatus(builder.rentalStatus);
        this.setYear(builder.year);
        this.setId(builder.id);
        this.setYear(builder.year);

        // Generate ID
        if(this.getId() == null) {
            int id = generateId();
            id++;
            if (id < 10) {
                this.setId("I" + "00" + id + "-" + this.getYear());
            } else if (getIdCount() < 100) {
                this.setId("I" + "0" + id + "-" + this.getYear());
            } else if (getIdCount() <= 999) {
                this.setId("I" + id + "-" + this.getYear());
            } else {
                System.out.println("ID Overflow");
            }
        }

    }
    @Override
    public String toString() {
        return "VideoGame{" +
                "id='" + super.getId() + '\'' +
                ", title='" + super.getTitle() + '\'' +
                ", rentalType='" + super.getRentalType() + '\'' +
                ", loanType=" + super.getLoanType() +
                ", copies=" + super.getCopies() +
                ", rentalFee=" + super.getRentalFee() +
                ", rentalStatus=" + super.isRentalStatus() +
                '}';
    }

    // Making sure cannot getGenres
    @Override
    public String getGenres() {
        return null;
    }

    // Builder pattern for Constructor
    public static class GameBuilder {
        private String id;
        private String title;
        private final String rentalType = "Game";
        private String loanType;
        private int copies;
        private double rentalFee;
        private boolean rentalStatus;
        private String year;

        public GameBuilder(String id, String title, int copies, String loanType, double rentalFee, boolean rentalStatus, String year) {
            this.id = id;
            this.title = title;
            this.copies = copies;
            this.loanType = loanType;
            this.rentalFee = rentalFee;
            this.rentalStatus = rentalStatus;
            this.year = year;
        }

        public GameBuilder() {
        }
        public GameBuilder(Item item){
            this.id = item.getId();
            this.title = item.getTitle();
            this.loanType = item.getLoanType();
            this.copies = item.getCopies();
            this.rentalFee = item.getRentalFee();
            this.year = item.getYear();
            this.rentalStatus = item.isRentalStatus();
        }
        public GameBuilder(String id, String title, String loanType, int copies, double rentalFee){
            this.id = id;
            this.title = title;
            this.loanType = loanType;
            this.copies = copies;
            this.rentalFee = rentalFee;
        }
        public GameBuilder buildTitle (String title) {
            this.title = title;
            return this;
        }

        public GameBuilder buildLoanType(int num) {
            LoanType enumLoan = null;
            if(num == 0) {
                enumLoan = LoanType.TwoDays;
            } else if(num == 1) {
                enumLoan = LoanType.OneWeek;
            } else {
                System.out.println("Enum out of bound");
            }
            this.loanType = String.valueOf(enumLoan);
            return this;
        }

        public GameBuilder buildCopies(int copies) {
            this.copies = copies;
            return this;
        }

        public GameBuilder buildRentalFee(double rentalFee) {

            this.rentalFee = rentalFee;
            return this;
        }

        public GameBuilder buildRentalStatus(boolean rentalStatus) {
            this.rentalStatus = rentalStatus;
            return this;
        }

        public GameBuilder buildYear(String year) {
            this.year = year;
            return this;
        }

        public Game build() {
            return new Game(this);
        }
    }
}
