package edu.esprit.controllers;

import edu.esprit.entities.Publication;
import edu.esprit.entities.Reclamation;
import edu.esprit.entities.Utilisateur;
import edu.esprit.services.ServiceReclamation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

public class AjouterReclamationController {
    @FXML
    private TextField TFTitre;
    @FXML
    private TextField TFType;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextArea TADescription;

    private final ServiceReclamation sr = new ServiceReclamation();
    Utilisateur user=new Utilisateur(1,11417264,"dhawadi","hachem","bizerte","123456789");
    Publication pub=new Publication(1,"code",user,"desc","hshtag","seen","image",LocalDate.of(2021,02,4),5);


    public void navigateToAfficherReclamationAction(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AfficherReclamation.fxml"));
            TFTitre.getScene().setRoot(root);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Sorry");
            alert.setTitle("Error");
            alert.show();
        }

    }
    @FXML
    void ajouterReclamationAction(ActionEvent event){
        try {
            LocalDate localDate = datePicker.getValue();
            Timestamp timestamp = Timestamp.valueOf(localDate.atStartOfDay());
            sr.ajouter(new Reclamation(0, TFType.getText(), TFTitre.getText(), TADescription.getText(), timestamp, pub, user));
            Alert alert =new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Your reclamation send with succes");
            alert.show();
            navigateToAfficherReclamationAction(event);
        }catch (SQLException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("SQL Exception");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }









}
