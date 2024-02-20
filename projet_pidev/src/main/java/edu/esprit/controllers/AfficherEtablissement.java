package edu.esprit.controllers;

import edu.esprit.entities.Etablissement;
import edu.esprit.services.ServiceEtablissement;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

public class AfficherEtablissement implements Initializable {
    @FXML
    private Label LLetablissements;
    private final ServiceEtablissement se = new ServiceEtablissement();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Set<Etablissement> etablissementsList = se.getAll();

            // Construire la chaîne avec chaque établissement sur une nouvelle ligne
            StringBuilder etablissementsStringBuilder = new StringBuilder();
            for (Etablissement etablissement : etablissementsList) {
                etablissementsStringBuilder.append(etablissement).append("\n");
            }

            // Afficher la chaîne résultante dans votre composant (LLetablissements)
            LLetablissements.setText(etablissementsStringBuilder.toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

