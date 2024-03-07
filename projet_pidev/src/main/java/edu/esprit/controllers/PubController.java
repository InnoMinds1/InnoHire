package edu.esprit.controllers;

import edu.esprit.entities.CurrentUser;
import edu.esprit.entities.Post;
import edu.esprit.entities.Utilisateur;
import edu.esprit.services.ServicePost;


import edu.esprit.services.ServiceUtilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

public class PubController implements Initializable {

    @FXML
    private ImageView trierordredecroissant;
    private ServicePost servicePost;


    /*@FXML
    private VBox postsContainer;
    private List<Post> posts = new ArrayList<>();
    @FXML
    private Button Naviguerversajouter;*/
    @FXML
    private AnchorPane AnchoPaneMessage131;
    @FXML
    private TextField nomRechercheTF;

    @FXML
    private Button Naviguerversajouter;

    @FXML
    private AnchorPane container;
    @FXML
    private AnchorPane AdminPane;
    @FXML
    private AnchorPane CandidatPane;
    @FXML
    private AnchorPane RepresentantPane;
    @FXML
    private Label nameUserLabel;
    @FXML
    private VBox postsContainer;

    @FXML
    private ImageView chercherparnom;

    @FXML
    private Label receivernomLabel2;

@FXML
    private Label userRepName;
    private List<Post> posts = new ArrayList<>();

    @FXML
    void Naviguerversajouter(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AjouterPublication.fxml"));
            postsContainer.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Sorry jjjj");
            alert.setTitle("Error");
            alert.show();
        }
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int userRole = CurrentUser.getRole();
        switch (userRole) {
            case 0:
                AdminPane.setVisible(true);
                RepresentantPane.setVisible(false);
                CandidatPane.setVisible(false);
                nameUserLabel.setText("Admin " + CurrentUser.getNom());
                break;
            case 1:
                AdminPane.setVisible(false);
                RepresentantPane.setVisible(true);
                CandidatPane.setVisible(false);
                nameUserLabel.setText(CurrentUser.getNom());
                userRepName.setText( CurrentUser.getNom());
                break;
            case 2:
                AdminPane.setVisible(false);
                RepresentantPane.setVisible(false);
                CandidatPane.setVisible(true);
                nameUserLabel.setText(CurrentUser.getNom());
                break;

        }

