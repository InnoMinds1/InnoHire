package edu.esprit.controllers;


import edu.esprit.entities.Question;
import edu.esprit.services.quizService;
import edu.esprit.utils.DataSource;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AfficherQuestionController {
    @FXML
    private Button TF_modifier;

    @FXML
    private Button TF_supprimer;

    @FXML
    private ListView<Question> TFaffiche;
    private List<Question> questions;

    public AfficherQuestionController() {

        questions = fetchQuestionsFromDatabase();
    }

    @FXML
    private void initialize() {

        for (Question question : questions) {
            TFaffiche.getItems().add(question);
        }
    }
    private int getCodeQuizbyID(int idQuiz) throws SQLException {
        Connection connection = DataSource.getInstance().getCnx();

        try {
            String sql = "SELECT code_quiz FROM quiz WHERE id_quiz = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, idQuiz);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("code_quiz");
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return -1;
    }



    private List<Question> fetchQuestionsFromDatabase() {
        List<Question> questionList = new ArrayList<>();

        try (Connection connection = DataSource.getInstance().getCnx();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT question, choix, id_quiz FROM question");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String questionText = resultSet.getString("question");
                String choix = resultSet.getString("choix");
                int idQuiz = resultSet.getInt("id_quiz");


                int codeQuiz = getCodeQuizbyID(idQuiz);
                quizService q= new quizService();


                Question question = new Question(questionText, choix, q.getOneByCode(codeQuiz));
                questionList.add(question);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Gérez les exceptions SQLException selon vos besoins
        }

        return questionList;
    }




    @FXML
    private void handleSupprimerButton() {
        int selectedIndex = TFaffiche.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            TFaffiche.getItems().remove(selectedIndex);
            questions.remove(selectedIndex);
        }
    }

}
