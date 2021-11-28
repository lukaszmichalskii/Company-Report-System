package com.companyreportsystem.systemlogic.initializators;

import javafx.scene.control.ChoiceBox;

import java.util.ArrayList;
import java.util.List;

public class PositionsInitialization implements ChoiceBoxInitialization {
    public void init(ChoiceBox<String> elementToInit) {
        List<String> positions = new ArrayList<>();
        positions.add("Chief Officer");
        positions.add("Director");
        positions.add("Manager");
        positions.add("Team Leader");
        positions.add("Associate");
        positions.add("Intern");

        elementToInit.getItems().addAll(positions);
    }
}
