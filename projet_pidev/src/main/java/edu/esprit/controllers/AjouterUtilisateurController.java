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
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;


public class AjouterUtilisateurController {

    @FXML
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






}
