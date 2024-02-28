package edu.esprit.controllers;
import edu.esprit.entities.Admin;
import edu.esprit.entities.Candidat;
import edu.esprit.entities.Representant;
import edu.esprit.entities.Utilisateur;
import edu.esprit.services.ServiceUtilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AjouterUtilisateurController {


    /* @FXML
    private TextField TFcin;

    @FXML
    private TextField TFcin_supprimer;

    @FXML
    private TextField TFemail;

    @FXML
    private TextField TFmdp;

    @FXML
    private TextField TFnom;

    @FXML
    private TextField TFprenom;

    @FXML
    private TextField TFrole;


    private final ServiceUtilisateur sp = new ServiceUtilisateur();

    @FXML
    void ajouterUtilisateurAction(ActionEvent event) {
        try {
            int cin = Integer.parseInt(TFcin.getText());
            int role = Integer.parseInt(TFrole.getText());
            String nom= TFnom.getText();
            String prenom = TFprenom.getText();
            String adresse = TFemail.getText();
            String mdp = TFmdp.getText();
            if(sp.utilisateurExiste(cin))
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("SQL Exception");
                alert.setContentText("L'UTILISATEUR AVEC CE CIN EXISTE DEJA");
                alert.showAndWait();
            }
            else {
                if (role==0)
                {
                    Admin a = new Admin(cin,nom,prenom,adresse,mdp);
                    sp.ajouter(a);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setContentText("GG Admin ADDED");
                    alert.show();


                }
                else if (role == 1) {
                    Representant r = new Representant(cin, nom, prenom, adresse, mdp);

                    sp.ajouter(r);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setContentText("GG REPRESENTANT ADDED");
                    alert.show();
                } else if (role == 2) {
                    Candidat c = new Candidat(cin, nom, prenom, adresse, mdp);
                    sp.ajouter(c);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setContentText("GG CANDIDAT ADDED");
                    alert.show();

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("IL Y A TROIS ROLE 0 pour ADMIN et 1 pour REPRESENTANT ET 2 pour candidat");
                    alert.showAndWait();
                }
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("SQL Exception");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }


    }
    @FXML
    void navigatetoAfficherUtilisateurAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AfficherUtilisateur.fxml"));
            TFnom.getScene().setRoot(root);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Sorry");
            alert.setTitle("Error");
            alert.show();
        }


    }
    @FXML
    void supprimerUtilisateurAction(ActionEvent event) {
        int cin = Integer.parseInt(TFcin_supprimer.getText());
        if (!sp.utilisateurExiste(cin))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("USER AVEC CE CIN N'EXISTE PAS");
            alert.showAndWait();
        }
        else
        {
            try {
                sp.supprimer_par_cin(cin);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("GG USER SUPPRIMEE");
                alert.show();

            } catch (SQLException e) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("erreur");
                alert.showAndWait();
            }

        }


    }
    @FXML
    void modifierUtilisateurAction(ActionEvent event) {

        int cin = Integer.parseInt(TFcin.getText());
        int role = Integer.parseInt(TFrole.getText());
        String nom= TFnom.getText();
        String prenom = TFprenom.getText();
        String adresse = TFemail.getText();
        String mdp = TFmdp.getText();
        if (!(sp.adminExiste(cin))&&!(sp.utilisateurExiste(cin)))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("il n y a personne avec ce cin");
            alert.showAndWait();
        }
        if (sp.adminExiste(cin)&&role!=0)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("ON NE PEUX PAS CHANGER LE ROLE D'UN ADMIN");
            alert.showAndWait();
        }
        else if (sp.adminExiste(cin)&&role==0)
        {
            Admin a = new Admin(cin,nom,prenom,adresse,mdp);
            try {
                sp.modifier_admin_parcin(a);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("GG ADMIN MODIFIEE");
                alert.show();
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("sql error");
                alert.showAndWait();
            }

        }



        else if(sp.utilisateurExiste(cin)&&role==1)
        {
        Representant r= new Representant(cin,nom,prenom,adresse,mdp);
            try {
                sp.modifier_representant_parcin(r);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("GG REPRESENTANT MODIFIEE");
                alert.show();
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("sql error");
                alert.showAndWait();
            }
        }
        else if(sp.utilisateurExiste(cin)&&role==2)
        {
            Candidat c= new Candidat(cin,nom,prenom,adresse,mdp);
            try {
                sp.modifier_candidat_parcin(c);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("GG Candidat MODIFIEE");
                alert.show();

            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("sql error");
                alert.showAndWait();
            }

        }


    }
*/
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
    private TextField tfmdp;

    @FXML
    private TextField tfrole;
    @FXML
    private ImageView profileImageView;
    @FXML
    private ComboBox<?> comboRole;

    @FXML
    private Button choosePhotoButton;

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


    @FXML
    void ajouterUtilisateurAction(ActionEvent event) {
        // Créer une instance de ServiceService
        String photoUrl = (selectedFile != null) ? selectedFile.toURI().toString() : null;

        ServiceUtilisateur serviceUtilisateur = new ServiceUtilisateur();

            int cin;

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
        String role = (String) comboRole.getSelectionModel().getSelectedItem();

        // Validate if a role is selected

        if (serviceUtilisateur.utilisateurExiste(cin))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Le cin doit etre unique");
            alert.showAndWait();
            return;

        }
        if (photoUrl==null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("veillez inserer une photo");
            alert.showAndWait();
            return;
        }

       if(role==null){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un rôle !");
            alert.showAndWait();
            return;

        }

        // Créer un nouvel objet Service avec les valeurs saisies
        if (role.equals("Admin"))
        {Admin u = new Admin();
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
                alert.setContentText("Admin ajouté avec succès !");
                alert.showAndWait();

                // Effacer les champs du formulaire après l'ajout réussi
                TFcin.clear();
                TFprenom.clear();
                TFnom.clear();
                TFadresse.clear();
                tfmdp.clear();
                tfrole.clear();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Erreur lors de l'ajout d'Utilisateur' : " + e.getMessage());
                alert.showAndWait();
            }
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
                alert.setContentText("Representant ajouté avec succès !");
                alert.showAndWait();

                // Effacer les champs du formulaire après l'ajout réussi
                TFcin.clear();
                TFprenom.clear();
                TFnom.clear();
                TFadresse.clear();
                tfmdp.clear();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Erreur lors de l'ajout d'Utilisateur' : " + e.getMessage());
                alert.showAndWait();
            }


        }
        else if (role.equals("Candidat")){
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
                    tfrole.clear();
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Erreur lors de l'ajout d'Utilisateur' : " + e.getMessage());
                    alert.showAndWait();
                }
            }
        }





        // Ajouter le service à la base de données

    }

    @FXML
    void navigatetoAfficherUtilisateurAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherUtilisateur.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) TFnom.getScene().getWindow(); // Utilisez la même fenêtre (Stage) actuelle
            stage.setScene(new Scene(root));
            stage.show();

            // Vous pouvez fermer la fenêtre actuelle si nécessaire
            // ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    }






