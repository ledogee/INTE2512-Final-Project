package com.example.videostore.Model;

import com.example.videostore.SystemBroker.SingletonDatabase;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;
public abstract class Customer  {
    private static int idCount = 0;
    private String id;
    private String name;
    private String address;
    private String phone;
    private String accountType;
    private List<String> listRentals;
    private double balance;
    private String thisname;
    private String password;

    private int numberOfReturn = 0;

    private int rewardPoint = 0;

    public int getNumberOfReturn() {
        return numberOfReturn;
    }

    public void setNumberOfReturn(int numberOfReturn) {
        this.numberOfReturn = numberOfReturn;
    }

    private String combinedString;

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", accountType='" + accountType + '\'' +
                ", listRentals=" + listRentals +
                ", balance=" + balance +
                ", thisname='" + thisname + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


    // Function Rent of Customer get the Database as ObservableList Type of customer and item then locate button rent at that row to create event
    public boolean rentItem( ObservableList<Item> itemObservableList, ObservableList<Customer>  customerObservableList, Button btn, Label balanceLabel, int indexUser, Label rewardLabel) {
        for(int i = 0; i < itemObservableList.size(); i++) {
            if (itemObservableList.get(i).getButtonRent() == btn) {
                Item item = itemObservableList.get(i);
                // Check item price with balance of the user
                if (item.getRentalFee() <= this.getBalance()) { // Enough balance to rent
                        itemObservableList.set(i, item);
                        this.setBalance(this.getBalance() - item.getRentalFee());

                        // Create Empty listRental when the listItems of the Customer is null
                        List<String> listItems;
                        if(this.getListRentals() == null) {
                            listItems = new ArrayList<>();
                        } else {
                            listItems = this.getListRentals();
                        }
                        listItems.add(item.getId());
                        this.setListRentals(listItems);
                        
                        // Locate the index of the customer then update into the Observable List of Customer Database
                        customerObservableList.set(indexUser, this);
                        String result = String.format("%.2f", this.getBalance());
                        balanceLabel.setText(result + " $");
                        item.setCopies(item.getCopies() - 1);
                        if (item.getCopies() == 0) {
                            btn.setDisable(true);
                            item.setRentalStatus(false);
                            itemObservableList.set(i, item);
                        }
                        return true;
                }
            }
        }
        return false;
    }

    // Function return of Customer get the Database as ObservableList Type of customer and item then locate button return at that row to create event
    public boolean returnItem(ObservableList<Item> itemObservableList,ObservableList<Item> rented, Button btn){
        for(int i = 0; i < rented.size();i++){
            
            // when the button is located 
            if(rented.get(i).getButtonReturn().equals(btn)){
                
                // Update the quality of the item in the item-list of customer
                Item item = rented.get(i);
                rented.get(i).setQuantity(rented.get(i).getQuantity()-1);
                System.out.println(rented.get(i).getQuantity());
                if(rented.get(i).getQuantity() == 0){ 
                    rented.remove(i);
                }
                
                System.out.println(item.getTitle());
                
                // Locate the item in the item database
                for (int j = 0; j < itemObservableList.size();j++){
                    if (item.getId().equals(itemObservableList.get(j).getId())) {
                        item = itemObservableList.get(j);
                        List<String> list = this.getListRentals();
                        
                        // Return the copies for the store
                        item.setCopies(item.getCopies()+1);
                        list.remove(item.getId());
                        this.setListRentals(list);
                        itemObservableList.set(j,item);
                        this.setNumberOfReturn(this.getNumberOfReturn()+1);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // Convert the list of ID into one string
    public String arraytostring (){
        if(this.listRentals==null||this.listRentals.isEmpty()){
            return "";
        }
        return  String.join(",",this.listRentals);
    }
    public String saverentals (List<String> listRentals){
        return  String.join(" ",listRentals);

    }

    // Increase the idCount of each time the new Customer object created
    public Customer() {
        idCount++;
    }

    public int getRewardPoint() {
        return rewardPoint;
    }

    public void setRewardPoint(int rewardPoint) {
        this.rewardPoint = rewardPoint;
    }

    public static int getIdCount() {
        return idCount;
    }
    public String getId() {
        return id;
    }


    // Constraint if the id is already set make sure object cannot generate new ID
    public void setId(String id) {
        if(this.id == null) {
            this.id = id;
        } else {
            System.out.println("You cannot set the ID because it is unique!");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public List<String> getListRentals() {
        return listRentals;
    }

    public void setListRentals(List<String> listRentals) {
        this.listRentals = listRentals;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getUsername() {
        return thisname;
    }

    public void setUsername(String thisname) {
        this.thisname = thisname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCombinedString() {
        return combinedString;
    }
    // Function to generateId automatically
    public static int generateId() {
        ObservableList<Customer> customer = SingletonDatabase.getCustomers();
        System.out.println(customer.size());
        if(customer.size() > 0) {
            Customer lastCustomer = customer.get(customer.size() - 1);
            String substring = lastCustomer.getId().substring(1, 4);
            System.out.println(substring);
            return Integer.parseInt(substring);
        }
        return 0;
    }
    public void setCombinedString(String combinedString) {
        this.combinedString = combinedString;

    }
}

