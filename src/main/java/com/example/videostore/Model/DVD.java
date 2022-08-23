package com.example.videostore.Model;

public class DVD extends Item {
    private String genres;

    private DVD(DVD.DVDBuilder builder) {
        this.genres = builder.genres;
        this.setTitle(builder.title);
        this.setRentalType(builder.rentalType);
        this.setLoanType(builder.loanType);
        this.setCopies(builder.copies);
        this.setRentalFee(builder.rentalFee);
        this.setRentalStatus(builder.rentalStatus);
        this.setYear(builder.year);
        this.setImageFile(builder.imageFile);
        if(getIdCount() < 10) {
            this.setId("I" + "00" +  getIdCount() + "-" + this.getYear());
        } else if(getIdCount() < 100) {
            this.setId("I" + "0" +  getIdCount() + "-" + this.getYear());
        } else if(getIdCount() <= 999) {
            this.setId("I" +  getIdCount() + "-" + this.getYear());
        } else {
            System.out.println("ID Overflow");
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
                ", imageFile = " + super.getImageFile() +
                '}';
    }

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
        private String imageFile;

<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
        public DVDBuilder(String id, String title, String loanType, int copies, double rentalFee, String genres){
            this.id = id;
            this.title = title;
            this.loanType = loanType;
            this.copies = copies;
            this.rentalFee = rentalFee;
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

        public DVD.DVDBuilder buildImage(String imageFile) {
            this.imageFile = imageFile;
            return this;
        }

        public DVD build() {
            return new DVD(this);
        }
    }

}
