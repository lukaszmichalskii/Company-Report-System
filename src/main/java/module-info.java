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
    opens com.companyreportsystem.systemlogic.models;
    opens com.companyreportsystem.guicontrollers.management;
    opens com.companyreportsystem.guicontrollers.analysis;
    exports com.companyreportsystem;
    exports com.companyreportsystem.guicontrollers.userdashboard;
    opens com.companyreportsystem.guicontrollers.userdashboard to javafx.fxml;
    opens com.companyreportsystem.systemlogic.databaseconnection;
    opens com.companyreportsystem.systemlogic.configurators.tableconfigurators;
    opens com.companyreportsystem.systemlogic.initializators;
    opens com.companyreportsystem.systemlogic.configurators.choiceboxconfigurators;
    opens com.companyreportsystem.systemlogic.loginvalidation;
    opens com.companyreportsystem.systemlogic.query;
    exports com.companyreportsystem.systemlogic.contentloaders;
    opens com.companyreportsystem.systemlogic.contentloaders to javafx.fxml;
}