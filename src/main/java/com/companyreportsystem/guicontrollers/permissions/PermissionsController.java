package com.companyreportsystem.guicontrollers.permissions;

import com.companyreportsystem.helpers.databaseconnection.DatabaseManager;
import com.companyreportsystem.helpers.databaseconnection.DatabaseResponse;
import com.companyreportsystem.helpers.initializators.ChoiceBoxInitialization;
import com.companyreportsystem.helpers.initializators.DepartmentInitialization;
import com.companyreportsystem.helpers.initializators.PositionsInitialization;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Class responsible for manage the permissions panel
 */
public class PermissionsController implements Initializable {

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private TextField firstnameField;

    @FXML
    private Label informationField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ChoiceBox<String> positionsChoiceBox;

    @FXML
    private ChoiceBox<String> departmentChoiceBox;

    @FXML
    private TextField surnameField;

    @FXML
    private TextField usernameField;

    private final DatabaseManager databaseManager;

    public PermissionsController() {
        this.databaseManager = DatabaseManager.getInstance();
    }

    @FXML
    void add() {
        if (isDataFieldsBlank()) {
            if (isPasswordsEquals()) {
                DatabaseResponse response = databaseManager.insertPermission(usernameField.getText(),
                        firstnameField.getText(),
                        surnameField.getText(),
                        passwordField.getText(),
                        positionsChoiceBox.getValue(),
                        departmentChoiceBox.getValue());

                if (response == DatabaseResponse.USERNAME_ALREADY_EXIST) {
                    informationField.setTextFill(Color.RED);
                    informationField.setText("Username already exist");
                } else if (response == DatabaseResponse.SUCCESS) {
                    informationField.setTextFill(Color.GREEN);
                    informationField.setText("Permission added successfully");
                }
            } else {
                informationField.setTextFill(Color.RED);
                informationField.setText("Passwords are not the same");
            }
        } else {
            informationField.setTextFill(Color.RED);
            informationField.setText("Missing data!");
        }

    }

    private boolean isPasswordsEquals() {
        return Objects.equals(passwordField.getText(), confirmPasswordField.getText());
    }

    private boolean isDataFieldsBlank() {
        return !usernameField.getText().isBlank() &&
                !passwordField.getText().isBlank() &&
                !confirmPasswordField.getText().isBlank() &&
                !firstnameField.getText().isBlank() &&
                !surnameField.getText().isBlank() &&
                !positionsChoiceBox.getValue().isBlank() &&
                !departmentChoiceBox.getValue().isBlank();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initPositions();
        initDepartments();
    }

    private void initPositions() {
        ChoiceBoxInitialization positionsInitialization = new PositionsInitialization();
        positionsInitialization.init(positionsChoiceBox);
    }

    private void initDepartments() {
        ChoiceBoxInitialization departmentsInitialization = new DepartmentInitialization();
        departmentsInitialization.init(departmentChoiceBox);
    }
}
