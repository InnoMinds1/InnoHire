package edu.esprit.services;

import edu.esprit.entities.Commentaire;
import edu.esprit.entities.Publication;
import edu.esprit.entities.Utilisateur;
import edu.esprit.utils.DataSource;


import java.sql.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class ServiceCommentaire implements IService<Commentaire>  {
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Commentaire commentaire) throws SQLException {
        String req = "INSERT INTO `commentaire`( `id_publication`,`id_utilisateur`,`description_co`, `date_co`) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, commentaire.getPublication().getId_publication());
            ps.setInt(2, commentaire.getUtilisateur().getId_utilisateur());
            ps.setString(3, commentaire.getDescription_co());
            ps.setDate(4, Date.valueOf(commentaire.getDate_co())); // Assuming date is a java.util.Date

            ps.executeUpdate();
            System.out.println("Commentaire added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding Commentaire: " + e.getMessage());
        }
    }



    @Override
    public void modifier(Commentaire commentaire) throws SQLException {
        String req = "UPDATE commentaire SET id_publication = ?, id_utilisateur = ?, description_co = ?, date_co = ? WHERE id_commentaire = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
          ps.setInt(1, commentaire.getPublication().getId_publication());
            ps.setInt(2, commentaire.getUtilisateur().getId_utilisateur());
            ps.setString(3, commentaire.getDescription_co());
            ps.setDate(4, Date.valueOf(commentaire.getDate_co()));
            ps.setInt(5, commentaire.getId_commentaire());

            ps.executeUpdate();
            System.out.println("Commentaire modifié!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void supprimer(int id) throws SQLException {

            Commentaire commentaire = getOneByID(id);
            if (commentaire != null) {
                String req = "DELETE FROM `commentaire` WHERE `id_commentaire`=?";
                try {
                    PreparedStatement ps = cnx.prepareStatement(req);
                    ps.setInt(1, id);
                    ps.executeUpdate();
                    System.out.println("Commentaire deleted !");
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Introuvable : Échec de suppression ");
            }
        }



    @Override
    public Set<Commentaire> getAll() throws SQLException {
        Set<Commentaire> commentaires = new HashSet<>();

        String req = "Select * from commentaire";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                int id_commentaire =rs.getInt("id_commentaire");
                String description_co = rs.getString("description_co");
                LocalDate date_co = rs.getDate("date_co").toLocalDate();
                int id_utilisateur=rs.getInt("id_utilisateur");
                int id_publication=rs.getInt("id_publication");
                Utilisateur utilisateur;
                ServiceUtilisateur sc=new ServiceUtilisateur();
                utilisateur=sc.getOneByID(id_utilisateur);
                Publication publication;
                ServicePublication sb=new ServicePublication();
                publication=sb.getOneByID(id_publication);

                Commentaire c = new Commentaire(id_commentaire,publication,utilisateur,description_co,date_co);
                commentaires.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return commentaires;
    }

    @Override
    public Commentaire getOneByID(int id_commentaire) throws SQLException {
        String req = "SELECT * FROM commentaire WHERE id_commentaire = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id_commentaire);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String description_co = rs.getString("description_co");
                LocalDate date_co = rs.getDate("date_co").toLocalDate();
                int id_utilisateur=rs.getInt("id_utilisateur");
                int id_publication=rs.getInt("id_publication");
                Utilisateur utilisateur;
                ServiceUtilisateur sc=new ServiceUtilisateur();
                utilisateur=sc.getOneByID(id_utilisateur);
                Publication publication;
                ServicePublication sb=new ServicePublication();
                publication=sb.getOneByID(id_publication);

                return new Commentaire(id_commentaire,publication,utilisateur,description_co,date_co);
            } else {
                System.out.print("Echec! Etablissement with ID " + id_commentaire + " est" + " " );
                return null;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
