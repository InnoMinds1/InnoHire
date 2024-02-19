package edu.esprit.controllers;
import edu.esprit.services.ServiceUtilisateur;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AfficherUtilisateurController implements Initializable {
    @FXML
    private Label LLutilisateurs;
    private final ServiceUtilisateur sp = new ServiceUtilisateur();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            LLutilisateurs.setText(sp.getAll().toString());
        } catch (SQLException e) {
            System.out.printf(e.getMessage());
        }


    }
}
