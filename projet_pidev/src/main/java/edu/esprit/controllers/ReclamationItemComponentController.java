package edu.esprit.controllers;

import edu.esprit.entities.Reclamation;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReclamationItemComponentController {


    @FXML
    private ImageView userPhoto;

    @FXML
    private Label userFullName;

    @FXML
    private Label userCode;

    @FXML
    private Label dateRec;

    // Create a method to set data from Reclamation object
    public void setReclamationData(Reclamation reclamation) {
        // Set data to UI elements
        //userPhoto.setImage(/* set image from reclamation */);
        userFullName.setText(reclamation.getUser().getNom()+" "+reclamation.getUser().getPrenom());
        userCode.setText(String.valueOf(reclamation.getPub().getCode_pub()));
        dateRec.setText(String.valueOf(reclamation.getDate()));
    }

}
