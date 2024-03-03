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
        Scene scene = new Scene(root);

        // Set full screen
        stage.setFullScreen(true);

        // Set the scene to the stage
        stage.setScene(scene);

        // Set the title
        stage.setTitle("Gestion Etablissement");

        // Show the stage
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}
