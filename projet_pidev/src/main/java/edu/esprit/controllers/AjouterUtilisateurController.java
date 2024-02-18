package edu.esprit.controllers;
import edu.esprit.entities.Candidat;
import edu.esprit.entities.Representant;
import edu.esprit.entities.Utilisateur;
import edu.esprit.services.ServiceUtilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;


public class AjouterUtilisateurController {

    @FXML
    private TextField TFcin;

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
                if (role == 0) {
                    Representant r = new Representant(cin, nom, prenom, adresse, mdp);

                    sp.ajouter(r);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setContentText("GG REPRESENTANT ADDED");
                    alert.show();
                } else if (role == 1) {
                    Candidat c = new Candidat(cin, nom, prenom, adresse, mdp);
                    sp.ajouter(c);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setContentText("GG CANDIDAT ADDED");
                    alert.show();

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("IL Y A DEUX ROLE 0 pour representant et 1 pour candidat");
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
    void navigatetoAfficherPersonneAction(ActionEvent event) {

    }

}
