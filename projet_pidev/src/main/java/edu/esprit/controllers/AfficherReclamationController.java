package edu.esprit.controllers;

import edu.esprit.entities.CurrentUser;
import edu.esprit.entities.Messagerie;
import edu.esprit.entities.Reclamation;
import edu.esprit.services.ServiceReclamation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Set;

public class AfficherReclamationController implements Initializable {

    private final ServiceReclamation serviceReclamation = new ServiceReclamation();

    @FXML
    private VBox reclamationsContainer;

    // Add the container variable
    @FXML
    private AnchorPane container;
    @FXML
    private AnchorPane RepresentantPane;

    @FXML
    private AnchorPane AdminPane;
    @FXML
    private AnchorPane CandidatPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (CurrentUser.getRole() == 0) {
            // Admin role, so show AdminPane and hide RepresentantPane
            AdminPane.setVisible(true);
            RepresentantPane.setVisible(false);
            CandidatPane.setVisible(false);
        } else if (CurrentUser.getRole()==1){
            // Representant role, so show RepresentantPane and hide AdminPane
            RepresentantPane.setVisible(true);
            AdminPane.setVisible(false);
            CandidatPane.setVisible(false);
        }else {
            CandidatPane.setVisible(true);
            AdminPane.setVisible(false);
            RepresentantPane.setVisible(false);
        }
        // Retrieve data from the database
        try {
            if(CurrentUser.getRole()==0){
                Set<Reclamation> reclamations = serviceReclamation.getAll();
                //Set<Reclamation> reclamations = serviceReclamation.getAllRecByUser(candidat.getId_utilisateur());
                // Load and add ReclamationItemComponent for each Reclamation
                for (Reclamation reclamation : reclamations) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/ReclamationItemComponent.fxml"));
                    try {
                        reclamationsContainer.getChildren().add(loader.load());
                        ReclamationItemComponentController controller = loader.getController();
                        controller.setReclamationData(reclamation, container);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            else {
                //Set<Reclamation> reclamations = serviceReclamation.getAll();
                System.out.println(CurrentUser.getId_utilisateur());
                Set<Reclamation> reclamations = serviceReclamation.getAllRecByUser(CurrentUser.getId_utilisateur());
                // Load and add ReclamationItemComponent for each Reclamation
                for (Reclamation reclamation : reclamations) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/ReclamationItemComponent.fxml"));
                    try {
                        reclamationsContainer.getChildren().add(loader.load());
                        ReclamationItemComponentController controller = loader.getController();
                        controller.setReclamationData(reclamation, container);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void navigateToAddReclamation(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AjouterReclamation.fxml"));
            reclamationsContainer.getScene().setRoot(root);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Sorry");
            alert.setTitle("Error");
            alert.show();
        }
    }



    public void openAiHelper(ActionEvent event) {
        // Create a new stage for the pop-up window
      /*  Stage aiHelperStage = new Stage();
        aiHelperStage.setTitle("OpenAI Helper");

        // Create a VBox to hold any content you want in the popup
        VBox aiHelperVBox = new VBox();

        // Create a scene and set it on the stage
        Scene aiHelperScene = new Scene(aiHelperVBox);
        aiHelperStage.setScene(aiHelperScene);

        // Show the pop-up window
        aiHelperStage.show();*/
         String githubLink = "https://github.com/InnoMinds1/InnoHire";

        try {
            // Create a URI object from the GitHub link
            URI uri = new URI(githubLink);

            // Open the link in the default browser
            Desktop.getDesktop().browse(uri);
        } catch (URISyntaxException | IOException e) {
            // Handle any exceptions that might occur
            e.printStackTrace();
        }
    }
}
