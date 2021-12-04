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
    opens com.companyreportsystem.guicontrollers.login;
    opens com.companyreportsystem.guicontrollers.decisions;
    opens com.companyreportsystem.guicontrollers.permissions;
    opens com.companyreportsystem.helpers.models;
    opens com.companyreportsystem.guicontrollers.management;
    opens com.companyreportsystem.guicontrollers.analysis;
    exports com.companyreportsystem;
    exports com.companyreportsystem.guicontrollers.userdashboard;
    opens com.companyreportsystem.guicontrollers.userdashboard to javafx.fxml;
    opens com.companyreportsystem.helpers.databaseconnection;
    opens com.companyreportsystem.helpers.configurators.tableconfigurators;
    opens com.companyreportsystem.helpers.initializators;
    opens com.companyreportsystem.helpers.configurators.choiceboxconfigurators;
    opens com.companyreportsystem.helpers.loginvalidation;
    opens com.companyreportsystem.helpers.query;
    exports com.companyreportsystem.helpers.contentloaders;
    opens com.companyreportsystem.helpers.contentloaders to javafx.fxml;
}