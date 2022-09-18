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
    public void start(Stage stage) throws IOException {
        Parent root1 = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("welcome.fxml")));
        //Main Stage
        setUserAgentStylesheet(STYLESHEET_CASPIAN); // this is radius border more circle theme
        Scene scene = new Scene(root1);
        stage.setTitle("Media Mart");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public static void main(String[] args) throws IOException {

        System.out.println("--------------------------------SINGLETON TEST--------------------------------");
        SingletonDatabase.loadItems();
        System.out.println(SingletonDatabase.getItems());
        SingletonDatabase.loadCustomers();
        System.out.println(SingletonDatabase.getCustomers());
        System.out.println(Customer.generateId());
        System.out.println("--------------------------------SINGLETON TEST--------------------------------");

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