package com.companyreportsystem.systemlogic.initializators;

import javafx.scene.control.ChoiceBox;

import java.util.ArrayList;
import java.util.List;

public class DepartmentInitialization implements ChoiceBoxInitialization {
    public void init(ChoiceBox<String> choiceBox) {
        List<String> departments = new ArrayList<>();
        departments.add("Operations");
        departments.add("Production");
        departments.add("Research and Development");
        departments.add("Purchasing");
        departments.add("Marketing and Sales");
        departments.add("Human Resource Management");
        departments.add("Accounting and Finance");

        choiceBox.getItems().addAll(departments);
    }
}
