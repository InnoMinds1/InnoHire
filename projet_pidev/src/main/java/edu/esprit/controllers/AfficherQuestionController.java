package edu.esprit.controllers;


import edu.esprit.entities.Question;
import edu.esprit.entities.Quiz;
import edu.esprit.services.questionService;
import edu.esprit.services.quizService;
import edu.esprit.utils.DataSource;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class AfficherQuestionController {
    @FXML
    private Button TF_modifier;

    @FXML
    private Button TF_supprimer;

    @FXML
    private ListView<Question> TFaffiche;
    private List<Question> questions;
    private final questionService serviceQ = new questionService();
    Connection connection = DataSource.getInstance().getCnx();

    public AfficherQuestionController() throws SQLException {

        questions = fetchQuestionsFromDatabase();
    }

    @FXML
    private void initialize() {

        for (Question question : questions) {
            TFaffiche.getItems().add(question);
        }
    }

    private int getCodeQuizbyID(int idQuiz) throws SQLException {


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


    private List<Question> fetchQuestionsFromDatabase() throws SQLException {
        List<Question> questionList = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT question, choix, id_quiz FROM question");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String questionText = resultSet.getString("question");
                String choix = resultSet.getString("choix");
                int idQuiz = resultSet.getInt("id_quiz");

                int codeQuiz = getCodeQuizbyID(idQuiz);
                quizService q = new quizService();

                Quiz quiz = q.getOneByCode(codeQuiz);

                if (quiz != null) {
                    Question question = new Question(questionText, choix, quiz);
                    questionList.add(question);
                } else {
                    System.err.println("Quiz is null for codeQuiz: " + codeQuiz);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérez les exceptions SQLException selon vos besoins
        }

        return questionList;
    }





    @FXML
    void SupprimerQuestion(ActionEvent event) {
        Question selectedQ = TFaffiche.getSelectionModel().getSelectedItem();
        if (selectedQ != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de suppression");
            alert.setHeaderText(null);
            alert.setContentText("Êtes-vous sûr de vouloir supprimer cette question ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    int id_question = selectedQ.getId_question();
                    questionService Service = new questionService();
                    Service.supprimer(id_question);
                    TFaffiche.getItems().remove(selectedQ);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


    }



    public void ajouterQuestion(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AjouterQuestion.fxml"));
            TFaffiche.getScene().setRoot(root);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Sorry");
            alert.setTitle("Error");
            alert.show();
        }


    }

    public void navigateToAjouter(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterQuestion.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) TFaffiche.getScene().getWindow(); // Utilisez la même fenêtre (Stage) actuelle
            stage.setScene(new Scene(root));
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}












