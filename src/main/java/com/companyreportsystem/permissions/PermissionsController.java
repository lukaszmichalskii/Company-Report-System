package com.companyreportsystem.permissions;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PermissionsController {

    @FXML
    private Button cancelButton;

    @FXML
    private TextField firstnameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button saveButton;

    @FXML
    private TextField surnameField;

    @FXML
    private TextField usernameField;

    @FXML
    private void cancel() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void save() {
        System.out.println("Save data");
    }
}
