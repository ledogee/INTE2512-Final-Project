package com.example.videostore.Model;

import com.example.videostore.SystemBroker.SingletonDatabase;

import java.io.IOException;

public class DVD extends Item {
    private String genres;


    // Builder pattern for Constructor
    private DVD(DVD.DVDBuilder builder) {
        this.genres = builder.genres;
        this.setTitle(builder.title);
        this.setRentalType(builder.rentalType);
        this.setLoanType(builder.loanType);
        this.setCopies(builder.copies);
        this.setRentalFee(builder.rentalFee);
        this.setRentalStatus(builder.rentalStatus);
        this.setYear(builder.year);
        this.setId(builder.id);
        this.setYear(builder.year);

        // ID generate
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

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "DVD{" +
                "id='" + super.getId() + '\'' +
                ", title='" + super.getTitle() + '\'' +
                ", rentalType='" + super.getRentalType() + '\'' +
                ", loanType=" + super.getLoanType() +
                ", copies=" + super.getCopies() +
                ", rentalFee=" + super.getRentalFee() +
                ", rentalStatus=" + super.isRentalStatus() +
                ", genres= " + this.genres +
                '}';
    }

    // Builder pattern for Constructor
    public static class DVDBuilder {
        private String id;
        private String title;
        private final String rentalType = "DVD";
        private String loanType;
        private int copies;
        private double rentalFee;
        private boolean rentalStatus;
        private String genres;
        private String year;

        public DVDBuilder() {
        }

        public DVDBuilder(String id, String title, String loanType, int copies, double rentalFee, String genres){
            this.id = id;
            this.title = title;
            this.loanType = loanType;
            this.copies = copies;
            this.rentalFee = rentalFee;
            this.genres = genres;

        }

        public DVDBuilder(Item item){
            this.id = item.getId();
            this.title = item.getTitle();
            this.loanType = item.getLoanType();
            this.copies = item.getCopies();
            this.rentalFee = item.getRentalFee();
            this.year = item.getYear();
            this.rentalStatus = item.isRentalStatus();
        }
        public DVDBuilder(String id, String title, int copies, String loanType, double rentalFee, boolean rentalStatus, String year, String genres) {
            this.id = id;
            this.title = title;
            this.copies = copies;
            this.loanType = loanType;
            this.rentalFee = rentalFee;
            this.rentalStatus = rentalStatus;
            this.year = year;
            this.genres = genres;
        }

        public DVD.DVDBuilder buildTitle (String title) {
            this.title = title;
            return this;
        }

        public DVD.DVDBuilder buildLoanType(int num) {
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

        public DVD.DVDBuilder buildCopies(int copies) {
            this.copies = copies;
            return this;
        }

        public DVD.DVDBuilder buildRentalFee(double rentalFee) {

            this.rentalFee = rentalFee;
            return this;
        }

        public DVD.DVDBuilder buildRentalStatus(boolean rentalStatus) {
            this.rentalStatus = rentalStatus;
            return this;
        }


        public DVD.DVDBuilder buildGenres (int num) {
            Genres enumGenres = null;
            if(num == 0) {
                enumGenres = Genres.Action;
            } else if(num == 1) {
                enumGenres = Genres.Horror;
            } else if(num == 2) {
                enumGenres = Genres.Drama;
            } else if(num == 3) {
                enumGenres = Genres.Comedy;
            } else {
                System.out.println("Enum out of bound");
            }
            this.genres = String.valueOf(enumGenres);
            return this;
        }

        public DVD.DVDBuilder buildYear(String year) {
            this.year = year;
            return this;
        }


        public DVD build() {
            return new DVD(this);
        }
    }

}
