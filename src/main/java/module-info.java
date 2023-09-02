module com.fns.studentsmanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    requires mysql.connector.java;
    opens com.fns.studentsmanager to javafx.fxml;
    exports com.fns.studentsmanager;
    exports com.fns.studentsmanager.data;
    exports com.fns.studentsmanager.controller;
    opens com.fns.studentsmanager.controller to javafx.fxml;
    exports com.fns.studentsmanager.entities;
    opens com.fns.studentsmanager.entities to javafx.fxml;
}
