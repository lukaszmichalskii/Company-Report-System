package com.companyreportsystem.helpers.initializators;

import com.companyreportsystem.helpers.databaseconnection.DatabaseManager;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LinePlotInitialization {
    private String firstname;
    private String lastname;
    private String position;
    private String department;

    public LinePlotInitialization(String firstname, String lastname, String position, String department) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.position = position;
        this.department = department;
    }

    public void init(LineChart<Number, Number> reviews) {
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        String query = "SELECT evaluation FROM `database`.reviews WHERE firstname = '" + firstname + "' " +
                "AND lastname = '" + lastname + "' " +
                "AND position = '" + position + "' " +
                "AND department = '" + department + "'";
        ResultSet reviewsPoints = databaseManager.executeQuery(query);

        try {
            int i = 1;
            XYChart.Series<Number, Number> series = new XYChart.Series<>();
            while (reviewsPoints.next()) {
                series.getData().add(new XYChart.Data<>(i, Double.parseDouble(reviewsPoints.getString("evaluation"))));
                i += 1;
            }

            reviews.getData().add(series);
        } catch (SQLException e) {
            e.getCause();
            e.printStackTrace();
        }
    }
}
