package edu.esprit.controllers;

import edu.esprit.entities.Publication;
import edu.esprit.services.ServicePublication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

public class AfficherPublication implements Initializable {

    @FXML
    private Button allerverscommentaire;


    @FXML
    private ListView<Publication> listView;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       ServicePublication serviceService = new ServicePublication();
        Set<Publication> publications = null;

        try {
            publications = serviceService.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Convert the Set to a List


        // Set the items in your ListView
        listView.setItems(FXCollections.observableArrayList(publications));
    }
    @FXML
    void supprimerPublication(ActionEvent event) {
        Publication selectedPublication = listView.getSelectionModel().getSelectedItem();
        if (selectedPublication != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de suppression");
            alert.setHeaderText(null);
            alert.setContentText("Êtes-vous sûr de vouloir supprimer cette conversation ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    int id_publication = selectedPublication.getId_publication();
                    ServicePublication ServicePublication = new ServicePublication();
                    ServicePublication.supprimer(id_publication);
                    listView.getItems().remove(selectedPublication);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


    }
    @FXML
    public void modifierPublication(ActionEvent actionEvent) {

        Publication selectedPublication = listView.getSelectionModel().getSelectedItem();
        if (selectedPublication != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierPublication.fxml"));
                Parent root = loader.load();
                ModifierPublication controller = loader.getController();
                controller.initData(selectedPublication); // Passer l'utilisateur sélectionné au contrôleur de l'interface de modification

                // Obtenir la scène actuelle
                Scene scene = listView.getScene();

                // Changer le contenu de la scène
                scene.setRoot(root);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void ajouterPublication(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AjouterPublication.fxml"));
            listView.getScene().setRoot(root);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Sorry");
            alert.setTitle("Error");
            alert.show();
        }

    }

    @FXML
    void allerverscommentaire(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AjouterCommentaire.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


}


