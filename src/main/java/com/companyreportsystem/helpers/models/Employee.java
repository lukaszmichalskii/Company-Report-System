package com.companyreportsystem.helpers.models;

/**
 * Model of employee presented in table
 */
public class Employee {
    private final String name;
    private final String position;
    private final String department;

    public Employee(String firstname, String lastname, String position, String department) {
        this.name = firstname + " " + lastname;
        this.position = position;
        this.department = department;
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
