package edu.esprit.controllers;

import edu.esprit.entities.Post;
import edu.esprit.entities.PostAudience;
import edu.esprit.entities.Utilisateur;
import edu.esprit.services.ServicePost;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

public class PubController implements Initializable {
    @FXML
    private VBox postsContainer;
    private List<Post>  posts = new ArrayList<>() ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        try {
            posts.addAll(getData());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        try {  for (Post post : posts)
              {
      FXMLLoader fxmlLoader= new FXMLLoader();
      fxmlLoader.setLocation(getClass().getResource("/post.fxml"));
      VBox vBox=fxmlLoader.load();
      PostController postController=fxmlLoader.getController();
      postController.setData(post);
      postsContainer.getChildren().add(vBox);
              }


                }
            catch (IOException e)
          { e.printStackTrace();
                  }
    }


    public List<Post> getpost()  {
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




    private List<Post> getData() throws SQLException {
        List<Post> postList = new ArrayList<>();
        ServicePost servicePost = new ServicePost();

        // Récupérer tous les posts
        Set<Post> posts = servicePost.getAll();

        // Ajouter tous les posts à la liste
        postList.addAll(posts);

        return postList;
    }

    /*private List<Post> getData() throws SQLException {
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
            modifiedEtablissement.setNbShares(etablissement.getNbShares());
            modifiedEtablissement.setNbComments(etablissement.getNbComments());
            modifiedEtablissement.setTotalReactions(etablissement.getTotalReactions());





            // If setPrice method is available, you can uncomment the following line
            modifiedEtablissement.setPrice(etablissement.getPrice());
            modifiedEtablissement.setImage(etablissement.getImage());

            modifiedEtablissement.setUtilisateur(etablissement.getUtilisateur());

            modifiedEtablissements.add(modifiedEtablissement);
        }

        return modifiedEtablissements;
    }*/


}
