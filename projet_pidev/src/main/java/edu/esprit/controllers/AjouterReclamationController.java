package edu.esprit.controllers;

import edu.esprit.entities.*;
import edu.esprit.services.ServiceReclamation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class AjouterReclamationController {
    @FXML
    private TextField TFTitre;
    @FXML
    private TextField TFType;
    @FXML
    private TextArea TADescription;
    @FXML
    private Label TitleError;
    @FXML
    private Label TypeError;
    @FXML
    private Label DescriptionError;


    private final ServiceReclamation sr = new ServiceReclamation();
    private final ServiceUtilisateur su = new ServiceUtilisateur();
    Utilisateur userSender = su.getOneByID(CurrentUser.getId_utilisateur());
    Utilisateur user=new Utilisateur(1,11417264,"dhawadi","hachem","bizerte","123456789","edit.png");
    //Post pub=new Post(1,"code",user,"desc","hshtag","seen","image",LocalDate.of(2021,02,4),5);
    Post pub = new Post(1,userSender
,PostAudience.PUBLIC,"2024-03-02 13:29:57","Fk off","blog.png",15,15,15);


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
   /* @FXML
    void ajouterReclamationAction(ActionEvent event) {
        String type = TFType.getText().trim();
        String titre = TFTitre.getText().trim();
        String description = TADescription.getText().trim();

        // Get the current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(currentDateTime);

        // Regular expression to allow only letters
        String lettersOnlyRegex = "^[a-zA-Z]+$";

        // Check if any field is empty
        if (type.isEmpty() || titre.isEmpty() || description.isEmpty()) {
            // Show a warning alert if any field is empty
            Alert emptyFieldAlert = new Alert(Alert.AlertType.WARNING);
            emptyFieldAlert.setTitle("Empty Fields");
            emptyFieldAlert.setContentText("Please fill in all fields before adding a reclamation.");
            emptyFieldAlert.showAndWait();
        } else {
            // Check if type contains only letters
            if (!type.matches(lettersOnlyRegex)) {
                // Show a warning alert if type contains symbols or numbers
                Alert typeAlert = new Alert(Alert.AlertType.WARNING);
                typeAlert.setTitle("Invalid Type");
                typeAlert.setContentText("Type should contain only letters.");
                typeAlert.showAndWait();
            }
            // Check if titre contains only letters
            else if (!titre.matches(lettersOnlyRegex)) {
                // Show a warning alert if titre contains symbols or numbers
                Alert titreAlert = new Alert(Alert.AlertType.WARNING);
                titreAlert.setTitle("Invalid Titre");
                titreAlert.setContentText("Titre should contain only letters.");
                titreAlert.showAndWait();
            } else {
                try {
                    // Proceed to add the reclamation if all fields are filled and type/titre are valid
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
    }*/

    @FXML
    void ajouterReclamationAction(ActionEvent event) {
        String type = TFType.getText().trim();
        String titre = TFTitre.getText().trim();
        String description = TADescription.getText().trim();

        // Get the current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(currentDateTime);

        // Regular expression to allow only letters
        String lettersOnlyRegex = "^[a-zA-Z]+$";

        // Regular expression to allow letters and spaces
        String lettersAndSpacesRegex = "^[a-zA-Z\\s]+$";

        // Check if title is not empty
        if (titre.isEmpty()) {
            TitleError.setText("Please enter a non-empty title");
            TitleError.setTextFill(javafx.scene.paint.Color.RED);
            TFTitre.setStyle("-fx-border-color:  #FF0000;");
        } else if (!titre.matches(lettersAndSpacesRegex)) {  // Check if title contains only letters and spaces
            TitleError.setText("Title should contain only letters and spaces");
            TitleError.setTextFill(javafx.scene.paint.Color.RED);
            TFTitre.setStyle("-fx-border-color:  #FF0000;");
        } else {
            TitleError.setText("");  // Clear the error text
            TitleError.setTextFill(Color.BLACK);  // Set the text color to black
            TFTitre.setStyle("");  // Reset the border color
        }

        // Check if type is not empty
        if (type.isEmpty()) {
            TypeError.setText("Please enter a non-empty type");
            TypeError.setTextFill(Color.RED);
            TFType.setStyle("-fx-border-color:  #FF0000;");
        } else if (!type.matches(lettersAndSpacesRegex)) {  // Check if type contains only letters and spaces
            TypeError.setText("Type should contain only letters and spaces");
            TypeError.setTextFill(Color.RED);
            TFType.setStyle("-fx-border-color:  #FF0000;");
        } else {
            TypeError.setText("");  // Clear the error text
            TypeError.setTextFill(Color.BLACK);  // Set the text color to black
            TFType.setStyle("");  // Reset the border color
        }

        // Check if description is not empty
        if (description.isEmpty()) {
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

        try {
            // Call the update method from ServiceReclamation to update the record in the database
            sr.ajouter(new Reclamation(0, type, titre, description, timestamp, pub, userSender
));

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


}
