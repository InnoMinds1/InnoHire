package edu.esprit.controllers;

import edu.esprit.entities.Question;
import edu.esprit.entities.Quiz;
import edu.esprit.services.questionService;
import edu.esprit.services.quizService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class QuizItemController {
    @FXML
    private ImageView imageView;

    @FXML
    private Text LabelNom;

    @FXML
    private Text LabelPrix;

    @FXML
    private Button btnModifier;

    @FXML
    private Button btnSupprimer;

    @FXML
    private Text labelCode;

    @FXML
    private Text labelDesc;
    private Quiz quiz ;
    private quizService qs;



    public void setData(Quiz quiz) {
        this.quiz = quiz;
        LabelNom.setText(quiz.getNom_quiz());
        labelDesc.setText(quiz.getDescription());
        labelCode.setText(String.valueOf(quiz.getCode_quiz()));
        LabelPrix.setText(String.valueOf(quiz.getPrix_quiz())+"DT");

        // Charger et afficher l'image du Quiz
        String imagePath = quiz.getImage_quiz();
        if (imagePath != null && !imagePath.isEmpty()) {
            Image image = new Image(imagePath);
            imageView.setImage(image);
        }
    }



    public void actualiserVueQuiz() {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AfficherQuiz.fxml"));
            LabelPrix.getScene().setRoot(root);
        } catch (IOException e) {

            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("Une erreur s'est produite lors de la redirection.");
            errorAlert.setTitle("Erreur de redirection");
            errorAlert.show();
        }
    }

    @FXML
    void supprimerQuizOnClick() throws SQLException {
        if (quiz != null) {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirmation de suppression");
            confirmationAlert.setHeaderText(null);
            confirmationAlert.setContentText("Êtes-vous sûr de vouloir supprimer ce quiz ?");

            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                if (qs == null) {
                    qs = new quizService();
                }
                qs.supprimer(quiz.getId_quiz());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Quiz a été supprimé avec succès.");
                alert.setTitle("Quiz supprimé");
                alert.show();
                actualiserVueQuiz();
            }
        } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("Impossible de supprimer le quiz car aucun quiz n'est sélectionnée.");
            errorAlert.setTitle("Erreur de suppression");
            errorAlert.show();
        }
    }
    public void modifierQuizOnClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierQuiz.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) labelCode.getScene().getWindow(); // Utilisez la même fenêtre (Stage) actuelle
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.sizeToScene(); // Redimensionne le stage pour s'adapter à la taille de la scène
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
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
        questionService qs1=new questionService();

        // Ajouter des boutons de suppression à côté de chaque question
        for (Question question : questions) {
            Button deleteButton = new Button("Supprimer Question");
            deleteButton.setOnAction(event1 -> {
                try {
                    qs1.supprimer(question.getId_question());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });

            // Ajouter seulement l'attribut "question" et "reponse_correcte"
            String questionDetails = "Question: " + question.getQuestion() +"\n"+ "Les choix:"+ question.getChoix()+
                    "\nRéponse correcte: " + question.getReponse_correcte() + "\n"
                    ;

            HBox hbox = new HBox(new Label(questionDetails), deleteButton);
            hbox.setSpacing(10);

            // Ajouter la ligne entière (Label + Button) à la liste d'affichage
            listView.getItems().add(hbox);
        }

        // Créer une boîte pour contenir la ListView
        VBox vbox = new VBox(listView);
        vbox.setPadding(new Insets(10));

        // Afficher la boîte de dialogue
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Questions associées au quiz");
        alert.getDialogPane().setContent(vbox);

        // Ajouter un bouton "Fermer"
        ButtonType closeButton = new ButtonType("Fermer", ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().add(closeButton);

        // Afficher la boîte de dialogue
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.showAndWait();
    }



}











