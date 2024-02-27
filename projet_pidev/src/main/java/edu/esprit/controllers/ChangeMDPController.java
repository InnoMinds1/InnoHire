package edu.esprit.controllers;
import edu.esprit.entities.*;
import edu.esprit.services.ServiceUtilisateur;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.util.Callback;
import jdk.jshell.execution.Util;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;


public class ChangeMDPController {
    @FXML
    private TextField TFancienmdp;

    @FXML
    private TextField TFnouveau;

    @FXML
    void RetourAccueil(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Accueil.fxml"));
            TFancienmdp.getScene().setRoot(root);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Sorry");
            alert.setTitle("Error");
            alert.show();
        }

    }

    @FXML
    void ok(ActionEvent event) {
        ServiceUtilisateur su = new ServiceUtilisateur();
        String ancienMDP = su.hashPassword(TFancienmdp.getText());

        try {
            String verifmdp=su.getMDPfromCIN(CurrentUser.getCin());
            System.out.println(ancienMDP);
            System.out.println(verifmdp);
            Utilisateur u = su.getOneByCin(CurrentUser.getCin());
            System.out.println(u);

            if (ancienMDP.equals(verifmdp)) {
                // You can directly set the new password without rehashing
                u.setMdp(TFnouveau.getText());
                su.modifier_par_cin(u);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("MDP MODIFIEE");
                alert.setTitle("GG");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("MDP INCORRECT");
                alert.setTitle("Error");
                alert.show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
