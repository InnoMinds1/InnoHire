package edu.esprit.controllers;

import edu.esprit.entities.Question;
import edu.esprit.entities.Quiz;
import edu.esprit.services.questionService;
import edu.esprit.services.quizService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.ButtonType;
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
        LabelPrix.setText(String.valueOf(quiz.getPrix_quiz()));

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
            // Initialiser le serviceQuestion si ce n'est pas déjà fait
            if (qs == null) {
                qs = new quizService();
            }

            // Charger la vue de modification de question
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierQuiz.fxml"));
            Parent root = loader.load();

            // Passer la question à modifier au contrôleur de modification
            ModifierQuizController controller = loader.getController();
            controller.setData(quiz);

            // Afficher la vue de modification de question
            labelDesc.getScene().setRoot(root);
        } catch (IOException e) {
            // Gérer les exceptions liées au chargement de la vue de modification
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Erreur lors de la modification du quiz.");
            alert.setTitle("Erreur de modification");
            alert.show();
        }
    }





}
