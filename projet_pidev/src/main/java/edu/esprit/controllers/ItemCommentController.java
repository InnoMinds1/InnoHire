package edu.esprit.controllers;

import edu.esprit.entities.Commentaire;
import edu.esprit.services.MyListener;
import edu.esprit.services.ServiceCommentaire;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class ItemCommentController {

    @FXML
    private Label Description;

    @FXML
    private ImageView img;

    @FXML
    private Label rate;
    private MyListener myListener;
    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(commentaire);
    }

    private Commentaire commentaire;



    public void setData(Commentaire commentaire, MyListener myListener) {
        this.commentaire = commentaire;
        this.myListener = myListener;

        Description.setText(commentaire.getDescription_co());
        rate.setText(String.valueOf(commentaire.getNb_etoile()));

    }

    @FXML
    public void modifier(ActionEvent actionEvent) {
        // Code pour modifier l'établissement sélectionné dans la liste
        Commentaire selectedCommentaire = new Commentaire();
        if (selectedCommentaire == null) {
            // Aucun élément sélectionné, afficher une alerte
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avertissement");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un établissement à modifier.");
            alert.showAndWait();
            return; // Sortir de la méthode, car rien à modifier
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierCommentaire.fxml"));
                Parent root = loader.load();
                ModifierCommentaire controller = loader.getController();
                controller.initData(selectedCommentaire);

                // Obtenir la scène actuelle
                Scene scene = ((Node) actionEvent.getSource()).getScene();

                // Changer le contenu de la scène
                scene.setRoot(root);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
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
                int id_Commentaire = commentaire.getId_commentaire();
                ServiceCommentaire serviceCommentaire = new ServiceCommentaire();
                serviceCommentaire.supprimer(id_Commentaire);
                myListener.onDeleteListener(commentaire);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

