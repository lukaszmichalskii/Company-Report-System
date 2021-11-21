package com.companyreportsystem;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SceneCreator {
    public static void createScene(String GUIFormFilepath, final int WIDTH, final int HEIGHT) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(GUIFormFilepath));
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}