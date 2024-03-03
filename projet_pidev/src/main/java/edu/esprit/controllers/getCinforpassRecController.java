package edu.esprit.controllers;
import edu.esprit.entities.*;
import edu.esprit.services.ServiceUtilisateur;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.util.Callback;

import javax.swing.plaf.ColorUIResource;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;

public class getCinforpassRecController {

    @FXML
    private Label LabelCIN;

    @FXML
    private TextField TFcin;

    @FXML
    void cinsearchAction(ActionEvent event) {
        int cin;
        ServiceUtilisateur  serviceUtilisateur = new ServiceUtilisateur();
        try {
            cin = Integer.parseInt(TFcin.getText());



        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Le cin  doivent etre être un nombre valide !");
            alert.showAndWait();
            return;
        }
        if(cin<0)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Le cin  doivent etre être un nombre valide !");
            alert.showAndWait();
            return;
        }
        if (!serviceUtilisateur.utilisateurExiste(cin))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("CIN INTROUVABLE ");
            alert.showAndWait();
            return;

        }
        else
        {
            Utilisateur u = null;
            try {
                u = serviceUtilisateur.getOneByCin(cin);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            CurrentUser.setCin(cin);
            CurrentUser.setRole(u.getRole());
            CurrentUser.setMdp(u.getMdp());
            CurrentUser.setAdresse(u.getAdresse());
            CurrentUser.setNom(u.getNom());
            CurrentUser.setPrenom(u.getPrenom());
            CurrentUser.setOtp(serviceUtilisateur.generateOTP());
            System.out.println("otp = "+CurrentUser.getOtp());



            try {
                Parent root = FXMLLoader.load(getClass().getResource("/OTP.fxml"));
                TFcin.getScene().setRoot(root);
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Sorry");
                alert.setTitle("Error");
                alert.show();
            }
        }

    }
}
