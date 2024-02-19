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
    private TextField TFnom;
    @FXML
    private TextField TFid_utilisateur;

    @FXML
    private TextField TFcode;

    @FXML
    private TextField TFlieu;

    @FXML
    private TextField TFtype;

    private final ServiceEtablissement se = new ServiceEtablissement();

    @FXML
    void ajouterEtablissementAction(ActionEvent event) {
        try {
            int code = Integer.parseInt(TFcode.getText());
            int id_utilisateur = Integer.parseInt(TFid_utilisateur.getText());
            se.ajouter(new Etablissement(TFnom.getText(),TFlieu.getText(),code,TFtype.getText(),id_utilisateur));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("GG");
            alert.show();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("SQL Exception");
            alert.setContentText(e.getMessage());
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
