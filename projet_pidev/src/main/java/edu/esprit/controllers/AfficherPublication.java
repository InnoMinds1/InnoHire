package edu.esprit.controllers;

import edu.esprit.services.ServicePublication;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AfficherPublication implements Initializable {
    @FXML
    private Label LLpersonnes;
    private final ServicePublication sp = new ServicePublication();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            LLpersonnes.setText(sp.getAll().toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

