package com.companyreportsystem.systemlogic.databaseconnection;

import com.companyreportsystem.systemlogic.errorhandling.alertmanager.AlertManager;
import com.companyreportsystem.systemlogic.query.QueryExecutor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;

public class DatabaseManager {

    private static DatabaseManager databaseManager;
    private final DatabaseConnector databaseConnector;
    private final QueryExecutor queryExecutor;
    private final AlertManager alertManager;

    private DatabaseManager() {
        databaseConnector = new DatabaseConnector();
        queryExecutor = new QueryExecutor();
        alertManager = new AlertManager();
    }

    public static DatabaseManager getInstance() {
        if (databaseManager == null) {
            databaseManager = new DatabaseManager();
        }
        return databaseManager;
    }

    public DatabaseResponse insertDecision(String query, LocalDate date, String subject, String employee, String priority, String description) {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            return getDatabaseResponse(date, subject, employee, priority, description, preparedStatement);
        } catch (SQLException e) {
            alertManager.throwError("Wrong data type!");
        }

        return DatabaseResponse.ERROR;
    }

    public DatabaseResponse insertReview(String query, LocalDate date, String employee, String position, String department, String review) {
        try {
            String firstname = employee.split(" ")[0];
            String lastname = employee.split(" ")[1];
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, String.valueOf(date));
            return getDatabaseResponse(lastname, firstname, position, department, review, preparedStatement);
        } catch (SQLException e) {
            alertManager.throwError("Database error while saving data.");
        } catch (Exception e) {
            alertManager.throwError("Something went wrong. Check your inputs");
        }
        return DatabaseResponse.ERROR;
    }

    public DatabaseResponse insertPermission(String query, String username, String firstname, String surname, String password, String position, String department) {
        ResultSet usernames = queryExecutor.executeQuery(query);

        try {

            while (usernames.next()) {
                if (Objects.equals(usernames.getString("username"), username)) {
                    return DatabaseResponse.USERNAME_ALREADY_EXIST;
                } else {
                    String addPermissionQuery = "INSERT INTO `database`.user_account (firstname, lastname, username, password, position, department) VALUES (?,?,?,?,?,?)";
                    try {
                        PreparedStatement preparedPermission = getConnection().prepareStatement(addPermissionQuery);
                        preparedPermission.setString(1, firstname);
                        return getDatabaseResponse(username, surname, password, position, department, preparedPermission);

                    } catch (SQLException e) {
                        e.printStackTrace();
                        e.getCause();
                        return DatabaseResponse.ERROR;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            e.getCause();
            return DatabaseResponse.ERROR;
        }
        return DatabaseResponse.ERROR;
    }

    private DatabaseResponse getDatabaseResponse(String username, String surname, String password, String position, String department, PreparedStatement preparedPermission) throws SQLException {
        preparedPermission.setString(2, surname);
        preparedPermission.setString(3, username);
        preparedPermission.setString(4, password);
        preparedPermission.setString(5, position);
        preparedPermission.setString(6, department);
        preparedPermission.execute();
        return DatabaseResponse.SUCCESS;
    }

    private DatabaseResponse getDatabaseResponse(LocalDate date, String employee, String position, String department, String review, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, String.valueOf(date));
        preparedStatement.setString(2, employee);
        preparedStatement.setString(3, position);
        preparedStatement.setString(4, department);
        preparedStatement.setString(5, review);
        preparedStatement.execute();

        return DatabaseResponse.SUCCESS;
    }

    public ResultSet executeQuery(String query) {
        return queryExecutor.executeQuery(query);
    }

    public Connection getConnection() {
        return databaseConnector.connect();
    }

    public DatabaseResponse delete(String query) {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.execute();
            return DatabaseResponse.SUCCESS;
        } catch (SQLException ex) {
            ex.printStackTrace();
            ex.getCause();
            return DatabaseResponse.ERROR;
        }
    }
}
