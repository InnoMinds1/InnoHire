package edu.esprit.controllers;

import edu.esprit.entities.Admin;
import edu.esprit.entities.CurrentUser;
import edu.esprit.entities.Utilisateur;
import edu.esprit.services.ServiceUtilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import javax.management.relation.Role;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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

    @FXML
    private ComboBox<String> comboRole;
    @FXML
    private Label CurrentUserEmail;
    @FXML
    private Label currentUserName;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ServiceUtilisateur serviceService = new ServiceUtilisateur();

        CurrentUserEmail.setText(CurrentUser.getAdresse());
        currentUserName.setText(CurrentUser.getNom());

        // Load all utilisateurs initially
        Set<Utilisateur> utilisateurs1 = null;
        Set<Admin> admins1 = null;
        try {
            utilisateurs1 = serviceService.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        admins1 = serviceService.getAll_admin();
        utilisateurs1.addAll(admins1);

        // Load and add UtilisateurItemComponent for each Utilisateur
        for (Utilisateur utilisateur : utilisateurs1) {
            loadUtilisateurItemComponent(utilisateur);
        }

        // Moved the 'role' declaration inside the lambda expression
        comboRole.setOnAction(event -> {
            String role = comboRole.getValue(); // Get the selected role

            try {
                Set<Utilisateur> utilisateurs = serviceService.getAll();
                Set<Admin> admins = serviceService.getAll_admin();
                utilisateurs.addAll(admins);

                // Clear previous components
                utilisateurContainer.getChildren().clear();

                // Load and add UtilisateurItemComponent for each Utilisateur based on the selected role
                for (Utilisateur utilisateur : utilisateurs) {
                    if (role.equals("NO FILTER") || role.equals("Admin") && serviceService.verifyRoleByCin(utilisateur.getCin()) == 0 ||
                            role.equals("Representant") && serviceService.verifyRoleByCin(utilisateur.getCin()) == 1 ||
                            role.equals("Candidat") && serviceService.verifyRoleByCin(utilisateur.getCin()) == 2) {
                        loadUtilisateurItemComponent(utilisateur);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // Helper method to load UtilisateurItemComponent
    private void loadUtilisateurItemComponent(Utilisateur utilisateur) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UtilisateurItemComponent.fxml"));
        try {
            utilisateurContainer.getChildren().add(loader.load());
            UtilisateurItemComponent controller = loader.getController();
            controller.setUtilisateurData(utilisateur, container);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Helper method to load UtilisateurItemComponent


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

    @FXML
    public void onSearchTextChanged(KeyEvent keyEvent) {
        // Handle search text change
    }
    @FXML
    void NavigateToEtablissement(ActionEvent event) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/Etablissement.fxml"));
            utilisateurContainer.getScene().setRoot(root);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Sorry");
            alert.setTitle("Error");
            alert.show();
        }

    }
}
