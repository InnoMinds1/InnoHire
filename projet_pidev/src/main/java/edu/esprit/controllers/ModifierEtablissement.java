package edu.esprit.controllers;

import edu.esprit.entities.Etablissement;
import edu.esprit.services.ServiceEtablissement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import edu.esprit.entities.Etablissement;


import java.io.IOException;
import java.sql.SQLException;

public class ModifierEtablissement {

    @FXML
    private TextField CodeETF;

    @FXML
    private TextField Id_utilisateurETF;

    @FXML
    private TextField LieuETF;

    @FXML
    private TextField NomETF;

    @FXML
    private TextField TypeETF;
    private Etablissement etablissement;
    private final ServiceEtablissement serviceEtablissement = new ServiceEtablissement();

    public void setEtablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
        initData(etablissement);
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
        if (etablissement != null) {
            CodeETF.setText(String.valueOf(etablissement.getCode_etablissement()));
            LieuETF.setText(etablissement.getLieu());
            NomETF.setText(etablissement.getNom());
            TypeETF.setText(etablissement.getType_etablissement());
            Id_utilisateurETF.setText(String.valueOf(etablissement.getId_utilisateur()));
        }
    }

    @FXML
    void ok(ActionEvent event) throws SQLException {
        int Code = Integer.parseInt(CodeETF.getText());
        int Id_utilisateur = Integer.parseInt(Id_utilisateurETF.getText());
        if (controlSaisie(NomETF) && controlSaisie(LieuETF) && controlSaisie(CodeETF)&& controlSaisie(TypeETF)&& controlSaisie(Id_utilisateurETF)) {
            Etablissement newEtablissement = new Etablissement();
            newEtablissement.setNom(NomETF.getText());
            newEtablissement.setLieu(LieuETF.getText());
            newEtablissement.setCode_etablissement(Code);
            newEtablissement.setType_etablissement(TypeETF.getText());
            newEtablissement.setId_utilisateur(Id_utilisateur);

            serviceEtablissement.modifier(newEtablissement);

            showAlert(Alert.AlertType.INFORMATION, "Succès", "Service modifié avec succès");
            Stage stage = (Stage) NomETF.getScene().getWindow();
            stage.close();
        }
    }

    public void AfficherEtablissement(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherEtablissement.fxml"));
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
