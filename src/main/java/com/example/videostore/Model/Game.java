package com.example.videostore.Model;

import com.example.videostore.SystemBroker.SingletonDatabase;
import javafx.collections.ObservableMap;

public class Game extends Item{
    private Game(Game.GameBuilder builder) {
        this.setTitle(builder.title);
        this.setRentalType(builder.rentalType);
        this.setLoanType(builder.loanType);
        this.setCopies(builder.copies);
        this.setRentalFee(builder.rentalFee);
        this.setRentalStatus(builder.rentalStatus);
        this.setYear(builder.year);
        this.setImageFile(builder.imageFile);
        this.setId(builder.id);
        this.setYear(builder.year);
        this.setImageFile(builder.imageFile);

        if(this.getId() == null) {
            if (getIdCount() < 10) {
                this.setId("I" + "00" + getIdCount() + "-" + this.getYear());
            } else if (getIdCount() < 100) {
                this.setId("I" + "0" + getIdCount() + "-" + this.getYear());
            } else if (getIdCount() <= 999) {
                this.setId("I" + getIdCount() + "-" + this.getYear());
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
                ", imageFile=" + super.getImageFile() +
                '}';
    }

    @Override
    public String getGenres() {
        return null;
    }

    public static class GameBuilder {
        private String id;
        private String title;
        private final String rentalType = "Game";
        private String loanType;
        private int copies;
        private double rentalFee;
        private boolean rentalStatus;
        private String year;
        private String imageFile;

        public Game.GameBuilder buildId (String id) {
            this.id = id;
            return this;
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

        public GameBuilder buildImage(String imageFile) {
            this.imageFile = imageFile;
            return this;
        }

        public Game build() {
            return new Game(this);
        }
    }
}
