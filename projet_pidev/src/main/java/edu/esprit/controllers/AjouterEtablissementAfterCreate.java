package edu.esprit.controllers;


import edu.esprit.entities.CurrentUser;
import edu.esprit.entities.Etablissement;
import edu.esprit.entities.Utilisateur;

import edu.esprit.services.ServiceEtablissement;

import edu.esprit.services.ServiceUtilisateur;

import edu.esprit.utils.DataSource;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.plaf.ColorUIResource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Set;

public class AjouterEtablissementAfterCreate implements Initializable {
    Connection cnx = DataSource.getInstance().getCnx();

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


    private final ServiceEtablissement se = new ServiceEtablissement();




    @FXML
    void ajouterEtablissementAction(ActionEvent event) throws SQLException {
        // Créer une instance de ServiceService
        ServiceEtablissement serviceEtablissement = new ServiceEtablissement();

        // Récupérer les valeurs des champs du formulaire
        String Nom = NomETF.getText();
        String Lieu = LieuETF.getText();
        String Code = CodeETF.getText();
        String Type = TypeETF.getText();


        // Vérifier si les champs requis sont vides
        if (Nom.isEmpty() || Lieu.isEmpty() || Code.isEmpty() || Type.isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Tous les champs sont obligatoires !");
            alert.showAndWait();
            return;
        }


        // Vérifier si le prix est un nombre valide
        int codeE;
        try {
            codeE = Integer.parseInt(Code);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Le code doit être un nombre valide !");
            alert.showAndWait();
            return;
        }
        if (serviceEtablissement.existe(codeE)) {
            // Afficher une alerte si le code existe déjà
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Erreur Un établissement avec le même code existe déjà !");
            alert.showAndWait();

            return;
        }




        String cin_utilisateur = cin_utilisateurETF.getText();
        int cin_utilisateurE;
        try {
                cin_utilisateurE = CurrentUser.getId_utilisateur();
            } catch (NumberFormatException e) { Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Le CIN doit être un nombre valide !");
                alert.showAndWait();
                return;
            }







        // Créer un nouvel objet Service avec les valeurs saisies
        Etablissement etablissement = new Etablissement();
        etablissement.setNom(Nom);
        etablissement.setCode_etablissement(codeE);
        etablissement.setLieu(Lieu);
        etablissement.setType_etablissement(Type);

        ServiceUtilisateur su=new ServiceUtilisateur();
        Utilisateur user = su.getOneByCin(CurrentUser.getCin());
        System.out.println(user);
        etablissement.setUser(user);

        // Ajouter le service à la base de données
        try {
            this.ajoutEtab_sansid(etablissement);
            this.addIdToEtab(etablissement);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("Etablissement ajouté avec succès !");
            alert.showAndWait();
            CurrentUser.setId_utilisateur(user.getId_utilisateur());
            CurrentUser.setNom(user.getNom());
            CurrentUser.setPrenom(user.getPrenom());
            CurrentUser.setAdresse(user.getAdresse());
            CurrentUser.setMdp(user.getMdp());
            CurrentUser.setRole(1);

            // Effacer les champs du formulaire après l'ajout réussi
            NomETF.clear();
            LieuETF.clear();
            CodeETF.clear();
            TypeETF.clear();
            cin_utilisateurETF.clear();
            Parent root = FXMLLoader.load(getClass().getResource("/Accueil.fxml"));
            cin_utilisateurETF.getScene().setRoot(root);
        }

        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Erreur lors de l'ajout d'etablissement' : " + e.getMessage());
            alert.showAndWait();
        }

    }



    public void navigatetoAfficherEtablissementAction(ActionEvent actionEvent) {
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

    @FXML
    void skip(ActionEvent event) {
        Parent root = null;
        ServiceUtilisateur su = new ServiceUtilisateur();
        try {
            Utilisateur user = su.getOneByCin(CurrentUser.getCin());
            CurrentUser.setId_utilisateur(user.getId_utilisateur());
            CurrentUser.setNom(user.getNom());
            CurrentUser.setPrenom(user.getPrenom());
            CurrentUser.setAdresse(user.getAdresse());
            CurrentUser.setMdp(user.getMdp());
            CurrentUser.setRole(1);


            root = FXMLLoader.load(getClass().getResource("/accueil.fxml"));
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
        cin_utilisateurETF.getScene().setRoot(root);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cin_utilisateurETF.setText(String.valueOf(CurrentUser.getCin()));
    }
    public void ajoutEtab_sansid(Etablissement etablissement) {
        String req = "INSERT INTO `etablissement`(`nom`, `lieu`, `code_etablissement`, `type_etablissement`) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, etablissement.getNom());
            ps.setString(2, etablissement.getLieu());
            ps.setInt(3, etablissement.getCode_etablissement());
            ps.setString(4, etablissement.getType_etablissement());

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                // Retrieve the auto-generated ID
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int id_etablissement = generatedKeys.getInt(1);
                    etablissement.setId_etablissement(id_etablissement);
                    System.out.println("Etablissement added with ID: " + id_etablissement);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addIdToEtab(Etablissement etablissement) {
        ServiceUtilisateur su = new ServiceUtilisateur();
        String req = "UPDATE etablissement SET id_utilisateur = ? WHERE id_etablissement = ?";
        String req1 = "UPDATE utilisateur SET status = ? WHERE id_utilisateur = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            PreparedStatement ps1 = cnx.prepareStatement(req1);
            ps.setInt(1, su.getUserIdByCin(CurrentUser.getCin()));
            ps.setInt(2, etablissement.getId_etablissement());
            ps1.setInt(1,1);
            ps1.setInt(2,su.getUserIdByCin(CurrentUser.getCin()));

            int affectedRows = ps.executeUpdate();
            int i = ps1.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("id added to etablissement: " + etablissement.getId_etablissement());
            } else {
                System.out.println("Failed to add id to etablissement");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}


