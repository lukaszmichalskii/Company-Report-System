package com.companyreportsystem.models;

import java.sql.Date;

public class Decision {
    private Date date;
    private String subject;
    private String employee;
    private String priority;
    private String description;

    public Decision(Date date, String subject, String employee, String priority, String description) {
        this.date = date;
        this.subject = subject;
        this.employee = employee;
        this.priority = priority;
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public String getSubject() {
        return subject;
    }

    public String getEmployee() {
        return employee;
    }

    public String getPriority() {
        return priority;
    }

    public String getDescription() {
        return description;
    }
}
