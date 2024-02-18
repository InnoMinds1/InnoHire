package edu.esprit.controllers;

import edu.esprit.entities.Question;
import edu.esprit.services.questionService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import edu.esprit.services.quizService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
public class AjouterQuestionController {


        @FXML
        private ComboBox<Integer> TFid_quiz;

        @FXML
        private TextField TFchoix;

        @FXML
        private TextField TFquestion;

        private final questionService qs = new questionService();
        private final quizService quizService = new quizService();

        @FXML
        public void initialize() {
            try {
                List<Integer> quizIds = quizService.getQuizIds();
                TFid_quiz.getItems().addAll(quizIds);
            } catch (SQLException e) {

                e.printStackTrace();
            }
        }

        @FXML
        void AjouterQuestionAction(ActionEvent event) throws SQLException {
            try {
                int idQuiz = TFid_quiz.getValue();
                this.qs.ajouter(new Question(TFquestion.getText(), TFchoix.getText(), idQuiz));
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("GG");
                alert.show();
            } catch (NumberFormatException e) {
                // Gérer les erreurs de conversion
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("SQL Exception");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
    }


