package com.companyreportsystem.helpers.initializators;

import javafx.scene.control.ChoiceBox;

import java.util.ArrayList;
import java.util.List;

public class PriorityInitialization implements ChoiceBoxInitialization {
    @Override
    public void init(ChoiceBox<String> choiceBox) {
        List<String> priority = new ArrayList<>();
        priority.add("high");
        priority.add("medium");
        priority.add("low");

        choiceBox.getItems().addAll(priority);
    }
}
