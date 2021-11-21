package com.companyreportsystem.decisions;

import com.companyreportsystem.databaseconnection.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
    private Button saveButton;

    @FXML
    private TextField subjectField;

    @FXML
    void cancel() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void save() {
        Connection connectDB = DatabaseConnection.getConnection();

        String query = "INSERT INTO `database`.decisions (date, subject, employee, priority, description) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connectDB.prepareStatement(query);
            preparedStatement.setString(1, String.valueOf(dateField.getValue()));
            preparedStatement.setString(2, subjectField.getText());
            preparedStatement.setString(3, employeeField.getText());
            preparedStatement.setString(4, priorityField.getText());
            preparedStatement.setString(5, descriptionField.getText());
            preparedStatement.execute();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Decision added successfully");
            alert.setHeaderText(null);
            alert.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}
