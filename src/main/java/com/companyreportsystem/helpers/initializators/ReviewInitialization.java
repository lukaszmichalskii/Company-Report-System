package com.companyreportsystem.helpers.initializators;

import javafx.scene.control.ChoiceBox;

import java.util.ArrayList;
import java.util.List;

public class ReviewInitialization implements  ChoiceBoxInitialization {
    public void init(ChoiceBox<String> elementToInit) {
        List<String> options = new ArrayList<>();
        options.add("2");
        options.add("3");
        options.add("3.5");
        options.add("4");
        options.add("4.5");
        options.add("5");

        elementToInit.getItems().addAll(options);
    }
}
