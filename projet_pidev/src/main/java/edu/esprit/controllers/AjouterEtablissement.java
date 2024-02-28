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
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.scene.image.Image;


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
    private ImageView imageViewETF;

    @FXML
    private ListView<Utilisateur> ListViewUser;



    @FXML
    private GridPane gridA;

    @FXML
    private ScrollPane scrollA;


    private ServiceUtilisateur serviceU = new ServiceUtilisateur();
    Set<Utilisateur> setU;

    {
        try {
            setU = serviceU.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }








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



        // Vérifier si le type est valide
        String[] validTypes = {"ecole", "college", "lycee", "faculte"};
        String lowerCaseType = Type.trim().toLowerCase(); // Convertir en minuscules et supprimer les espaces inutiles

        if (!Arrays.asList(validTypes).contains(lowerCaseType)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Erreur : Le type d'établissement doit être 'ecole', 'college', 'lycee' ou 'faculte' !");
            alert.showAndWait();
            return;
        }





        String currentDir = System.getProperty("user.dir");
        String imagePath = currentDir + "/src/main/resources/img/" + image;
        File imageFile = new File(imagePath);
        if (!imageFile.exists()) {
            // Afficher une alerte si l'image n'existe pas
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Erreur : L'image spécifiée n'existe pas !");
            alert.showAndWait();
            return;
        }




        String cin_utilisateur = cin_utilisateurETF.getText();
        int cin_utilisateurE;

        if (cin_utilisateur.isEmpty()) {
            // If the TextField is empty, show an error alert
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez saisir un CIN ou choisir de la Liste !");
            alert.showAndWait();
            return;
        } else {
            try {
                // Parse the CIN from the TextField
                cin_utilisateurE = Integer.parseInt(cin_utilisateur);
            } catch (NumberFormatException e) {
                // If parsing fails, show an error alert
                Alert alert = new Alert(Alert.AlertType.ERROR);
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
            imageViewETF.setImage(null);

        }

        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Erreur lors de l'ajout d'etablissement' : " + e.getMessage());
            alert.showAndWait();
        }
        navigatetoAfficherEtablissementAction(event);

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


        int column = 0;
        int row = 1;
        try {
            for (Utilisateur utilisateur : setU) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/UtilisateurItem.fxml"));
                HBox hbox = fxmlLoader.load();

                UtilisateurItemController itemController = fxmlLoader.getController();
                itemController.setAjouterEtablissementController(this);// Set the reference
                itemController.setData(utilisateur);

                if (column == 1) {
                    column = 0;
                    row++;
                }

                gridA.add(hbox, column++, row);
                gridA.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridA.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridA.setMaxWidth(Region.USE_PREF_SIZE);
                gridA.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridA.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridA.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(hbox, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }





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

            // Display the image in the ImageView
            Image image = new Image(imagePath);
            imageViewETF.setImage(image);


            // Do something with the imagePath, for example, display the image
            // imageView.setImage(new Image(imagePath));
            System.out.println("Selected Image: " + imagePath);
        } else {
            // The user canceled the operation
            System.out.println("Operation canceled.");
        }
    }
    private boolean checkImageExistence(String imageName) {
        String currentDir = System.getProperty("user.dir");
        String imagePath = currentDir + "/src/main/resources/img/" + imageName;

        File imageFile = new File(imagePath);
        return imageFile.exists();
    }
    @FXML
    void updateCinTextField(String cin) {
        cin_utilisateurETF.setText(cin);
    }

    private boolean isValidType(String type) {
        // Liste des types valides
        String[] validTypes = {"ecole", "college", "lycee", "faculte"};

        // Convertir le type en minuscules pour une comparaison insensible à la casse
        String lowerCaseType = type.toLowerCase();

        // Vérifier si le type est dans la liste des types valides
        for (String validType : validTypes) {
            if (validType.equals(lowerCaseType)) {
                return true;
            }
        }

        return false;
    }

}
