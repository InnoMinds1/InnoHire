package edu.esprit.controllers;


import edu.esprit.entities.Etablissement;
import edu.esprit.entities.Utilisateur;
import edu.esprit.entities.Wallet;
import edu.esprit.services.ServiceEtablissement;

import edu.esprit.services.ServiceUtilisateur;
import edu.esprit.services.ServiceWallet;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ResourceBundle;
import java.util.Set;

public class AjouterEtablissement implements Initializable {
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
    private TextField imageETF;

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
        String image = imageETF.getText();


        // Vérifier si les champs requis sont vides
        if (Nom.isEmpty() || Lieu.isEmpty() || Code.isEmpty() || Type.isEmpty()|| image.isEmpty() ) {
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

        if (cin_utilisateur.isEmpty()) {
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

            // Utiliser le CIN de l'utilisateur sélectionné dans la ListView
            cin_utilisateurE = selectedUser.getCin();
        } else {
            try {
                cin_utilisateurE = Integer.parseInt(cin_utilisateur);
            } catch (NumberFormatException e) { Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Le CIN doit être un nombre valide !");
                alert.showAndWait();
                return;
            }
        }






        // Créer un nouvel objet Service avec les valeurs saisies
        Etablissement etablissement = new Etablissement();
        etablissement.setNom(Nom);
        etablissement.setCodeEtablissement(codeE);
        etablissement.setLieu(Lieu);
        etablissement.setTypeEtablissement(Type);
        etablissement.setImage(image);


        ServiceUtilisateur su=new ServiceUtilisateur();
        Utilisateur user = su.getOneByCin(cin_utilisateurE);
        etablissement.setUser(user);

        // Ajouter le service à la base de données
        try {
            serviceEtablissement.ajouter(etablissement);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("Etablissement ajouté avec succès !");
            alert.showAndWait();

            // Effacer les champs du formulaire après l'ajout réussi
            NomETF.clear();
            LieuETF.clear();
            CodeETF.clear();
            TypeETF.clear();
            imageETF.clear();
            cin_utilisateurETF.clear();
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/market.fxml"));
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

    @FXML
    public void importImage(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");

        // Set the initial directory to the img folder in the resources
        String currentDir = System.getProperty("user.dir");
        fileChooser.setInitialDirectory(new File(currentDir + "/src/main/resources/img"));

        // Set the file extension filters if needed (e.g., for images)
        FileChooser.ExtensionFilter imageFilter =
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif");
        fileChooser.getExtensionFilters().add(imageFilter);

        // Show the file chooser dialog
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
            // The user selected a file, you can handle it here
            String imagePath = selectedFile.toURI().toString();

            // Set the image file name to the TextField
            imageETF.setText(selectedFile.getName());

            // Do something with the imagePath, for example, display the image
            // imageView.setImage(new Image(imagePath));
            System.out.println("Selected Image: " + imagePath);
        } else {
            // The user canceled the operation
            System.out.println("Operation canceled.");
        }
    }
}
