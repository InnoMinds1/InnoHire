package edu.esprit.controllers;

import edu.esprit.entities.Quiz;
import edu.esprit.services.quizService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;

public class AjouterQuizController {
    @FXML
    private TextField TFCode;

    @FXML
    private TextField TFDesc;

    @FXML
    private TextField TFNom;

    @FXML
    private TextField imageView;

    @FXML
    private ImageView imageViewQ;

    @FXML
    private TextField TFprix;
    @FXML

    private void AjouterQuizAction(ActionEvent event) {
        try {
            // Récupérer les valeurs des champs
            String codeQuizText = TFCode.getText();
            String nomQuiz = TFNom.getText();
            String description = TFDesc.getText();
            String prixQuizText = TFprix.getText();
            String nomImage = imageView.getText(); // Assurez-vous que vous récupérez le chemin correctement

            // Vérifier si tous les champs sont remplis
            if (codeQuizText.isEmpty() || nomQuiz.isEmpty() || description.isEmpty() || prixQuizText.isEmpty() || nomImage.isEmpty()) {
                // Afficher une alerte en cas de champs manquants
                showAlert("Champs manquants", "Veuillez remplir tous les champs.");
                return;
            }

            // Convertir les valeurs en types appropriés
            int codeQuiz = Integer.parseInt(codeQuizText);
            int prixQuiz = Integer.parseInt(prixQuizText);

            // Vérifier que prixQuiz est supérieur à 0 et inférieur à 50
            if (prixQuiz <= 0 || prixQuiz >= 50) {
                // Afficher une alerte en cas de prix incorrect
                showAlert("Erreur de saisie", "Le prix du quiz doit être supérieur à 0 et inférieur à 50.");
                return;
            }

            // Créer une instance de Quiz
            Quiz quiz = new Quiz(codeQuiz, nomQuiz, description, prixQuiz, nomImage);

            // Ajouter le quiz à la base de données
            quizService qs = new quizService();
            qs.ajouter(quiz);

            // Afficher une alerte de succès
            showAlert("Success", "Le quiz a été ajouté avec succès.");

        } catch (NumberFormatException e) {
            // Afficher une alerte d'erreur si une exception de format numérique se produit
            e.printStackTrace();
            showAlert("Number Format Exception", "Erreur de format numérique : " + e.getMessage());
        } catch (SQLException e) {
            // Afficher une alerte d'erreur si une exception SQL se produit
            e.printStackTrace();
            showAlert("SQL Exception", "Erreur SQL : " + e.getMessage());
        }
    }

    // Méthode pour afficher une alerte
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
    public void navigateToAfficher(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherQuiz.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) TFCode.getScene().getWindow(); // Utilisez la même fenêtre (Stage) actuelle
            stage.setScene(new Scene(root));
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void importImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            // Mettre à jour le champ d'image avec le chemin de l'image sélectionnée
            imageView.setText(selectedFile.getAbsolutePath());

            // Charger l'image sélectionnée dans l'ImageView
            Image image = new Image(selectedFile.toURI().toString());
            imageViewQ.setImage(image);
        }
    }

}
