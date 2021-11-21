package com.companyreportsystem.decisions;

import com.companyreportsystem.SceneCreator;
import com.companyreportsystem.databaseconnection.DatabaseConnection;
import com.companyreportsystem.models.Decision;
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
import java.util.Date;
import java.util.ResourceBundle;

public class DecisionsController implements Initializable {

    @FXML
    private Button backButton;

    @FXML
    private TableColumn<Decision, Date> dateColumn;

    @FXML
    private TableView<Decision> decisionTable;

    @FXML
    private TableColumn<Decision, String> descriptionColumn;

    @FXML
    private TableColumn<Decision, String> employeeColumn;

    @FXML
    private TableColumn<Decision, String> priorityColumn;

    @FXML
    private TextField searchTextField;

    @FXML
    private TableColumn<Decision, String> subjectColumn;

    private final ObservableList<Decision> decisionsObservableList = FXCollections.observableArrayList();

    @FXML
    void add() {
        SceneCreator.createScene("gui/new-decision-panel.fxml", 400, 500);
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
            decisionsObservableList.clear();

            String query = "SELECT * FROM `database`.decisions";
            PreparedStatement preparedStatement = connectDB.prepareStatement(query);
            ResultSet decisions = preparedStatement.executeQuery();

            while (decisions.next()){
                decisionsObservableList.add(new Decision(decisions.getDate("date"), decisions.getString("subject"),
                        decisions.getString("employee"), decisions.getString("priority"), decisions.getString("description")));
                decisionTable.setItems(decisionsObservableList);
            }

            initData();


        } catch (SQLException ex) {
            ex.printStackTrace();
            ex.getCause();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Connection connectDB = DatabaseConnection.getConnection();

        String query = "SELECT * FROM `database`.decisions";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet decisions = statement.executeQuery(query);

            while (decisions.next()) {
                decisionsObservableList.add(new Decision(decisions.getDate("date"), decisions.getString("subject"),
                        decisions.getString("employee"), decisions.getString("priority"), decisions.getString("description")));
            }

            initData();

        } catch (SQLException e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    private void initData() {
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
        employeeColumn.setCellValueFactory(new PropertyValueFactory<>("employee"));
        priorityColumn.setCellValueFactory(new PropertyValueFactory<>("priority"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        decisionTable.setItems(decisionsObservableList);

        FilteredList<Decision> filteredData = new FilteredList<>(decisionsObservableList, b -> true);

        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(decision -> {
            if (newValue.isEmpty() || newValue.isBlank()) {
                return true;
            }

            String searchKeyword = newValue.toLowerCase();
            if (decision.getEmployee().toLowerCase().contains(searchKeyword)) {
                return true;
            } else if (decision.getSubject().toLowerCase().contains(searchKeyword)) {
                return true;
            } else if (decision.getPriority().toLowerCase().contains(searchKeyword)) {
                return true;
            } else if (decision.getDescription().toLowerCase().contains(searchKeyword)) {
                return true;
            } else return decision.getDate().toString().contains(searchKeyword);
        }));

        SortedList<Decision> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(decisionTable.comparatorProperty());

        decisionTable.setItems(sortedData);
    }
}
