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
        if (nom.isEmpty() || Lieu.isEmpty() || prixStr.isEmpty() || tempService.isEmpty() || domaineService.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Tous les champs sont obligatoires !");
            alert.showAndWait();
            return;
        }

        // Vérifier si le prix est un nombre valide
        float prix;
        try {
            prix = Float.parseFloat(prixStr);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Le prix doit être un nombre valide !");
            alert.showAndWait();
            return;
        }

        // Créer un nouvel objet Service avec les valeurs saisies
        Service service = new Service();
        service.setNom_service(nomService);
        service.setTitre_service(titreService);
        service.setPrix(prix);
        service.setTmpService(tempService);
        service.setDomaine(domaineService);

        // Ajouter le service à la base de données
        try {
            serviceService.ajouter(service);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("Service ajouté avec succès !");
            alert.showAndWait();

            // Effacer les champs du formulaire après l'ajout réussi
            NomSTF.clear();
            TitreSTF.clear();
            PrixSTF.clear();
            TempSTF.clear();
            DomaineSTF.clear();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Erreur lors de l'ajout du service : " + e.getMessage());
            alert.showAndWait();
        }

    }

    public void navigatetoAfficherEtablissementAction(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AfficherEtablissement.fxml"));
            TFnom.getScene().setRoot(root);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Sorry");
            alert.setTitle("Error");
            alert.show();
        }

    }
}
