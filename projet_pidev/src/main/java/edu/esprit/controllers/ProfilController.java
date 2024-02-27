package edu.esprit.controllers;
import edu.esprit.entities.*;
import edu.esprit.services.ServiceUtilisateur;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProfilController implements Initializable {
    @FXML
    private TextField TFadresse;

    @FXML
    private TextField TFcin;

    @FXML
    private TextField TFmdp;

    @FXML
    private TextField TFnom;

    @FXML
    private TextField TFprenom;

    @FXML
    private TextField TFrole;

    @FXML
    private ImageView profileImageView;

    @FXML
    private Label tfnom_init;
    ServiceUtilisateur serviceUtilisateur = new ServiceUtilisateur();
    private File selectedFile;
    @FXML
    void choosePhoto(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );

        // Afficher la boîte de dialogue de choix de fichier
        selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            // Charger l'image sélectionnée dans ImageView
            Image selectedImage = new Image(selectedFile.toURI().toString());
            profileImageView.setImage(selectedImage);
        }
    }

    public static void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public boolean controlSaisie(TextField field) {
        if (field.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Champ vide", "Veuillez remplir tous les champs.");
            return false;
        }
        return true;
    }

    @FXML
    void RetourAccueil(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/Accueil.fxml"));
            TFadresse.getScene().setRoot(root);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Sorry");
            alert.setTitle("Error");
            alert.show();
        }

    }




    @FXML
    void ok(ActionEvent event) throws SQLException {
        String photoUrl = (selectedFile != null) ? selectedFile.toURI().toString() : null;
        int role = Integer.parseInt(TFrole.getText());
        if (controlSaisie(TFnom) && controlSaisie(TFprenom) && controlSaisie(TFadresse) ){
            Utilisateur u = new Utilisateur();
            u.setCin(Integer.parseInt(TFcin.getText()));
            u.setNom(TFnom.getText());
            u.setPrenom(TFprenom.getText());
            u.setAdresse(TFadresse.getText());
            u.setImage(photoUrl);



            String Nom = TFnom.getText();
            String prenom = TFprenom.getText();
            String adresse = TFadresse.getText();


            // Vérifier si les champs requis sont vides
            if (Nom.isEmpty() || prenom.isEmpty() || adresse.isEmpty() ) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Tous les champs sont obligatoires !");
                alert.showAndWait();
                return;
            }
            if (Nom.matches(".*[\\d\\W].*")) {
                // Nom contains digits or symbols
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Le champ 'Nom' ne doit pas contenir de chiffres ou de symboles !");
                alert.showAndWait();
                return;
            }

            if (prenom.matches(".*[\\d\\W].*")) {
                // Prenom contains digits or symbols
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Le champ 'Prenom' ne doit pas contenir de chiffres ou de symboles !");
                alert.showAndWait();
                return;
            }
            String emailPattern = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,6}$";
            Pattern pattern = Pattern.compile(emailPattern);
            Matcher matcher = pattern.matcher(adresse);

            if (!matcher.matches()) {
                // Invalid email format
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Adresse email invalide !");
                alert.showAndWait();
                return;
            }

            serviceUtilisateur.modifier_par_cin_sansmdp(u);
            serviceUtilisateur.modifier_Image(u,photoUrl);

            showAlert(Alert.AlertType.INFORMATION, "Succès", "Utilisateur modifié avec succès");  // Corrected line
            CurrentUser.setNom(u.getNom());
            CurrentUser.setPrenom(u.getPrenom());
            CurrentUser.setAdresse(u.getAdresse());
            tfnom_init.setText(CurrentUser.getNom());

        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TFcin.setText(String.valueOf(CurrentUser.getCin()));
        TFadresse.setText(CurrentUser.getAdresse());
        TFnom.setText(CurrentUser.getNom());
        TFprenom.setText(CurrentUser.getPrenom());
        TFrole.setText(String.valueOf(CurrentUser.getRole()));
        tfnom_init.setText(CurrentUser.getNom());
        String imagePath = CurrentUser.getProfileImagePath();  // Change this to the actual method to get the image path
        if (imagePath != null && !imagePath.isEmpty()) {
            Image image = new Image("file:" + imagePath); // Assuming imagePath is a file path
            profileImageView.setImage(image);
        }
    }
    @FXML
    void changeMDP(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/ChangeMDP.fxml"));
            TFadresse.getScene().setRoot(root);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Sorry");
            alert.setTitle("Error");
            alert.show();
        }


    }

    }

