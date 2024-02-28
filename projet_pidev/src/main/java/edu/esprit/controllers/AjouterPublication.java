package edu.esprit.controllers;

import edu.esprit.entities.*;
import edu.esprit.services.ServicePost;
import edu.esprit.services.ServicePublication;
import edu.esprit.services.ServiceUtilisateur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.Image;


import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class AjouterPublication  {



    @FXML
    private TextField TFaudience;

    @FXML
    private TextField TFcaption;

    @FXML
    private TextField TFdate;

    @FXML
    private TextField TFnomuser;

    @FXML
    private Button button;

    @FXML
    private TextField imageETF;

    @FXML
    private ImageView imgPost;

    private final ServicePost sp=new ServicePost();
    private final ServiceUtilisateur  su=new ServiceUtilisateur();

    @FXML
    void ajouterPublicationAction(ActionEvent event) {
        try {
            LocalDate dateActuelle = LocalDate.now();

            // Vérifier la conformité de la saisie pour le champ audience
            String audienceText = TFaudience.getText().toUpperCase();
            if (!audienceText.equals("PUBLIC") && !audienceText.equals("FRIENDS")) {
                throw new IllegalArgumentException("Le champ audience doit être 'PUBLIC' ou 'FRIENDS'.");
            }

            // Vérifier la conformité de la saisie pour le champ caption
            String captionText = TFcaption.getText();
            if (captionText.length() > 20) {
                throw new IllegalArgumentException("Le champ caption doit contenir moins de 20 lettres.");
            }

            String currentDir = System.getProperty("user.dir");
            String imagePath = currentDir + "/src/main/resources/img/" + imageETF.getText();
            File imageFile = new File(imagePath);
            if (!imageFile.exists()) {
                // Afficher une alerte si l'image n'existe pas
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Erreur : L'image spécifiée n'existe pas !");
                alert.showAndWait();
                return;
            }
String imageBDD= "/img/"+imageETF.getText();

            int idUtilisateur = CurrentUser.getId_utilisateur();
            Utilisateur utilisateur = su.getOneByID(idUtilisateur);
            Post post = new Post(PostAudience.valueOf(audienceText), captionText, imageBDD);
            post.setUtilisateur(utilisateur);
            post.setTotalReactions(0);
            post.setNbComments(0);
            post.setNbShares(0);
            post.setDate(String.valueOf(dateActuelle));

            sp.ajouter(post);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Publication ajoutée avec succès.");
            alert.show();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("SQL Exception");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (IllegalArgumentException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }


    /*void ajouterPublicationAction(ActionEvent event) {
        try {
            LocalDate dateActuelle = LocalDate.now();

            // Convertir la chaîne de texte de la date en LocalDate
            //String dateString = TFdate.getText();
            //LocalDate datePost = LocalDate.parse(dateString);

            int idUtilisateur = CurrentUser.getId_utilisateur();
            Utilisateur utilisateur = su.getOneByID(idUtilisateur);
          Post post = new Post(PostAudience.valueOf(TFaudience.getText()),TFcaption.getText(),imageETF.getText());
            //post.setDate(String.valueOf(datePost));
            post.setUtilisateur(utilisateur);
            post.setTotalReactions(0);
            post.setNbComments(0);
            post.setNbShares(0);
            post.setDate(String.valueOf(dateActuelle));
            //
            sp.ajouter(post);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("GG");
            alert.show();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("SQL Exception");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

    }*/

    public void navigatetoAfficherPublicationAction(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Pub.fxml"));
            TFcaption.getScene().setRoot(root);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Sorry jjjj");
            alert.setTitle("Error");
            alert.show();
        }

    }




    public void importImage(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");

        // Set the initial directory to the img folder in the resources
        String currentDir = System.getProperty("user.dir");
        fileChooser.setInitialDirectory(new File(currentDir + "/src/main/resources/img"));

        // Set the file extension filters if needed (e.g., for images)
        FileChooser.ExtensionFilter imageFilter =
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif");
        fileChooser.getExtensionFilters().add(imageFilter);

        // Show the file chooser dialog
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
            // The user selected a file, you can handle it here
            String imagePath = selectedFile.toURI().toString();

            // Set the image file name to the TextField
            imageETF.setText(selectedFile.getName());

            // Display the selected image on the ImageView
            imgPost.setImage(new Image(imagePath));

            System.out.println("Selected Image: " + imagePath);
        } else {
            // The user canceled the operation
            System.out.println("Operation canceled.");
        }
    }

}


