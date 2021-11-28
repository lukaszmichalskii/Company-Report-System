package com.companyreportsystem.systemlogic.initializators;

import com.companyreportsystem.systemlogic.databaseconnection.DatabaseManager;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LinePlotInitialization {
    public void init(String query, LineChart<Number, Number> reviews) {
        DatabaseManager databaseManager = DatabaseManager.getInstance();
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
