package edu.esprit.controllers;

import edu.esprit.entities.CurrentEtablissement;
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
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

public class AfficherWalletController extends AjouterEtablissementController implements Initializable {
  // Assurez-vous que l'attribut fx:id correspond à celui dans votre fichier FXML



    @FXML
    private AnchorPane NavBar;
    @FXML
    private AnchorPane grandAnchor;
    @FXML
    private AnchorPane anchorContenu;
    @FXML
    private AnchorPane selecUserAnchor;
    @FXML
    private GridPane gridA;
    @FXML
    private ScrollPane scrollA;



    ServiceWallet serviceW = new ServiceWallet();




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Set<Wallet> setW;


            //-----------------------Navebar setup
            NavBar.setVisible(false);
            NavBar.setManaged(false);

            anchorContenu.setLayoutX(0);
            grandAnchor.setPrefWidth(anchorContenu.getPrefWidth());
            //-------------------------------------

            ServiceEtablissement se = new ServiceEtablissement();
            Etablissement etablassimentConnecte;
            try {
                etablassimentConnecte = se.getOneByID(CurrentEtablissement.getIdEtablissement());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            Wallet walletConnecte = se.getWalletByEtablissement(etablassimentConnecte);
            setW = new HashSet<>(); // Créez un nouvel ensemble
            setW.add(walletConnecte); // Ajoutez le walletConnecte à l'ensemble







        int column = 0;
        int row = 1;
        try {
            for (Wallet wallet : setW) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/WalletItem.fxml"));
                HBox hbox = fxmlLoader.load();

                WalletItemController itemController = fxmlLoader.getController();

                itemController.setData(wallet);

                if (column == 1) {
                    column = 0;
                    row++;
                }

                gridA.add(hbox, column++, row);
                gridA.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridA.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridA.setMaxWidth(Region.USE_PREF_SIZE);
                gridA.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridA.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridA.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(hbox, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }




    }







    public void afiicherEtablissement(MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Etablissement.fxml")));
            gridA.getScene().setRoot(root);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Sorry");
            alert.setTitle("Error");
            alert.show();
        }

    }
}

