module com.example.carreviewproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.carreviewproject to javafx.fxml;
    exports com.example.carreviewproject;
}