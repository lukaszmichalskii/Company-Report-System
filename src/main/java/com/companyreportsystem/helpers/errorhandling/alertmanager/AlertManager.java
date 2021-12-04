package com.companyreportsystem.helpers.errorhandling.alertmanager;

import javafx.scene.control.Alert;

public class AlertManager implements AlertManagerInterface {
    public void throwError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    public void throwConfirmation(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
