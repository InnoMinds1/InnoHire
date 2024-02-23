package edu.esprit.controllers;

import edu.esprit.entities.Reclamation;
import edu.esprit.services.ServiceReclamation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;

public class ModifierReclamationController {

    @FXML
    private TextField TFType;
    @FXML
    private TextField TFTitre;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextArea TADescription;
    @FXML
    private Button confirmButton;

    private Reclamation selectedReclamation;
    private ServiceReclamation serviceReclamation = new ServiceReclamation();

    public void initData(Reclamation selectedReclamation) {
        // Store the selected Reclamation for later use
        this.selectedReclamation = selectedReclamation;

        // Initialize the fields with data from the selected Reclamation
        TFType.setText(selectedReclamation.getType());
        TFTitre.setText(selectedReclamation.getTitre());
        datePicker.setValue(LocalDate.now());
        TADescription.setText(selectedReclamation.getDescription());

    }


    public void ModifierReclamation(ActionEvent event) {

        // Get the modified values from the input fields
        String newType = TFType.getText();
        String newTitre = TFTitre.getText();
        LocalDate newDate = datePicker.getValue();
        String newDescription = TADescription.getText();

        // Store the original values
        String originalType = selectedReclamation.getType();
        String originalTitre = selectedReclamation.getTitre();
        //LocalDate originalDate = selectedReclamation.getDate().toLocalDateTime().toLocalDate();
        String originalDescription = selectedReclamation.getDescription();

        // Check if any field is modified
        boolean isModified = !newType.equals(originalType) ||
                !newTitre.equals(originalTitre) ||
                //!newDate.equals(originalDate) ||
                !newDescription.equals(originalDescription);

        if (isModified) {
            // Update the selectedReclamation object with the modified values
            selectedReclamation.setType(newType);
            selectedReclamation.setTitre(newTitre);
            selectedReclamation.setDate(Timestamp.valueOf(newDate.atStartOfDay()));
            selectedReclamation.setDescription(newDescription);

            try {
                // Call the update method from ServiceReclamation to update the record in the database
                serviceReclamation.modifier(selectedReclamation);

                // Show success alert
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Success");
                successAlert.setContentText("Reclamation updated successfully!");
                successAlert.show();
                navigateToAfficherReclamationAction(event);
            } catch (SQLException e) {
                // Handle any SQL exception that might occur during the update
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("SQL Exception");
                errorAlert.setContentText(e.getMessage());
                errorAlert.showAndWait();
            }
        } else {
            // If no modifications are made, show an information alert
            Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
            infoAlert.setTitle("Information");
            infoAlert.setContentText("No changes were made to the reclamation.");
            infoAlert.show();
        }
    }

    public void navigateToAfficherReclamationAction(ActionEvent event) {
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
}
