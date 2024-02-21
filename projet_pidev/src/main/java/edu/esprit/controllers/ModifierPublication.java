package edu.esprit.controllers;

import edu.esprit.entities.Publication;
import edu.esprit.entities.Utilisateur;
import edu.esprit.services.ServicePublication;
import edu.esprit.services.ServiceUtilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;


public class ModifierPublication {


    private int id ;

    @FXML
    private TextField TFcin_utilisateur;

    @FXML
    private TextField TFcode_pub;

    @FXML
    private DatePicker TFdate;

    @FXML
    private TextField TFdescription;

    @FXML
    private TextField TFhashtag;


    @FXML
    private TextField TFimage;

    @FXML
    private TextField TFnb_report;

    @FXML
    private TextField TFvisibilite;
    private final ServicePublication servicePublication = new ServicePublication();
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

    public void initData(Publication publication) {
        if (publication != null) {
            setId(publication.getId_publication());
            TFcode_pub.setText(publication.getCode_pub());
            TFcin_utilisateur.setText(String.valueOf(publication.getUtilisateur().getCin()));
            TFdate.setValue(publication.getDate()); // Assuming publication.getDate() returns LocalDate
            TFdescription.setText(publication.getDescription());
            TFhashtag.setText(publication.getHashtag());
            TFimage.setText(publication.getImage());
            TFnb_report.setText(String.valueOf(publication.getNb_report()));
            TFvisibilite.setText(publication.getVisibilite());
        }
    }

    @FXML
    void ok(ActionEvent event) throws SQLException {

        int cinUtilisateur = Integer.parseInt(TFcin_utilisateur.getText());


        if ( controlSaisie(TFdescription) && controlSaisie(TFhashtag) && controlSaisie(TFimage) && controlSaisie(TFnb_report) && controlSaisie(TFvisibilite)) {
            Publication newPublication = new Publication();

            // Récupération de l'ID de la publication à modifier
            //int idPublication = getId(); // Assurez-vous de définir cette méthode

            // Configuration des nouvelles valeurs
            newPublication.setId_publication(getId());
            newPublication.setCode_pub(TFcode_pub.getText());

            newPublication.setDate(TFdate.getValue()); // Assurez-vous que TFdate est un DatePicker
            newPublication.setDescription(TFdescription.getText());
            newPublication.setHashtag(TFhashtag.getText());
            newPublication.setImage(TFimage.getText());
            newPublication.setNb_report(Integer.parseInt(TFnb_report.getText()));
            newPublication.setVisibilite(TFvisibilite.getText());

            ServiceUtilisateur se=new ServiceUtilisateur();
            Utilisateur user=se.getByCin(cinUtilisateur);


            newPublication.setUtilisateur(user);

            // Appel de la méthode pour effectuer la modification dans la base de données
            servicePublication.modifier(newPublication);

            showAlert(Alert.AlertType.INFORMATION, "Succès", "Publication modifiée avec succès");
            // Assurez-vous d'ajuster le code pour afficher les publications après la modification
          AfficherPublication(event);
        }
    }

    public void AfficherPublication(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherPublication.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) TFcode_pub.getScene().getWindow(); // Utilisez la même fenêtre (Stage) actuelle
            stage.setScene(new Scene(root));
            stage.show();

            // Vous pouvez fermer la fenêtre actuelle si nécessaire
            // ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}