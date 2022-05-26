module com.warship {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.warship to javafx.fxml;
    exports com.warship;
}