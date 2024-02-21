package edu.esprit.controllers;


import edu.esprit.entities.Etablissement;
import edu.esprit.services.ServiceEtablissement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class AjouterEtablissement {
    @FXML
    private TextField CodeETF;

    @FXML
    private TextField Id_utilisateurETF;

    @FXML
    private TextField LieuETF;

    @FXML
    private TextField NomETF;

    @FXML
    private TextField TypeETF;

    private final ServiceEtablissement se = new ServiceEtablissement();

    @FXML
    void ajouterEtablissementAction(ActionEvent event) {
        // Créer une instance de ServiceService
        ServiceEtablissement serviceEtablissement = new ServiceEtablissement();

        // Récupérer les valeurs des champs du formulaire
        String Nom = NomETF.getText();
        String Lieu = LieuETF.getText();
        String Code = CodeETF.getText();
        String Type = TypeETF.getText();
        String Id_utilisateur = Id_utilisateurETF.getText();

        // Vérifier si les champs requis sont vides
        if (Nom.isEmpty() || Lieu.isEmpty() || Code.isEmpty() || Type.isEmpty() || Id_utilisateur.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Tous les champs sont obligatoires !");
            alert.showAndWait();
            return;
        }

        // Vérifier si le prix est un nombre valide
        int codeE;
        int id_utilisateurE;
        codeE = Integer.parseInt(Code);
        try {
            codeE = Integer.parseInt(Code);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Le code doit être un nombre valide !");
            alert.showAndWait();
            return;
        }
        try {
            id_utilisateurE = Integer.parseInt(Id_utilisateur);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("L'id doit être un nombre valide !");
            alert.showAndWait();
            return;
        }

        // Créer un nouvel objet Service avec les valeurs saisies
        Etablissement etablissement = new Etablissement();
        etablissement.setNom(Nom);
        etablissement.setCode_etablissement(codeE);
        etablissement.setLieu(Lieu);
        etablissement.setType_etablissement(Type);
        etablissement.setId_utilisateur(id_utilisateurE);

        // Ajouter le service à la base de données
        try {
            serviceEtablissement.ajouter(etablissement);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("Etablissement ajouté avec succès !");
            alert.showAndWait();

            // Effacer les champs du formulaire après l'ajout réussi
            NomETF.clear();
            LieuETF.clear();
            CodeETF.clear();
            TypeETF.clear();
            Id_utilisateurETF.clear();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Erreur lors de l'ajout d'etablissement' : " + e.getMessage());
            alert.showAndWait();
        }

    }

    public void navigatetoAfficherEtablissementAction(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AfficherEtablissement.fxml"));
            NomETF.getScene().setRoot(root);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Sorry");
            alert.setTitle("Error");
            alert.show();
        }

    }
}
