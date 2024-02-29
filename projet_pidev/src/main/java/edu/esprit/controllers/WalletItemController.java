package edu.esprit.controllers;

import edu.esprit.entities.CurrentUser;
import edu.esprit.entities.Wallet;
import edu.esprit.services.ServiceWallet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class WalletItemController implements Initializable {
    @FXML
    private Text LabelBalance;

    @FXML
    private HBox hboxMere;

    @FXML
    private Text labelCode;

    @FXML
    private Text labelDate;

    @FXML
    private Text labelStatus;
    @FXML
    private Button modifierBtn;


    private Wallet wallet;

    private ServiceWallet sw;
    // Assurez-vous d'avoir la référence correcte à votre HBox
    private static HBox hboxSelectionne; // Champ statique pour suivre le HBox précédemment sélectionné


    public void setData(Wallet wallet) {
        this.wallet = wallet;
        LabelBalance.setText(String.valueOf(wallet.getBalance()));
        labelCode.setText(String.valueOf(wallet.getEtablissement().getCodeEtablissement()));
        labelDate.setText(String.valueOf(wallet.getDateCreation()));
        int status = wallet.getStatus();
        labelStatus.setText(status != 0 ? "Actif" : "Non Actif");


    }

    public void modifier(ActionEvent actionEvent) {


        // Code pour modifier l'utilisateur sélectionné dans la liste
        Wallet selectedWallet = wallet;


        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierWallet.fxml"));
            Parent root = loader.load();
            ModifierWalletController controller = loader.getController();
            controller.initDataWallet(selectedWallet); // Passer l'utilisateur sélectionné au contrôleur de l'interface de modification

            // Obtenir la scène actuelle
            Scene scene = labelCode.getScene();

            // Changer le contenu de la scène
            scene.setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void supprimer(ActionEvent actionEvent) {

        Wallet selectedWallet = wallet;

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
                actualiserVueQuestions();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void actualiserVueQuestions() {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Etablissement.fxml"));
            labelCode.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("Une erreur s'est produite lors de la redirection.");
            errorAlert.setTitle("Erreur de redirection");
            errorAlert.show();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (CurrentUser.getRole() != 0) {
            modifierBtn.setVisible(false);
            modifierBtn.setManaged(false);
        }
    }

}
    // ... (autres méthodes)



