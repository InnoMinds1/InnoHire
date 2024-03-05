package edu.esprit.tests;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainFX extends Application {
   // public static final String CURRENCY = "$";
    @Override
    public void start(Stage stage) throws Exception {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login.fxml"));

        Parent root = loader.load();




        Scene scene = new Scene(root);

// Set the scene to the stage
        stage.setScene(scene);

        stage.setTitle("Login");



    }


    public static void main(String[] args) {
        launch();
    }
}
