package com.companyreportsystem.guicontrollers.management;

import com.companyreportsystem.systemlogic.errorhandling.alertmanager.AlertManager;
import com.companyreportsystem.systemlogic.databaseconnection.DatabaseManager;
import com.companyreportsystem.systemlogic.databaseconnection.DatabaseResponse;
import com.companyreportsystem.systemlogic.initializators.ChoiceBoxInitialization;
import com.companyreportsystem.systemlogic.initializators.DepartmentInitialization;
import com.companyreportsystem.systemlogic.initializators.PositionsInitialization;
import com.companyreportsystem.systemlogic.initializators.ReviewInitialization;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Class responsible for add review
 * -database connection
 * -parse query
 * -insert
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
        String query = "INSERT INTO `database`.reviews (date, firstname, lastname, position, department, evaluation) VALUES (?,?,?,?,?,?)";

        if (isReviewFieldsBlank()) {
            DatabaseResponse result = databaseManager.insertReview(query,
                    dateField.getValue(),
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
        return !employeeField.getText().isBlank() &&
                !positionChoiceBox.getValue().isBlank() &&
                !departmentChoiceBox.getValue().isBlank() &&
                !reviewChoiceBox.getValue().isBlank();
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
