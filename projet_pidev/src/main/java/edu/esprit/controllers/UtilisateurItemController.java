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
import javafx.scene.layout.HBox;
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
    @FXML
    private HBox hboxMere; // Assurez-vous d'avoir la référence correcte à votre HBox
    private static HBox hboxSelectionne; // Champ statique pour suivre le HBox précédemment sélectionné
    @FXML
    private Button affecteBtn;
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





    public void affecterUtilisateurOnClick(ActionEvent actionEvent) {
        // Désélectionner le précédent HBox s'il existe
        if (hboxSelectionne != null) {
            simulateDeselection(hboxSelectionne);
        }

        // Affecter le nouvel utilisateur
        ajouterEtablissementController.updateCinTextField(labelCin.getText());
        simulateSelection(hboxMere);

        // Mettre à jour le HBox sélectionné actuel
        hboxSelectionne = hboxMere;
    }

    // ... (autres méthodes)

    private void simulateSelection(HBox hbox) {
        hbox.getStyleClass().add("selected-item");
    }

    private void simulateDeselection(HBox hbox) {
        hbox.getStyleClass().remove("selected-item");
    }

}