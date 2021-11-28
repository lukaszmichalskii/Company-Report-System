package com.companyreportsystem.systemlogic.errorhandling.alertmanager;

public interface AlertManagerInterface {
    void throwError(String message);
    void throwConfirmation(String message);
}
