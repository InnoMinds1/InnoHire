package edu.esprit.controllers;

import edu.esprit.entities.Quiz;
import edu.esprit.services.quizService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

public class AfficherQuizDisponibleController implements Initializable {
    @FXML
    private GridPane gridA;

    @FXML
    private ScrollPane scrollA;
    private quizService serviceQ = new quizService();
    Set<Quiz> setQ;

    {
        setQ = serviceQ.getAll();
    }




    public void initialize(URL url, ResourceBundle resourceBundle) {

        int column = 0;
        int row = 1;
        try {
            for (Quiz quiz1 : setQ) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/QuizPourAcheter.fxml"));
                HBox hbox = fxmlLoader.load();

                QuizPourAcheterController itemController = fxmlLoader.getController();
                itemController.setData(quiz1);

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


}
