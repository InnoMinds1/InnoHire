package edu.esprit.controllers;

import edu.esprit.entities.Etablissement;
import edu.esprit.entities.Utilisateur;

import edu.esprit.services.ServiceEtablissement;
import edu.esprit.services.ServiceUtilisateur;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Set;


public class ModifierEtablissement implements Initializable {


    private int id ;

    private int codeInit ;






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
    @FXML
    private ListView<Utilisateur> ListViewUser;

//----------------------wallet------------------------------------


    private final ServiceEtablissement serviceEtablissement = new ServiceEtablissement();
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getCodeInit() {
        return codeInit;
    }

    public void setCodeInit(int codeInit) {
        this.codeInit = codeInit;
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
            setCodeInit(etablissement.getCode_etablissement());
            CodeETF.setText(String.valueOf(etablissement.getCode_etablissement()));
            LieuETF.setText(etablissement.getLieu());
            NomETF.setText(etablissement.getNom());
            TypeETF.setText(etablissement.getType_etablissement());
           cin_utilisateurETF.setText(String.valueOf(etablissement.getUser().getCin()));

        }
    }



    @FXML
    void ok(ActionEvent event) throws SQLException {


        if (controlSaisie(NomETF) && controlSaisie(LieuETF) && controlSaisie(CodeETF)&& controlSaisie(TypeETF))
        {
            int Code = Integer.parseInt(CodeETF.getText());
            if(Code != getCodeInit() ) {
                if (serviceEtablissement.existe(Code)) {
                    // Afficher une alerte si le code existe déjà
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Erreur Un établissement avec le même code existe déjà !");
                    alert.showAndWait();

                    return;
                }
            }

            if (cin_utilisateurETF.getText().isEmpty()) {
                // Si le TextField est vide, vérifiez si un utilisateur est sélectionné dans la ListView
                Utilisateur selectedUser = ListViewUser.getSelectionModel().getSelectedItem();
                if (selectedUser == null) {
                    // Si rien n'est saisi et rien n'est sélectionné, afficher une alerte
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Veuillez saisir un CIN ou sélectionner un utilisateur !");
                    alert.showAndWait();
                    return;
                }
            }



            int cin_utilisateur = Integer.parseInt(cin_utilisateurETF.getText());


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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ServiceUtilisateur serviceService = new ServiceUtilisateur();
        Set<Utilisateur> users = null;

        try {
            users = serviceService.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ListViewUser.setItems(FXCollections.observableArrayList(users));

        // Ajouter un ChangeListener pour mettre à jour le TextField lorsqu'un utilisateur est sélectionné
        ListViewUser.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                cin_utilisateurETF.setText(String.valueOf(newValue.getCin()));
            }
        });
    }



}
