package com.companyreportsystem.guicontrollers.management;

import com.companyreportsystem.helpers.errorhandling.alertmanager.AlertManager;
import com.companyreportsystem.helpers.databaseconnection.DatabaseManager;
import com.companyreportsystem.helpers.databaseconnection.DatabaseResponse;
import com.companyreportsystem.helpers.initializators.ChoiceBoxInitialization;
import com.companyreportsystem.helpers.initializators.DepartmentInitialization;
import com.companyreportsystem.helpers.initializators.PositionsInitialization;
import com.companyreportsystem.helpers.initializators.ReviewInitialization;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Class responsible for add review
 */
public class AddReviewController implements Initializable {

    @FXML
    private Button cancelButton;

    @FXML
    private DatePicker dateField;

    @FXML
    private ChoiceBox<String> departmentChoiceBox;

    @FXML
    private TextField employeeField;

    @FXML
    private ChoiceBox<String> positionChoiceBox;

    @FXML
    private ChoiceBox<String> reviewChoiceBox;

    private final DatabaseManager databaseManager;
    private AlertManager alertManager;

    public AddReviewController() {
        this.databaseManager = DatabaseManager.getInstance();
    }

    @FXML
    void cancel() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void save() {
        if (isReviewFieldsBlank()) {
            DatabaseResponse result = databaseManager.insertReview(dateField.getValue(),
                    employeeField.getText(),
                    positionChoiceBox.getValue(),
                    departmentChoiceBox.getValue(),
                    reviewChoiceBox.getValue());
            if (result == DatabaseResponse.SUCCESS) {
                alertManager.throwConfirmation("Review added successfully.");
                cancel();
            } else {
                alertManager.throwError("Error while saving data into database");
            }
        } else {
            alertManager.throwError("Missing Data");
        }
    }

    public void setAlertManager(AlertManager alertManager) {
        this.alertManager = alertManager;
    }

    private boolean isReviewFieldsBlank() {
        try {
            return !employeeField.getText().isBlank() &&
                    !positionChoiceBox.getValue().isBlank() &&
                    !departmentChoiceBox.getValue().isBlank() &&
                    !reviewChoiceBox.getValue().isEmpty();
        } catch (Exception e) {
            return false;
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initPositionOptions();
        initDepartmentOptions();
        initReviewOptions();
    }

    private void initDepartmentOptions() {
        ChoiceBoxInitialization choiceBoxInitialization = new DepartmentInitialization();
        choiceBoxInitialization.init(departmentChoiceBox);
    }

    private void initReviewOptions() {
        ChoiceBoxInitialization choiceBoxInitialization = new ReviewInitialization();
        choiceBoxInitialization.init(reviewChoiceBox);
    }

    private void initPositionOptions() {
        ChoiceBoxInitialization choiceBoxInitialization = new PositionsInitialization();
        choiceBoxInitialization.init(positionChoiceBox);
    }
}
