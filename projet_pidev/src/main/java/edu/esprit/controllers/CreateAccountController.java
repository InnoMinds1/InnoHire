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
import javafx.util.Callback;

import javax.swing.plaf.ColorUIResource;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateAccountController {
    private File selectedFile;

    @FXML
    private TextField TFadresse;

    @FXML
    private TextField TFcin;

    @FXML
    private TextField TFnom;

    @FXML
    private TextField TFprenom;

    @FXML
    private ImageView profileImageView;


    @FXML
    private TextField tfmdp;

    @FXML
    private TextField tfrole;
    @FXML
    private ComboBox<?> comboRole;

    @FXML
    void ajouterUtilisateurAction(ActionEvent event) {
        // Créer une instance de ServiceService
        String photoUrl = (selectedFile != null) ? selectedFile.toURI().toString() : null;

        ServiceUtilisateur serviceUtilisateur = new ServiceUtilisateur();

        int cin;
       // int role;
        String Nom = TFnom.getText();
        String prenom = TFprenom.getText();
        String adresse = TFadresse.getText();
        String mdp = tfmdp.getText();

        if (Nom.isEmpty() || prenom.isEmpty() || adresse.isEmpty() || mdp.isEmpty() ) {
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
        if (photoUrl==null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("AJOUTER PHOTO !");
            alert.showAndWait();
            return;
        }



        try {
            cin = Integer.parseInt(TFcin.getText());


        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Le cin et le role doivent etre être un nombre valide !");
            alert.showAndWait();
            return;
        }
        if(cin<0)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Le cin  doivent etre être un nombre valide !");
            alert.showAndWait();
            return;
        }


        if (serviceUtilisateur.utilisateurExiste(cin))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Le cin doit etre unique");
            alert.showAndWait();
            return;

        }
        String role = (String) comboRole.getSelectionModel().getSelectedItem();





        // Créer un nouvel objet Service avec les valeurs saisies
        if (role==null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("veillez choisir un role");
            alert.showAndWait();
        }
        else if(role.equals("Representant"))
        {Representant u = new Representant();
            u.setNom(Nom);
            u.setCin(cin);
            u.setMdp(mdp);
            u.setAdresse(adresse);
            u.setPrenom(prenom);
            u.setImage(photoUrl);
            try {
                serviceUtilisateur.ajouter(u);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Succès");
                alert.setHeaderText(null);
                alert.setContentText("Representant ajouté avec succès.Veillez ajouter une société !");
                alert.showAndWait();
                CurrentUser.setCin(Integer.parseInt(TFcin.getText()));
                CurrentUser.setId_utilisateur(serviceUtilisateur.getUserIdByCin(CurrentUser.getCin()));


                        // Effacer les champs du formulaire après l'ajout réussi
                TFcin.clear();
                TFprenom.clear();
                TFnom.clear();
                TFadresse.clear();
                tfmdp.clear();

                Parent root = FXMLLoader.load(getClass().getResource("/AjouterEtablissementAfterCreate.fxml"));
                TFadresse.getScene().setRoot(root);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Erreur lors de l'ajout d'Utilisateur' : " + e.getMessage());
                alert.showAndWait();


            }


        }
        else if (role .equals("Candidat")){
            {Candidat u = new Candidat();
                u.setNom(Nom);
                u.setCin(cin);
                u.setMdp(mdp);
                u.setAdresse(adresse);
                u.setPrenom(prenom);
                u.setImage(photoUrl);
                try {
                    serviceUtilisateur.ajouter(u);
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Succès");
                    alert.setHeaderText(null);
                    alert.setContentText("Candidat ajouté avec succès !");
                    alert.showAndWait();

                    // Effacer les champs du formulaire après l'ajout réussi
                    TFcin.clear();
                    TFprenom.clear();
                    TFnom.clear();
                    TFadresse.clear();
                    tfmdp.clear();
                    CurrentUser.setCin(u.getCin());
                    CurrentUser.setNom(u.getNom());
                    CurrentUser.setPrenom(u.getPrenom());
                    CurrentUser.setAdresse(u.getAdresse());
                    CurrentUser.setMdp(u.getMdp());
                    CurrentUser.setRole(2);
                    Parent root = FXMLLoader.load(getClass().getResource("/Accueil.fxml"));
                    TFadresse.getScene().setRoot(root);

                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Erreur lors de l'ajout d'Utilisateur' : " + e.getMessage());
                    alert.showAndWait();
                }
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("ROLE DOIT ETRE  1 pour Rep et 2 pour candidat ");
            alert.showAndWait();
        }




        // Ajouter le service à la base de données

    }

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


}
