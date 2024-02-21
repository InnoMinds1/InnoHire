package edu.esprit.controllers;

import edu.esprit.entities.Etablissement;
import edu.esprit.services.ServiceEtablissement;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;

public class AfficherEtablissement implements Initializable {
    @FXML
    private ListView<Etablissement> listView; // Assurez-vous que l'attribut fx:id correspond à celui dans votre fichier FXML

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ServiceEtablissement serviceService = new ServiceEtablissement();
        Set<Etablissement> etablissements = null;

        try {
            etablissements = serviceService.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        listView.setItems(FXCollections.observableArrayList(etablissements));
    }


    @FXML
    void supprimerEtablissement(ActionEvent event) {
        Etablissement selectedEtablissement = listView.getSelectionModel().getSelectedItem();
        if (selectedEtablissement != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de suppression");
            alert.setHeaderText(null);
            alert.setContentText("Êtes-vous sûr de vouloir supprimer cette conversation ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    int id_etablissement = selectedEtablissement.getId_etablissement();
                    ServiceEtablissement ServiceEtablissement = new ServiceEtablissement();
                    ServiceEtablissement.supprimer(id_etablissement);
                    listView.getItems().remove(selectedEtablissement);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


    }





    @FXML
    public void modifierEtablissement(ActionEvent actionEvent) {
        // Code pour modifier l'utilisateur sélectionné dans la liste
        Etablissement selectedEtablissement = listView.getSelectionModel().getSelectedItem();
        if (selectedEtablissement != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierEtablissement.fxml"));
                Parent root = loader.load();
                ModifierEtablissement controller = loader.getController();
                controller.initData(selectedEtablissement); // Passer l'utilisateur sélectionné au contrôleur de l'interface de modification
                Stage window = (Stage) listView.getScene().getWindow();
                window.setScene(new Scene(root));
                window.show();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
