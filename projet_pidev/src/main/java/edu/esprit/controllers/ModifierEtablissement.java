package edu.esprit.controllers;

import edu.esprit.entities.Etablissement;
import edu.esprit.entities.Utilisateur;
import edu.esprit.services.ServiceEtablissement;
import edu.esprit.services.ServiceUtilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;


public class ModifierEtablissement {


    private int id ;

    @FXML
    private TextField CodeETF;

    @FXML
    private TextField cin_utilisateurETF;

    @FXML
    private TextField LieuETF;

    @FXML
    private TextField NomETF;

    @FXML
    private TextField TypeETF;

    private final ServiceEtablissement serviceEtablissement = new ServiceEtablissement();
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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
            setId(etablissement.getId_etablissement());
            CodeETF.setText(String.valueOf(etablissement.getCode_etablissement()));
            LieuETF.setText(etablissement.getLieu());
            NomETF.setText(etablissement.getNom());
            TypeETF.setText(etablissement.getType_etablissement());
           cin_utilisateurETF.setText(String.valueOf(etablissement.getUser().getCin()));
        }
    }

    @FXML
    void ok(ActionEvent event) throws SQLException {
        int Code = Integer.parseInt(CodeETF.getText());
        int cin_utilisateur = Integer.parseInt(cin_utilisateurETF.getText());
        if (controlSaisie(NomETF) && controlSaisie(LieuETF) && controlSaisie(CodeETF)&& controlSaisie(TypeETF)&& controlSaisie(cin_utilisateurETF)) {
            Etablissement newEtablissement = new Etablissement();

            newEtablissement.setId_etablissement(getId());
            newEtablissement.setNom(NomETF.getText());
            newEtablissement.setLieu(LieuETF.getText());
            newEtablissement.setCode_etablissement(Code);
            newEtablissement.setType_etablissement(TypeETF.getText());


            ServiceUtilisateur se=new ServiceUtilisateur();
            Utilisateur user=se.getOneByCin(cin_utilisateur);


           newEtablissement.setUser(user);

            serviceEtablissement.modifier(newEtablissement);

            showAlert(Alert.AlertType.INFORMATION, "Succès", "Service modifié avec succès");
            AfficherEtablissement(event);

        }
    }

   /* public void AfficherEtablissement(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherEtablissement.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) NomETF.getScene().getWindow(); // Utilisez la même fenêtre (Stage) actuelle
            stage.setScene(new Scene(root));
            stage.show();

            // Vous pouvez fermer la fenêtre actuelle si nécessaire
            // ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

      public void AfficherEtablissement(ActionEvent actionEvent) {
          try {
              FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherEtablissement.fxml"));
              Parent root = loader.load();

              Stage stage = (Stage) NomETF.getScene().getWindow(); // Utilisez la même fenêtre (Stage) actuelle
              stage.setScene(new Scene(root));
              stage.show();

              // Vous pouvez fermer la fenêtre actuelle si nécessaire
              // ((Node)(event.getSource())).getScene().getWindow().hide();
          } catch (IOException e) {
              e.printStackTrace();
          }
      }


}
