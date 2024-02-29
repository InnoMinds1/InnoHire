package edu.esprit.controllers;

import edu.esprit.entities.Commentaire;
import edu.esprit.entities.Publication;
import edu.esprit.entities.Utilisateur;
import edu.esprit.services.ServiceCommentaire;
import edu.esprit.services.ServicePublication;
import edu.esprit.services.ServiceUtilisateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.Set;

public class AjouterCommentaire implements Initializable {

    @FXML
    private Button validerCommentaire;
    @FXML
    private TextField cinTF1;

    @FXML
    private TextField descriptionTF1;
    @FXML
    private DatePicker dateTf1;

    @FXML
    private TextField ratingTF1;
    @FXML
    private ListView<Publication> listView;

    @FXML
    private Button afficherCommentaire;
    private final ServicePublication es = new ServicePublication();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ServicePublication serviceService = new ServicePublication();
        Set<Publication> publications = null;

        try {
            publications = serviceService.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Convert the Set to a List


        // Set the items in your ListView
        listView.setItems(FXCollections.observableArrayList(publications));
    }



    @FXML
    void validerCommentaire(ActionEvent event) {

        Publication publicationSelectionne = listView.getSelectionModel().getSelectedItem();
        ServiceUtilisateur su = new ServiceUtilisateur();
        // Récupération des données des champs
        int cin = Integer.parseInt(cinTF1.getText());
        String descrption_co = descriptionTF1.getText();
        LocalDate date_co = dateTf1.getValue();
        String nb_etoile = ratingTF1.getText();
        Utilisateur u = new Utilisateur();
        u = su.getByCin(cin);


        if (publicationSelectionne != null && u != null && !descrption_co.isEmpty() && date_co != null && !nb_etoile.isEmpty()) {
            LocalDate dateActuelle = LocalDate.now();
            if (!date_co.isBefore(dateActuelle) && !date_co.isAfter(dateActuelle)) {
                // La date est valide, procédez à l'ajout du commentaire
                Commentaire commentaire = new Commentaire();
                commentaire.setPublication(publicationSelectionne);
                commentaire.setUtilisateur(u);
                commentaire.setDescription_co(descrption_co);
                commentaire.setDate_co(date_co);
                commentaire.setNb_etoile(Integer.parseInt(nb_etoile));
                // Ajoutez l'utilisateur actuel au commentaire

                ServiceCommentaire serviceCommentaire = new ServiceCommentaire();
                try {
                    serviceCommentaire.ajouter(commentaire);
                    AfficherInformation("Commentaire ajouté", "Le commentaire a été ajouté avec succès.");
                } catch (SQLException e) {
                    AfficherErreur("Erreur d'ajout", "Une erreur s'est produite lors de l'ajout du commentaire.", e.getMessage());
                }
            } else {
                AfficherAvertissement("Date non valide", "Veuillez sélectionner une date valide.");
            }
        } else {
            AfficherAvertissement("Champs non remplis", "Veuillez remplir tous les champs avant de valider le commentaire.");

        }
    }







private void AfficherAvertissement(String titre, String contenu) {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle(titre);
    alert.setHeaderText(null);
    alert.setContentText(contenu);
    alert.showAndWait();
}

// Méthode pour afficher une boîte de dialogue d'information
private void AfficherInformation(String titre, String contenu) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(titre);
    alert.setHeaderText(null);
    alert.setContentText(contenu);
    alert.showAndWait();
}


// Méthode utilitaire pour afficher une boîte de dialogue d'erreur
private void AfficherErreur(String titre, String contenu, String details) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(titre);
    alert.setContentText(contenu);
    alert.setHeaderText(details);
    alert.showAndWait();
}


    @FXML
    void navigatetoAfficherCommentaireAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AfficherCommentaire.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}



