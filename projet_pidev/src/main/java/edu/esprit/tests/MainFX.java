/*package edu.esprit.tests;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainFX extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterReclamation.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Gestion Reclamation");
        // Set the width and height of the Stage
        stage.setWidth(1000); // Set your desired width
        stage.setHeight(660); // Set your desired height
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
*/
package edu.esprit.tests;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MainFX extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherReclamation.fxml"));
        //AfficherReclamationCandidat
        //AfficherReclamation
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Gestion Reclamation");

        // Set the width and height of the Stage to the maximum values
        maximizeStage(stage);

        stage.show();
    }

    private void maximizeStage(Stage stage) {
        // Get the primary screen
        Screen screen = Screen.getPrimary();

        // Set the width and height of the Stage to the screen dimensions
        stage.setWidth(screen.getVisualBounds().getWidth());
        stage.setHeight(screen.getVisualBounds().getHeight());
    }

    public static void main(String[] args) {
        launch();
    }
}
