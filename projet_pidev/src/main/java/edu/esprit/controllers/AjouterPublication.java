package edu.esprit.controllers;

import edu.esprit.entities.Publication;
import edu.esprit.entities.Utilisateur;
import edu.esprit.services.ServicePublication;
import edu.esprit.services.ServiceUtilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class AjouterPublication {



        @FXML
        private TextField TFcode_pub;
         @FXML
         private TextField TFcin_utilisateur;
        @FXML
        private DatePicker TFdate;

        @FXML
        private TextField TFdescription;

        @FXML
        private TextField TFhashtag;

        @FXML
        private TextField TFimage;

        @FXML
        private TextField TFnb_report;

        @FXML
        private TextField TFvisibilite;
       private final ServicePublication sp=new ServicePublication();
    private final ServiceUtilisateur  su=new ServiceUtilisateur();


        @FXML
    void ajouterPublicationAction(ActionEvent event) {
        try {
            int cin=Integer.parseInt(TFcin_utilisateur.getText());
            int nb_report=Integer.parseInt(TFnb_report.getText());

            Utilisateur u=new Utilisateur();
            u=su.getByCin(cin);
            sp.ajouter(new Publication(TFcode_pub.getText(),u,TFdescription.getText(),TFhashtag.getText(),TFvisibilite.getText(),TFimage.getText(),TFdate.getValue(),nb_report));
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

    public void navigatetoAfficherPublicationAction(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AfficherPublication.fxml"));
            TFcode_pub.getScene().setRoot(root);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Sorry");
            alert.setTitle("Error");
            alert.show();
        }

    }
}
