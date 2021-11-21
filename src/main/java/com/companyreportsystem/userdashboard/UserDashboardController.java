package com.companyreportsystem.userdashboard;

import com.companyreportsystem.SectionLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class UserDashboardController {

    @FXML
    private Button logoutButton;

    @FXML
    private BorderPane borderpane;

    @FXML
    private void logout() {
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.close();
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
        SectionLoader.load("gui/permissions-test.fxml", borderpane);
    }

    @FXML
    private void gotoAnalysisPanel() {
    }
}
