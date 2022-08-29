package com.example.videostore;

import com.example.videostore.Model.Customer;
import com.example.videostore.Model.Item;
import com.example.videostore.SystemBroker.SingletonDatabase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage logIn) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("customer.fxml")));
        setUserAgentStylesheet(STYLESHEET_CASPIAN); // this is radius border more circle theme
        Scene scene = new Scene(root);
        logIn.setScene(scene);
        logIn.show();
    }

    public static void main(String[] args) throws IOException {

        System.out.println("--------------------------------SINGLETON TEST--------------------------------");
        SingletonDatabase.loadItems();
        System.out.println(SingletonDatabase.getItems());
        SingletonDatabase.loadCustomers();
        System.out.println(SingletonDatabase.getCustomers());
        System.out.println(Customer.generateId());
        System.out.println("--------------------------------SINGLETON TEST--------------------------------");



       /* Item item1 = new Movie.MovieBuilder().buildTitle("Alpha Dog").buildLoanType(1).buildCopies(3).buildRentalFee(1.99).buildGenres(1).buildYear("1992").build();


        Item item2 = new DVD.DVDBuilder().buildTitle("Rat Race").buildLoanType(1).buildCopies(1).buildRentalFee(1.99).buildGenres(2).buildYear("2015").build();

        Item item3 = new Game.GameBuilder().buildTitle("Medal of Honour").buildLoanType(1).buildCopies(3).buildRentalFee(0.99).buildYear("2001").buildImage("asdasd").build();

        List<String> listItems = new ArrayList<>();
        listItems.add(item1.getId());
        listItems.add(item2.getId());
        listItems.add(item3.getId());

        for(String it : listItems) {
            System.out.println(it);
        }


        System.out.printf("\n\nAccount Model Test:\n");
        Customer customer1 = new Vip.VipBuilder().buildName("Minh Dinh").buildAddress("18 Irwin Street").buildPhone("0421473243").buildListRentals(listItems).buildUsername("minhdinh").buildPassword("123456").buildPhone("123456").buildRewardPoint(100).buildBalance(100).build();

        List<String> listItem2 = new ArrayList<>();

        Customer customer2 = new Guest.GuestBuilder().buildName("Hong Wang").buildAddress("20 Irwin Street").buildPhone("0424173255").buildListRentals(listItem2).buildUsername("wanghong98").buildPhone("987654").buildBalance(100).build();

        List<String> listItem3 = new ArrayList<>();
        listItem3.add(item1.getId());

        Customer customer3 = new Guest.GuestBuilder().buildName("Linh Nguyen").buildAddress("12 Brunswick Street").buildPhone("0424173255").buildListRentals(listItem3).buildUsername("linhtb").buildPhone("linhlinh").build();

        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer1);
        customerList.add(customer2);
        customerList.add(customer3);
        for(Customer it: customerList) {
            System.out.println(it);
        }

        // test rent method
        System.out.println();
        customer1.rentItem(item1);
        System.out.println("Check the copies of item1 " + item1.getCopies());
        System.out.println("Information of customer1: ");
        System.out.println(customer1);*/
        launch();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run(){
                //save data here
                try {
                    SingletonDatabase.saveCustomers();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    SingletonDatabase.saveitems();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Exiting");
            }
        });
    }
}