        try {
            posts.addAll(getData());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        try {
            for (Post post : posts) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/post.fxml"));
               // VBox vBox = fxmlLoader.load();
                AnchorPane anchorPane = fxmlLoader.load();

                PostController postController = fxmlLoader.getController();
                postController.setData(post);
               // postsContainer.getChildren().add(vBox);
                postsContainer.getChildren().add(anchorPane);

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        servicePost = new ServicePost();
    }


    public List<Post> getpost() {
        List<Post> ls = new ArrayList<>();
        ServicePost servicePost = new ServicePost();
        for (int i = 0; i < 50; i++) {
            try {
                Set<Post> sp = servicePost.getAll();
                ls.addAll(sp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        return ls;
    }


    private Post getPost() throws SQLException {

        ServicePost sp = new ServicePost();

        ServiceUtilisateur su = new ServiceUtilisateur();


        Post post1 = sp.getOneByID(1);

        Post post = new Post();
        Utilisateur user = new Utilisateur();
        user.setNom(post1.getUtilisateur().getNom());
        user.setImage(post1.getUtilisateur().getImage());

        post.setUtilisateur(user);
        post.setDate(post1.getDate());
        post.setAudience(post1.getAudience());
        post.setCaption(post1.getCaption());
        post.setImage(post1.getImage());
        post.setTotalReactions(post1.getTotalReactions());
        post.setNbComments(post1.getNbComments());

        return post;
    }


    private List<Post> getData() throws SQLException {
        ServicePost sp = new ServicePost();
        Set<Post> etablissements = sp.getAll();//admin
        List<Post> modifiedEtablissements = new ArrayList<>();
        for (Post etablissement : etablissements) {
            // Assuming setNom, setPrice, and setImage methods are available in the Etablissement class
            Post modifiedEtablissement = new Post();
            modifiedEtablissement.setId_post(etablissement.getId_post());
            modifiedEtablissement.setCaption(etablissement.getCaption());
            modifiedEtablissement.setAudience(etablissement.getAudience());
            modifiedEtablissement.setDate(etablissement.getDate());

            modifiedEtablissement.setNbComments(etablissement.getNbComments());
            modifiedEtablissement.setTotalReactions(etablissement.getTotalReactions());
            // If setPrice method is available, you can uncomment the following line
            modifiedEtablissement.setImage(etablissement.getImage());
            ServiceUtilisateur su = new ServiceUtilisateur();
            Utilisateur user = su.getOneByID(etablissement.getUtilisateur().getId_utilisateur());
            modifiedEtablissement.setUtilisateur(user);
            modifiedEtablissements.add(modifiedEtablissement);
        }

        return modifiedEtablissements;
    }





    public void refreshUI() {
        postsContainer.getChildren().clear();
        try {
            for (Post post : posts) {
                // Récupérer les données de l'utilisateur associé à chaque post
                Utilisateur utilisateur = post.getUtilisateur();

                // Charger le fichier FXML du post
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/post.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                // Récupérer le contrôleur de post.fxml
                PostController postController = fxmlLoader.getController();

                // Envoyer les données du post et de l'utilisateur au contrôleur
                postController.setData(post);

                // Ajouter le post à votre conteneur
                postsContainer.getChildren().add(anchorPane);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    void chercherparnom(MouseEvent event) {
        String nomUtilisateur = nomRechercheTF.getText();
        try {
            // Appel de la méthode rechercherParNom du servicePost pour obtenir les posts correspondants
            Set<Post> posts = servicePost.rechercherParNom(nomUtilisateur);

            // Nettoyer d'abord le conteneur
            postsContainer.getChildren().clear();

            // Charger chaque post dans le conteneur
            for (Post post : posts) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/post.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                // Récupérer le contrôleur de post.fxml
                PostController postController = fxmlLoader.getController();

                // Envoyer les données du post au contrôleur
                postController.setData(post);

                // Ajouter le post à votre conteneur
                postsContainer.getChildren().add(anchorPane);
            }
        } catch (SQLException | IOException e) {
            // Gérer les exceptions
            System.out.println("Erreur lors de la recherche par nom: " + e.getMessage());
        }
    }






    @FXML
    void trierordredecroissant(MouseEvent event) {
        try {
            // Appel de la fonction de tri
            Set<Post> sortedPosts = servicePost.trierPostsParDateDecroissante();
            // Mettre à jour la liste de posts avec les posts triés
            posts.clear();
            posts.addAll(sortedPosts);
            // Rafraîchir l'interface utilisateur avec les posts triés
            refreshUI();
        } catch (SQLException e) {
            afficherAlerte("Erreur", "Erreur lors du tri des posts", e.getMessage());
        }
    }

    // Méthode pour rafraîchir l'interface utilisateur avec les posts
//    private void refreshUI() {
//        // Nettoyer d'abord le conteneur
//        postsContainer.getChildren().clear();
//
//        // Charger chaque post dans le conteneur
//        for (Post post : posts) {
//            try {
//                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/post.fxml"));
//                AnchorPane anchorPane = fxmlLoader.load();
//
//                // Récupérer le contrôleur de post.fxml
//                PostController postController = fxmlLoader.getController();
//
//                // Envoyer les données du post au contrôleur
//                postController.setData(post);
//
//                // Ajouter le post à votre conteneur
//                postsContainer.getChildren().add(anchorPane);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    // Méthode pour afficher une alerte en cas d'erreur
    private void afficherAlerte(String titre, String enTete, String contenu) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setHeaderText(enTete);
        alert.setContentText(contenu);
        alert.showAndWait();
    }

    @FXML
    public void navigateToReclamation(MouseEvent mouseEvent) {

    }
    @FXML
    void NavigateToDisponnible(ActionEvent event) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/AfficherQuizDisponible.fxml"));
            nomRechercheTF.getScene().setRoot(root);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Sorry");
            alert.setTitle("Error");
            alert.show();
        }


    }
    @FXML
    void NavigateToPasserQuiz(ActionEvent event) {
       try{
        Parent root = FXMLLoader.load(getClass().getResource("/PasserQuiz.fxml"));
        nomRechercheTF.getScene().setRoot(root);
    } catch (IOException e) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Sorry");
        alert.setTitle("Error");
        alert.show();
    }

    }












}


