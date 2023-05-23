module com.example.demoinclass {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.demoinclass to javafx.fxml;
    exports com.example.demoinclass;
}