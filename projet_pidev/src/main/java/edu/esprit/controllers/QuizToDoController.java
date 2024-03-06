package edu.esprit.controllers;

import edu.esprit.entities.Question;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class QuizToDoController {

    @FXML
    private VBox LVquestion;

    @FXML
    private Button btnValider;

    private List<Integer> reponsesCorrectes;

    public void displayQuestions(List<Question> questions) {
        LVquestion.getChildren().clear();
        reponsesCorrectes = new ArrayList<>();

        for (Question question : questions) {
            Label questionLabel = new Label("Question: " + question.getQuestion());
            Label choixLabel = new Label("Choix: " + question.getChoix());
            TextField reponseTextField = new TextField();
            reponseTextField.setPromptText("Tapez votre réponse ici");

            reponsesCorrectes.add(question.getReponse_correcte());

            // Ajoutez des styles CSS pour rendre l'interface plus attrayante
            questionLabel.setStyle("-fx-font-size: 16; -fx-text-fill: #008CBA; -fx-font-weight: bold;");
            choixLabel.setStyle("-fx-font-size: 14; -fx-text-fill: #333;");
            reponseTextField.setStyle(
                    "-fx-font-size: 14; -fx-background-color: #f4f4f4; -fx-border-color: #ccc; -fx-border-radius: 5;");

            // Effet de transition sur le survol
            reponseTextField.setOnMouseEntered(e -> reponseTextField.setStyle(
                    "-fx-font-size: 14; -fx-background-color: #e0e0e0; -fx-border-color: #ccc; -fx-border-radius: 5;"));
            reponseTextField.setOnMouseExited(e -> reponseTextField.setStyle(
                    "-fx-font-size: 14; -fx-background-color: #f4f4f4; -fx-border-color: #ccc; -fx-border-radius: 5;"));

            VBox.setMargin(questionLabel, new javafx.geometry.Insets(0, 0, 5, 0));
            VBox.setMargin(choixLabel, new javafx.geometry.Insets(0, 0, 5, 0));
            VBox.setMargin(reponseTextField, new javafx.geometry.Insets(0, 0, 15, 0));

            LVquestion.getChildren().addAll(questionLabel, choixLabel, reponseTextField);
        }
    }

    @FXML
    void validerQuiz(ActionEvent event) {
        if (toutesReponsesRemplies()) {
            int score = calculerScore();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Résultat du Quiz");
            alert.setHeaderText(null);
            alert.setContentText("Score final : " + score);

            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez répondre à toutes les questions avant de valider.");

            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait();
        }
    }

    private boolean toutesReponsesRemplies() {
        ObservableList<Node> children = LVquestion.getChildren();

        for (int i = 0; i < children.size(); i += 3) {
            TextField reponseTextField = (TextField) children.get(i + 2);

            if (reponseTextField.getText().isEmpty()) {
                return false;
            }
        }

        return true;
    }

    private int calculerScore() {
        int score = 0;
        ObservableList<Node> children = LVquestion.getChildren();

        for (int i = 0; i < children.size(); i += 3) {
            int reponseCorrecte = reponsesCorrectes.get(i / 3);
            TextField reponseTextField = (TextField) children.get(i + 2);

            try {
                int reponseCandidat = Integer.parseInt(reponseTextField.getText());
                if (reponseCandidat == reponseCorrecte) {
                    score++;
                }
            } catch (NumberFormatException e) {
                // Gérez l'exception si l'utilisateur n'a pas entré un nombre valide
            }
        }

        return score;
    }
}
