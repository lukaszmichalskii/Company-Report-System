package com.companyreportsystem.helpers.loginvalidation;

import com.companyreportsystem.helpers.databaseconnection.DatabaseManager;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class responsible for verify the login of user
 */
public class LoginValidator implements LoginValidation {

    private final String username;
    private final String password;
    private final DatabaseManager databaseManager;

    public LoginValidator(String username, String password) {
        this.username = username;
        this.password = password;
        this.databaseManager = DatabaseManager.getInstance();
    }

    public ValidationResult validate() {
        ValidationResult result = ValidationResult.INVALID_LOGIN;

        String verifyLogin = "SELECT count(1) FROM `database`.user_account WHERE username = '" + this.username + "' AND password = '" + this.password + "'";

        ResultSet queryResult = databaseManager.executeQuery(verifyLogin);
        try {
            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    return ValidationResult.AUTHORIZATION_OBTAINED;
                }
            }
        } catch (SQLException t) {
            t.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        return result;
    }

}
