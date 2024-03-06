package edu.esprit.controllers;

import edu.esprit.entities.Question;
import edu.esprit.entities.Quiz;
import edu.esprit.services.questionService;
import edu.esprit.services.quizService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QuizParEtablissementItemController {
    @FXML
    private Button btnPasser;

    @FXML
    private Text TFcodeA ;
    @FXML
    private Text TFnomA;
    questionService qs=new questionService();
    quizService qs1=new quizService();

    public QuizParEtablissementItemController() throws SQLException {
    }

    public void setQuizData(Quiz quiz) {
        // Personnalisez l'affichage en fonction des données du quiz
        TFnomA.setText(quiz.getNom_quiz());
        TFcodeA.setText(String.valueOf(quiz.getCode_quiz()));

        // Autres personnalisations si nécessaire
    }
    @FXML
    void PasserQuiz(ActionEvent event) throws SQLException {
        // Obtenez le code du quiz à partir du Text (convertissez-le en int si nécessaire)
        int codeQuiz = Integer.parseInt(TFcodeA.getText());
        System.out.println("Code du quiz à récupérer : " + codeQuiz);

        Quiz quiz = qs1.getQuizByCode(codeQuiz);

        // Appelez la méthode pour obtenir les questions, choix et réponses correctes
        List<Map<String, Object>> questionsData = qs.getQuestionsForQuiz(codeQuiz);

        // Créez une liste de questions
        List<Question> questions = new ArrayList<>();
        System.out.println("Nombre de questions récupérées : " + questionsData.size());

        // Maintenant, questionsData contient les données nécessaires pour chaque question du quiz
        // Vous pouvez traiter ces données selon vos besoins
        for (Map<String, Object> questionData : questionsData) {
            String questionText = (String) questionData.get("question");
            String choix = (String) questionData.get("choix");
            int reponseCorrecte = (int) questionData.get("reponse_correcte");


            // Créez un objet Question avec les données récupérées
            Question question = new Question(questionText, choix, quiz, reponseCorrecte);
            questions.add(question);
        }

        // Affichez les questions dans la vue QuizToDo
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/QuizToDo.fxml"));
        Parent root;
        try {
            root = loader.load();
            QuizToDoController quizToDoController = loader.getController();
            quizToDoController.displayQuestions(questions);

            // Créez une nouvelle scène avec la vue QuizToDo
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Quiz To Do");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
