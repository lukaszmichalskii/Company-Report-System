module com.companyreportsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires mysql.connector.java;

    opens com.companyreportsystem to javafx.fxml;
    opens com.companyreportsystem.login;
    opens com.companyreportsystem.decisions;
    opens com.companyreportsystem.permissions;
    opens com.companyreportsystem.models;
    opens com.companyreportsystem.management;
    exports com.companyreportsystem;
    exports com.companyreportsystem.userdashboard;
    opens com.companyreportsystem.userdashboard to javafx.fxml;
}