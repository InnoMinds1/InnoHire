package edu.esprit.controllers;

import edu.esprit.entities.Commentaire;
import edu.esprit.entities.Publication;
import edu.esprit.entities.Utilisateur;
import edu.esprit.services.ServiceUtilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.SQLException;

import static edu.esprit.controllers.ModifierPublication.showAlert;

public class ModifierCommentaire {
    @FXML
    private DatePicker dateTf1;

    @FXML
    private TextField descriptionTF1;

    @FXML
    private Button modifierCommentaire;

    @FXML
    private TextField ratingTF1;
    int id;
    public int getId() {

        return  id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @FXML
    void modifierCommentaire(ActionEvent event) {

            int cinUtilisateur = Integer.parseInt(TFcin_utilisateur.getText());


            if ( controlSaisie(TFdescription) && controlSaisie(TFhashtag) && controlSaisie(TFimage) && controlSaisie(TFnb_report) && controlSaisie(TFvisibilite)) {
                Commentaire c = new Commentaire();

                // Récupération de l'ID de la publication à modifier
                //int idPublication = getId(); // Assurez-vous de définir cette méthode

                // Configuration des nouvelles valeurs
                c.setId_commmentaire(getId());


                 // Assurez-vous que TFdate est un DatePicker
               c.setDescription(TFdescription.getText());
                c.setDate(TFdate.getValue());
               c.setNb_report(Integer.parseInt(nb_etoileTF.getText()));


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

    public void initData(Commentaire commentaire) {
        if (commentaire != null) {
        setId(commentaire.getId_commentaire());

            descriptionTF1.setText(commentaire.getDescription_co());
            dateTf1.setValue(commentaire.getDate_co()); // Assuming publication.getDate() returns LocalDate
            ratingTF1.setText(String.valueOf(commentaire.getNb_etoile()));

    }
    }
}
