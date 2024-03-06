package edu.esprit.controllers;

import edu.esprit.entities.Quiz;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class QuizParEtablissementItemController {
    @FXML
    private Button btnPasser;

    @FXML
    private Text TFnomA;
    public void setQuizData(Quiz quiz) {
        // Personnalisez l'affichage en fonction des données du quiz
        TFnomA.setText(quiz.getNom_quiz());
        // Autres personnalisations si nécessaire
    }
}
