module com.example.primer {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.primer to javafx.fxml;
    exports com.example.primer;
}