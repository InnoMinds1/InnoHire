package edu.esprit.controllers;

import edu.esprit.entities.*;
import edu.esprit.services.ServiceCommentaire;
import edu.esprit.services.ServicePost;
import edu.esprit.services.ServiceUtilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class PostController implements Initializable {
    @FXML
    private ImageView imgProfile;

    @FXML
    private Label usernom;



    @FXML
    private Label date;

    @FXML
    private ImageView audience;

    @FXML
    private Label caption;

    @FXML
    private ImageView imgPost;

    @FXML
    private Label nbReactions;

    @FXML
    private Label nbComments;

    @FXML
    private Label nbShares;

    @FXML
    private HBox reactionsContainer;

    @FXML
    private ImageView imgLike;

    @FXML
    private ImageView imgLove;

    @FXML
    private ImageView imgCare;

    @FXML
    private ImageView imgHaha;

    @FXML
    private ImageView imgWow;

    @FXML
    private ImageView imgSad;

    @FXML
    private ImageView imgAngry;

    @FXML
    private HBox likeContainer;

    @FXML
    private ImageView imgReaction;

    @FXML
    private Label reactionnom;

    @FXML
    private Button Modifier;
    @FXML
    private Button supprimer;

    @FXML
    private Button AjouterC;


    private long startTime = 0;

    private Reactions currentReaction;
    private Post post;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    @FXML
    public void onLikeContainerPressed(MouseEvent me) {
        startTime = System.currentTimeMillis();
    }

    @FXML
    public void onLikeContainerMouseReleased(MouseEvent me){
        if(System.currentTimeMillis() - startTime > 500){
            reactionsContainer.setVisible(true);
        }else {
            if(reactionsContainer.isVisible()){
                reactionsContainer.setVisible(false);
            }
            if(currentReaction == Reactions.NON){
                setReaction(Reactions.LIKE);
            }else{
                setReaction(Reactions.NON);
            }
        }
    }


    @FXML
    public void onReactionImgPressed(MouseEvent me) {
        switch (((ImageView) me.getSource()).getId()) {
            case "imgLove":
                setReaction(Reactions.LOVE);
                break;
            case "imgCare":
                setReaction(Reactions.CARE);
                break;
            case "imgHaha":
                setReaction(Reactions.HAHA);
                break;
            case "imgWow":
                setReaction(Reactions.WOW);
                break;
            case "imgSad":
                setReaction(Reactions.SAD);
                break;
            case "imgAngry":
                setReaction(Reactions.ANGRY);
                break;
            default:
                setReaction(Reactions.LIKE);
                break;
        }
        reactionsContainer.setVisible(false);
    }

    public void setReaction(Reactions reaction) {
        ServiceCommentaire sc = new ServiceCommentaire();



            Image image = new Image(getClass().getResourceAsStream(reaction.getImgSrc()));
            imgReaction.setImage(image);
            reactionnom.setText(reaction.getNom());
            reactionnom.setTextFill(Color.web(reaction.getColor()));

            if (currentReaction == Reactions.NON) {
                post.setTotalReactions(post.getTotalReactions() + 1);


            }

            currentReaction = reaction;

            if (currentReaction == Reactions.NON) {
                post.setTotalReactions(post.getTotalReactions() - 1);
            }

            nbReactions.setText(String.valueOf(post.getTotalReactions()));


            this.post = post;
            ServicePost sp = new ServicePost();
            int nbRactions = post.getTotalReactions();
            post.setTotalReactions(nbRactions);
            try {
                sp.modifier(post);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


            ServiceUtilisateur su = new ServiceUtilisateur();
            Utilisateur cu = su.getOneByID(CurrentUser.getId_utilisateur());
            Commentaire c1 = new Commentaire(post, cu, "", LocalDate.of(2023, 02, 4), 1);
            try {
                sc.ajouter(c1);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


    }

    public void setData(Post post) {
        this.post = post;

        // Set profile image
        if (post.getUtilisateur() != null && post.getUtilisateur().getProfileImg() != null) {
            String profileImgPath = post.getUtilisateur().getProfileImg();
            if (getClass().getResource(profileImgPath) != null) { // Vérifie si le chemin d'accès est valide
                Image img = new Image(getClass().getResourceAsStream(profileImgPath));
                this.imgProfile.setImage(img);
            } else {
                System.err.println("Chemin d'accès à l'image de profil invalide : " + profileImgPath);
            }
        }


        if (post.getUtilisateur() != null) {
            usernom.setText(post.getUtilisateur().getNom());

        }

        // Set date
        date.setText(String.valueOf(post.getDate()));

        // Set audience image
        Image img;
        if (post.getAudience() == PostAudience.PUBLIC) {
            img = new Image(getClass().getResourceAsStream(PostAudience.PUBLIC.getImgSrc()));
        } else {
            img = new Image(getClass().getResourceAsStream(PostAudience.FRIENDS.getImgSrc()));
        }
        audience.setImage(img);

        // Set caption
        if (post.getCaption() != null && !post.getCaption().isEmpty()) {
            caption.setText(post.getCaption());
        } else {
            caption.setManaged(false);
        }

        // Set post image
        if (post.getImage() != null && !post.getImage().isEmpty()) {
            Image img2 = new Image(getClass().getResourceAsStream(post.getImage()));
            this.imgPost.setImage(img2);
        } else {
            imgPost.setVisible(false);
            imgPost.setManaged(false);
        }

        // Set reactions count
        nbReactions.setText(String.valueOf(post.getTotalReactions()));

        // Set comments count
        nbComments.setText(post.getNbComments() + " comments");

        // Set shares count
        nbShares.setText(post.getNbShares() + " shares");

        // Reset current reaction
        currentReaction = Reactions.NON;



    }

    private Post getPost() throws SQLException {

        ServicePost sp = new ServicePost();

        ServiceUtilisateur su = new ServiceUtilisateur();


        Post post1 = sp.getOneByID(1);

        Post post = new Post();
        Utilisateur user = new Utilisateur();
        user.setnom(post1.getUtilisateur().getNom());
        user.setProfileImg(post1.getUtilisateur().getProfileImg());

        post.setUtilisateur(user);
        post.setDate(post1.getDate());
        post.setAudience(post1.getAudience());
        post.setCaption(post1.getCaption());
        post.setImage(post1.getImage());
        post.setTotalReactions(post1.getTotalReactions());
        post.setNbComments(post1.getNbComments());
        post.setNbShares(post1.getNbShares());
        return post;
    }

    /* @Override
     public void initialize(URL location, ResourceBundle resources) {
         try {
             setData(getPost());
         } catch (SQLException e) {
             throw new RuntimeException(e);
         }
     }*/
   /*@FXML
   void navigatetoModifierPublicationAction(ActionEvent event) {
       try {
           Parent root = FXMLLoader.load(getClass().getResource("/ModifierPublication.fxml"));
           Scene scene = new Scene(root);
           Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
           stage.setScene(scene);
           stage.show();
       } catch (IOException e) {
           throw new RuntimeException(e);
       }

   }*/
    @FXML
    public void navigatetoModifierPublicationAction(ActionEvent actionEvent) {
        // Code to modify the selected post in the list
        Post selectedPost = post;

        // Check if an item is selected
        if (selectedPost == null) {
            // No item selected, show a warning alert
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avertissement");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner une publication à modifier.");
            alert.showAndWait();
            return; // Exit the method as there's nothing to modify
        } else {
            // Check if the current user has the right to modify posts
            if (CurrentUser.getRole() == 0 || selectedPost.getUtilisateur().getId_utilisateur() == CurrentUser.getId_utilisateur()) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierPublication.fxml"));
                    Parent root = loader.load();
                    ModifierPublication controller = loader.getController();
                    controller.initData(selectedPost); // Pass the selected post to the modification interface controller

                    // Get the current scene
                    Scene scene = ((Node) actionEvent.getSource()).getScene();

                    // Change the content of the scene
                    scene.setRoot(root);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                // Display a message indicating that the user doesn't have the right to modify this post
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Erreur");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Vous n'avez pas le droit de modifier cette publication.");
                errorAlert.showAndWait();
            }
        }
    }


    public void NaviguerversPub(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Pub.fxml"));
            caption.getScene().setRoot(root);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Sorry jjjj");
            alert.setTitle("Error");
            alert.show();
        }

    }


    public void supprimer(ActionEvent actionEvent) {
        // If an item is selected, show the deletion confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de vouloir supprimer cette conversation ?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Check if the current user has the right to delete posts
            if (CurrentUser.getRole() == 0 || (CurrentUser.getRole() != 0 && post.getUtilisateur().getId_utilisateur() == CurrentUser.getId_utilisateur())) {
                try {
                    int idPost = post.getId_post();
                    ServicePost servicePost = new ServicePost();
                    servicePost.supprimer(idPost);
                    NaviguerversPub(actionEvent);

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                // Display a message indicating that the user can only delete their own posts
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Erreur");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Vous ne pouvez supprimer que vos propres publications.");
                errorAlert.showAndWait();
            }
        }
    }

    /*public void Naviguerversajouter(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AjouterCommentaire.fxml"));
            caption.getScene().setRoot(root);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Sorry");
            alert.setTitle("Error");
            alert.show();
        }

    }*/
    @FXML
    void NavigatetoC(MouseEvent event) {
        try {
            CurrentPost.setId_post(post.getId_post());
            Parent root = FXMLLoader.load(getClass().getResource("/AfficherCommentaire2.fxml"));
            caption.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Sorry");
            alert.setTitle("Error");
            alert.show();
        }
    }




    }


