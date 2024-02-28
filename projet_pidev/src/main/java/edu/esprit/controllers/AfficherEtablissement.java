package edu.esprit.controllers;

import edu.esprit.entities.CurrentUser;
import edu.esprit.entities.Etablissement;
import edu.esprit.entities.Wallet;
import edu.esprit.services.ServiceEtablissement;

import edu.esprit.services.ServiceWallet;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

public class AfficherEtablissement implements Initializable {
    @FXML
    private ListView<Etablissement> listView; // Assurez-vous que l'attribut fx:id correspond à celui dans votre fichier FXML

    @FXML
    private ListView<Wallet> listView1;

    @FXML
    private AnchorPane NavBar;
    @FXML
    private AnchorPane grandAnchor;
    @FXML
    private AnchorPane anchorContenu;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ServiceEtablissement serviceService = new ServiceEtablissement();
        ServiceWallet serviceWallet = new ServiceWallet();
        Set<Etablissement> etablissements = null;
        Set<Wallet> wallets = null;

        try {
            etablissements = serviceService.getAll();
            wallets = serviceWallet.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        listView.setItems(FXCollections.observableArrayList(etablissements));
        listView1.setItems(FXCollections.observableArrayList(wallets));
        if (CurrentUser.getRole() != 0) {
            NavBar.setVisible(false);
            NavBar.setManaged(false);

            anchorContenu.setLayoutX(0);
            grandAnchor.setPrefWidth(anchorContenu.getPrefWidth());
        }
    }


    @FXML
    void supprimerEtablissement(ActionEvent event) {
        Etablissement selectedEtablissement = listView.getSelectionModel().getSelectedItem();

        if (selectedEtablissement == null) {
            // Aucun élément sélectionné, afficher une alerte
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avertissement");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un établissement à supprimer.");
            alert.showAndWait();
            return; // Sortir de la méthode, car rien à supprimer
        }

        // Si un élément est sélectionné, afficher la confirmation de suppression
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de vouloir supprimer cette conversation ?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                int id_etablissement = selectedEtablissement.getIdEtablissement();
                ServiceEtablissement serviceEtablissement = new ServiceEtablissement();
                serviceEtablissement.supprimer(id_etablissement);
                listView.getItems().remove(selectedEtablissement);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }






    @FXML
    public void modifierEtablissement(ActionEvent actionEvent) {
        // Code pour modifier l'utilisateur sélectionné dans la liste
        Etablissement selectedEtablissement = listView.getSelectionModel().getSelectedItem();
        if (selectedEtablissement == null) {
            // Aucun élément sélectionné, afficher une alerte
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avertissement");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un établissement à modifier.");
            alert.showAndWait();
            return; // Sortir de la méthode, car rien à supprimer
        }
        else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherEtablissement.fxml"));
                Parent root = loader.load();
                ModifierEtablissement controller = loader.getController();
                controller.initData(selectedEtablissement); // Passer l'utilisateur sélectionné au contrôleur de l'interface de modification

                // Obtenir la scène actuelle
                Scene scene = listView.getScene();

                // Changer le contenu de la scène
                scene.setRoot(root);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public void ajouterEtablissement(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AjouterEtablissement.fxml"));
            listView.getScene().setRoot(root);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Sorry");
            alert.setTitle("Error");
            alert.show();
        }

    }

    public void ajouterWallet(ActionEvent actionEvent) {

        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/AjouterWallet.fxml")));
            listView.getScene().setRoot(root);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Sorry");
            alert.setTitle("Error");
            alert.show();
        }
    }

    public void modifierWallet(ActionEvent actionEvent) {

        // Code pour modifier l'utilisateur sélectionné dans la liste
        Wallet selectedWallet = listView1.getSelectionModel().getSelectedItem();
        if (selectedWallet == null) {
            // Aucun élément sélectionné, afficher une alerte
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avertissement");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un Wallet à modifier.");
            alert.showAndWait();
            return; // Sortir de la méthode, car rien à supprimer
        }
        else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierWallet.fxml"));
                Parent root = loader.load();
                ModifierWallet controller = loader.getController();
                controller.initDataWallet(selectedWallet); // Passer l'utilisateur sélectionné au contrôleur de l'interface de modification

                // Obtenir la scène actuelle
                Scene scene = listView1.getScene();

                // Changer le contenu de la scène
                scene.setRoot(root);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void supprimerWallet(ActionEvent actionEvent) {
        Wallet selectedWallet = listView1.getSelectionModel().getSelectedItem();

        if (selectedWallet == null) {
            // Aucun élément sélectionné, afficher une alerte
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avertissement");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un Wallet à supprimer.");
            alert.showAndWait();
            return; // Sortir de la méthode, car rien à supprimer
        }

        // Si un élément est sélectionné, afficher la confirmation de suppression
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de vouloir supprimer cette conversation ?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                int id_wallet = selectedWallet.getIdWallet();
                ServiceWallet serviceWallet = new ServiceWallet();
                serviceWallet.supprimer(id_wallet);
                listView1.getItems().remove(selectedWallet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

