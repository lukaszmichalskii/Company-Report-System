package com.companyreportsystem.models;

import java.sql.Date;

public class Review {
    private final Integer id;
    private final Date date;
    private final String employee;
    private final String position;
    private final String department;
    private final String evaluation;

    public Review(Integer id, Date date, String employee, String position, String department, String evaluation) {
        this.id = id;
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

    public Integer getID() {
        return id;
    }
}
