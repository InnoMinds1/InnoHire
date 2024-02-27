package edu.esprit.controllers;

import edu.esprit.entities.*;
import edu.esprit.services.ServicePost;
import edu.esprit.services.ServiceUtilisateur;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PostController implements Initializable{
    @FXML
    private ImageView imgProfile;

    @FXML
    private Label username;

    @FXML
    private ImageView imgVerified;

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
    private Label reactionName;

    private long startTime = 0;
    private Reactions currentReaction;
    private Post post;

    @FXML
    public void onLikeContainerPressed(MouseEvent me){
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
    public void onReactionImgPressed(MouseEvent me){
        switch (((ImageView) me.getSource()).getId()){
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

    public void setReaction(Reactions reaction){
        Image image = new Image(getClass().getResourceAsStream(reaction.getImgSrc()));
        imgReaction.setImage(image);
        reactionName.setText(reaction.getName());
        reactionName.setTextFill(Color.web(reaction.getColor()));

        if(currentReaction == Reactions.NON){
            post.setTotalReactions(post.getTotalReactions() + 1);
        }

        currentReaction = reaction;

        if(currentReaction == Reactions.NON){
            post.setTotalReactions(post.getTotalReactions() - 1);
        }

        nbReactions.setText(String.valueOf(post.getTotalReactions()));
    }

    public void setData(Post post){
        this.post = post;
        Image img;
        img = new Image(getClass().getResourceAsStream(post.getUtilisateur().getProfileImg()));
        imgProfile.setImage(img);
        username.setText(post.getUtilisateur().getName());
        if(post.getUtilisateur().isVerified()){
            imgVerified.setVisible(true);
        }else{
            imgVerified.setVisible(false);
        }

        date.setText(post.getDate());
        if(post.getAudience() == PostAudience.PUBLIC){
            img = new Image(getClass().getResourceAsStream(PostAudience.PUBLIC.getImgSrc()));
        }else{
            img = new Image(getClass().getResourceAsStream(PostAudience.FRIENDS.getImgSrc()));
        }
        audience.setImage(img);

        if(post.getCaption() != null && !post.getCaption().isEmpty()){
            caption.setText(post.getCaption());
        }else{
            caption.setManaged(false);
        }

        if(post.getImage() != null && !post.getImage().isEmpty()){
            img = new Image(getClass().getResourceAsStream(post.getImage()));
            imgPost.setImage(img);
        }else{
            imgPost.setVisible(false);
            imgPost.setManaged(false);
        }

        nbReactions.setText(String.valueOf(post.getTotalReactions()));
        nbComments.setText(post.getNbComments() + " comments");
        nbShares.setText(post.getNbShares()+" shares");

        currentReaction = Reactions.NON;
    }
    public void initData(Post post) {
        this.post = post;
        Image img;
        img = new Image(getClass().getResourceAsStream(post.getUtilisateur().getProfileImg()));
        imgProfile.setImage(img);
        username.setText(post.getUtilisateur().getName());
        if(post.getUtilisateur().isVerified()){
            imgVerified.setVisible(true);
        }else{
            imgVerified.setVisible(false);
        }

        date.setText(post.getDate());
        if(post.getAudience() == PostAudience.PUBLIC){
            img = new Image(getClass().getResourceAsStream(PostAudience.PUBLIC.getImgSrc()));
        }else{
            img = new Image(getClass().getResourceAsStream(PostAudience.FRIENDS.getImgSrc()));
        }
        audience.setImage(img);

        if(post.getCaption() != null && !post.getCaption().isEmpty()){
            caption.setText(post.getCaption());
        }else{
            caption.setManaged(false);
        }

        if(post.getImage() != null && !post.getImage().isEmpty()){
            img = new Image(getClass().getResourceAsStream(post.getImage()));
            imgPost.setImage(img);
        }else{
            imgPost.setVisible(false);
            imgPost.setManaged(false);
        }
        if (post != null) {
            //setId(post.getIdPost());
            //TFcode_pub.setText(post.getCode_pub());

            date.setText(post.getDate()); // Assuming publication.getDate() returns LocalDate
            caption.setText(post.getCaption());
            //v imgPost.set(post.getImage());
            nbReactions.setText(String.valueOf(post.getTotalReactions()));
            nbComments.setText(String.valueOf(post.getNbComments()));
            nbShares.setText(String.valueOf(post.getNbShares()));
        }
    }

    private Post getPost() throws SQLException {
        /*Post post = new Post();
        ServicePost sp=new ServicePost();
        Post post1 = sp.getOneByID(1);
        post.setUtilisateur(post1.getUtilisateur());
        post.setDate(post1.getDate());
        post.setAudience(post1.getAudience());
        post.setCaption(post1.getCaption());
        post.setImage("/img/img2.jpg");
        post.setTotalReactions(post1.getTotalReactions());
        post.setNbComments(post1.getNbComments());
        post.setNbShares(post1.getNbShares());

        return post;*/

        ServicePost sp=new ServicePost();

        ServiceUtilisateur su=new ServiceUtilisateur();


        Post post1 = sp.getOneByID(1);

        Post post = new Post();
        Utilisateur user = new Utilisateur();
        user.setName(post1.getUtilisateur().getName());
        user.setProfileImg("/img/user.png");
        user.setVerified(true);
        post.setUtilisateur(user);
        post.setDate(post1.getDate());
        post.setAudience(PostAudience.PUBLIC);
        post.setCaption(post1.getCaption());
        post.setImage("/img/img2.jpg");
        post.setTotalReactions(post1.getTotalReactions());
        post.setNbComments(post1.getNbComments());
        post.setNbShares(post1.getNbShares());
        return post;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setData(getPost());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
