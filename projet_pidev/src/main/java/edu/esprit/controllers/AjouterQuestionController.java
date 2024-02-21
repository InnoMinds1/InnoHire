package edu.esprit.controllers;

import edu.esprit.entities.Question;
import edu.esprit.entities.Quiz;
import edu.esprit.services.questionService;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import edu.esprit.services.quizService;
import edu.esprit.utils.DataSource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
        private ComboBox<Integer> TFcode_quiz;

        @FXML
        private TextField TFchoix;

        @FXML
        private TextField TFquestion;

        private final questionService qs = new questionService();
        private final quizService quizService = new quizService();

    @FXML
    public void initialize() {
        List<Integer> codesQuiz;
        try {
            codesQuiz = quizService.getQuizcodes();
        } catch (SQLException e) {

            e.printStackTrace();
            return;
        }


        ObservableList<Integer> observableCodesQuiz = FXCollections.observableArrayList(codesQuiz);
        TFcode_quiz.setItems(observableCodesQuiz);
    }





        @FXML
        void AjouterQuestionAction(ActionEvent event) throws SQLException {
            try {

                Integer codeQuiz = TFcode_quiz.getValue();


                int idQuiz = getIdQuizByCode(codeQuiz);
                quizService qs= new quizService();

                if (idQuiz != -1) {
                    this.qs.ajouter(new Question(TFquestion.getText(), TFchoix.getText(), qs.getOneByID(idQuiz)));
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setContentText("GG");
                    alert.show();
                } else {

                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setContentText("Impossible de trouver l'id_quiz pour le code_quiz sélectionné.");
                    alert.showAndWait();
                }
            } catch (NumberFormatException e) {

                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("SQL Exception");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }


    private int getIdQuizByCode(Integer codeQuiz) throws SQLException {

        Connection connection = DataSource.getInstance().getCnx();

        try {
            String sql = "SELECT id_quiz FROM quiz WHERE code_quiz = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, codeQuiz);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("id_quiz");
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return -1;
    }

}


