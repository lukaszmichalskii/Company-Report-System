package com.companyreportsystem.helpers.configurators.choiceboxconfigurators;

import com.companyreportsystem.helpers.databaseconnection.DatabaseManager;
import javafx.scene.control.ChoiceBox;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeChoiceBoxConfigurator {
    public void initValues(String query, ChoiceBox<String> employeeChoiceBox) {
        try {
            DatabaseManager databaseManager = DatabaseManager.getInstance();
            ResultSet employees = databaseManager.executeQuery(query);
            while (employees.next()) {
                if (!employeeChoiceBox.getItems().contains(employeeRepresentation(employees))) {
                    employeeChoiceBox.getItems().add(employeeRepresentation(employees));
                }
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    private String employeeRepresentation(ResultSet employees) throws SQLException {
        return employees.getString("firstname") + " "
                + employees.getString("lastname") + "/"
                + employees.getString("position") + "/"
                + employees.getString("department");
    }
}
