package edu.esprit.controllers;

import edu.esprit.entities.Post;
import edu.esprit.entities.PostAudience;
import edu.esprit.entities.Publication;
import edu.esprit.entities.Utilisateur;
import edu.esprit.services.ServicePost;
import edu.esprit.services.ServicePublication;
import edu.esprit.services.ServiceUtilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;


public class ModifierPublication {


    private int id ;

    @FXML
    private TextField TFaudience;

    @FXML
    private TextField TFcaption;

    @FXML
    private TextField TFdate;

    @FXML
    private TextField TFnomuser;

    @FXML
    private Button button;

    @FXML
    private TextField imageETF;

    @FXML
    private ImageView imgPost;

    private final ServicePost sp=new ServicePost();
    private final ServiceUtilisateur  su=new ServiceUtilisateur();

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

    public void initData(Post post) {
        if (post != null) {
            setId(post.getId_post());
            TFaudience.setText(PostAudience.valueOf(TFaudience.getText()).getName());
            TFcaption.setText(post.getCaption());

        }
    }

    @FXML
    void ok(ActionEvent event) throws SQLException {

         ServicePost servicePost=new ServicePost();


        if ( controlSaisie(TFaudience) && controlSaisie(TFcaption) && controlSaisie(imageETF)) {
            Post newPost = new Post();

            // Récupération de l'ID de la publication à modifier
            //int idPublication = getId(); // Assurez-vous de définir cette méthode

            // Configuration des nouvelles valeurs
            newPost.setId_post(getId());
            newPost.setAudience(PostAudience.valueOf(TFaudience.getText()));
            newPost.setCaption(TFcaption.getText()); // Assurez-vous que TFdate est un DatePicker




            // Appel de la méthode pour effectuer la modification dans la base de données
            servicePost.modifier(newPost);

            showAlert(Alert.AlertType.INFORMATION, "Succès", "Publication modifiée avec succès");
            // Assurez-vous d'ajuster le code pour afficher les publications après la modification
          AfficherPublication(event);
        }
    }

    public void AfficherPublication(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherPublication.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) TFaudience.getScene().getWindow(); // Utilisez la même fenêtre (Stage) actuelle
            stage.setScene(new Scene(root));
            stage.show();

            // Vous pouvez fermer la fenêtre actuelle si nécessaire
            // ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
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

            // Display the selected image on the ImageView
            imgPost.setImage(new Image(imagePath));

            System.out.println("Selected Image: " + imagePath);
        } else {
            // The user canceled the operation
            System.out.println("Operation canceled.");
        }
    }


}