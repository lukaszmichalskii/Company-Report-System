package com.companyreportsystem.models;

import java.sql.Date;

public class Review {
    Date date;
    String employee, position, department, evaluation;

    public Review(Date date, String employee, String position, String department, String evaluation) {
        this.date = date;
        this.employee = employee;
        this.position = position;
        this.department = department;
        this.evaluation = evaluation;
    }

    public Date getDate() {
        return date;
    }

    public String getEmployee() {
        return employee;
    }

    public String getPosition() {
        return position;
    }

    public String getDepartment() {
        return department;
    }

    public String getEvaluation() {
        return evaluation;
    }
}
