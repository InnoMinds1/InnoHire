package edu.esprit.controllers;

import edu.esprit.entities.Quiz;
import edu.esprit.services.quizService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class AjouterQuizController {
    @FXML
    private TextField TFCode;

    @FXML
    private TextField TFDesc;

    @FXML
    private TextField TFNom;

    @FXML
    private TextField TFimage;

    @FXML
    private TextField TFprix;
    @FXML
    void AjouterQuizAction(ActionEvent event) {
        try {
            // Récupérer les valeurs des champs
            int codeQuiz = Integer.parseInt(TFCode.getText());
            String nomQuiz = TFNom.getText();
            String description = TFDesc.getText();
            int prixQuiz = Integer.parseInt(TFprix.getText());
            String imageQuiz = TFimage.getText();

            // Créer une instance de Quiz
            Quiz quiz = new Quiz(codeQuiz, nomQuiz, description, prixQuiz, imageQuiz);

            // Ajouter le quiz à la base de données
            quizService qs=new quizService();
            qs.ajouter(quiz);

            // Afficher une alerte de succès
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Le quiz a été ajouté avec succès.");
            alert.show();
        } catch (NumberFormatException e) {
            // Afficher une alerte d'erreur si une exception de format numérique se produit
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Number Format Exception");
            alert.setContentText("Erreur de format numérique : " + e.getMessage());
            alert.showAndWait();
        } catch (SQLException e) {
            // Afficher une alerte d'erreur si une exception SQL se produit
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("SQL Exception");
            alert.setContentText("Erreur SQL : " + e.getMessage());
            alert.showAndWait();
        }
    }


}
