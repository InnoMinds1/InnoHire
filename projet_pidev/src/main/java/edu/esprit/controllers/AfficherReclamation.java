package edu.esprit.controllers;

import edu.esprit.entities.Reclamation;
import edu.esprit.services.ServiceReclamation;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.ListCell;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Set;

public class AfficherReclamation implements Initializable {
    @FXML
    private ListView<Reclamation> listView; // Make sure the fx:id matches the one in your FXML file

    private final ServiceReclamation serviceReclamation = new ServiceReclamation();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            // Fetch all reclamations from the database
            Set<Reclamation> reclamations = serviceReclamation.getAll();

            // Set the fetched reclamations to the ListView
            listView.setItems(FXCollections.observableArrayList(reclamations));

            // Customize the list view cell rendering to display more information
            listView.setCellFactory(new Callback<ListView<Reclamation>, ListCell<Reclamation>>() {
                @Override
                public ListCell<Reclamation> call(ListView<Reclamation> listView) {
                    return new ListCell<Reclamation>() {
                        @Override
                        protected void updateItem(Reclamation reclamation, boolean empty) {
                            super.updateItem(reclamation, empty);
                            if (reclamation != null) {
                                setText("     " + reclamation.getUser().getNom()+"  "+ reclamation.getUser().getPrenom()+ "                 " + reclamation.getTitre() + "                                     " + reclamation.getType() +
                                        "                            " + reclamation.getPub().getCode_pub()+"                                  "+reclamation.getDate()) ;
                            } else {
                                setText(null);
                            }
                        }
                    };
                }
            });

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void navigateToModifyReclamationAction() {
        Reclamation selectedReclamation = listView.getSelectionModel().getSelectedItem();
        if (selectedReclamation != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierReclamation.fxml"));
                Parent root = loader.load();

                // Get the controller of the ModifierReclamationController
                ModifierReclamationController modifierController = loader.getController();
                modifierController.initData(selectedReclamation);

                // Replace the scene with the ModifierReclamationController scene
                listView.getScene().setRoot(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void DeleteReclamation(ActionEvent event) {
        Reclamation selectedReclamation = listView.getSelectionModel().getSelectedItem();
        if (selectedReclamation != null) {
            // Show confirmation dialog
            Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationDialog.setTitle("Confirmation");
            confirmationDialog.setHeaderText("Delete Reclamation");
            confirmationDialog.setContentText("Are you sure you want to delete the selected reclamation?");

            // Add OK and Cancel buttons to the confirmation dialog
            confirmationDialog.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);

            // Wait for user input
            ButtonType result = confirmationDialog.showAndWait().orElse(ButtonType.CANCEL);

            // Check the user's choice
            if (result == ButtonType.OK) {
                try {
                    // Call the DeleteReclamation method from your ServiceReclamation
                    serviceReclamation.supprimer(selectedReclamation.getId_reclamation());

                    // Show success alert
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Success");
                    successAlert.setContentText("Reclamation deleted successfully!");
                    successAlert.showAndWait();

                    // Optionally, you can reload the updated reclamations in the list view
                    Set<Reclamation> updatedReclamations = serviceReclamation.getAll();
                    listView.setItems(FXCollections.observableArrayList(updatedReclamations));
                } catch (SQLException e) {
                    // Handle any SQL exception that might occur during the delete
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("SQL Exception");
                    errorAlert.setContentText(e.getMessage());
                    errorAlert.showAndWait();
                }
            }
            // If the user clicked Cancel, do nothing
        } else {
            // Show an alert if no reclamation is selected
            Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
            infoAlert.setTitle("Information");
            infoAlert.setContentText("No reclamation selected for deletion.");
            infoAlert.showAndWait();
        }
    }

}
