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
            int codeQuiz = Integer.parseInt(TFCode1.getText());
            String nomQuiz = TFNom1.getText();
            String description = TFDesc1.getText();
            int prixQuiz = Integer.parseInt(TFprix1.getText());

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

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Le quiz a été modifié avec succès.");
            alert.show();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Number Format Exception");
            alert.setContentText("Erreur de format numérique : " + e.getMessage());
            alert.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("SQL Exception");
            alert.setContentText("Erreur SQL : " + e.getMessage());
            alert.showAndWait();
        }
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
            stage.setScene(new Scene(root));
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
