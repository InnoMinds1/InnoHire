package edu.esprit.controllers;

import edu.esprit.entities.Publication;
import edu.esprit.entities.Reclamation;
import edu.esprit.entities.Utilisateur;
import edu.esprit.services.ServiceReclamation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class AjouterReclamationController {
    @FXML
    private TextField TFTitre;
    @FXML
    private TextField TFType;
    @FXML
    private TextArea TADescription;

    private final ServiceReclamation sr = new ServiceReclamation();
    Utilisateur user=new Utilisateur(1,11417264,"dhawadi","hachem","bizerte","123456789");
    Publication pub=new Publication(1,"code",user,"desc","hshtag","seen","image",LocalDate.of(2021,02,4),5);


    public void navigateToAfficherReclamationAction(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AfficherReclamation.fxml"));
            TFTitre.getScene().setRoot(root);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Sorry");
            alert.setTitle("Error");
            alert.show();
        }

    }
    @FXML
    void ajouterReclamationAction(ActionEvent event) {
        String type = TFType.getText();
        String titre = TFTitre.getText();
        String description = TADescription.getText();

        // Get the current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(currentDateTime);

        // Check if any field is empty
        if (type.trim().isEmpty() || titre.trim().isEmpty() || description.trim().isEmpty()) {
            // Show a warning alert if any field is empty
            Alert emptyFieldAlert = new Alert(Alert.AlertType.WARNING);
            emptyFieldAlert.setTitle("Empty Fields");
            emptyFieldAlert.setContentText("Please fill in all fields before adding a reclamation.");
            emptyFieldAlert.showAndWait();
        } else {
            try {
                // Proceed to add the reclamation if all fields are filled
                sr.ajouter(new Reclamation(0, type, titre, description, timestamp, pub, user));

                // Show a success alert
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Success");
                successAlert.setContentText("Your reclamation has been added successfully.");
                successAlert.showAndWait();

                navigateToAfficherReclamationAction(event);
            } catch (SQLException e) {
                // Show an alert for SQL exception
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("SQL Exception");
                errorAlert.setContentText(e.getMessage());
                errorAlert.showAndWait();
            }
        }
    }











}
