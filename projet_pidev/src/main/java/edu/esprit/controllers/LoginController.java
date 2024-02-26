package edu.esprit.controllers;

import edu.esprit.entities.CurrentUser;
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

public class LoginController {

    @FXML
    private TextField TFcin;

    @FXML
    private TextField TFmdp;

    @FXML
    void loginAction(ActionEvent event) {
        String mdp = TFmdp.getText();
        ServiceUtilisateur sp= new ServiceUtilisateur();
        String cinText = TFcin.getText().trim();

        // Check if TFcin is not empty and contains a numeric value
        if (cinText.isEmpty() || !cinText.matches("\\d+")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Veuillez entrer un nombre valide dans le champ CIN.");
            alert.setTitle("Erreur");
            alert.show();
            return;
        }
        int cin = Integer.parseInt(TFcin.getText());


        if (mdp.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("REMPLIR MDP");
            alert.setTitle("NON");
            alert.show();
        }

       else if (sp.utilisateurExiste(cin))
        {
            try {
                Utilisateur u = sp.getOneByCin(cin);
                String hashed_mdp= sp.hashPassword(mdp);
                if (!u.getMdp().equals(hashed_mdp))
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("MDP INCORRECT");
                    alert.setTitle("NON");
                    alert.show();
                    return;
                }
                else
                {
                   int role= sp.verifyRoleByCin(cin);
                   if (role==0)
                   {   CurrentUser.setCin(u.getCin());
                       CurrentUser.setNom(u.getNom());
                       CurrentUser.setPrenom(u.getPrenom());
                       CurrentUser.setAdresse(u.getAdresse());
                       CurrentUser.setMdp(u.getMdp());
                       CurrentUser.setRole(0);
                       Alert alert = new Alert(Alert.AlertType.INFORMATION);
                       alert.setContentText("WELCOME ADMINE");
                       alert.setTitle("Oui");
                       alert.show();
                       try {
                           Parent root = FXMLLoader.load(getClass().getResource("/Accueil.fxml"));
                           TFcin.getScene().setRoot(root);
                       } catch (IOException e) {
                           Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                           alert1.setContentText("Sorry");
                           alert1.setTitle("Error");
                           alert1.show();
                       }
                   }
                   else if(role == 1)
                   {
                       CurrentUser.setCin(u.getCin());
                       CurrentUser.setNom(u.getNom());
                       CurrentUser.setPrenom(u.getPrenom());
                       CurrentUser.setAdresse(u.getAdresse());
                       CurrentUser.setMdp(u.getMdp());
                       CurrentUser.setRole(1);
                       int status= sp.getStatusfromCIN(u.getCin());
                       if (status==1){
                       Alert alert = new Alert(Alert.AlertType.INFORMATION);
                       alert.setContentText("WELCOME Rep");
                       alert.setTitle("Oui");
                       alert.show();
                       try {
                           Parent root = FXMLLoader.load(getClass().getResource("/Accueil.fxml"));
                           TFcin.getScene().setRoot(root);
                       } catch (IOException e) {
                           Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                           alert1.setContentText("Sorry");
                           alert1.setTitle("Error");
                           alert1.show();
                       }
                       }
                       else
                       {
                           Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                           alert1.setContentText("VEILLEZ CREER UNE SOCIETE");
                           alert1.setTitle("Error");
                           alert1.show();
                           Parent root = null;
                           try {
                               root = FXMLLoader.load(getClass().getResource("/AjouterEtablissementAfterCreate.fxml"));
                           } catch (IOException e) {
                               throw new RuntimeException(e);
                           }
                           TFcin.getScene().setRoot(root);
                       }
                   }
                   else if(role == 2)
                   {     CurrentUser.setCin(u.getCin());
                       CurrentUser.setNom(u.getNom());
                       CurrentUser.setPrenom(u.getPrenom());
                       CurrentUser.setAdresse(u.getAdresse());
                       CurrentUser.setMdp(u.getMdp());
                       CurrentUser.setRole(2);
                       Alert alert = new Alert(Alert.AlertType.INFORMATION);
                       alert.setContentText("WELCOME CANDIDAT");
                       alert.setTitle("Oui");
                       alert.show();
                       try {
                           Parent root = FXMLLoader.load(getClass().getResource("/Accueil.fxml"));
                           TFcin.getScene().setRoot(root);
                       } catch (IOException e) {
                           Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                           alert1.setContentText("Sorry");
                           alert1.setTitle("Error");
                           alert1.show();
                       }
                   }

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("An error occurred while processing your request.");
            alert.setTitle("Error");
            alert.show();
        }
    }
    @FXML
    void navigateToCreateAccount(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/CreateAccount.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        TFcin.getScene().setRoot(root);


    }

}