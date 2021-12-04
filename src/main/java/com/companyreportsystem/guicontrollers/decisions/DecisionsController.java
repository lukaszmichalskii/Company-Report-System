package com.companyreportsystem.guicontrollers.decisions;

import com.companyreportsystem.helpers.contentloaders.SceneCreator;
import com.companyreportsystem.helpers.errorhandling.alertmanager.AlertManager;
import com.companyreportsystem.helpers.databaseconnection.DatabaseManager;
import com.companyreportsystem.helpers.databaseconnection.DatabaseResponse;
import com.companyreportsystem.helpers.models.Decision;
import com.companyreportsystem.helpers.configurators.tableconfigurators.DecisionTableConfigurator;
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
 * Class manage the decision panel
 */
public class DecisionsController implements Initializable {

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

    private final DatabaseManager databaseManager;
    private final DecisionTableConfigurator decisionTableConfigurator;
    private final ObservableList<Decision> decisionsObservableList = FXCollections.observableArrayList();
    private final AlertManager alertManager;

    public DecisionsController() {
        databaseManager = DatabaseManager.getInstance();
        decisionTableConfigurator = new DecisionTableConfigurator();
        alertManager = new AlertManager();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        decisionTableConfigurator.provideConfiguration(decisionsObservableList, decisionTable);
        initData();
    }

    @FXML
    private void add() {
        AddDecisionController addDecisionController = (AddDecisionController) SceneCreator.createScene("gui/new-decision-panel.fxml", 400, 500);
        assert addDecisionController != null;
        addDecisionController.setAlertManager(alertManager);
    }

    @FXML
    private void delete() {
        try {
            Decision decision = decisionTable.getSelectionModel().getSelectedItem();

            DatabaseResponse result = databaseManager.delete(decision);
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
        decisionTableConfigurator.provideConfiguration(decisionsObservableList, decisionTable);
        initData();
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
