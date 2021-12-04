package com.companyreportsystem.helpers.models;

import java.sql.Date;

/**
 * Model of decision presented in table
 */
public class Decision {
    private final Integer id;
    private final Date date;
    private final String subject;
    private final String employee;
    private final String priority;
    private final String description;

    public Decision(Integer id, Date date, String subject, String employee, String priority, String description) {
        this.id = id;
        this.date = date;
        this.subject = subject;
        this.employee = employee;
        this.priority = priority;
        this.description = description;
    }

    public Integer getID() {
        return id;
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
