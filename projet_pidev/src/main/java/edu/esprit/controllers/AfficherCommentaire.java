package edu.esprit.controllers;

import edu.esprit.entities.Commentaire;
import edu.esprit.entities.Publication;
import edu.esprit.services.ServiceCommentaire;
import edu.esprit.services.ServicePublication;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;

public class AfficherCommentaire implements Initializable {
    @FXML
    private ListView<Commentaire> ListviewC;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ServiceCommentaire serviceService = new ServiceCommentaire();
        Set<Commentaire> Commentaires = null;

        try {
            Commentaires = serviceService.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Convert the Set to a List


        // Set the items in your ListView
            ListviewC.setItems(FXCollections.observableArrayList(Commentaires));
    }

    public void ajouterCommentaire(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AjouterCommentaire.fxml"));
            ListviewC.getScene().setRoot(root);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Sorry");
            alert.setTitle("Error");
            alert.show();
        }

    }


    @FXML
    void modifierCommentaire(ActionEvent event) {
        Commentaire selectedCommentaire = ListviewC.getSelectionModel().getSelectedItem();
        if (selectedCommentaire != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierCommentaire.fxml"));
                Parent root = loader.load();
                ModifierCommentaire controller = loader.getController();
                controller.initData(selectedCommentaire); // Passer l'utilisateur sélectionné au contrôleur de l'interface de modification

                // Obtenir la scène actuelle
                Scene scene = ListviewC.getScene();

                // Changer le contenu de la scène
                scene.setRoot(root);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void supprimerCommentaire(ActionEvent event) {
        Commentaire selectedCommentaire = ListviewC.getSelectionModel().getSelectedItem();
        if (selectedCommentaire != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de suppression");
            alert.setHeaderText(null);
            alert.setContentText("Êtes-vous sûr de vouloir supprimer cette conversation ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    int id_commentaire = selectedCommentaire.getId_commentaire();
                    ServiceCommentaire ServiceCommentaire = new ServiceCommentaire();
                    ServiceCommentaire.supprimer(id_commentaire);
                    ListviewC.getItems().remove(selectedCommentaire);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void allerverspublication(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AfficherPublication.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
