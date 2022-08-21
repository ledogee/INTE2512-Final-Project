module com.example.videostore {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.videostore to javafx.fxml;
    exports com.example.videostore;
    exports com.example.videostore.Controller;
    opens com.example.videostore.Controller to javafx.fxml;
}