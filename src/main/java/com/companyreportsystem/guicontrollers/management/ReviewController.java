package com.companyreportsystem.guicontrollers.management;

import com.companyreportsystem.helpers.contentloaders.SceneCreator;
import com.companyreportsystem.helpers.errorhandling.alertmanager.AlertManager;
import com.companyreportsystem.helpers.databaseconnection.DatabaseManager;
import com.companyreportsystem.helpers.databaseconnection.DatabaseResponse;
import com.companyreportsystem.helpers.models.Review;
import com.companyreportsystem.helpers.configurators.tableconfigurators.ReviewTableConfigurator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

/**
 * Class responsible for manage the management panel
 */
public class ReviewController implements Initializable {

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
    private final DatabaseManager databaseManager;
    private final ReviewTableConfigurator reviewTableConfigurator;
    private final AlertManager alertManager;

    public ReviewController() {
        databaseManager = DatabaseManager.getInstance();
        reviewTableConfigurator = new ReviewTableConfigurator();
        alertManager = new AlertManager();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reviewTableConfigurator.provideConfiguration(reviewsObservableList, reviewsTable);
        initData();
    }

    @FXML
    private void add() {
        AddReviewController addReviewController = (AddReviewController) SceneCreator.createScene("gui/new-review-panel.fxml", 400, 500);
        assert addReviewController != null;
        addReviewController.setAlertManager(alertManager);
    }

    @FXML
    private void delete() {
        try {
            Review review = reviewsTable.getSelectionModel().getSelectedItem();

            DatabaseResponse result = databaseManager.delete(review);
            if (result == DatabaseResponse.ERROR) {
                alertManager.throwError("Error deleting data from database.");
            } else if (result == DatabaseResponse.SUCCESS) {
                refresh();
            }
        } catch (Exception e) {
            alertManager.throwError("Error deleting data from database. Select record you want to delete");
        }
    }

    @FXML
    private void refresh() {
        reviewTableConfigurator.provideConfiguration(reviewsObservableList, reviewsTable);
        initData();
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
}
