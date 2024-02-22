package edu.esprit.controllers;


import edu.esprit.entities.Question;

import edu.esprit.services.questionService;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

public class AfficherQuestionController implements Initializable {


    @FXML
    private ListView<Question> TFaffiche;



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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        questionService serviceService = new questionService();
        Set<Question> questions = null;

        try {
            questions = serviceService.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        TFaffiche.setItems(FXCollections.observableArrayList(questions));
    }
}












