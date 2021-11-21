package com.companyreportsystem.management;

import com.companyreportsystem.databaseconnection.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddReviewController {

    @FXML
    private Button cancelButton;

    @FXML
    private DatePicker dateField;

    @FXML
    private TextField departmentField;

    @FXML
    private TextField employeeField;

    @FXML
    private TextField positionField;

    @FXML
    private TextArea reviewField;

    @FXML
    void cancel() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void save() {
        Connection connectDB = DatabaseConnection.getConnection();

        String query = "INSERT INTO `database`.reviews (date, employee, position, department, evaluation) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connectDB.prepareStatement(query);
            preparedStatement.setString(1, String.valueOf(dateField.getValue()));
            preparedStatement.setString(2, employeeField.getText());
            preparedStatement.setString(3, positionField.getText());
            preparedStatement.setString(4, departmentField.getText());
            preparedStatement.setString(5, reviewField.getText());
            preparedStatement.execute();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Review added successfully");
            alert.setHeaderText(null);
            alert.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}
