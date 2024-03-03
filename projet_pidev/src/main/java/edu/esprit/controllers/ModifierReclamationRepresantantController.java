package edu.esprit.controllers;

import edu.esprit.entities.Reclamation;
import edu.esprit.services.ServiceReclamation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.sql.SQLException;

public class ModifierReclamationRepresantantController {
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
    private ImageView userPhoto;
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
        labelFullName.setText(selectedReclamation.getPub().getUtilisateur().getNom() + " " + selectedReclamation.getPub().getUtilisateur().getPrenom());
        labelCin.setText(String.valueOf(selectedReclamation.getUser().getCin()));
        //labelCodePub.setText(selectedReclamation.getPub().getCode_pub());
       // labelNbReports.setText(String.valueOf(selectedReclamation.getPub().getNb_report()));
        // Set user photo
        String imageName = selectedReclamation.getPub().getUtilisateur().getImage();
        System.out.println(imageName);// Replace with the actual method to get the image name
        if (imageName != null && !imageName.isEmpty()) {
            String imagePath = "/images/" + imageName; // Assuming images are stored in src/main/resources/images
            Image image = new Image(getClass().getResource(imagePath).toExternalForm());
            userPhoto.setImage(image);
        } else {
            // Set a default image if the name is not available
            userPhoto.setImage(new Image(getClass().getResource("/images/edit.png.jpg").toExternalForm()));
        }

    }

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
            Parent root = FXMLLoader.load(getClass().getResource("/AfficherReclamationRepresentant.fxml"));
            TFTitre.getScene().setRoot(root);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Sorry");
            alert.setTitle("Error");
            alert.show();
        }
    }
}
