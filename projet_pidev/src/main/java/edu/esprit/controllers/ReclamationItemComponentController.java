package edu.esprit.controllers;

import edu.esprit.entities.Reclamation;
import edu.esprit.services.ServiceReclamation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

public class ReclamationItemComponentController {

    @FXML
    private ImageView userPhoto;

    @FXML
    private Label userFullName;

    @FXML
    private Label userCode;

    @FXML
    private Label dateRec;

    ServiceReclamation serviceReclamation = new ServiceReclamation();

    // Reference to the Reclamation object
    private Reclamation reclamation;

    // Reference to the container (parent) HBox
    private AnchorPane container;

    // Create a method to set data from Reclamation object
    public void setReclamationData(Reclamation reclamation, AnchorPane container) {
        this.reclamation = reclamation;
        this.container = container;

        // Set data to UI elements
        userFullName.setText(reclamation.getUser().getNom() + " " + reclamation.getUser().getPrenom());
        //userCode.setText(reclamation.getPub().getCode_pub());
        dateRec.setText(String.valueOf(reclamation.getDate()));
    }


    public void navigateToAfficherReclamationAction(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AfficherReclamation.fxml"));
            userCode.getScene().setRoot(root);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Sorry");
            alert.setTitle("Error");
            alert.show();
        }

    }

    @FXML
    public void deleteReclamationAction(ActionEvent event) {
        // Display a confirmation dialog before deleting
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this reclamation?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            // Perform deletion logic here
            try {
                // Delete the reclamation from the database
                serviceReclamation.supprimer(reclamation.getId_reclamation());

                // Remove the HBox from the parent container
                container.getChildren().remove(container);

                System.out.println("Reclamation deleted successfully!");
                navigateToAfficherReclamationAction(event);
            } catch (SQLException e) {
                // Handle any potential exceptions (e.g., database connection issues)
                e.printStackTrace();
                // You may want to display an error message to the user
                // or log the error for further investigation
            }
        }
    }

    public void navigateToChatRec(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AjouterAfficherMessage.fxml"));
            userCode.getScene().setRoot(root);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Sorry");
            alert.setTitle("Error");
            alert.show();
        }
    }

    public void DetailsReclamationAction(ActionEvent event) {
        try {
            // Load the DetailsReclamation.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierReclamation.fxml"));
            Parent root = loader.load();

            // Get the controller for the DetailsReclamation page
            ModifierReclamationController detailsController = loader.getController();

            // Pass the selected reclamation data to the DetailsReclamationController
            detailsController.initData(reclamation);

            // Set the root of the scene to the DetailsReclamation page
            userCode.getScene().setRoot(root);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Sorry");
            alert.setTitle("Error");
            alert.show();
        }
    }

}
