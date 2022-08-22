package com.example.videostore.Model;

public abstract class  Item {


    public abstract String getGenres();

    enum Genres {
        Action,
        Horror,
        Drama,
        Comedy
    }
    enum LoanType {
        TwoDays,
        OneWeek
    }
    /* Attribute */
    private static int idCount = 0;
    protected String id;
    private String title;
    private String rentalType;
    private String loanType;
    private int copies;
    private double rentalFee;
    private boolean rentalStatus;
    private String year;
    private String imageFile;

    public Item() {
        idCount++;
    }

    public String getRentalType() {
        return rentalType;
    }

    public void setRentalType(String rentalType) {
        this.rentalType = rentalType;
    }

    public String getId() {
        return id;
    }

    public Item setId(String id) {
        this.id = id;
        return null;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public double getRentalFee() {
        return rentalFee;
    }

    public void setRentalFee(double rentalFee) {
        this.rentalFee = rentalFee;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public boolean isRentalStatus() {
        return rentalStatus;
    }

    public void setRentalStatus(boolean rentalStatus) {
        this.rentalStatus = rentalStatus;
    }

    public static int getIdCount() {
        return idCount;
    }

    public static void setIdCount(int idCount) {
        Item.idCount = idCount;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }
}
