package edu.esprit.services;


import edu.esprit.entities.Post;
import edu.esprit.entities.PostAudience;
import edu.esprit.utils.DataSource;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

public class ServicePost implements IService<Post> {
    Connection cnx = DataSource.getInstance().getCnx();

    //private Connection connection;



    @Override
    public void ajouter(Post post) throws SQLException {
        LocalDateTime currentDate = LocalDateTime.now();
        String req = "INSERT INTO `post`(`id_utilisateur`, `audience`, `date`, `caption`, `image`, `totalReactions`, `nbComments`, `nbShares`) VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, post.getUtilisateur().getId_utilisateur());
            ps.setString(2, post.getAudience().toString());

            ps.setTimestamp(3, Timestamp.valueOf(currentDate));
            ps.setString(4, post.getCaption());
            ps.setString(5, post.getImage());
            ps.setInt(6, post.getTotalReactions());
            ps.setInt(7, post.getNbComments());
            ps.setInt(8, post.getNbShares());

            ps.executeUpdate();
            System.out.println("Post added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding post: " + e.getMessage());
        }
    }


    @Override
    public void modifier(Post post) throws SQLException {
        int id = post.getId_post();
        String req = "UPDATE post SET id_utilisateur = ?, audience = ?, date = ?, caption = ?, image = ?, totalReactions = ?, nbComments = ?, nbShares = ? WHERE id_post = ?";
        try {
            LocalDateTime currentDate = LocalDateTime.now();
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, post.getUtilisateur().getId_utilisateur());
            ps.setString(2, post.getAudience().toString());
            ps.setTimestamp(3, Timestamp.valueOf(currentDate));

            ps.setString(4, post.getCaption());
            ps.setString(5, post.getImage());
            ps.setInt(6, post.getTotalReactions());
            ps.setInt(7, post.getNbComments());
            ps.setInt(8, post.getNbShares());
            ps.setInt(9, id);

            ps.executeUpdate();
            System.out.println("Post modified!");
        } catch (SQLException e) {
            System.out.println("Error modifying post: " + e.getMessage());
        }
    }

    @Override
    public void supprimer(int id_post) throws SQLException {
        Post post = getOneByID(id_post);
        if (post != null) {
            String req = "DELETE FROM post WHERE id_post = ?";
            try {
                PreparedStatement ps = cnx.prepareStatement(req);
                ps.setInt(1, id_post);
                ps.executeUpdate();
                System.out.println("Post deleted !");
            } catch (SQLException e) {
                System.out.println("Error deleting post: " + e.getMessage());
            }
        } else {
            System.out.println("Post not found: Deletion failed");
        }
    }

    @Override
    public Set<Post> getAll() throws SQLException {
        Set<Post> posts = new HashSet<>();

        String req = "SELECT * FROM post";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()) {
                int id_post = rs.getInt("id_post");
                int id_utilisateur = rs.getInt("id_utilisateur");
                String audienceStr = rs.getString("audience");


                Timestamp timestamp = rs.getTimestamp("date");
                LocalDateTime date = timestamp.toLocalDateTime();


                String caption = rs.getString("caption");
                String image = rs.getString("image");
                int totalReactions = rs.getInt("totalReactions");
                int nbComments = rs.getInt("nbComments");
                int nbShares = rs.getInt("nbShares");

                Utilisateur utilisateur = new Utilisateur();
                utilisateur.setId_utilisateur(id_utilisateur); // Assuming there's a method to set id_utilisateur in Utilisateur

                PostAudience audience = PostAudience.valueOf(audienceStr); // Assuming PostAudience is an enum

                Post post = new Post(id_post, utilisateur, audience, date, caption, image, totalReactions, nbComments, nbShares);
                posts.add(post);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving posts: " + e.getMessage());
        }

        return posts;
    }

    @Override
    public Post getOneByID(int id_post) throws SQLException {
        String req = "SELECT * FROM post WHERE id_post = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id_post);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id_utilisateur = rs.getInt("id_utilisateur");
                String audienceStr = rs.getString("audience");


                Timestamp timestamp = rs.getTimestamp("date");
                LocalDateTime date = timestamp.toLocalDateTime();

                String caption = rs.getString("caption");
                String image = rs.getString("image");
                int totalReactions = rs.getInt("totalReactions");
                int nbComments = rs.getInt("nbComments");
                int nbShares = rs.getInt("nbShares");


                ServiceUtilisateur su = new ServiceUtilisateur();
                Utilisateur user= su.getOneByID(id_utilisateur);
                 // Assuming there's a method to set id_utilisateur in Utilisateur

                PostAudience audience = PostAudience.valueOf(audienceStr); // Assuming PostAudience is an enum

                Post post1 = new Post(id_post,user,audience,date,caption,image,totalReactions,nbComments,nbShares);
                return post1 ;
            } else {
                System.out.println("Post with ID " + id_post + " not found");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving post: " + e.getMessage());
            return null;
        }
    }

   /* public Set<Post> rechercherParNom(String nom) throws SQLException {
        Set<Post> posts = new HashSet<>();

        String req = "SELECT * FROM post WHERE id_utilisateur LIKE ?";

        try (PreparedStatement st = cnx.prepareStatement(req)) {
            st.setString(1, "%" + nom + "%");
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int id_post = rs.getInt("id_post");
                int id_utilisateur = rs.getInt("id_utilisateur");
                String audienceStr = rs.getString("audience");
                Timestamp timestamp = rs.getTimestamp("date");
                LocalDateTime date = timestamp.toLocalDateTime();
                String caption = rs.getString("caption");
                String image = rs.getString("image");
                int totalReactions = rs.getInt("totalReactions");
                int nbComments = rs.getInt("nbComments");
                int nbShares = rs.getInt("nbShares");

                Utilisateur utilisateur = new Utilisateur();
                utilisateur.setnom(nom);

                PostAudience audience = PostAudience.valueOf(audienceStr);

                Post post = new Post(id_post, utilisateur, audience, date, caption, image, totalReactions, nbComments, nbShares);
                posts.add(post);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving posts by user name: " + e.getMessage());
        }

        return posts;
    }*/
  /* public Set<Post> rechercherParNom(String nom) throws SQLException {
       Set<Post> posts = new HashSet<>();

       String req = "SELECT * FROM post WHERE id_utilisateur IN (SELECT id_utilisateur FROM utilisateur WHERE nom LIKE ?)";

       try (PreparedStatement st = cnx.prepareStatement(req)) {
           st.setString(1, "%" + nom + "%");
           ResultSet rs = st.executeQuery();

           while (rs.next()) {
               int id_post = rs.getInt("id_post");
               int id_utilisateur = rs.getInt("id_utilisateur");
               String audienceStr = rs.getString("audience");
               Timestamp timestamp = rs.getTimestamp("date");
               LocalDateTime date = timestamp.toLocalDateTime();
               String caption = rs.getString("caption");
               String image = rs.getString("image");
               int totalReactions = rs.getInt("totalReactions");
               int nbComments = rs.getInt("nbComments");
               int nbShares = rs.getInt("nbShares");

               // Créer un objet Utilisateur avec l'ID récupéré
               Utilisateur utilisateur = new Utilisateur();
               utilisateur.setId_utilisateur(id_utilisateur);

               // Convertir la chaîne d'audience en PostAudience enum
               PostAudience audience = PostAudience.valueOf(audienceStr);

               // Créer un objet Post avec les données récupérées
               Post post = new Post(id_post, utilisateur, audience, date, caption, image, totalReactions, nbComments, nbShares);
               posts.add(post);
           }
       } catch (SQLException e) {
           System.out.println("Error retrieving posts by user name: " + e.getMessage());
       }

       return posts;
   }*/
   public Set<Post> rechercherParNom(String nom) throws SQLException {
       Set<Post> posts = new HashSet<>();

       String req = "SELECT * FROM post INNER JOIN utilisateur ON post.id_utilisateur = utilisateur.id_utilisateur WHERE utilisateur.nom LIKE ?";

       try (PreparedStatement st = cnx.prepareStatement(req)) {
           st.setString(1, "%" + nom + "%");
           ResultSet rs = st.executeQuery();

           while (rs.next()) {
               int id_post = rs.getInt("id_post");
               int id_utilisateur = rs.getInt("id_utilisateur");
               String audienceStr = rs.getString("audience");
               Timestamp timestamp = rs.getTimestamp("date");
               LocalDateTime date = timestamp.toLocalDateTime();
               String caption = rs.getString("caption");
               String image = rs.getString("image");
               int totalReactions = rs.getInt("totalReactions");
               int nbComments = rs.getInt("nbComments");
               int nbShares = rs.getInt("nbShares");

               // Maintenant, récupérez les informations de l'utilisateur à partir de la base de données en utilisant son ID
               ServiceUtilisateur serviceUtilisateur = new ServiceUtilisateur();
               Utilisateur utilisateur = serviceUtilisateur.getOneByID(id_utilisateur);

               PostAudience audience = PostAudience.valueOf(audienceStr);

               Post post = new Post(id_post, utilisateur, audience, date, caption, image, totalReactions, nbComments, nbShares);
               posts.add(post);
           }
       } catch (SQLException e) {
           System.out.println("Error retrieving posts by user name: " + e.getMessage());
       }

       return posts;
   }







    public Set<Post> trierPostsParDateDecroissante() throws SQLException {
        Set<Post> postsTries = new TreeSet<>(Comparator.comparing(Post::getDate).reversed());
        postsTries.addAll(getAll());
        return postsTries;
    }




}