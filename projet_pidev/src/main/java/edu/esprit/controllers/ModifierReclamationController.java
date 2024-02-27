package edu.esprit.controllers;

import edu.esprit.entities.Reclamation;
import edu.esprit.services.ServiceReclamation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ModifierReclamationController {
    @FXML
    private Label labelFullName;
    @FXML
    private Label labelCin;
    @FXML
    private Label labelCodePub;
    @FXML
    private Label labelNbReports;
    @FXML
    private Label TitleError;
    @FXML
    private Label TypeError;
    @FXML
    private Label DescriptionError;

    @FXML
    private TextField TFType;
    @FXML
    private TextField TFTitre;
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
        //datePicker.setValue(LocalDate.now());
        TADescription.setText(selectedReclamation.getDescription());
        labelFullName.setText(selectedReclamation.getUser().getNom()+" "+selectedReclamation.getUser().getPrenom());
        labelCin.setText(String.valueOf(selectedReclamation.getUser().getCin()));
        labelCodePub.setText(selectedReclamation.getPub().getCode_pub());
        labelNbReports.setText(String.valueOf(selectedReclamation.getPub().getNb_report()));

    }


    /*public void ModifierReclamation(ActionEvent event) {
        // Get the modified values from the input fields
        String newType = TFType.getText();
        String newTitre = TFTitre.getText();
        // LocalDate newDate = datePicker.getValue();  // Uncomment this line if you have a DatePicker
        String newDescription = TADescription.getText();

        // Store the original values
        String originalType = selectedReclamation.getType();
        String originalTitre = selectedReclamation.getTitre();
        // LocalDate originalDate = selectedReclamation.getDate().toLocalDateTime().toLocalDate();
        String originalDescription = selectedReclamation.getDescription();

        LocalDateTime currentDateTime = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(currentDateTime);

        // Regular expression to allow only letters
        String lettersOnlyRegex = "^[a-zA-Z]+$";


        if (selectedReclamation!=null) {

            if (newTitre.trim().isEmpty()){
                TitleError.setText("Please enter a non-empty title");
                TitleError.setTextFill(Color.RED);
                TFTitre.setStyle("-fx-border-color:  #FF0000;");
            }

            if (newType.trim().isEmpty()){
                TypeError.setText("Please enter a non-empty type");
                TypeError.setTextFill(Color.RED);
                TFType.setStyle("-fx-border-color:  #FF0000;");
            }
            if (newDescription.trim().isEmpty()){
                DescriptionError.setText("Please enter a non-empty description");
                DescriptionError.setTextFill(Color.RED);
                TADescription.setStyle("-fx-border-color:  #FF0000;");
            }

            // Check if the newTitre contains only letters
            if (!newTitre.matches(lettersOnlyRegex)) {
                TitleError.setTextFill(Color.RED);
                TFTitre.setStyle("-fx-border-color:  #FF0000;");
                TitleError.setText("Titre should contain only letters");
                return;  // Exit the method if newTitre is invalid
            }

            // Check if the newType contains only letters
            if (!newType.matches(lettersOnlyRegex)) {
                TypeError.setTextFill(Color.RED);
                TFType.setStyle("-fx-border-color:  #FF0000;");
                TypeError.setText("Type should contain only letters");
                return;  // Exit the method if newType is invalid
            }

            // Update the selectedReclamation object with the modified values
            selectedReclamation.setType(newType);
            selectedReclamation.setTitre(newTitre);
            selectedReclamation.setDate(timestamp);
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
    }*/
    public void ModifierReclamation(ActionEvent event) {
        // Get the modified values from the input fields
        String newType = TFType.getText().trim();
        String newTitre = TFTitre.getText().trim();
        // LocalDate newDate = datePicker.getValue();  // Uncomment this line if you have a DatePicker
        String newDescription = TADescription.getText().trim();

        // Regular expression to allow letters and spaces
        String lettersAndSpacesRegex = "^[a-zA-Z\\s]+$";

        // Check if title is not empty
        if (newTitre.isEmpty()) {
            TitleError.setText("Please enter a non-empty title");
            TitleError.setTextFill(Color.RED);
            TFTitre.setStyle("-fx-border-color:  #FF0000;");
        } else if (!newTitre.matches(lettersAndSpacesRegex)) {  // Check if title contains only letters and spaces
            TitleError.setText("Title should contain only letters and spaces");
            TitleError.setTextFill(Color.RED);
            TFTitre.setStyle("-fx-border-color:  #FF0000;");
        } else {
            TitleError.setText("");  // Clear the error text
            TitleError.setTextFill(Color.BLACK);  // Set the text color to black
            TFTitre.setStyle("");  // Reset the border color
        }

        // Check if type is not empty
        if (newType.isEmpty()) {
            TypeError.setText("Please enter a non-empty type");
            TypeError.setTextFill(Color.RED);
            TFType.setStyle("-fx-border-color:  #FF0000;");
        } else if (!newType.matches(lettersAndSpacesRegex)) {  // Check if type contains only letters and spaces
            TypeError.setText("Type should contain only letters and spaces");
            TypeError.setTextFill(Color.RED);
            TFType.setStyle("-fx-border-color:  #FF0000;");
        } else {
            TypeError.setText("");  // Clear the error text
            TypeError.setTextFill(Color.BLACK);  // Set the text color to black
            TFType.setStyle("");  // Reset the border color
        }

        // Check if description is not empty
        if (newDescription.isEmpty()) {
            DescriptionError.setText("Please enter a non-empty description");
            DescriptionError.setTextFill(Color.RED);
            TADescription.setStyle("-fx-border-color:  #FF0000;");
        } else {
            DescriptionError.setText("");  // Clear the error text
            DescriptionError.setTextFill(Color.BLACK);  // Set the text color to black
            TADescription.setStyle("");  // Reset the border color
        }

        // If any of the error labels still have text, return without updating
        if (!TitleError.getText().isEmpty() || !TypeError.getText().isEmpty() || !DescriptionError.getText().isEmpty()) {
            return;
        }

        // Check if no modifications are made
        if (newType.equals(selectedReclamation.getType())
                && newTitre.equals(selectedReclamation.getTitre())
                && newDescription.equals(selectedReclamation.getDescription())) {
            // If no modifications are made, show an information alert
            Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
            infoAlert.setTitle("Information");
            infoAlert.setContentText("No changes were made to the reclamation.");
            infoAlert.show();
            return;
        }

        // Update the selectedReclamation object with the modified values
        selectedReclamation.setType(newType);
        selectedReclamation.setTitre(newTitre);
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
