package com.companyreportsystem.guicontrollers.userdashboard;

import com.companyreportsystem.helpers.contentloaders.SceneCreator;
import com.companyreportsystem.helpers.contentloaders.SectionLoader;
import com.companyreportsystem.helpers.errorhandling.alertmanager.AlertManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Objects;


/**
 * Class responsible for manage user decisions and provide appropriate options
 */
public class UserDashboardController {

    @FXML
    private Button logoutButton;

    @FXML
    private BorderPane borderpane;

    @FXML
    private Label userInfo;

    @FXML
    private Label userOccupation;

    private String userPosition;
    private final AlertManager alertManager;

    public UserDashboardController() {
        alertManager = new AlertManager();
    }

    public void setUserInfo(String userInfo) {
        this.userInfo.setText(userInfo);
    }

    public void setUserOccupation(String userPosition, String userDepartment) {
        this.userPosition = userPosition;
        this.userOccupation.setText(userPosition + " / " + userDepartment);
    }

    @FXML
    private void logout() {
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.close();
        SceneCreator.createScene("gui/login-panel.fxml", 754, 492);
    }

    @FXML
    private void goToDecisionDashboard() {
        SectionLoader.load("gui/decisions-test.fxml", borderpane);
    }

    @FXML
    private void gotoManagementDashboard() {
        SectionLoader.load("gui/management-panel.fxml", borderpane);
    }

    @FXML
    private void openPermissionPanel() {
        if (Objects.equals(userPosition, "Chief Officer")) {
            SectionLoader.load("gui/permissions-test.fxml", borderpane);
        } else {
            alertManager.throwError("Option only for users with Chief Officer position");
        }
    }

    @FXML
    private void gotoAnalysisPanel() {
        SectionLoader.load("gui/analysis-panel.fxml", borderpane);
    }
}
