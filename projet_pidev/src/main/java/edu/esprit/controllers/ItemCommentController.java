package edu.esprit.controllers;
import edu.esprit.entities.Commentaire;
import edu.esprit.entities.CurrentUser;
import edu.esprit.services.ServiceCommentaire;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class ItemCommentController {

    @FXML
    private HBox hboxMere;

    @FXML
    private Text labelDate;

    @FXML
    private Text labelDescription;

    @FXML
    private Text labelRate;

    @FXML
    private Text labelUser;
    private ServiceCommentaire sc;
    // Assurez-vous d'avoir la référence correcte à votre HBox
    private static HBox hboxSelectionne;

    private Commentaire commentaire;



    public void setData(Commentaire commentaire) {
        this.commentaire = commentaire;
        labelUser.setText(String.valueOf(commentaire.getUtilisateur().getName()));
        labelDescription.setText(String.valueOf(commentaire.getDescription_co()));
        labelDate.setText(String.valueOf(commentaire.getDate_co()));
        labelRate.setText(String.valueOf(commentaire.getNb_etoile()));

    }

    public void modifier(ActionEvent actionEvent) {
        Commentaire selectedCommentaire = commentaire;

        // Check if the current user has the right to modify comments
        if (CurrentUser.getRole() != 0) {
            // Check if the selected comment belongs to the current user
            if (selectedCommentaire.getUtilisateur().getId_utilisateur() == CurrentUser.getId_utilisateur()) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierCommentaire.fxml"));
                    Parent root = loader.load();
                    ModifierCommentaire controller = loader.getController();
                    controller.initData(selectedCommentaire); // Pass the selected comment to the modification interface controller

                    // Get the current scene
                    Scene scene = labelDescription.getScene();

                    // Change the content of the scene
                    scene.setRoot(root);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                // Display a message indicating that the user can only modify their own comments
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Attention");
                alert.setHeaderText(null);
                alert.setContentText("Vous ne pouvez modifier que vos propres commentaires.");
                alert.showAndWait();
            }
        } else {
            // Display a message indicating that the user does not have the right to modify comments
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText(null);
            alert.setContentText("Vous n'avez pas le droit de modifier des commentaires.");
            alert.showAndWait();
        }
    }



    public void supprimer(ActionEvent actionEvent) {

        Commentaire selectedCommentaire = commentaire;

        // Check if the current user's role is not 0
        if (CurrentUser.getRole() != 0) {
            // Check if the selected comment belongs to the current user
            if (selectedCommentaire.getUtilisateur().getId_utilisateur() == CurrentUser.getId_utilisateur()) {
                // If an element is selected, show the confirmation of deletion
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation de suppression");
                alert.setHeaderText(null);
                alert.setContentText("Êtes-vous sûr de vouloir supprimer ce commentaire ?");

                Optional<ButtonType> result = alert.showAndWait();

                if (result.isPresent() && result.get() == ButtonType.OK) {
                    try {
                        int id_commentaire = selectedCommentaire.getId_commentaire();
                        ServiceCommentaire serviceCommentaire = new ServiceCommentaire();
                        serviceCommentaire.supprimer(id_commentaire);
                        actualiserVueQuestions();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                // Display a message indicating that the user can only delete their own comments
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Attention");
                alert.setHeaderText(null);
                alert.setContentText("Vous ne pouvez supprimer que vos propres commentaires.");
                alert.showAndWait();
            }
        } else {
            // Display a message indicating that the user does not have the right to delete comments
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText(null);
            alert.setContentText("Vous n'avez pas le droit de supprimer des commentaires.");
            alert.showAndWait();
        }
    }

    public void actualiserVueQuestions() {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AfficherCommentaire2.fxml"));
            labelDescription.getScene().setRoot(root);
        } catch (IOException e) {

            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("Une erreur s'est produite lors de la redirection.");
            errorAlert.setTitle("Erreur de redirection");
            errorAlert.show();
        }
    }


}