module com.example.latihan1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.opencsv;

    opens com.example.latihan1 to javafx.fxml;
    exports com.example.latihan1;
    exports com.example.latihan1.Controller;
    opens com.example.latihan1.Controller to javafx.fxml;
    exports com.example.latihan1.Model;
    opens com.example.latihan1.Model to javafx.base;
}