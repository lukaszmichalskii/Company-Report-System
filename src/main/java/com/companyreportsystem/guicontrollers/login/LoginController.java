package com.companyreportsystem.guicontrollers.login;

import com.companyreportsystem.helpers.contentloaders.SceneCreator;
import com.companyreportsystem.helpers.query.QueryExecutor;
import com.companyreportsystem.helpers.loginvalidation.LoginValidation;
import com.companyreportsystem.helpers.loginvalidation.LoginValidator;
import com.companyreportsystem.helpers.loginvalidation.UserInfoProvider;
import com.companyreportsystem.helpers.loginvalidation.ValidationResult;
import com.companyreportsystem.guicontrollers.userdashboard.UserDashboardController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Class manage the login panel and listen action
 */
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
            LoginValidation loginValidator = new LoginValidator(usernameField.getText(), passwordField.getText());
            ValidationResult result = loginValidator.validate();

            if (result == ValidationResult.AUTHORIZATION_OBTAINED) {
                statementField.setTextFill(Color.GREEN);
                statementField.setText("Access granted.");
                UserInfoProvider userInfoProvider = new UserInfoProvider(usernameField.getText(), passwordField.getText());
                UserDashboardController userDashboardController = (UserDashboardController) SceneCreator.createScene("gui/user-dashboard.fxml", 1280, 720);
                assert userDashboardController != null;
                userDashboardController.setUserInfo(userInfoProvider.getName());
                userDashboardController.setUserOccupation(userInfoProvider.getPosition(), userInfoProvider.getDepartment());
                quit();
            } else if (result == ValidationResult.INVALID_LOGIN) {
                statementField.setTextFill(Color.RED);
                statementField.setText("Invalid login. Please try again.");
            }
        }
        else {
            statementField.setTextFill(Color.RED);
            statementField.setText("Please enter username and password.");
        }
    }
}
