package com.companyreportsystem.helpers.configurators.tableconfigurators;

import com.companyreportsystem.helpers.databaseconnection.DatabaseManager;
import com.companyreportsystem.helpers.models.Decision;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DecisionTableConfigurator {

    public void provideConfiguration(ObservableList<Decision> decisionsObservableList, TableView<Decision> decisionsTable) {
        try {
            DatabaseManager databaseManager = DatabaseManager.getInstance();
            decisionsObservableList.clear();

            String query = "SELECT * FROM `database`.decisions";
            ResultSet decisions = databaseManager.executeQuery(query);

            while (decisions.next()) {
                decisionsObservableList.add(new Decision(decisions.getInt("id"), decisions.getDate("date"), decisions.getString("subject"),
                        decisions.getString("employee"), decisions.getString("priority"), decisions.getString("description")));
                decisionsTable.setItems(decisionsObservableList);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}
