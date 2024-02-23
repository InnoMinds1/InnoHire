package edu.esprit.controllers;

import edu.esprit.entities.Etablissement;
import edu.esprit.entities.Utilisateur;
import edu.esprit.entities.Wallet;
import edu.esprit.services.ServiceEtablissement;
import edu.esprit.services.ServiceUtilisateur;
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

public class ModifierWallet implements Initializable {
    private int idW ;
    private int CodeInit;
    @FXML
    private TextField BalanceETF;

    @FXML
    private ListView<Etablissement> ListViewEtab;

    @FXML
    private TextField code_EtabETF;
    private final ServiceWallet serviceWallet = new ServiceWallet();
    public int getIdW() {
        return idW;
    }

    public void setIdW(int idW) {
        this.idW = idW;
    }

    public int getCodeInit() {
        return CodeInit;
    }

    public void setCodeInit(int codeInit) {
        CodeInit = codeInit;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
                code_EtabETF.setText(String.valueOf(newValue.getCode_etablissement()));
            }
        });

    }
    public static void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public boolean controlSaisie(TextField field) {
        if (field.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Champ vide", "Veuillez remplir tous les champs.");
            return false;
        }
        return true;
    }

    public void initDataWallet(Wallet wallet) {
        if (wallet != null) {
            setIdW(wallet.getId_wallet());

            BalanceETF.setText(String.valueOf(wallet.getBalance()));

            code_EtabETF.setText(String.valueOf(wallet.getEtablissement().getCode_etablissement()));
            setCodeInit(wallet.getEtablissement().getCode_etablissement());

        }
    }

    public void okWallet(ActionEvent actionEvent) throws SQLException {
        if (controlSaisie(BalanceETF))
        {
            int Balance = Integer.parseInt(BalanceETF.getText());


            if (code_EtabETF.getText().isEmpty()) {
                // Si le TextField est vide, vérifiez si un utilisateur est sélectionné dans la ListView
                Etablissement selectedEtab = ListViewEtab.getSelectionModel().getSelectedItem();
                if (selectedEtab == null) {
                    // Si rien n'est saisi et rien n'est sélectionné, afficher une alerte
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Veuillez saisir un code ou sélectionner un etablissement !");
                    alert.showAndWait();
                    return;
                }
            }



            int code_Etab = Integer.parseInt(code_EtabETF.getText());


            Wallet newWallet = new Wallet();

            newWallet.setId_wallet(getIdW());

            newWallet.setBalance(Balance);



            ServiceEtablissement se=new ServiceEtablissement();
            Etablissement etab=se.getOneByCode(code_Etab);


            newWallet.setEtablissement(etab);

if (getCodeInit()!=etab.getCode_etablissement()) {
    if (serviceWallet.portefeuilleExistePourEtablissement(etab.getCode_etablissement())) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Erreur : Cet établissement est déjà associé à un portefeuille. La modification n'est pas autorisée.");
        alert.showAndWait();
        return;
    }
}


            serviceWallet.modifier(newWallet);

            showAlert(Alert.AlertType.INFORMATION, "Succès", "Service modifié avec succès");
            AfficherEtablissement(actionEvent);

        }
    }

    public void AfficherEtablissement(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherEtablissement.fxml"));
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


