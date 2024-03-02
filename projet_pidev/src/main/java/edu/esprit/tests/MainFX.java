package edu.esprit.tests;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainFX extends Application {
   // public static final String CURRENCY = "$";
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Etablissement.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 500, 500); // Set width and height as per your requirement
        stage.setScene(scene);
        stage.setTitle("Gestion Etablissement");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
