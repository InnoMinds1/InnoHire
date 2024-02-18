package edu.esprit.controllers;

import edu.esprit.entities.Question;
import edu.esprit.services.questionService;
import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
public class AjouterQuestionController {

    @FXML
    private TextField TFchoix;

    @FXML
    private TextField TFid_quiz;

    @FXML
    private TextField TFquestion;
    private final questionService sp = new questionService();
    @FXML
    void AjouterQuestionAction(ActionEvent event) {
        try {
            int id_quiz=Integer.parseInt(TFid_quiz.getText());
            this.sp.ajouter(new Question(this.TFquestion.getText(), this.TFchoix.getText(),id_quiz));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("GG");
            alert.show();
        } catch (SQLException var4) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("SQL Exception");
            alert.setContentText(var4.getMessage());
            alert.showAndWait();
        }

    }
}
