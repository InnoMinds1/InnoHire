package edu.esprit.controllers;

import edu.esprit.entities.Question;
import edu.esprit.entities.Quiz;
import edu.esprit.services.questionService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class AfficherQuestionController implements Initializable {
    @FXML
    private GridPane gridA;

    @FXML
    private ScrollPane scrollA;
    @FXML
    private TextField TFsearch;


    private questionService serviceQ = new questionService();
    Set<Question> setQ;

    {
        try {
            setQ = serviceQ.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    // Méthode pour récupérer la question à partir de la grille

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        int column = 0;
        int row = 1;
        try {
            for (Question question : setQ) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/QuestionItem.fxml"));
                HBox hbox = fxmlLoader.load();

                QuestionItemController itemController = fxmlLoader.getController();
                itemController.setData(question);

                if (column == 1) {
                    column = 0;
                    row++;
                }

                gridA.add(hbox, column++, row);
                gridA.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridA.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridA.setMaxWidth(Region.USE_PREF_SIZE);
                gridA.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridA.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridA.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(hbox, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void navigateToAjouter(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterQuestion.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) gridA.getScene().getWindow(); // Utilisez la même fenêtre (Stage) actuelle
            stage.setScene(new Scene(root));
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void searchByCode(KeyEvent event) {
        // Récupérer le texte de recherche
        String searchQuery = TFsearch.getText().trim();

        // Appliquer la recherche seulement si la saisie n'est pas vide
        if (!searchQuery.isEmpty()) {
            try {
                // Récupérer toutes les questions
                Set<Question> allQuestions = serviceQ.getAll();

                // Filtrer les questions en fonction du code Quiz
                List<Question> filteredQuestions = allQuestions.stream()
                        .filter(question -> String.valueOf(question.getQuiz().getCode_quiz()).equals(searchQuery))
                        .collect(Collectors.toList());
                Set<Question> filteredQuestionsSet = new HashSet<>(filteredQuestions);

                // Mettre à jour la vue avec les résultats de la recherche
                updateQuestionGridView(filteredQuestionsSet);
            } catch (NumberFormatException e) {
                // Gérer l'exception si la saisie n'est pas un nombre
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisie");
                alert.setContentText("Veuillez saisir un code Quiz valide (nombre entier).");
                alert.show();
            } catch (SQLException e) {
                // Gérer les exceptions SQL, par exemple afficher un message d'erreur ou lancer une nouvelle exception
                e.printStackTrace();
            }
        } else {
            try {
                // Récupérer toutes les questions
                Set<Question> allQuestions = serviceQ.getAll();

                // Mettre à jour la vue avec toutes les questions
                updateQuestionGridView(allQuestions);
            } catch (SQLException e) {
                // Gérer les exceptions SQL, par exemple afficher un message d'erreur ou lancer une nouvelle exception
                e.printStackTrace();
            }
        }
    }

    private void updateQuestionGridView(Set<Question> questions) {
        // Effacer le contenu actuel du GridPane
        gridA.getChildren().clear();

        // Mettre à jour le GridPane avec les nouvelles données
        int rowIndex = 0;

        for (Question question : questions) {
            // Charger le composant QuestionItem à partir du fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/QuestionItem.fxml"));
            try {
                Parent questionItem = loader.load();

                // Accéder au contrôleur du composant QuestionItem
                QuestionItemController questionItemController = loader.getController();

                // Configurer les données pour le QuestionItem
                questionItemController.setData(question);

                // Ajouter le QuestionItem à la GridPane
                gridA.add(questionItem, 0, rowIndex);

                // Incrémenter l'indice de ligne pour la prochaine question
                rowIndex++;
            } catch (IOException e) {
                // Gérer les exceptions liées au chargement du composant QuestionItem
                e.printStackTrace();
            }
        }
    }









}