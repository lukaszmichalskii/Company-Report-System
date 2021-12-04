package com.companyreportsystem.helpers.errorhandling.alertmanager;

public interface AlertManagerInterface {
    void throwError(String message);
    void throwConfirmation(String message);
}
