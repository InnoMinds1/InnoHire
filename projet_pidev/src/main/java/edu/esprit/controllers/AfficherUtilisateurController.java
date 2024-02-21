package edu.esprit.controllers;
import edu.esprit.entities.Admin;
import edu.esprit.entities.Utilisateur;
import edu.esprit.services.ServiceUtilisateur;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;

public class AfficherUtilisateurController implements Initializable {
    @FXML
    private ListView<Utilisateur> listView; // Assurez-vous que l'attribut fx:id correspond à celui dans votre fichier FXML

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ServiceUtilisateur serviceService = new ServiceUtilisateur();
        Set<Utilisateur> utilisateurs = null;
        Set<Admin> admins = null;

        try {
            utilisateurs = serviceService.getAll();
            admins=serviceService.getAll_admin();
            utilisateurs.addAll(admins);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        listView.setItems(FXCollections.observableArrayList(utilisateurs));

    }


    @FXML
    void supprimerUtilisateur(ActionEvent event) {
        Utilisateur selectedUtilisateur = listView.getSelectionModel().getSelectedItem();
        if (selectedUtilisateur != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de suppression");
            alert.setHeaderText(null);
            alert.setContentText("Êtes-vous sûr de vouloir supprimer cet utilisateur?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    int cin = selectedUtilisateur.getCin();
                    ServiceUtilisateur serviceUtilisateur = new ServiceUtilisateur();
                    serviceUtilisateur.supprimer_par_cin(cin);
                    listView.getItems().remove(selectedUtilisateur);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


    }





    @FXML
    public void modifierUtilisateur(ActionEvent actionEvent) {
        // Code pour modifier l'utilisateur sélectionné dans la liste
        Utilisateur selectedUtilisateur = listView.getSelectionModel().getSelectedItem();
        if (selectedUtilisateur != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierUtilisateur.fxml"));
                Parent root = loader.load();
                ModifierUtilisateurController controller = loader.getController();
                controller.initData(selectedUtilisateur); // Passer l'utilisateur sélectionné au contrôleur de l'interface de modification

                // Obtenir la scène actuelle
                Scene scene = listView.getScene();

                // Changer le contenu de la scène
                scene.setRoot(root);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public void ajouterUtilisateur(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AjouterUtilisateur.fxml"));
            listView.getScene().setRoot(root);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Sorry");
            alert.setTitle("Error");
            alert.show();
        }

    }

   /* @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            LLutilisateurs.setText(sp.getAll().toString());
            LLadmins.setText(sp.getAll_admin().toString());
        } catch (SQLException e) {
            System.out.printf(e.getMessage());
        }


    }

    @FXML
    void backtomenuAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AjouterUtilisateur.fxml"));
            LLadmins.getScene().setRoot(root);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Sorry");
            alert.setTitle("Error");
            alert.show();
        }
    }*/
}
