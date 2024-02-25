package edu.esprit.controllers;

import edu.esprit.entities.Reclamation;
import edu.esprit.services.ServiceReclamation;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Set;

public class AfficherReclamationController implements Initializable {

    private final ServiceReclamation serviceReclamation = new ServiceReclamation();

    @FXML
    private VBox reclamationsContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Retrieve data from the database
        try {
            Set<Reclamation> reclamations = serviceReclamation.getAll();

            // Load and add ReclamationItemComponent for each Reclamation
            for (Reclamation reclamation : reclamations) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ReclamationItemComponent.fxml"));
                try {
                    reclamationsContainer.getChildren().add(loader.load());
                    ReclamationItemComponentController controller = loader.getController();
                    controller.setReclamationData(reclamation);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
