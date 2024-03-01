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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Set;

public class ModifierWalletController implements Initializable {
    private int idW ;
    private int CodeInit;
    @FXML
   private AnchorPane mainAnchor;
    @FXML
    private TextField BalanceETF;
    @FXML
    private TextField statusETF;

    @FXML
    private TextField dateCreationETF;

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
            setIdW(wallet.getIdWallet());

            BalanceETF.setText(String.valueOf(wallet.getBalance()));
            statusETF.setText(String.valueOf(wallet.getStatus()));
            code_EtabETF.setText(String.valueOf(wallet.getEtablissement().getCodeEtablissement()));
            dateCreationETF.setText(String.valueOf(wallet.getDateCreation()));
            setCodeInit(wallet.getEtablissement().getCodeEtablissement());

        }
    }

    public void okWallet(ActionEvent actionEvent) throws SQLException {
        if (controlSaisie(BalanceETF)&&controlSaisie(statusETF)) {
            int Balance ;
            try {
                Balance = Integer.parseInt(BalanceETF.getText());

            } catch (NumberFormatException e) {
                showAlert(Alert.AlertType.ERROR, "Format invalide", "Balance doit etre un nombre valide.");
                return;
            }
            int status;
            try {
                status = Integer.parseInt(statusETF.getText());
            } catch (NumberFormatException e) {
                showAlert(Alert.AlertType.ERROR, "Format invalide", "status doit etre un nombre valide.");
                return;
            }
            // Additional check for valid status (should be 0 or 1)
            if (status != 0 && status != 1) {
                showAlert(Alert.AlertType.ERROR, "Erreur de statut", "Le statut doit être 0 ou 1.");
                return;
            }



            int code_Etab = Integer.parseInt(code_EtabETF.getText());


            Wallet newWallet = new Wallet();

            newWallet.setIdWallet(getIdW());

            newWallet.setBalance(Balance);
            newWallet.setStatus(status);


            ServiceEtablissement se = new ServiceEtablissement();
            Etablissement etab = se.getOneByCode(code_Etab);


            newWallet.setEtablissement(etab);

            if (getCodeInit() != etab.getCodeEtablissement()) {
                if (serviceWallet.portefeuilleExistePourEtablissement(etab.getCodeEtablissement())) {
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
            AfficherWallet(actionEvent);

        }
    }

    public void AfficherWallet(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherWallet.fxml"));
            Parent root = loader.load();

            // Obtenir la scène actuelle
            Scene currentScene = BalanceETF.getScene();

            // Créer une nouvelle scène avec la même taille que la scène actuelle
            Scene newScene = new Scene(root, currentScene.getWidth(), currentScene.getHeight());

            Stage stage = (Stage) BalanceETF.getScene().getWindow();
            stage.setScene(newScene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}


