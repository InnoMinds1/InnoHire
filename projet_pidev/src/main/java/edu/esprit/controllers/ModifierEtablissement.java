package edu.esprit.controllers;

import edu.esprit.entities.Etablissement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.models.Service;
import org.example.services.ServiceService;

import java.io.IOException;
import java.sql.SQLException;

public class ModifierEtablissement {

    @FXML
    private TextField DomaineSTF;

    @FXML
    private TextField NomSTF;

    @FXML
    private TextField PrixSTF;

    @FXML
    private TextField TempSTF;

    @FXML
    private TextField TitreSTF;
    private Service service;
    private final ServiceService serviceService = new ServiceService();

    public void setService(Service service) {
        this.service = service;
        initData(service);
    }

    public static void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public boolean controlSaisie(TextField field) {
        if (field.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Champ vide", "Veuillez remplir tous les champs.");
            return false;
        }
        return true;
    }

    public void initData(Etablissement etablissement) {
        if (service != null) {
            NomSTF.setText(service.getNom_service());
            TitreSTF.setText(service.getTitre_service());
            DomaineSTF.setText(service.getDomaine());
            TempSTF.setText(service.getTmpService());
            PrixSTF.setText(String.valueOf(service.getPrix()));
        }
    }

    @FXML
    void ok(ActionEvent event) throws SQLException {
        float Prix = Float.parseFloat(PrixSTF.getText());
        if (controlSaisie(NomSTF) && controlSaisie(TempSTF) && controlSaisie(TitreSTF)&& controlSaisie(PrixSTF)&& controlSaisie(DomaineSTF)) {
            Service newService = new Service();
            newService.setNom_service(NomSTF.getText());
            newService.setTmpService(TempSTF.getText());
            newService.setDomaine(DomaineSTF.getText());
            newService.setTitre_service(TitreSTF.getText());
            newService.setPrix(Prix);

            serviceService.modifier(newService);

            showAlert(Alert.AlertType.INFORMATION, "Succès", "Service modifié avec succès");
            Stage stage = (Stage) NomSTF.getScene().getWindow();
            stage.close();
        }
    }

    public void afficherService(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ServiceFX/Afficher.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            // Pour fermer la fenêtre actuelle si nécessaire
            // ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
