package edu.esprit.controllers;
import  edu.esprit.entities.Admin;
import edu.esprit.entities.Candidat;
import edu.esprit.entities.Representant;
import edu.esprit.entities.Utilisateur;
import edu.esprit.services.ServiceUtilisateur;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;

public class ListUsersControllers implements Initializable {
    @FXML
    private AnchorPane AnchoPaneMessage131;
    @FXML
    private TextField TFrecherche;

    @FXML
    private Label adresseLabel;

    @FXML
    private Label cinLabel;

    @FXML
    private AnchorPane container;

    @FXML
    private Label currentUserLabel;

    @FXML
    private Label nomLabel;

    @FXML
    private Label prenomLabel;

    @FXML
    private Label roleLabel;

    @FXML
    private ScrollPane scrollPaneClaim;

    @FXML
    private VBox utilisateurContainer;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Retrieve data from the database
        ServiceUtilisateur serviceService = new ServiceUtilisateur();
        // Retrieve data from the database
        Set<Utilisateur> utilisateurs = null;
        Set<Admin> admins = null;
        try {
            utilisateurs = serviceService.getAll();
            admins = serviceService.getAll_admin();
            utilisateurs.addAll(admins);
            // Load and add ReclamationItemComponent for each Reclamation
            for (Utilisateur utilisateur : utilisateurs) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UtilisateurItemComponent.fxml"));
                try {
                    utilisateurContainer.getChildren().add(loader.load());
                    UtilisateurItemComponent controller = loader.getController();
                    controller.setUtilisateurData(utilisateur, container);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void navigateToAjouterUtilisateur(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AjouterUtilisateur.fxml"));
            utilisateurContainer.getScene().setRoot(root);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Sorry");
            alert.setTitle("Error");
            alert.show();
        }

    }
    @FXML
    void navigatotoAccueil(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Accueil.fxml"));
            utilisateurContainer.getScene().setRoot(root);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Sorry");
            alert.setTitle("Error");
            alert.show();
        }

    }



}