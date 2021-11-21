package com.companyreportsystem.management;

import com.companyreportsystem.SceneCreator;
import com.companyreportsystem.databaseconnection.DatabaseConnection;
import com.companyreportsystem.models.Employee;
import com.companyreportsystem.models.Review;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ManagementController implements Initializable {

    @FXML
    private Button backButton;

    @FXML
    private TableColumn<Review, Date> dateColumn;

    @FXML
    private TableView<Review> reviewsTable;

    @FXML
    private TableColumn<Review, String> departmentColumn;

    @FXML
    private TableColumn<Review, String> employeeColumn;

    @FXML
    private TableColumn<Review, String> evaluationColumn;

    @FXML
    private TableColumn<Review, String> positionColumn;

    @FXML
    private TextField searchTextField;

    private final ObservableList<Review> reviewsObservableList = FXCollections.observableArrayList();


    @FXML
    private void add() {
        SceneCreator.createScene("gui/new-review-panel.fxml", 400, 500);
    }

    @FXML
    private void back() {
        SceneCreator.createScene("gui/user-dashboard.fxml", 1280, 720);
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void refresh() {
        try {
            Connection connectDB = DatabaseConnection.getConnection();
            reviewsObservableList.clear();

            String query = "SELECT * FROM `database`.reviews";
            PreparedStatement preparedStatement = connectDB.prepareStatement(query);
            ResultSet reviews = preparedStatement.executeQuery();

            while (reviews.next()){
                Employee employee = new Employee(reviews.getString("employee"), reviews.getString("position"), reviews.getString("department"));
                reviewsObservableList.add(new Review(reviews.getDate("date"), employee.getName(), employee.getPosition(), employee.getDepartment(), reviews.getString("evaluation")));
                reviewsTable.setItems(reviewsObservableList);
            }

            initData();


        } catch (SQLException ex) {
            ex.printStackTrace();
            ex.getCause();
        }
    }

    private void initData() {
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        employeeColumn.setCellValueFactory(new PropertyValueFactory<>("employee"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("department"));
        evaluationColumn.setCellValueFactory(new PropertyValueFactory<>("evaluation"));

        reviewsTable.setItems(reviewsObservableList);

        FilteredList<Review> filteredData = new FilteredList<>(reviewsObservableList, b -> true);

        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(review -> {
            if (newValue.isEmpty() || newValue.isBlank()) {
                return true;
            }

            String searchKeyword = newValue.toLowerCase();
            if (review.getEmployee().toLowerCase().contains(searchKeyword)) {
                return true;
            } else if (review.getPosition().toLowerCase().contains(searchKeyword)) {
                return true;
            } else if (review.getDepartment().toLowerCase().contains(searchKeyword)) {
                return true;
            } else if (review.getEvaluation().toLowerCase().contains(searchKeyword)) {
                return true;
            } else return review.getDate().toString().contains(searchKeyword);
        }));

        SortedList<Review> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(reviewsTable.comparatorProperty());

        reviewsTable.setItems(sortedData);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Connection connectDB = DatabaseConnection.getConnection();

        String query = "SELECT * FROM `database`.reviews";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet reviews = statement.executeQuery(query);

            while (reviews.next()) {
                Employee employee = new Employee(reviews.getString("employee"), reviews.getString("position"), reviews.getString("department"));
                reviewsObservableList.add(new Review(reviews.getDate("date"), employee.getName(), employee.getPosition(), employee.getDepartment(), reviews.getString("evaluation")));
            }

            initData();

        } catch (SQLException e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}
