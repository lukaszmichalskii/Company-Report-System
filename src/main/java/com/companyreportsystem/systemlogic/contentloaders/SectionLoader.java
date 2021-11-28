package com.companyreportsystem.systemlogic.contentloaders;

import com.companyreportsystem.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

/**
 * Class responsible for load content of desired panel
 */
public class SectionLoader {
    public static Object load(String GUIFormFilepath, BorderPane borderPane) {
        Parent root = null;
        FXMLLoader fxmlLoader = null;
        try {
            fxmlLoader = new FXMLLoader(Main.class.getResource(GUIFormFilepath));
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        borderPane.setCenter(root);

        return fxmlLoader.getController();
    }
}
