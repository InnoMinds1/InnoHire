package edu.esprit.controllers;

import edu.esprit.entities.Etablissement;
import edu.esprit.entities.Question;
import edu.esprit.entities.Quiz;
import edu.esprit.entities.CurrentWallet;
import edu.esprit.services.questionService;
import edu.esprit.services.quizService;
import edu.esprit.utils.DataSource;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class QuizPourAcheterController {


    @FXML
    private Text TFnomA;

    @FXML
    private Text TFprixA;

    @FXML
    private Button btnAcheter;

    @FXML
    private ImageView imageView;
    private Quiz quiz;
    private quizService qs;

    public void setData(Quiz quiz) {
        this.quiz = quiz;
        TFnomA.setText(quiz.getNom_quiz());

        TFprixA.setText(String.valueOf(quiz.getPrix_quiz())+"DT");

        // Charger et afficher l'image du Quiz
        String imagePath = quiz.getImage_quiz();
        if (imagePath != null && !imagePath.isEmpty()) {
            Image image = new Image(imagePath);
            imageView.setImage(image);
        }
    }

    @FXML
    void AcheterQuizOnClick(ActionEvent event) {
        try {
            int idQuiz = quiz.getId_quiz();
            Etablissement e=new Etablissement();
            CurrentWallet.setEtablissement(e);
            int idEtablissement = CurrentWallet.getEtablissement().getIdEtablissement();
            Connection cnx = DataSource.getInstance().getCnx();

            // Exécutez la requête d'insertion
            String query = "INSERT INTO etablissement_quiz (id_etablissement, id_quiz) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
                preparedStatement.setInt(1, idEtablissement);
                preparedStatement.setInt(2, idQuiz);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    // Afficher une alerte de succès
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Achat réussi");
                    alert.setContentText("Achat du quiz réussi.");
                    alert.show();
                } else {
                    // Afficher une alerte d'échec
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Échec de l'achat");
                    alert.setContentText("Échec de l'achat du quiz.");
                    alert.show();
                }
            }
            } catch (SQLException e) {
                e.printStackTrace();
                // Gérer les exceptions SQL, par exemple afficher un message d'erreur ou lancer une nouvelle exception

            } catch (Exception e) {
                e.printStackTrace();
                // Gérer les exceptions, par exemple afficher un message d'erreur
            }


        }

    @FXML
    private void showQuizDetails(ActionEvent event) {
        int codeQuiz = quiz.getCode_quiz();
        if (qs == null) {
            qs = new quizService();
        }

        // Récupérer les questions associées à ce code de quiz
        List<Question> questions = qs.getQuestionsByCodeQuiz(codeQuiz);

        // Créer une ListView pour afficher les détails des questions
        ListView<HBox> listView = new ListView<>();
        questionService qs1 = new questionService();

        // Ajouter des éléments HBox pour chaque question
        for (Question question : questions) {
            // Ajouter seulement l'attribut "question" et "reponse_correcte"
            String questionDetails = "Question: " + question.getQuestion() + "\n" + "Les choix:" + question.getChoix() +
                    "\nRéponse correcte: " + question.getReponse_correcte() + "\n";

            HBox hbox = new HBox(new Label(questionDetails));
            hbox.setSpacing(10);

            // Ajouter la ligne entière (Label) à la liste d'affichage
            listView.getItems().add(hbox);


            // Créer une boîte pour contenir la ListView
            VBox vbox = new VBox(listView);
            vbox.setPadding(new Insets(10));

            // Afficher la boîte de dialogue
            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setTitle("Questions associées au quiz");
            alert.getDialogPane().setContent(vbox);

            // Ajouter un bouton "Fermer"
            ButtonType closeButton = new ButtonType("Question suivante", ButtonBar.ButtonData.OK_DONE);
            alert.getButtonTypes().add(closeButton);

            // Afficher la boîte de dialogue
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.showAndWait();
        }


    }
}