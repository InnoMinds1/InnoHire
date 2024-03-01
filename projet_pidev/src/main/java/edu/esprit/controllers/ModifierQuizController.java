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
import java.sql.SQLException;

public class ModifierQuizController {

    @FXML
    private TextField TFCode1;

    @FXML
    private TextField TFDesc1;

    @FXML
    private TextField TFNom1;

    @FXML
    private TextField TFprix1;

    @FXML
    private TextField imageView1;

    @FXML
    private ImageView imageViewQ1;

    private Quiz quiz;
    private quizService qs;

    public void setData(Quiz quiz) {
        this.quiz = quiz;
        TFCode1.setText(String.valueOf(quiz.getCode_quiz()));
        TFNom1.setText(quiz.getNom_quiz());
        TFDesc1.setText(quiz.getDescription());
        TFprix1.setText(String.valueOf(quiz.getPrix_quiz()));

        // Afficher l'image actuelle du quiz
        String imagePath = quiz.getImage_quiz();
        if (imagePath != null && !imagePath.isEmpty()) {
            Image image = new Image(imagePath);
            imageViewQ1.setImage(image);
        }
    }
@FXML

void ModifierQuizAction(ActionEvent event) {
    try {
        // Récupérer les valeurs des champs
        String codeQuizText = TFCode1.getText();
        String nomQuiz = TFNom1.getText();
        String description = TFDesc1.getText();
        String prixQuizText = TFprix1.getText();

        // Vérifier si tous les champs sont remplis
        if (codeQuizText.isEmpty() || nomQuiz.isEmpty() || description.isEmpty() || prixQuizText.isEmpty()) {
            // Afficher une alerte en cas de champs manquants
            showAlert("Champs manquants", "Veuillez remplir tous les champs.");
            return;
        }

        // Convertir les valeurs en types appropriés
        int codeQuiz = Integer.parseInt(codeQuizText);

        // Ajouter ici le même scénario de contrôle de saisie pour la description que dans AjouterQuizAction
        if (!isValidDifficulty(description)) {
            // Afficher une alerte si la description n'est pas valide
            showAlert("Erreur de saisie", "La description doit être 'facile', 'moyen' ou 'difficile'.");
            return;
        }

        int prixQuiz = Integer.parseInt(prixQuizText);

        // Ajouter ici le même scénario de contrôle de saisie pour le prix que dans AjouterQuizAction
        if (isValidDifficulty(description)) {
            if (description.equalsIgnoreCase("facile") && (prixQuiz < 0 || prixQuiz > 15)) {
                showAlert("Erreur de saisie", "Le prix du quiz pour la description 'facile' doit être dans la plage [0..15].");
                return;
            } else if (description.equalsIgnoreCase("moyen") && (prixQuiz < 16 || prixQuiz > 30)) {
                showAlert("Erreur de saisie", "Le prix du quiz pour la description 'moyen' doit être dans la plage [16..30].");
                return;
            } else if (description.equalsIgnoreCase("difficile") && (prixQuiz < 31 || prixQuiz > 49)) {
                showAlert("Erreur de saisie", "Le prix du quiz pour la description 'difficile' doit être dans la plage [31..49].");
                return;
            }
        } else {
            showAlert("Erreur de saisie", "La description doit être 'facile', 'moyen' ou 'difficile'.");
            return;
        }


        // Mettre à jour les attributs du quiz
        quiz.setCode_quiz(codeQuiz);
        quiz.setNom_quiz(nomQuiz);
        quiz.setDescription(description);
        quiz.setPrix_quiz(prixQuiz);

        // Mettre à jour le chemin de l'image dans le quiz
        quiz.setImage_quiz(imageView1.getText());

        if (qs == null) {
            qs = new quizService();
        }

        qs.modifier(quiz);

        // Afficher une alerte de succès
        showAlert("Success", "Le quiz a été modifié avec succès.");

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
    private boolean isValidDifficulty(String description) {
        return description.equalsIgnoreCase("facile") || description.equalsIgnoreCase("moyen") || description.equalsIgnoreCase("difficile");
    }



    // Méthode pour afficher une alerte
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private void importImage1(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            imageView1.setText(selectedFile.getAbsolutePath());

            Image image = new Image(selectedFile.toURI().toString());
            imageViewQ1.setImage(image);
        }
    }
    public void navigateToAfficher(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherQuiz.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) TFCode1.getScene().getWindow(); // Utilisez la même fenêtre (Stage) actuelle
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.sizeToScene(); // Redimensionne le stage pour s'adapter à la taille de la scène
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
