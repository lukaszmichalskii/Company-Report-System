package com.companyreportsystem.systemlogic.loginvalidation;

import com.companyreportsystem.systemlogic.query.QueryExecutor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class responsible for verify the login of user
 */
public class LoginValidator implements LoginValidation {

    private final String username;
    private final String password;
    private final QueryExecutor queryExecutor;

    public LoginValidator(String username, String password, QueryExecutor queryExecutor) {
        this.username = username;
        this.password = password;
        this.queryExecutor = queryExecutor;
    }

    public ValidationResult validate() {
        ValidationResult result = ValidationResult.INVALID_LOGIN;

        String verifyLogin = "SELECT count(1) FROM `database`.user_account WHERE username = '" + this.username + "' AND password = '" + this.password + "'";

        ResultSet queryResult = queryExecutor.executeQuery(verifyLogin);
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
