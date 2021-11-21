package com.companyreportsystem.permissions;

import com.companyreportsystem.SceneCreator;
import com.companyreportsystem.databaseconnection.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class PermissionsController {

    @FXML
    private Button backButton;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private TextField firstnameField;

    @FXML
    private Label informationField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField surnameField;

    @FXML
    private TextField usernameField;

    @FXML
    void add() {
        // check if fields are not empty
        if (!usernameField.getText().isBlank() || !passwordField.getText().isBlank() || !confirmPasswordField.getText().isBlank() || !firstnameField.getText().isBlank() || !surnameField.getText().isBlank()) {
            // check if passwords are the same
            if (Objects.equals(passwordField.getText(), confirmPasswordField.getText())) {
                Connection connectDB = DatabaseConnection.getConnection();
                try {
                    String query = "SELECT * FROM `database`.user_account";
                    PreparedStatement preparedStatement = connectDB.prepareStatement(query);
                    ResultSet usernames = preparedStatement.executeQuery();

                    while (usernames.next()) {
                        // check if username already exist
                        if (Objects.equals(usernames.getString("username"), usernameField.getText())) {
                            informationField.setTextFill(Color.RED);
                            informationField.setText("Username already exist");
                        } else {
                            String addPermissionQuery = "INSERT INTO `database`.user_account (firstname, lastname, username, password) VALUES (?,?,?,?)";
                            try {
                                PreparedStatement preparedPermission = connectDB.prepareStatement(addPermissionQuery);
                                preparedPermission.setString(1, firstnameField.getText());
                                preparedPermission.setString(2, surnameField.getText());
                                preparedPermission.setString(3, usernameField.getText());
                                preparedPermission.setString(4, passwordField.getText());
                                preparedPermission.execute();

                                informationField.setTextFill(Color.GREEN);
                                informationField.setText("Permission added successfully");
                            } catch (SQLException e) {
                                e.printStackTrace();
                                e.getCause();
                            }
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    e.getCause();
                }

            } else {
                informationField.setTextFill(Color.RED);
                informationField.setText("Password is not correct");
            }
        } else {
            informationField.setTextFill(Color.RED);
            informationField.setText("Missing data");
        }

    }

    @FXML
    void back() {
        SceneCreator.createScene("gui/user-dashboard.fxml", 1280, 720);
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }
}
