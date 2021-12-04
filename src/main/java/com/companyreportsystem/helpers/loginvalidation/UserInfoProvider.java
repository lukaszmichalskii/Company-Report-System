package com.companyreportsystem.helpers.loginvalidation;

import com.companyreportsystem.helpers.databaseconnection.DatabaseManager;

import java.sql.ResultSet;

public class UserInfoProvider {
    private String name;
    private String position;
    private String department;
    private final DatabaseManager databaseManager = DatabaseManager.getInstance();

    public UserInfoProvider(String username, String password) {
        init(username, password);
    }

    private void init(String username, String password) {
        String getUserQuery = "SELECT * FROM `database`.user_account WHERE username = '" + username + "' AND password = '" + password + "'";
        try {
            ResultSet user = databaseManager.executeQuery(getUserQuery);

            while (user.next()) {
                this.name = user.getString("firstname") + " " + user.getString("lastname");
                this.position = user.getString("position");
                this.department = user.getString("department");
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getDepartment() {
        return department;
    }
}
