package edu.esprit.controllers;

import edu.esprit.services.ServiceEtablissement;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AfficherEtablissement implements Initializable {
    @FXML
    private Label LLetablissements;
    private final ServiceEtablissement se = new ServiceEtablissement();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            LLetablissements.setText(se.getAll().toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

