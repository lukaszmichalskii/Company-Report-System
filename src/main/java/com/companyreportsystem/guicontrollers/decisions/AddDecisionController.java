package com.companyreportsystem.guicontrollers.decisions;

import com.companyreportsystem.helpers.errorhandling.alertmanager.AlertManager;
import com.companyreportsystem.helpers.databaseconnection.DatabaseManager;
import com.companyreportsystem.helpers.databaseconnection.DatabaseResponse;
import com.companyreportsystem.helpers.initializators.ChoiceBoxInitialization;
import com.companyreportsystem.helpers.initializators.PriorityInitialization;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Class responsible for add review
 */
public class AddDecisionController implements Initializable {
    @FXML
    private Button cancelButton;

    @FXML
    private DatePicker dateField;

    @FXML
    private TextArea descriptionField;

    @FXML
    private TextField employeeField;

    @FXML
    private ChoiceBox<String> priorityChoiceBox;

    @FXML
    private TextField subjectField;

    private final DatabaseManager databaseManager;
    private AlertManager alertManager;

    public AddDecisionController() {
        this.databaseManager = DatabaseManager.getInstance();
    }

    @FXML
    void cancel() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void save() {
        if (isDataFieldsBlank()) {
            DatabaseResponse result = databaseManager.insertDecision(dateField.getValue(), subjectField.getText(), employeeField.getText(), priorityChoiceBox.getValue(), descriptionField.getText());
            if (result == DatabaseResponse.SUCCESS) {
                alertManager.throwConfirmation("Decision added successfully.");
                cancel();
            }
        } else {
            alertManager.throwError("Error while saving data into database.");
        }
    }

    public void setAlertManager(AlertManager alertManager) {
        this.alertManager = alertManager;
    }

    private boolean isDataFieldsBlank() {
        try {
            return !subjectField.getText().isBlank() && !employeeField.getText().isBlank() && !priorityChoiceBox.getValue().isBlank() && !descriptionField.getText().isBlank();
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initPriority();
    }

    private void initPriority() {
        ChoiceBoxInitialization choiceBoxInitialization = new PriorityInitialization();
        choiceBoxInitialization.init(priorityChoiceBox);
    }
}
