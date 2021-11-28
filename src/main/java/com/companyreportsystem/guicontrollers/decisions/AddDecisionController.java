package com.companyreportsystem.guicontrollers.decisions;

import com.companyreportsystem.systemlogic.errorhandling.alertmanager.AlertManager;
import com.companyreportsystem.systemlogic.databaseconnection.DatabaseManager;
import com.companyreportsystem.systemlogic.databaseconnection.DatabaseResponse;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * Class responsible for add review
 */
public class AddDecisionController {
    @FXML
    private Button cancelButton;

    @FXML
    private DatePicker dateField;

    @FXML
    private TextArea descriptionField;

    @FXML
    private TextField employeeField;

    @FXML
    private TextField priorityField;

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
        String query = "INSERT INTO `database`.decisions (date, subject, employee, priority, description) VALUES (?,?,?,?,?)";

        if (isDataFieldsBlank()) {
            DatabaseResponse result = databaseManager.insertDecision(query, dateField.getValue(), subjectField.getText(), employeeField.getText(), priorityField.getText(), descriptionField.getText());
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
        return !subjectField.getText().isBlank() && !employeeField.getText().isBlank() && !priorityField.getText().isBlank() && !descriptionField.getText().isBlank();
    }
}
