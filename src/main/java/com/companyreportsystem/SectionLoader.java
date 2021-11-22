package com.companyreportsystem;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Objects;

public class SectionLoader {

    public static void load(String GUIFormFilepath, BorderPane borderPane) {
        Parent root = null;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(GUIFormFilepath)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        borderPane.setCenter(root);
    }
}
