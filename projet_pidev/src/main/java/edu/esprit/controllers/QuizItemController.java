package edu.esprit.controllers;

import edu.esprit.entities.Question;
import edu.esprit.entities.Quiz;
import edu.esprit.services.questionService;
import edu.esprit.services.quizService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.ButtonType;
import javafx.scene.text.Text;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
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
        System.out.println("Chemin de l'image : " + quiz.getImage_quiz());
        try {
            // Utiliser le chemin relatif stocké dans la base de données
            String imageUrl = quiz.getImage_quiz();
            Image image = new Image(imageUrl);
            imageView.setImage(image);
        } catch (Exception e) {
            // Gérer les exceptions, par exemple, afficher une image par défaut ou un message d'erreur
            e.printStackTrace();
        }
        labelDesc.setText(quiz.getDescription());
        labelCode.setText(String.valueOf(quiz.getCode_quiz()));
        LabelPrix.setText(String.valueOf(quiz.getPrix_quiz()));



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


}
