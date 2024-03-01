package edu.esprit.controllers;

import edu.esprit.entities.Commentaire;
import edu.esprit.entities.Utilisateur;
import edu.esprit.services.ServiceCommentaire;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import static edu.esprit.controllers.ModifierPublication.showAlert;

public class ModifierCommentaire {
    @FXML
    private DatePicker dateTf1;

    @FXML
    private TextField descriptionTF1;

    @FXML
    private Button modifierCommentaire;

    @FXML
    private TextField ratingTF1;
    @FXML
    private Button AfficherCommentaire;
    int id;
    Utilisateur utilisateur;

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }




    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public boolean controlSaisie(TextField field) {
        if (field.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Champ vide", "Veuillez remplir tous les champs.");
            return false;
        }
        return true;
    }

    @FXML
   /* void modifierCommentaire(ActionEvent event) throws SQLException {
        if (controlSaisie(descriptionTF1) && controlSaisie(ratingTF1)) {
            Commentaire c = new Commentaire();
            c.setId_commentaire(getId());
            c.setDescription_co(descriptionTF1.getText());
            c.setDate_co(dateTf1.getValue());
            c.setNb_etoile(Integer.parseInt(ratingTF1.getText()));
            ServiceCommentaire sc = new ServiceCommentaire();
            // Appel de la méthode pour effectuer la modification dans la base de données
            sc.modifier(c);

            showAlert(Alert.AlertType.INFORMATION, "Succès", "Commentaire modifiée avec succès");
            // Assurez-vous d'ajuster le code pour afficher les publications après la modification
            AfficherCommentaire(event);
        }
    }*/

       void modifierCommentaire(ActionEvent event) throws SQLException {
        if (controlSaisie(descriptionTF1) && controlSaisie(ratingTF1)) {
            String description_co = descriptionTF1.getText();
            String nb_etoile = ratingTF1.getText();

            // Vérification de la longueur de la description
            if (description_co.split("\\s+").length > 50) {
                showAlert(Alert.AlertType.WARNING, "Erreur de saisie", "La description ne doit pas dépasser 50 mots.");
                return;
            }

            // Vérification du nombre d'étoiles
            try {
                int nbEtoiles = Integer.parseInt(nb_etoile);
                if (nbEtoiles < 1 || nbEtoiles > 5) {
                    showAlert(Alert.AlertType.WARNING, "Erreur de saisie", "Le nombre d'étoiles doit être compris entre 1 et 5.");
                    return;
                }
            } catch (NumberFormatException e) {
                showAlert(Alert.AlertType.WARNING, "Erreur de saisie", "Veuillez entrer un nombre valide pour les étoiles.");
                return;
            }

            Commentaire c = new Commentaire();
            c.setId_commentaire(getId());
            c.setDescription_co(description_co);
            c.setDate_co(dateTf1.getValue());
            c.setNb_etoile(Integer.parseInt(nb_etoile));
            ServiceCommentaire sc = new ServiceCommentaire();
            // Appel de la méthode pour effectuer la modification dans la base de données
            sc.modifier(c);

            showAlert(Alert.AlertType.INFORMATION, "Succès", "Commentaire modifié avec succès");
            // Assurez-vous d'ajuster le code pour afficher les publications après la modification
            AfficherCommentaire(event);
        }
    }


    public void initData(Commentaire commentaire) {
        if (commentaire != null) {
            setId(commentaire.getId_commentaire());

            descriptionTF1.setText(commentaire.getDescription_co());
            dateTf1.setValue(commentaire.getDate_co()); // Assuming publication.getDate() returns LocalDate
            ratingTF1.setText(String.valueOf(commentaire.getNb_etoile()));

        }
    }

    public void AfficherCommentaire(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherCommentaire2.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) descriptionTF1.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

            // Vous pouvez fermer la fenêtre actuelle si nécessaire
            // ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
