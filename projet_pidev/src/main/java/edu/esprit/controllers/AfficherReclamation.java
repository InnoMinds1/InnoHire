package edu.esprit.controllers;

import edu.esprit.entities.Reclamation;
import edu.esprit.services.ServiceReclamation;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
    private ListView<Reclamation> listview;

    private final ServiceReclamation sr = new ServiceReclamation();
    private Set<Reclamation> reclamations = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            reclamations = sr.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Set up a custom cell factory for the ListView
        listview.setCellFactory(new Callback<ListView<Reclamation>, ListCell<Reclamation>>() {
            @Override
            public ListCell<Reclamation> call(ListView<Reclamation> param) {
                return new ListCell<Reclamation>() {
                    @Override
                    protected void updateItem(Reclamation item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setText(null);
                        } else {
                            // Customize the appearance of each item in the ListView
                            setText("ID: " + item.getType() + " Sujet: " + item.getTitre() + " Description: " + item.getDescription() + " Date: " + item.getDate());
                        }
                    }
                };
            }
        });

        // Add event handler for item selection
        listview.setOnMouseClicked(event -> {
            Reclamation selectedReclamation = listview.getSelectionModel().getSelectedItem();
            if (selectedReclamation != null) {
                try {
                    // Load ModifierReclamation page and pass the selected item's data
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierReclamation.fxml"));
                    Parent root = loader.load();
                    ModifierReclamationController modifyReclamationController = loader.getController();
                    modifyReclamationController.initData(selectedReclamation);

                    // Set the root of the scene to the ModifyReclamation page
                    listview.getScene().setRoot(root);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        // Populate the ListView with data
        listview.setItems(FXCollections.observableArrayList(reclamations));
    }
}
