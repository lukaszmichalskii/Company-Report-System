package com.companyreportsystem.login;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label statementField;

    @FXML
    private Button quitButton;

    @FXML
    private TextField usernameField;


    @FXML
    private void quit() {
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void login() {
        if (!usernameField.getText().isBlank() && !passwordField.getText().isBlank()) {
            LoginValidator loginValidator = new LoginValidator(usernameField.getText(), passwordField.getText());
            ValidationResult result = loginValidator.validate();

            if (result == ValidationResult.AUTHORIZATION_OBTAINED) {
                statementField.setTextFill(Color.GREEN);
                statementField.setText("Access granted.");
            } else if (result == ValidationResult.INVALID_LOGIN) {
                statementField.setText("Invalid login. Please try again.");
            }
        }
        else {
            statementField.setText("Please enter username and password.");
        }
    }
}
