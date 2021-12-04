package com.companyreportsystem.helpers.configurators.tableconfigurators;

import com.companyreportsystem.helpers.databaseconnection.DatabaseManager;
import com.companyreportsystem.helpers.models.Employee;
import com.companyreportsystem.helpers.models.Review;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewTableConfigurator {

    public void provideConfiguration(ObservableList<Review> reviewsObservableList, TableView<Review> reviewsTable) {
        try {
            DatabaseManager databaseManager = DatabaseManager.getInstance();
            reviewsObservableList.clear();

            String query = "SELECT * FROM `database`.reviews";
            ResultSet reviews = databaseManager.executeQuery(query);

            while (reviews.next()){
                Employee employee = new Employee(reviews.getString("firstname"), reviews.getString("lastname"), reviews.getString("position"), reviews.getString("department"));
                reviewsObservableList.add(new Review(reviews.getInt("id"), reviews.getDate("date"), employee.getName(), employee.getPosition(), employee.getDepartment(), reviews.getString("evaluation")));
                reviewsTable.setItems(reviewsObservableList);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            ex.getCause();
        }
    }
}
