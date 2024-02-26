package edu.esprit.controllers;

import edu.esprit.entities.Etablissement;
import edu.esprit.entities.Wallet;
import edu.esprit.services.ServiceEtablissement;
import edu.esprit.services.ServiceWallet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import edu.esprit.tests.MainFX;
import edu.esprit.services.MyListener;
import edu.esprit.entities.Fruit;

import java.sql.SQLException;
import java.util.Optional;

public class ItemController {
    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLable;

    @FXML
    private ImageView img;

    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(etablissement);
    }

    private Etablissement etablissement;
    private MyListener myListener;


    public void setData(Etablissement etablissement, MyListener myListener) {
        this.etablissement = etablissement;
        this.myListener = myListener;
        nameLabel.setText(etablissement.getNom());

      //  priceLable.setText(MainFX.CURRENCY + etablissement.getPrice());
        Image image = new Image(getClass().getResourceAsStream(etablissement.getImage()));
        img.setImage(image);
    }

    public void modifier(ActionEvent actionEvent) {

    }

    public void supprimer(ActionEvent actionEvent) {

        // Si un élément est sélectionné, afficher la confirmation de suppression
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de vouloir supprimer cette conversation ?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                int idEtablissement = etablissement.getIdEtablissement();
                ServiceEtablissement serviceEtablissement = new ServiceEtablissement();
                serviceEtablissement.supprimer(idEtablissement);
                myListener.onDeleteListener(etablissement);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
