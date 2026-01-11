module com.example.tp2_fx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.tp2_fx to javafx.fxml;
    exports com.example.tp2_fx;
}