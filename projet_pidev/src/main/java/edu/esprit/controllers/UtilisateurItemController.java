package edu.esprit.controllers;

import edu.esprit.entities.Utilisateur;
import edu.esprit.services.ServiceUtilisateur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import edu.esprit.controllers.AjouterEtablissement;
public class UtilisateurItemController {
    @FXML
    private Text LabelPrenom;

    @FXML
    private Button btnModifier;

    @FXML
    private Text labelCin;

    @FXML
    private Text labelNom;


    private Utilisateur utilisateur;

    private ServiceUtilisateur su;

    private AjouterEtablissement ajouterEtablissementController;

    public void setAjouterEtablissementController(AjouterEtablissement ajouterEtablissementController) {
        this.ajouterEtablissementController = ajouterEtablissementController;
    }

    public void setData(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
        LabelPrenom.setText(utilisateur.getPrenom());
        labelCin.setText(String.valueOf(utilisateur.getCin()));
        labelNom.setText(utilisateur.getNom());


    }


    public void actualiserVueQuestions() {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AjouterEtablissement.fxml"));
            LabelPrenom.getScene().setRoot(root);
        } catch (IOException e) {

            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("Une erreur s'est produite lors de la redirection.");
            errorAlert.setTitle("Erreur de redirection");
            errorAlert.show();
        }
    }


    public void affecterUtilisateurOnClick(ActionEvent actionEvent) {
        // You can now access cin_utilisateurETF and update it in AjouterEtablissement
        ajouterEtablissementController.updateCinTextField(labelCin.getText());
    }
}