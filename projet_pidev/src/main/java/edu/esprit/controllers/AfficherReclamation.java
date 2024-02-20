package edu.esprit.controllers;

import edu.esprit.services.ServiceReclamation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;


import java.net.URL;
import java.util.ResourceBundle;

public class AfficherReclamation implements Initializable {
    @FXML
    private Label LabListe;
    private final ServiceReclamation sr = new ServiceReclamation();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LabListe.setText(sr.getAll().toString());
    }
}
