package com.example.videostore.Model;

import com.example.videostore.SystemBroker.SingletonDatabase;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;

public abstract class  Item {


    public abstract String getGenres();

    // Enum for quickly choose the string we want
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

    private Button buttonRent;

    private Button buttonReturn;

    private int quantity = 1;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // increase count of the Item ID when item is created
    public Item() {
        idCount++;
        this.buttonRent = new Button("Rent");
        this.buttonReturn = new Button("Return");
    }

    public Button getButtonReturn() {
        return buttonReturn;
    }

    public Button getButtonRent() {
        return buttonRent;
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

    // Generate ID automatically
    public static int generateId() {
        ObservableList<Item> listItems = SingletonDatabase.getItems();
        System.out.println(listItems.size());
        if(listItems.size() > 0) {
            Item lastItem = listItems.get(listItems.size() - 1);
            String substring = lastItem.getId().substring(1, 4);
            System.out.println(substring);
            return Integer.parseInt(substring);
        }
        return 0;

    }
}
