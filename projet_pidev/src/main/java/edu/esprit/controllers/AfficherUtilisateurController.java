package edu.esprit.controllers;
import edu.esprit.services.ServiceUtilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AfficherUtilisateurController implements Initializable {
    @FXML
    private Label LLutilisateurs;
    @FXML
    private Label LLadmins;
    private final ServiceUtilisateur sp = new ServiceUtilisateur();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            LLutilisateurs.setText(sp.getAll().toString());
            LLadmins.setText(sp.getAll_admin().toString());
        } catch (SQLException e) {
            System.out.printf(e.getMessage());
        }


    }

    @FXML
    void backtomenuAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AjouterUtilisateur.fxml"));
            LLadmins.getScene().setRoot(root);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Sorry");
            alert.setTitle("Error");
            alert.show();
        }
    }
}
