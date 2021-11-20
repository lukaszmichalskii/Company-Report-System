package com.companyreportsystem.login;

import com.companyreportsystem.databaseconnection.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginValidator {

    private final String username;
    private final String password;

    public LoginValidator(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public ValidationResult validate() {
        ValidationResult result = ValidationResult.INVALID_LOGIN;
        Connection connectDB = DatabaseConnection.getConnection();

        String verifyLogin = "SELECT count(1) FROM user_account WHERE username = '" + this.username + "' AND password = '" + this.password + "'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    return ValidationResult.AUTHORIZATION_OBTAINED;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        return result;
    }

}
