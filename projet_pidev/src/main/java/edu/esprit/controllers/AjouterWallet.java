package edu.esprit.controllers;

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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Set;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AjouterWallet implements Initializable {
    //--------------------wallet
    @FXML
    private TextField BalanceETF;

    @FXML
    private ListView<Etablissement> ListViewEtab;

    @FXML
    private TextField code_EtabETF;

    @FXML
    private TextField statusETF;

    @FXML
    private TextField dateCreationETF;
    private final ServiceWallet sw = new ServiceWallet();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LocalDateTime currentDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = currentDate.format(formatter);
        dateCreationETF.setText(formattedDate);



        ServiceEtablissement serviceEtablissement = new ServiceEtablissement();
        Set<Etablissement> etablissements = null;
        try {
            etablissements = serviceEtablissement.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ListViewEtab.setItems(FXCollections.observableArrayList(etablissements));
        // Ajouter un ChangeListener pour mettre à jour le TextField lorsqu'un utilisateur est sélectionné
        ListViewEtab.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                code_EtabETF.setText(String.valueOf(newValue.getCodeEtablissement()));
            }
        });

    }
    public void ajouterWalletAction(ActionEvent actionEvent) throws SQLException {
        ServiceWallet serviceWallet = new ServiceWallet();

        // Récupérer les valeurs des champs du formulaire
        String Balance = BalanceETF.getText();
        String Date=dateCreationETF.getText();
        String status = statusETF.getText();



        // Vérifier si les champs requis sont vides
        if (Balance.isEmpty()||Date.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Tous les champs sont obligatoires !");
            alert.showAndWait();
            return;
        }


        // Vérifier si le prix est un nombre valide
        int balanceE;
        try {
            balanceE = Integer.parseInt(Balance);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Le balance doit être un nombre valide !");
            alert.showAndWait();
            return;
        }

        int statueE;
        try {
            statueE = Integer.parseInt(status);

            // Check if the parsed value is either 0 or 1
            if (statueE != 0 && statueE != 1) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Le statut doit être soit '0 : Wallet non actif ' soit '1 : Wallet actif ' !");
            alert.showAndWait();
            return;
        }






        String code_Etab = code_EtabETF.getText();
        int code_EtabE;

        if (code_Etab.isEmpty()) {
            // Si le TextField est vide, vérifiez si un utilisateur est sélectionné dans la ListView
            Etablissement selectedEtab = ListViewEtab.getSelectionModel().getSelectedItem();
            if (selectedEtab == null) {
                // Si rien n'est saisi et rien n'est sélectionné, afficher une alerte
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez saisir un code_etab ou sélectionner un etablissement !");
                alert.showAndWait();
                return;
            }

            // Utiliser le CIN de l'utilisateur sélectionné dans la ListView
            code_EtabE = selectedEtab.getCodeEtablissement();
        } else {
            try {
                code_EtabE = Integer.parseInt(code_Etab);
            } catch (NumberFormatException e) { Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Le code_etab doit être un nombre valide !");
                alert.showAndWait();
                return;
            }
        }

// Vérifier si un portefeuille existe déjà pour cet établissement
        if (serviceWallet.portefeuilleExistePourEtablissement(code_EtabE)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Un portefeuille existe déjà pour cet établissement !");
            alert.showAndWait();
            return;
        }




        // Créer un nouvel objet Service avec les valeurs saisies
        Wallet wallet = new Wallet();

        wallet.setBalance(balanceE);
        wallet.setStatus(statueE);


        ServiceEtablissement se=new ServiceEtablissement();
        Etablissement etab = se.getOneByCode(code_EtabE);
        wallet.setEtablissement(etab);

        // Ajouter le service à la base de données
        try {
            serviceWallet.ajouter(wallet);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("Wallet ajouté avec succès !");
            alert.showAndWait();

            // Effacer les champs du formulaire après l'ajout réussi
            BalanceETF.clear();
            statusETF.clear();
            code_EtabETF.clear();

            navigatetoAfficherEtablissementAction(actionEvent);

        }

        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Erreur lors de l'ajout de Wallet' : " + e.getMessage());
            alert.showAndWait();
        }



    }

    public void navigatetoAfficherEtablissementAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/market.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) BalanceETF.getScene().getWindow(); // Utilisez la même fenêtre (Stage) actuelle
            stage.setScene(new Scene(root));
            stage.show();

            // Vous pouvez fermer la fenêtre actuelle si nécessaire
            // ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}

